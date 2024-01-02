package com.fastcampus.ch4.controller;

import com.fastcampus.ch4.domain.BoardDto;
import com.fastcampus.ch4.domain.PageHandler;
import com.fastcampus.ch4.domain.SearchCondition;
import com.fastcampus.ch4.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/write")
    public String write(Model m) {
        m.addAttribute("mode", "new");
        return "board";
    }

    @PostMapping("/write")
    public String write(BoardDto boardDto, HttpSession session, Model m, RedirectAttributes rattr) {
        String writer = (String)session.getAttribute("id");
        boardDto.setWriter(writer);
        try {
            int rowCnt = this.boardService.write(boardDto);
            if (rowCnt != 1) {
                throw new Exception("Write failed");
            }
            rattr.addFlashAttribute("msg", "WRT_OK");
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg", "WRT_ERR");
            m.addAttribute(boardDto);
            m.addAttribute("mode", "new");
            return "board";
        }
        return "redirect:/board/list";
    }

    @GetMapping("/list")
    public String list(SearchCondition sc, Model m, HttpServletRequest request) {
        if (!loginCheck(request)) return "redirect:/login/login?toURL="+request.getRequestURL();
        try {
            int totalCnt = this.boardService.getSearchResultCount(sc);
            PageHandler ph = new PageHandler(totalCnt, sc);
            List<BoardDto> list = this.boardService.getSearchSelectPage(sc);

            m.addAttribute("ph", ph);
            m.addAttribute("list", list);
            m.addAttribute("totalCnt", totalCnt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "boardList";
    }

    @GetMapping("/read")
    public String read(Integer bno, Integer page, Integer pageSize, Model m) {
        try {
            BoardDto boardDto = this.boardService.read(bno);
            m.addAttribute("boardDto", boardDto);
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "board";
    }

    @PostMapping("/modify")
    public String modify(Integer page, Integer pageSize, Model m, BoardDto boardDto, HttpSession session, RedirectAttributes rattr) {
        String writer = (String)session.getAttribute("id");
        boardDto.setWriter(writer);
        try {
            int rowCnt = this.boardService.modify(boardDto);
            if (rowCnt != 1) {
                throw new Exception("Write failed");
            }
            rattr.addFlashAttribute("msg", "MOD_OK");
            rattr.addAttribute("page", page);
            rattr.addAttribute("pageSize", pageSize);
            return "redirect:/board/list";
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg", "MOD_ERR");
            m.addAttribute(boardDto);
            m.addAttribute("mode", "new");
            return "board";
        }
    }

    @PostMapping("/remove")
    public String remove(Integer bno, Integer page, Integer pageSize, Model m, HttpSession session, RedirectAttributes rattr) {
        String writer = (String) session.getAttribute("id");
        try {

            int rowCnt = this.boardService.remove(bno, writer);
            if (rowCnt != 1) {
                System.out.println("데이터 삭제 실패");
                throw new Exception("remove board error");
            }
            System.out.println("데이터 삭제 성공");
            rattr.addFlashAttribute("msg", "DEL_OK");
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "DEL_ERR");
        }
        rattr.addAttribute("page", page);
        rattr.addAttribute("pageSize", pageSize);
        return "redirect:/board/list";
    }

    private boolean loginCheck(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null && session.getAttribute("id") != null;
    }
}

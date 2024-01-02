package com.fastcampus.ch4.controller;

import com.fastcampus.ch4.domain.CommentDto;
import com.fastcampus.ch4.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    CommentService commentServiceImpl;

    @PostMapping("/comments") // /comments?bno=
    @ResponseBody
    public ResponseEntity<String> write(@RequestBody CommentDto commentDto, Integer bno) {
        String commenter = "asdf";
        commentDto.setCommenter(commenter);
        commentDto.setBno(bno);
        System.out.println("commentDto = " + commentDto);

        try {
            int rowCnt = this.commentServiceImpl.write(commentDto);

            if (rowCnt != 1)
                new Exception("Write failed.");

            return new ResponseEntity<String>("WRT_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("WRT_ERR", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/comments") // /comments?bno=
    @ResponseBody
    public ResponseEntity<List<CommentDto>> commList(Integer bno) {
        List<CommentDto> list = null;
        try {
            list = this.commentServiceImpl.getList(bno);
            return new ResponseEntity<List<CommentDto>>(list, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<CommentDto>>(HttpStatus.BAD_REQUEST);
        }
    }

//    {
//        "pcno": 0,
//            "comment": "modified comment",
//            "commenter":"asdf"
//    }
    @PatchMapping("/comments/{cno}")
    @ResponseBody
    public ResponseEntity<String> modify(@PathVariable Integer cno, @RequestBody CommentDto commentDto) {
        commentDto.setCno(cno);
        System.out.println("commentDto = " + commentDto);

        try {
            int rowCnt = this.commentServiceImpl.modify(commentDto);

            if (rowCnt != 1)
                new Exception("Modify failed.");

            return new ResponseEntity<String>("MOD_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("MOD_ERR", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/comments/{cno}")   // /comments/1?bno=[bno]
    public ResponseEntity<String> remove(@PathVariable Integer cno, Integer bno, HttpSession session) {
//        String commenter = (String) session.getAttribute("id");
        String commenter = "asdf";
        try {
            int rowCnt = this.commentServiceImpl.remove(cno, bno, commenter);
            if (rowCnt != 1)
                throw new Exception("Remove Failed.");
            return new ResponseEntity<String>("DEL_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("DEL_ERR", HttpStatus.BAD_REQUEST);
        }
    }
}

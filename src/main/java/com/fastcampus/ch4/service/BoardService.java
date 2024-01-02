package com.fastcampus.ch4.service;

import com.fastcampus.ch4.dao.BoardDao;
import com.fastcampus.ch4.domain.BoardDto;
import com.fastcampus.ch4.domain.SearchCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardService {

    @Autowired
    private BoardDao boardDao;

    public int getCount() throws Exception {
        return boardDao.count();
    }
    public BoardDto read(Integer bno) throws Exception {
        BoardDto boardDto = this.boardDao.select(bno);
        this.boardDao.updateViewCount(bno);
        return boardDto;
    }
    public List<BoardDto> list() throws Exception {
        return this.boardDao.selectAll();
    }
    public List<BoardDto> listPage(int pageSize, int offset) throws Exception {
        Map map = new HashMap();
        map.put("pageSize", pageSize);
        map.put("offset", offset);
        return this.boardDao.selectPage(map);
    }
    public int write(BoardDto boardDto) throws Exception {
        return this.boardDao.insert(boardDto);
    }
    public int modify(BoardDto boardDto) throws Exception {
        return this.boardDao.update(boardDto);
    }
    public int incrementViewCount(Integer bno) throws Exception {
        return this.boardDao.updateViewCount(bno);
    }
    public int remove(Integer bno, String writer) throws Exception {
        Map map = new HashMap();
        map.put("bno", bno);
        map.put("writer", writer);
        return this.boardDao.delete(map);
    }
    public int removeAll() throws Exception {
        return this.boardDao.deleteAll();
    }

    public List<BoardDto> getSearchSelectPage(SearchCondition sc) throws Exception {
        return this.boardDao.searchSelectPage(sc);
    }

    public int getSearchResultCount(SearchCondition sc) throws Exception {
        return boardDao.searchResultCount(sc);
    }
}

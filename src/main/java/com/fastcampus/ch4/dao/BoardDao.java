package com.fastcampus.ch4.dao;

import com.fastcampus.ch4.domain.BoardDto;
import com.fastcampus.ch4.domain.SearchCondition;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BoardDao {
    @Autowired
    SqlSession session;
    String namespace="com.fastcampus.ch4.dao.BoardMapper.";
    public int count() throws Exception {
        return session.selectOne(namespace + "count");
    }
    public BoardDto select(Integer bno) throws Exception {
        return session.selectOne(namespace+"select", bno);
    }
    public List<BoardDto> selectAll() throws Exception {
        return session.selectList(namespace + "selectAll");
    }
    public List<BoardDto> selectPage(Map map) throws Exception {
        return session.selectList(namespace + "selectPage", map);
    }
    public int insert(BoardDto boardDto) throws Exception {
        return session.insert(namespace + "insert", boardDto);
    }
    public int update(BoardDto boardDto) throws Exception {
        return session.update(namespace + "update", boardDto);
    }
    public int updateViewCount(Integer bno) throws Exception {
        return session.update(namespace + "updateViewCount", bno);
    }
    public int delete(Map map) throws Exception {
        return session.delete(namespace+"delete", map);
    }
    public int deleteAll() throws Exception {
        return session.delete(namespace + "deleteAll");
    }

    public List<BoardDto> searchSelectPage(SearchCondition sc) throws Exception {
        return session.selectList(namespace + "searchSelectPage", sc);
    }

    public int searchResultCount(SearchCondition sc) throws Exception {
        return session.selectOne(namespace + "searchResultCount", sc);
    }

    public int updateCommentCnt(Integer bno, int cnt) throws Exception {
        Map map = new HashMap();
        map.put("bno", bno);
        map.put("cnt", cnt);
        return session.update(namespace+"updateCommentCnt", map);
    }
}

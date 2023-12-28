package com.fastcampus.ch4.dao;

import com.fastcampus.ch4.domain.BoardDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDao {
    @Autowired
    SqlSession session;
    String namespace="com.fastcampus.ch4.dao.BoardMapper.";
    public int count() {
        return session.selectOne(namespace + "count");
    }
    public BoardDto select(Integer bno) {
        return session.selectOne(namespace+"select", bno);
    }
    public List<BoardDto> selectAll() {
        return session.selectList(namespace + "selectAll");
    }
    public int insert(BoardDto boardDto) {
        return session.insert(namespace + "insert", boardDto);
    }
    public int update(BoardDto boardDto) {
        return session.update(namespace + "update", boardDto);
    }
    public int delete(Integer bno) {
        return session.delete(namespace+"delete", bno);
    }
    public int deleteAll() {
        return session.delete(namespace + "deleteAll");
    }
}

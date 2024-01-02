package com.fastcampus.ch4.dao;

import com.fastcampus.ch4.domain.BoardDto;
import com.fastcampus.ch4.domain.SearchCondition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static java.time.LocalDateTime.now;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class BoardDaoTest {
    @Autowired BoardDao boardDao;

    @Test
    public void searchSeletPageTest() {

        try {
            this.boardDao.deleteAll();
            for (int i = 1; i <= 20 ; i++) {
                BoardDto boardDto = new BoardDto("test" + i, "no content", "asdf");
                this.boardDao.insert(boardDto);
            }
            SearchCondition sc = new SearchCondition(1, 10, "T", "test2");
            List<BoardDto> list = this.boardDao.searchSelectPage(sc);
            System.out.println("list = " + list);
            assertTrue(list.size()==2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void searchResultCountTest() {

        try {
            this.boardDao.deleteAll();
            for (int i = 1; i <= 20 ; i++) {
                BoardDto boardDto = new BoardDto("test" + i, "no content", "asdf");
                this.boardDao.insert(boardDto);
            }
            SearchCondition sc = new SearchCondition(1, 10, "T", "test2");
            int count = this.boardDao.searchResultCount(sc);
            System.out.println("count = " + count);
            assertTrue(count==2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void insertTestDate() throws Exception {
        boardDao.deleteAll();
        for (int i = 1; i <= 200 ; i++) {
            BoardDto boardDto = new BoardDto("test title" + i, "no content", "asdf");
            this.boardDao.insert(boardDto);
        }
    }
    @Test
    public void select() throws Exception {
        assertTrue(this.boardDao!=null);
        System.out.println("boardDao = " + boardDao);
        BoardDto boardDto = this.boardDao.select(1);
        System.out.println("boardDto = " + boardDto);
        assertTrue(boardDto.getBno().equals(1));
    }

    @Test
    public void selectAllTest() throws Exception {
        this.boardDao.deleteAll();
        List<BoardDto> list = this.boardDao.selectAll();
        assertTrue(list.size() == 0);

        BoardDto boardDto = new BoardDto("new title", "new content", "newtestman");
        this.boardDao.insert(boardDto);
        list = this.boardDao.selectAll();
        assertTrue(list.size() == 1);

        BoardDto boardDto2 = new BoardDto("new title2", "new content2", "newtestman");
        this.boardDao.insert(boardDto2);
        list = this.boardDao.selectAll();
        System.out.println("list = " + list);
        assertTrue(list.size() == 2);

        for (BoardDto dto : list) {
            System.out.println("dto = " + dto);
        }
        BoardDto pick = this.boardDao.select(1);
        System.out.println("pick = " + pick);
    }

    @Test
    public void updateTest() throws Exception {
        this.boardDao.deleteAll();
        BoardDto boardDto = new BoardDto("created title from test", "created content from test", "testman");
        int rowCnt = this.boardDao.insert(boardDto);
        assertTrue(rowCnt == 1);

        int bno = this.boardDao.selectAll().get(0).getBno();
        BoardDto pickBoard = this.boardDao.select(bno);
        System.out.println("board info = " + pickBoard);
        pickBoard.setTitle("modified title");
        assertTrue(this.boardDao.update(pickBoard)==1);
        BoardDto pickBaord2 = this.boardDao.select(bno);
        System.out.println("pickBaord2 = " + pickBaord2);
        assertTrue(pickBoard.equals(pickBaord2));
    }

    @Test
    public void udpateViewCntTest() throws Exception {
        BoardDto board = boardDao.select(2);
        System.out.println("board = " + board);
        assertTrue(board.getView_cnt() == 4);

        boardDao.updateViewCount(2);

        BoardDto board2 = this.boardDao.select(2);
        System.out.println("board2 = " + board2);
        assertTrue(board2.getView_cnt() == 5);
        assertTrue(board.equals(board2));
    }
}
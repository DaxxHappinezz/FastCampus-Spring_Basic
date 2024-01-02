package com.fastcampus.ch4.dao;

import com.fastcampus.ch4.domain.CommentDto;
import com.fastcampus.ch4.service.CommentService;
import com.fastcampus.ch4.service.CommentServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class CommentDaoImplTest {

    @Autowired
    CommentService commentServiceImpl;

    @Test
    public void getListCommentTest() {
        int bno = 1;
        try {
            List< CommentDto> list = this.commentServiceImpl.getList(bno);
            System.out.println("list = " + list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
//package com.fastcampus.ch4.domain;
//
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class PageHandlerTest {
//
//    @Test
//    public void pageTest() {
//        PageHandler ph = new PageHandler(155, 10);
//        System.out.println("ph = " + ph);
//        ph.print();
//        assertTrue(ph.getBeginPage() == 1);
//        assertTrue(ph.getEndPage() == 10);
//    }
//    @Test
//    public void pageTest2() {
//        PageHandler ph = new PageHandler(155, 15);
//        System.out.println("ph = " + ph);
//        ph.print();
//        assertTrue(ph.getBeginPage() == 11);
//        assertTrue(ph.getEndPage() == 16);
//    }
//    @Test
//    public void pageTest3() {
//        PageHandler ph = new PageHandler(201, 21);
//        System.out.println("ph = " + ph);
//        ph.print();
//        assertTrue(ph.getBeginPage() == 21);
//        assertTrue(ph.getEndPage() == 21);
//    }
//}
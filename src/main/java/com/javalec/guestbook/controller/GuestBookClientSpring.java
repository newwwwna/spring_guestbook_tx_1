package com.javalec.guestbook.controller;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


import com.javalec.guestbook.vo.GuestBookVo;

public class GuestBookClientSpring {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//1. 스프링 컨테이너를 구동함
		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml") ;
		
		//2. 컨테이너로 부터 BoardServiceImpl 객체를 룩업함..
		GuestbookControllerSpring guestbookController = (GuestbookControllerSpring) container.getBean("guestbookspring") ;
		
		
		// 인터페이스와 클래스 사용 시 차이 있음..
		
		
		//3. 글 쓰기
		
		GuestBookVo vo = new GuestBookVo() ;
		vo.setNo(new Long(72));
		vo.setName("hong");
		vo.setPassword("1234");
		vo.setContent("aaaaaaaa");

		guestbookController.add(vo);
		
		//4. 글 목록 검색
		/*
		List<GuestBookVo> guestList = guestbookController.getList() ;
		for(GuestBookVo guestbook : guestList) {
			System.out.println( guestbook.toString());
		}
		*/
		//5. Spring 컨테이너 종료
		container.close();
		
	}

}

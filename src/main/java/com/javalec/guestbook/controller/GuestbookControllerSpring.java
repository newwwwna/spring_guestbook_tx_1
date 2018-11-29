package com.javalec.guestbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.javalec.guestbook.dao.GuestbookDao;
import com.javalec.guestbook.dao.GuestbookSpringDao;
import com.javalec.guestbook.vo.GuestBookVo;


@Controller("guestbookspring")
public class GuestbookControllerSpring {
	
	@Autowired
	@Qualifier("SpringDao")
	private GuestbookSpringDao guestbookDao;
	//@Qualifier("dao")
	//private GuestbookDao guestbookDao;

	public List<GuestBookVo> getList(){
			
		List<GuestBookVo> list = guestbookDao.getList();
		return list;
	}
	
	
	public void delete(GuestBookVo vo ){
		guestbookDao.delete( vo );
	}
	
	public void add(GuestBookVo vo ) {
		guestbookDao.insert(vo);
		// JDBC 트랜잭션 처리 시...
		guestbookDao.insert(vo);
		
	}
}

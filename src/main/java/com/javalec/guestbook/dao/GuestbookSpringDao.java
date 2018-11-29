package com.javalec.guestbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import com.javalec.guestbook.vo.GuestBookVo;



@Repository("SpringDao")
public class GuestbookSpringDao {

	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "beast", "1111");
		} catch (ClassNotFoundException e) {
			System.out.println("���� �߻�!! :" + e);
		}
		return conn;
	}
/*	
	private String guestBookInsert ="insert into guestbook values "
			+ "(guestbook_seq.nextval, ?, ?, ?, sysdate)";
*/	
	private String guestBookInsert ="insert into guestbook values "
			+ "(?, ?, ?, ?, sysdate)";
	
	private String guestBookDelete="delete from guestbook where no = ? and password = ?";
	private String guestBookList = "select no, name, content, password, "
			+ "to_char(reg_date, 'yyyy-mm-dd hh:mi:ss' ) regdate from guestbook order by reg_date desc"; 
		
	
	
	// XML 설정에 의해 JdbcTemplate을 주입해 준다..
	@Autowired
	private JdbcTemplate jdbcTemplate ;
	
	
	
	public void delete(GuestBookVo vo) {
		System.out.println("GuestBook 삭제 수행");
		jdbcTemplate.update(guestBookDelete, vo.getNo(), vo.getPassword());
	}
	

	
	public void insert(GuestBookVo vo) {
		System.out.println("GuestBook 입력 수행");
			jdbcTemplate.update(guestBookInsert, vo.getNo(), vo.getName(), vo.getContent(), vo.getPassword());
			
	}	
	
	
	//글 목록 조회
		public List<GuestBookVo> getList(){
			System.out.println("GuestBook 상세조회 수행");
			
			return jdbcTemplate.query(guestBookList, new GuestBookMapper()) ;
		}
	
}

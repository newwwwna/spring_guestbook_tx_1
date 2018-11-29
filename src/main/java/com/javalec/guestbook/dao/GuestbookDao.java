package com.javalec.guestbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javalec.guestbook.vo.GuestBookVo;

@Repository("dao")
public class GuestbookDao {

	

	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "beast", "1111");
			
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("ï¿½ï¿½ï¿½ï¿½ ï¿½ß»ï¿½!! :" + e);
		}
		return conn;
	}
	
	public void delete(GuestBookVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql =
				" delete" +
				"   from guestbook" +
				"  where no = ?" +
				"    and password = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong( 1, vo.getNo() );
			pstmt.setString( 2, vo.getPassword() );
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if( pstmt != null ) {
					pstmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
	}
	
	public void insert(GuestBookVo vo ) {
		System.out.println("INSERT DOING!!!");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			
			String sql =
				" insert" +
				"   into guestbook" +
				" values (?, ?, ?, ?, sysdate)";
			pstmt = conn.prepareStatement(sql);
			// 테스팅을 위해 주가!!
			pstmt.setLong( 1, vo.getNo());
			pstmt.setString( 2, vo.getName() );
			pstmt.setString( 3, vo.getContent() );
			pstmt.setString( 4, vo.getPassword() );
			
			int i = 0 ; 
			while(i <2) {
				i++;
				pstmt.executeUpdate();
				System.out.println("INSERT DOING !") ;
			}
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			}catch(Exception ex) {
				ex.printStackTrace();
			}
			System.out.println("error:" + e);
		} finally {
			try {
				conn.setAutoCommit(true);
			}catch(Exception e) {
				e.printStackTrace();
			}
			try {
				if( pstmt != null ) {
					pstmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
	}
	
	public List<GuestBookVo> getList() {
		List<GuestBookVo> list = new ArrayList<GuestBookVo>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			stmt = conn.createStatement();
			
			String sql =
				"   select no, name, content, password, to_char(reg_date, 'yyyy-mm-dd hh:mi:ss' )" +
				"     from guestbook" +
				" order by reg_date desc";
			rs = stmt.executeQuery(sql);
			
			while( rs.next() ) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String content = rs.getString(3);
				String password = rs.getString(4);
				String regDate = rs.getString(5);
				
				GuestBookVo vo = new GuestBookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setContent(content);
				vo.setPassword(password);
				vo.setRegDate(regDate);
				
				list.add( vo );
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if( rs != null ) {
					rs.close();
				}
				if( stmt != null ) {
					stmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}

		return list;
	}
}

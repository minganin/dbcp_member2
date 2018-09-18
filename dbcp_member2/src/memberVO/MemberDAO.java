package memberVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import memberVO.MemberVO;

public class MemberDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	
	private Connection getConnection() {
		//Class.forName~~
		
			try {
				Context ctx = new InitialContext();
				ds=(DataSource)ctx.lookup("java:comp/env/jdbc/MySQL");
				con = ds.getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return con;
		}
	private void close(Connection con,PreparedStatement pstmt,
			ResultSet rs) {
	try {
		if(pstmt!=null)
			pstmt.close();
		if(con!=null)
			con.close();
		if(rs!=null)
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void close(Connection con,PreparedStatement pstmt) {
		try {
			if(pstmt!=null)
				pstmt.close();
			if(con!=null)
				con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	
	public int member_insert(MemberVO vo) {
		int result = 0;
		
		try {
		con = getConnection();
		//트랜잭션
		con.setAutoCommit(false);
		String sql = "insert into member values(?,?,?,?,?)";
		pstmt= con.prepareStatement(sql);
		pstmt.setString(1, vo.getUserId());
		pstmt.setString(2, vo.getPassword());
		pstmt.setString(3, vo.getName());
		pstmt.setString(4, vo.getGender());
		pstmt.setString(5, vo.getEmail());
		result=pstmt.executeUpdate();
		con.commit();
	} catch(SQLException e) {
		try {
			con.rollback();
		} catch(SQLException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();
		}finally {
			close(con,pstmt);
		}
		return result;
	
	}
	
	public MemberVO member_islogin(String id, String password) {
		
		MemberVO vo = null;
		
		try {
		con = getConnection();
		//트랜잭션
		con.setAutoCommit(false);
		String sql = "select * from member where Userid = ? and password = ?";
		pstmt= con.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, password);
		rs = pstmt.executeQuery();//입력한 값이 db와 같은지.
		if(rs.next()){
			vo=new MemberVO();
			vo.setUserId(rs.getString("userid"));
			vo.setName(rs.getString("name"));
		}
		con.commit();                              
	} catch(SQLException e) {
		try {
			con.rollback();
		} catch(SQLException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();
		}finally {
			close(con,pstmt,rs);
		}
		return vo;
	
	}

	public boolean checkId(String userid) {
		boolean flag = false;
		
		try {
			con = getConnection();
			con.setAutoCommit(false);
			String sql = "select * from member where userid = ?";
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				flag = true;
			}
			con.commit();
		} catch(SQLException e) {
			try {
				con.rollback();
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			}finally {
				close(con,pstmt,rs);
			}
		return flag;
	}
	
	
}

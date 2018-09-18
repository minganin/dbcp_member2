<%@page import="memberVO.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");

	String userid=request.getParameter("userid");	
	
	MemberDAO dao=new MemberDAO();	
	boolean flag=dao.checkId(userid);
	
	if(flag){
		out.print("false");
	}
	else{
		out.print("true");
	}
%>   


</body>
</html>
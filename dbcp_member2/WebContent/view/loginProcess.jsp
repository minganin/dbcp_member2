<%@page import="memberVO.MemberVO"%>
<%@page import="memberVO.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//로그인처리
	//loginForm.jsp에서 사용자가 입력한 id와 current_password를 가져온 후
	//DB를 거쳐 확인 후 존재하는 사용자라면 세션에 담기
	
    //book_insertForm.jsp에서 넘긴 값 처리하기
	request.setCharacterEncoding("utf-8");
	
	//book_insertForm.jsp 값 가져오기
	String id=request.getParameter("userid");
	String password=request.getParameter("current_password");
	
	//DAO 연동
	MemberDAO dao = new MemberDAO();
	
	//Data 삽입
	MemberVO vo = dao.member_islogin(id, password);
	
	if(vo!=null){//존재하는 사용자이기 때문에 세션에 정보담기
		session.setAttribute("vo", vo);
		out.print("<script>");
		out.print("alert('로그인 성공');");
		out.print("location.href='loginForm.jsp';");
		out.print("</script>");
	}else{
		out.print("<script>");
		out.print("alert('로그인 성공');");
		out.print("location.href='loginForm.jsp';");
		out.print("</script>");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
</body>
</html>
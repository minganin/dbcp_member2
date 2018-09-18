<%@page import="memberVO.MemberVO"%>
<%@page import="memberVO.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//book_insertForm.jsp에서 넘긴 값 처리하기
	request.setCharacterEncoding("utf-8");
	
	//book_insertForm.jsp 값 가져오기
	String userId=request.getParameter("userid");
	String password=request.getParameter("password");
	String name=request.getParameter("name");
	String gender = request.getParameter("gender");
	String email = request.getParameter("email");

	//DAO 연동
	MemberDAO dao = new MemberDAO();
	
	//Data 삽입
	MemberVO vo = new MemberVO(); 
	vo.setUserId(userId);
	vo.setPassword(password);
	vo.setName(name);
	vo.setGender(gender);
	vo.setEmail(email);
	
	int result=dao.member_insert(vo);

	if(result>0){
		out.print("<script>");
		out.print("alert('회원 가입 성공');");
		out.print("location.href='loginForm.jsp';");
		out.print("</script>");
	}else{
		out.print("<script>");
		out.print("alert('회원 가입 실패');");
		out.print("location.href='joinForm.jsp';");
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
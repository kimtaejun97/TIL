<%@ page import="com.servlet.domain.member.Member" %>
<%@ page import="com.servlet.domain.member.MemberRepository" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    MemberRepository memberRepository = MemberRepository.getInstance();
    ArrayList<Member> result = memberRepository.findAll();
%>
<html>
<head>
    <title>회원 목록</title>
</head>
<body>
    <table>
        <thead>
            <th>id</th><th>username</th><th>age</th>
        </thead>
        <tbody>
            <%
                for(Member member : result){
                    out.write("     <tr>\n");
                    out.write("         <td>"+member.getId() + "</td>");
                    out.write("         <td>"+member.getUsername() + "</td>");
                    out.write("         <td>"+member.getAge() + "</td>\n");
                    out.write("     </tr>\n");
                }
            %>
        </tbody>
    </table>
</body>
</html>

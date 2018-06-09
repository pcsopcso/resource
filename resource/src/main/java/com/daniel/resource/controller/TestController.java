package com.daniel.resource.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController
{
	@ResponseBody
	@RequestMapping(path="/session", produces="text/plain")
	public String sessionTest(HttpSession session)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Session 생성");
        
    
        //session을 통해 카운트 체크 (웹서버에 저장되는 것) , 어떤 곳에서도 접근 가능 (global)
        //쿠키는 클라이언트에 저장
		String visitedStr = (String)session.getAttribute("visited");
		
        if(visitedStr == null) {
            sb.append(" 첫 방문입니다");
                        
            /*
             * session안 Object를 생성 (클래스나 dto 주고받기 가능 : 브라우저 종료 할 때까지 정보를 갖고 있음)
             * DTO dto = new DTO(12, "이름");
             * session.setAttribute("visited", dto); */
            session.setAttribute("visited", "1");
           
        }else {
            visitedStr = (String)session.getAttribute("visited");
            int visited = Integer.parseInt(visitedStr);
            visited++;
            
            sb.append(" 방문횟수는 ");
            sb.append(visited);
            sb.append(" 번째 입니다. ");
            
            //session에 다시 넣기
            session.setAttribute("visited", Integer.toString(visited));
        }

		return (String)sb.toString();
	}
}
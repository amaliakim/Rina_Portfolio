package controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import model.Member;
import service.IMemberService;

@Controller
public class MemberController {
	@Autowired
	private IMemberService memberService;

	@RequestMapping("login.do")
	public String login(HttpSession session, String id, String pw) {
		if (memberService.checkId(id) == true) {
			if(memberService.checkPw(id, pw) == true){
				session.setAttribute("id", id);
			}
			return "redirect:main.do";				
		} else {
			return "redirect:main.do";
		}
	}

	@RequestMapping("logout.do")
	public String logout(HttpSession session) {
		session.removeAttribute("id");

		return "redirect:main.do";
	}

	@RequestMapping("joinForm.do")
	public ModelAndView joinForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("joinForm.tiles");
		return mav;
	}

	@RequestMapping("idCheck.do")
	public @ResponseBody HashMap<String, Object> idCheck(HttpServletResponse resp, String id) {
		HashMap<String, Object> response = new HashMap<>();
		response.put("result", memberService.checkId(id));

		return response;
	}

	@RequestMapping("joinOk.do")
	public String joinOk(String id, String pw, String name) {
		Member member = new Member();
		member.setId(id);
		member.setPw(pw);
		member.setName(name);

		memberService.join(member);

		return "redirect:main.do";
	}
}

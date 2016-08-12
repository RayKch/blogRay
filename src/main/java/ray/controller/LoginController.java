package ray.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ray.data.LoginVo;
import ray.data.param.LoginParamVo;
import ray.service.CategoryService;
import ray.service.LoginService;
import ray.util.Const;
import ray.util.StringUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * Created by BELL on 2016-08-11.
 */

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private LoginService loginService;

	@RequestMapping("/proc")
	public String proc(LoginParamVo vo, Model model, HttpServletRequest request, HttpServletResponse response) {
		try {
			vo.setPassword(StringUtil.encryptSha2(vo.getPassword()));
		} catch(NoSuchAlgorithmException e) {
			model.addAttribute("message", "비밀번호 암호화에 실패하였습니다.");
			return Const.ALERT_PAGE;
		}

		LoginVo rvo = loginService.getVo(vo);
		if(rvo == null) {
			model.addAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다");
			return Const.ALERT_PAGE;
		}

		/* 로그인 처리 */
		doLogin(request, response, rvo);
		return Const.AJAX_PAGE;
	}

	/**
	 * 로그인 시키는 메서드
	 * 이 메서드는 사용자 검증을 하지 않는다
	 * 올바른 사용자인지 아닌지 판별한 후에 vo를 넘기도록 구현해야 한다
	 */
	public void doLogin(HttpServletRequest request, HttpServletResponse response, LoginVo vo) {
		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(60 * 60 * 2);
		session.setAttribute("loginSeq", vo.getLoginSeq());
		session.setAttribute("loginId", vo.getId());
		session.setAttribute("nickname", vo.getNickname());

		/* 로그인 상태 정보를 변경한다 */
		vo.setLastIp(request.getRemoteAddr());
		vo.setLoginToken(UUID.randomUUID().toString());
		LoginParamVo paramVo = new LoginParamVo();
		loginService.updateVo(paramVo); //DB 입력

		/* 쿠키에 로그인 토큰과 로그인 타입을 담는다 */
		response.addCookie(this.createCookie("loginToken", vo.getLoginToken()));
	}

	/** 쿠키 생성 */
	public Cookie createCookie(String name, String value) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(60 * 60 * 24); //하루동안만 유지시킨다.
		cookie.setPath("/");
		return cookie;
	}
}

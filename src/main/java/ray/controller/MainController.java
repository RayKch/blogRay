package ray.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;

/**
 * Created by ChanPong on 2016-05-17.
 */
@Slf4j
@Controller
public class MainController {
	@RequestMapping({"/index", "/"})
	public String index() {
//		new MemberUpdateValidator().validate(vo, result);
//		if (result.hasErrors()) {
//			response.setStatus( HttpServletResponse.SC_BAD_REQUEST );
//			map.put("error", "bind error");
//			map.put("errorList", result.getAllErrors());
//			return JsonHelper.render(map);
//		}
		// 고급 설정에 따라 비밀번호를 검증한다
//		if(!"".equals(vo.getPassword())) {
//			// 아이디만큼은 넣어줘야할 필요가 있다
//			vo.setId(mvo.getId());
//
//			PasswordValidator passwordValidator = new PasswordValidator(configService.getVo(), configService);
//			String passwordErrorMessage = passwordValidator.validate(vo);
//			if (passwordErrorMessage != null) {
//				map.put("error", passwordErrorMessage);
//				return JsonHelper.render(map);
//			}
//		}
//
//		// 비밀번호 MD5 HASH
//		try {
//			if(!"".equals(vo.getPassword())) {
//				vo.setPassword(StringUtil.hashPassword(vo.getPassword()));
//			} else {
//				// 변경할 필요가 없다면 null을 날린다
//				vo.setPassword(null);
//			}
//		} catch (NoSuchAlgorithmException e) {
//			map.put("error", "비밀번호를 제대로 처리할 수 없었습니다");
//			return JsonHelper.render(map);
//		}
		return "/index.jsp";
	}

	@RequestMapping("/about")
	public String about() {
		return "/index.jsp";
	}

	@RequestMapping("/contact")
	public String contact() {
		return "/index.jsp";
	}

	@RequestMapping("/post")
	public String post() {
		return "/index.jsp";
	}
}
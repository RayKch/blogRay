package ray.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 세션을 체크해서 반환하는 인터셉터
 * Created by ChanPong on 2016-05-17.
 */
@Slf4j
@Component
public class LoginCheckInterceptorImpl extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		/* 브라우저 캐시를 제거한다 */
		response.setHeader("Progma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store"); // 일부 파이어폭스 버그 관련
		response.setContentType("text/html; charset=utf-8");

//		String requestURI = request.getRequestURI();
//		log.info("### requestURI : " + requestURI);
//
//		/* 세션 체크 */
//		HttpSession session = request.getSession(false);
//		if(session == null || session.getAttribute("memberVo") == null ) {
//			response.sendRedirect("/app/login?status=expired");
//			return false;
//		}
		return true;
	}
}
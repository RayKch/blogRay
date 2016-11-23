package ray.interceptor;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import ray.data.param.StatsParamVo;
import ray.repository.StatsDao;
import ray.service.StatsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Random;

/**
 * 일일방문자를 체크해서 반환하는 인터셉터
 * Created by ChanPong on 2016-05-17.
 */
@Slf4j
@Service
@Component
public class StatsCheckInterceptorImpl extends HandlerInterceptorAdapter {
	@Autowired
	private StatsService statsService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		/* 브라우저 캐시를 제거한다 */
		response.setHeader("Progma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store"); // 일부 파이어폭스 버그 관련
		response.setContentType("text/html; charset=utf-8");

		String splitUri = "";
		String requestURI = request.getRequestURI();
		int uriSize = requestURI.split("/").length;
		if(uriSize > 1) {
			splitUri = requestURI.split("/")[1];
		}
		log.info("### requestURI : " + requestURI);

		/** 히어로배너 자동변경 */
		//각 계절별 이미지 보유 갯수
		int spring = 3;
		int summer = 1;
		int autumn = 2;
		int winter = 5;

		//이미지 선택번호
		int idx = 0;

		//각 계절별 폴더 이름
		String season = "";

		Calendar cal = Calendar.getInstance();
		int month = (cal.get(Calendar.MONTH) + 1);

		Random r = new Random();
		if(month >= 3 && month <=5) { //봄
			season = "spring";
			idx = r.nextInt(spring) + 1;
		} else if(month >= 6 && month <=8) { //여름
			season = "summer";
			idx = r.nextInt(summer) + 1;
		} else if(month >= 9 && month <=11) { //가을
			season = "autumn";
			idx = r.nextInt(autumn) + 1;
		} else if(month >= 12 && month <=2) { //겨울
			season = "winter";
			idx = r.nextInt(winter) + 1;
		}

		request.setAttribute("season", season);
		request.setAttribute("imgIdx", idx);

		/** 일일방문자 체크 */
//		if("/".equals(requestURI) || (uriSize > 1 && "index".equals(splitUri) || (uriSize > 1 && "view".equals(splitUri)))) {
//			try {
//				StatsParamVo paramVo = new StatsParamVo();
//				paramVo.setTypeCode("today");
//				paramVo.setIp(request.getRemoteAddr());
//
//				if(!statsService.getStatsCnt(paramVo)) {
//					statsService.insertBufferVo(paramVo);
//					statsService.updateStatsVo(paramVo);
//				}
//			} catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
		return true;
	}
}
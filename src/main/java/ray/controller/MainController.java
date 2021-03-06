package ray.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ray.data.BoardVo;
import ray.data.CategoryVo;
import ray.data.StatsVo;
import ray.data.param.BoardParamVo;
import ray.data.param.StatsParamVo;
import ray.service.BoardService;
import ray.service.CategoryService;
import ray.service.StatsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by ChanPong on 2016-05-17.
 */
@Slf4j
@Controller
public class MainController {
	@Autowired
	private BoardService boardService;

	@Autowired
	private StatsService statsService;

	@Autowired
	private CategoryService categoryService;

	@RequestMapping({"/index", "/"})
	public String index(BoardParamVo vo, HttpServletRequest request, Model model) {
		model.addAttribute("vo", vo);
		String metaTitle = "찬퐁의 개발 블로그";
		String metaDescription = "즐겁고 활기차게 개발하는 찬퐁의 개인 블로그";
		if(vo.getCategorySeq() != null) {
			CategoryVo cvo = categoryService.getVo(vo.getCategorySeq());
			metaTitle = "\"" + cvo.getTitle() + "\"" + " 카테고리";
			metaDescription = cvo.getDescription();
			model.addAttribute("categoryVo", cvo);
		}

		//메타태그에 적용시킬 문구 생성
		metaCreate(request, model, metaTitle, metaDescription, request.getRequestURL().toString());

		try {
			statsService.todayStatsProcess(request);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "/index.jsp";
	}

	@RequestMapping("/view/{seq}")
	public String view(@PathVariable Integer seq, HttpServletRequest request, Model model) {
		BoardVo vo = boardService.getVo(seq);
		model.addAttribute("seq", seq);
		model.addAttribute("vo", vo);

		//메타태그에 적용시킬 문구 생성
		metaCreate(request, model, vo.getTitle(), vo.getCategoryDescription(), request.getRequestURL().toString());

		try {
			statsService.viewStatsProcess(seq, request);
			statsService.todayStatsProcess(request);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "/view.jsp";
	}

	@RequestMapping(value = "/stats/data/json", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody StatsVo stats() {
		return statsService.getVisitorCnt();
	}

	private void metaCreate(HttpServletRequest request, Model model, String metaTitle, String metaDescription, String metaUrl) {
		model.addAttribute("metaTitle", metaTitle);
		model.addAttribute("metaDescription", metaDescription);
		model.addAttribute("metaUrl", metaUrl);
	}
}
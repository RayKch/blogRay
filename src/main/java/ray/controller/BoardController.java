package ray.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ray.data.param.BoardParamVo;
import ray.service.BoardService;
import ray.service.CategoryService;

/**
 * Created by ChanPong on 2016-05-17.
 */
@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;

	@Autowired
	private CategoryService categoryService;

	@RequestMapping("/form")
	public String form(Model model) {
		model.addAttribute("categoryList", categoryService.getList());
		return "/board/form.jsp";
	}

	@RequestMapping("/list")
	public String list(BoardParamVo vo, Model model) {
		vo.setTotalRowCount(boardService.getListTotalCount(vo));

		model.addAttribute("list", boardService.getList(vo));
		model.addAttribute("vo", vo);
		model.addAttribute("paging", vo.drawPagingNavigation("goPage"));
		return "/board/list.jsp";
	}


}
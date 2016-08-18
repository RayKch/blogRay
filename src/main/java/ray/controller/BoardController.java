package ray.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ray.data.param.BoardParamVo;
import ray.data.validator.BoardInsertValidator;
import ray.service.BoardService;
import ray.service.CategoryService;
import ray.util.Const;

import javax.servlet.http.HttpSession;

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

	@RequestMapping(value = "/insert/proc", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String insert(BoardParamVo vo, HttpSession session, Model model, BindingResult result) {
		new BoardInsertValidator().validate(vo, result);
		if (result.hasErrors()) {
			model.addAttribute("message", result.getFieldError().getDefaultMessage());
			return Const.AJAX_PAGE;
		}

		vo.setMemberSeq(Integer.parseInt(""+session.getAttribute("loginSeq")));
		if(!boardService.insertVo(vo)) {
			model.addAttribute("message", "포스트 등록이 실패하였습니다");
			return Const.AJAX_PAGE;
		}
		model.addAttribute("message", "포스트가 등록되었습니다.");
		model.addAttribute("returnUrl", "/board/list?categorySeq="+vo.getCategorySeq());
		return Const.REDIRECT_PAGE;
	}
}
package ray.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ray.data.BoardVo;
import ray.data.param.BoardParamVo;
import ray.data.validator.BoardInsertValidator;
import ray.service.BoardService;
import ray.service.CategoryService;
import ray.util.Const;

import javax.servlet.http.HttpSession;
import java.util.List;

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
	public String form(Integer seq, HttpSession session, Model model) {
		if(session.getAttribute("loginSeq") == null) {
			model.addAttribute("message", "로그인 후 이용가능합니다");
			return Const.BACK_PAGE;
		}

		model.addAttribute("categoryList", categoryService.getList());

		if(seq != null) {
			model.addAttribute("vo", boardService.getVo(seq));
			model.addAttribute("seq", seq);
		}

		return "/board/form.jsp";
	}

	@RequestMapping(value = "/list/json", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody List<BoardVo> list(BoardParamVo vo) {
		vo.setRowCount(5);
		vo.setTotalRowCount(boardService.getListTotalCount(vo));
		return boardService.getList(vo);
	}

	@RequestMapping(value = "/list/paging/json", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody String paging(BoardParamVo vo) {
		vo.setRowCount(5);
		vo.setTotalRowCount(boardService.getListTotalCount(vo));
		return vo.drawPagingNavigation("goPage");
	}

	@RequestMapping(value = "/data/json", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody BoardVo data(Integer seq) {
		return boardService.getVo(seq);
	}

	@RequestMapping(value = "/insert/proc", method = RequestMethod.POST)
	public String insert(BoardParamVo vo, HttpSession session, Model model, BindingResult result) {
		if(session.getAttribute("loginSeq") == null) {
			model.addAttribute("message", "로그인 후 이용가능합니다");
			return Const.AJAX_PAGE;
		}

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
		model.addAttribute("returnUrl", "/?categorySeq="+vo.getCategorySeq());
		return Const.REDIRECT_PAGE;
	}

	@RequestMapping(value = "/update/proc", method = RequestMethod.POST)
	public String update(BoardParamVo vo, HttpSession session, Model model, BindingResult result) {
		if(session.getAttribute("loginSeq") == null) {
			model.addAttribute("message", "로그인 후 이용가능합니다");
			return Const.AJAX_PAGE;
		}

		new BoardInsertValidator().validate(vo, result);
		if (result.hasErrors()) {
			model.addAttribute("message", result.getFieldError().getDefaultMessage());
			return Const.AJAX_PAGE;
		}

		vo.setMemberSeq(Integer.parseInt(""+session.getAttribute("loginSeq")));
		if(!boardService.updateVo(vo)) {
			model.addAttribute("message", "포스트 수정이 실패하였습니다");
			return Const.AJAX_PAGE;
		}
		model.addAttribute("message", "포스트가 수정되었습니다.");
		model.addAttribute("returnUrl", "/?categorySeq="+vo.getCategorySeq());
		return Const.REDIRECT_PAGE;
	}

	@RequestMapping(value = "/delete/proc", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String delete(int seq, HttpSession session, Model model) {
		if(session.getAttribute("loginSeq") == null) {
			model.addAttribute("message", "로그인 후 이용가능합니다");
			return Const.AJAX_PAGE;
		}

		if(!boardService.deleteVo(seq)) {
			model.addAttribute("message", "포스트삭제가 실패하였습니다");
			return Const.AJAX_PAGE;
		}
		model.addAttribute("message", "success");
		return Const.AJAX_PAGE;
	}
}
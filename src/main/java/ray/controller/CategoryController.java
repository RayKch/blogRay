package ray.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ray.data.CategoryVo;
import ray.data.PagingResultVo;
import ray.data.param.BoardParamVo;
import ray.data.param.CategoryParamVo;
import ray.data.validator.CategoryInsertValidator;
import ray.data.validator.MemberLoginValidator;
import ray.service.BoardService;
import ray.service.CategoryService;
import ray.util.Const;
import ray.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by BELL on 2016-08-10.
 */
@Slf4j
@Controller
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@RequestMapping("/form")
	public String form(HttpSession session, Model model) {
		if(session.getAttribute("loginSeq") == null) {
			model.addAttribute("message", "로그인 후 이용가능합니다");
			model.addAttribute("returnUrl", "/");
			return Const.REDIRECT_PAGE;
		}
		return "/category/form.jsp";
	}

	@RequestMapping(value = "/list/json", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody List<CategoryVo> list() {
		return categoryService.getList();
	}

	@RequestMapping(value = "/data/json", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody CategoryVo data(Integer seq) {
		return categoryService.getVo(seq);
	}

	@RequestMapping(value = "/insert/proc", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String insert(CategoryParamVo vo, HttpSession session, Model model, BindingResult result) {
		new CategoryInsertValidator().validate(vo, result);
		if (result.hasErrors()) {
			model.addAttribute("message", result.getFieldError().getDefaultMessage());
			return Const.AJAX_PAGE;
		}

		if(session.getAttribute("loginSeq") == null) {
			model.addAttribute("message", "로그인 후 이용가능합니다");
			return Const.AJAX_PAGE;
		}

		vo.setMemberSeq(Integer.parseInt(""+session.getAttribute("loginSeq")));

		if(!categoryService.insertVo(vo)) {
			model.addAttribute("message", "카테고리등록이 실패하였습니다");
			return Const.AJAX_PAGE;
		}
		model.addAttribute("message", "success");
		return Const.AJAX_PAGE;
	}

	@RequestMapping(value = "/update/proc", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String update(CategoryParamVo vo, HttpSession session, Model model, BindingResult result) {
		new CategoryInsertValidator().validate(vo, result);
		if (result.hasErrors()) {
			model.addAttribute("message", result.getFieldError().getDefaultMessage());
			return Const.AJAX_PAGE;
		}

		if(session.getAttribute("loginSeq") == null) {
			model.addAttribute("message", "로그인 후 이용가능합니다");
			return Const.AJAX_PAGE;
		}

		vo.setMemberSeq(Integer.parseInt(""+session.getAttribute("loginSeq")));
		if(!categoryService.updateVo(vo)) {
			model.addAttribute("message", "카테고리수정이 실패하였습니다");
			return Const.AJAX_PAGE;
		}
		model.addAttribute("message", "success");
		return Const.AJAX_PAGE;
	}

	@RequestMapping("/update/order/proc")
	public String updateOrder(CategoryParamVo vo, HttpSession session, Model model) {
		// todo : 사용자가 카테고리를 수정할 권한이 있는지 검사해야 한다

		if(vo.getSeq() == 0 || vo.getOrderNo() < 0) {
			model.addAttribute("message", "비정상적인 접근입니다");
			return Const.AJAX_PAGE;
		}

		if(session.getAttribute("loginSeq") == null) {
			model.addAttribute("message", "로그인 후 이용가능합니다");
			return Const.AJAX_PAGE;
		}

		vo.setMemberSeq(Integer.parseInt(""+session.getAttribute("loginSeq")));
		if(!categoryService.updateOrderNo(vo)) {
			model.addAttribute("message", "정렬순서 수정이 실패했습니다");
			return Const.AJAX_PAGE;
		}

		model.addAttribute("message", "success");
		return Const.AJAX_PAGE;
	}

	@RequestMapping(value = "/delete/proc", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String delete(int seq, HttpSession session, Model model) {
		if(session.getAttribute("loginSeq") == null) {
			model.addAttribute("message", "로그인 후 이용가능합니다");
			return Const.AJAX_PAGE;
		}

		if(!categoryService.deleteVo(seq)) {
			model.addAttribute("message", "카테고리삭제가 실패하였습니다");
			return Const.AJAX_PAGE;
		}
		model.addAttribute("message", "success");
		return Const.AJAX_PAGE;
	}
}

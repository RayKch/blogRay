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
import ray.data.param.CategoryParamVo;
import ray.data.validator.CategoryInsertValidator;
import ray.data.validator.MemberLoginValidator;
import ray.service.CategoryService;
import ray.util.Const;
import ray.util.JsonHelper;

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
			model.addAttribute("message", "로그인 후 이용가능합니다.");
			return Const.BACK_PAGE;
		}
		return "/category/form.jsp";
	}

	@RequestMapping("/insert/proc")
	public String insert(CategoryParamVo vo, HttpSession session, Model model, BindingResult result) {
		new CategoryInsertValidator().validate(vo, result);
		if (result.hasErrors()) {
			model.addAttribute("message", result.getFieldError().getDefaultMessage());
			return Const.AJAX_PAGE;
		}

		vo.setMemberSeq(Integer.parseInt(""+session.getAttribute("loginSeq")));

		if(!categoryService.insertVo(vo)) {
			model.addAttribute("message", "카테고리등록이 실패하였습니다.");
			return Const.AJAX_PAGE;
		}
		model.addAttribute("message", "success");
		return Const.AJAX_PAGE;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody List<CategoryVo> list() {
		return categoryService.getList();
	}
}

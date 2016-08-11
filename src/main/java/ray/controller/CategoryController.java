package ray.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ray.data.CategoryVo;
import ray.data.PagingResultVo;
import ray.data.param.CategoryParamVo;
import ray.service.CategoryService;
import ray.util.Const;
import ray.util.JsonHelper;

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
	public String form() {
		return "/category/form.jsp";
	}

	@RequestMapping("/insert/proc")
	public String insert(CategoryParamVo vo, Model model) {
		/** 로그인을 구현하고나서 로그인 세션시퀀스를 박도록 해야한다. */
		vo.setMemberSeq(1);

		if(!categoryService.insertVo(vo)) {
			model.addAttribute("message", "카테고리등록이 실패하였습니다.");
			return Const.AJAX_PAGE;
		}
		model.addAttribute("message", "success");
		return Const.AJAX_PAGE;
	}

	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public String getCategoryList(CategoryParamVo vo) {
		PagingResultVo resultVo = new PagingResultVo();
//		resultVo.setTotal(categoryService.getListTotalCount(vo));
//		resultVo.setList( categoryService.getList(vo) );

		return JsonHelper.render(resultVo);
	}
}

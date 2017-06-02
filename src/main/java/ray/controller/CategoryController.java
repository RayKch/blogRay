package ray.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.site.SitePreference;
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

	@RequestMapping(value = "/title/list/json", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody List<CategoryVo> titleList() {
		return categoryService.getTitleList();
	}

	@RequestMapping(value = "/list/json", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody List<CategoryVo> list(HttpServletRequest request, CategoryParamVo vo) {
		SitePreference sitePreference = (SitePreference)request.getAttribute("currentSitePreference");
		/** 한페이지에 보여줄 페이징 넘버 갯수(PC: 10개, Mobile: 3개) */
		if(sitePreference.isNormal()) {
			vo.setPageCount(10);
		} else {
			vo.setPageCount(3);
		}
		vo.setRowCount(10);
		vo.setTotalRowCount(categoryService.getListTotalCount(vo));
		return categoryService.getList(vo);
	}

	@RequestMapping(value = "/list/paging/json", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody String paging(HttpServletRequest request, CategoryParamVo vo) {
		SitePreference sitePreference = (SitePreference)request.getAttribute("currentSitePreference");
		/** 한페이지에 보여줄 페이징 넘버 갯수(PC: 10개, Mobile: 3개) */
		if(sitePreference.isNormal()) {
			vo.setPageCount(10);
		} else {
			vo.setPageCount(3);
		}
		vo.setRowCount(10);
		vo.setTotalRowCount(categoryService.getListTotalCount(vo));
		return vo.drawPagingNavigation("goPage");
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
	public String delete(CategoryParamVo vo, HttpSession session, Model model) {
		if(session.getAttribute("loginSeq") == null) {
			model.addAttribute("message", "로그인 후 이용가능합니다");
			return Const.AJAX_PAGE;
		}

		if(!categoryService.deleteVo(vo)) {
			model.addAttribute("message", "카테고리삭제가 실패하였습니다");
			return Const.AJAX_PAGE;
		}
		model.addAttribute("message", "success");
		return Const.AJAX_PAGE;
	}
}

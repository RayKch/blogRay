package ray.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ray.service.CategoryService;

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
}

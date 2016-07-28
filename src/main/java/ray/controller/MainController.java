package ray.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ChanPong on 2016-05-17.
 */
@Slf4j
@Controller
public class MainController {
	@RequestMapping({"/index", "/"})
	public String index() {
		return "/index.jsp";
	}

	@RequestMapping("/about")
	public String about() {
		return "/index.jsp";
	}

	@RequestMapping("/contact")
	public String contact() {
		return "/index.jsp";
	}

	@RequestMapping("/post")
	public String post() {
		return "/index.jsp";
	}
}
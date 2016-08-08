package ray.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ChanPong on 2016-05-17.
 */
@Slf4j
@Controller
public class BoardController {
	@RequestMapping("/board/form")
	public String form() {
		return "/board/form.jsp";
	}
}
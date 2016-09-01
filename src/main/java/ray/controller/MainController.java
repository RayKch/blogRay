package ray.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ray.data.BoardVo;
import ray.data.param.BoardParamVo;
import ray.service.BoardService;
import ray.service.CategoryService;

import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by ChanPong on 2016-05-17.
 */
@Slf4j
@Controller
public class MainController {
	@RequestMapping({"/index", "/"})
	public String index(BoardParamVo vo, Model model) {
		model.addAttribute("vo", vo);
		return "/index.jsp";
	}

	@RequestMapping("/view")
	public String view(Integer seq, Model model) {
		model.addAttribute("seq", seq);
		return "/view.jsp";
	}
}
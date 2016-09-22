package ray.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
	@Autowired
	private BoardService boardService;

	@RequestMapping({"/index", "/"})
	public String index(BoardParamVo vo, Model model) {
		model.addAttribute("vo", vo);
		return "/index.jsp";
	}

	@RequestMapping("/view/{seq}")
	public String view(@PathVariable Integer seq, Model model) {
		BoardVo vo = boardService.getVo(seq);
		model.addAttribute("seq", seq);
		model.addAttribute("vo", vo);

		try {
			boardService.updateViewCnt(seq);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "/view.jsp";
	}
}
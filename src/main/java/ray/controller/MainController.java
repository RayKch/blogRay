package ray.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ray.data.param.BoardParamVo;
import ray.service.BoardService;
import ray.service.CategoryService;

import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;

/**
 * Created by ChanPong on 2016-05-17.
 */
@Slf4j
@Controller
public class MainController {
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private BoardService boardService;

	@RequestMapping({"/index", "/"})
	public String index(BoardParamVo vo, Model model) {
		vo.setTotalRowCount(boardService.getListTotalCount(vo));

		model.addAttribute("list", boardService.getList(vo));
		model.addAttribute("vo", vo);
		model.addAttribute("paging", vo.drawPagingNavigation("goPage"));
		model.addAttribute("categoryVo", categoryService.getVo(vo.getCategorySeq()));
		return "/index.jsp";
	}
}
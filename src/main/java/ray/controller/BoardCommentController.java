package ray.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ray.data.BoardVo;
import ray.data.param.BoardParamVo;
import ray.data.validator.BoardCommentInsertValidator;
import ray.service.BoardService;
import ray.util.Const;
import ray.util.StringUtil;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by ChanPong on 2016-09-18.
 */
@Slf4j
@Controller
@RequestMapping("/board/comment")
public class BoardCommentController {
	@Autowired
	private BoardService boardService;

	@RequestMapping(value = "/list/json", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody
	List<BoardVo> commentList(BoardParamVo vo) {
		return boardService.getCommentList(vo);
	}

	@RequestMapping(value = "/insert/proc", method = RequestMethod.POST)
	public String insertComment(BoardParamVo vo, HttpSession session, Model model, BindingResult result) {
		String loginSeq = ""+session.getAttribute("loginSeq");
		if(!"null".equals(loginSeq)) {
			vo.setMemberSeq(Integer.parseInt(loginSeq));
		}

		new BoardCommentInsertValidator().validate(vo, result);
		if (result.hasErrors()) {
			model.addAttribute("message", result.getFieldError().getDefaultMessage());
			return Const.AJAX_PAGE;
		}

		if(!StringUtils.isEmpty(vo.getPassword())) {
			try {
				vo.setPassword(StringUtil.encryptSha2(vo.getPassword()));
			} catch(NoSuchAlgorithmException e) {
				model.addAttribute("message", "비밀번호 암호화에 실패하였습니다.");
				return Const.AJAX_PAGE;
			}
		}

		if(!boardService.insertCommentVo(vo)) {
			model.addAttribute("message", "댓글 등록이 실패하였습니다");
			return Const.AJAX_PAGE;
		}
		model.addAttribute("message", "success");
		return Const.AJAX_PAGE;
	}

	@RequestMapping(value = "/delete/proc", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String deleteComment(BoardParamVo vo, HttpSession session, Model model) throws Exception {
		String loginSeq = ""+session.getAttribute("loginSeq");

		//삭제를 하는데 로그인이 되어있지 않으면 비원으로 댓글을 작성한 것이고 패스워드가 있어야지만 삭제를 할 수 있도록 한다.
		if("null".equals(loginSeq)) {
			if (vo.getPassword() == null) {
				model.addAttribute("message", "댓글 작성시 입력한 비밀번호를 입력해 주세요.");
				return Const.AJAX_PAGE;
			}
		} else {
			if(!boardService.getCommentCnt(Integer.valueOf(loginSeq))) {
				model.addAttribute("message", "본인이 작성한 댓글(답글)만 삭제 가능합니다.");
				return Const.AJAX_PAGE;
			} else {
				vo.setMemberSeq(Integer.parseInt(loginSeq));
			}
		}

		if(!StringUtils.isEmpty(vo.getPassword())) {
			try {
				vo.setPassword(StringUtil.encryptSha2(vo.getPassword()));
			} catch(NoSuchAlgorithmException e) {
				model.addAttribute("message", "비밀번호 암호화에 실패하였습니다.");
				return Const.AJAX_PAGE;
			}
		}

		if(!boardService.deleteCommentVo(vo)) {
			model.addAttribute("message", "댓글 삭제가 실패하였습니다");
			return Const.AJAX_PAGE;
		}
		model.addAttribute("message", "success");
		return Const.AJAX_PAGE;
	}
}

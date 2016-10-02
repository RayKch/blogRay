package ray.controller;

import ray.util.MediaUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import ray.data.BoardVo;
import ray.data.FileVo;
import ray.data.param.BoardParamVo;
import ray.data.validator.BoardInsertValidator;
import ray.service.BoardService;
import ray.service.CategoryService;
import ray.util.Const;
import ray.util.exception.ImageIsNotAvailableException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by ChanPong on 2016-05-17.
 */
@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;

	@Autowired
	private CategoryService categoryService;

	@RequestMapping("/form")
	public String form(Integer seq, HttpSession session, Model model) {
		if(session.getAttribute("loginSeq") == null) {
			model.addAttribute("message", "로그인 후 이용가능합니다");
			model.addAttribute("returnUrl", "/");
			return Const.REDIRECT_PAGE;
		}

		model.addAttribute("categoryList", categoryService.getList());

		if(seq != null) {
			model.addAttribute("vo", boardService.getVo(seq));
			model.addAttribute("seq", seq);
		}

		return "/board/form.jsp";
	}

	@RequestMapping(value = "/list/json", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody List<BoardVo> list(BoardParamVo vo) {
		vo.setRowCount(5);
		vo.setTotalRowCount(boardService.getListTotalCount(vo));
		return boardService.getList(vo);
	}

	@RequestMapping(value = "/list/paging/json", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody String paging(BoardParamVo vo) {
		vo.setRowCount(5);
		vo.setTotalRowCount(boardService.getListTotalCount(vo));
		return vo.drawPagingNavigation("goPage");
	}

	@RequestMapping(value = "/data/json", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody BoardVo data(Integer seq) {
		return boardService.getVo(seq);
	}

	@RequestMapping(value = "/insert/proc", method = RequestMethod.POST)
	public String insert(BoardParamVo vo, HttpSession session, Model model, BindingResult result) {
		if(session.getAttribute("loginSeq") == null) {
			model.addAttribute("message", "로그인 후 이용가능합니다");
			return Const.AJAX_PAGE;
		}

		new BoardInsertValidator().validate(vo, result);
		if (result.hasErrors()) {
			model.addAttribute("message", result.getFieldError().getDefaultMessage());
			return Const.AJAX_PAGE;
		}

		vo.setMemberSeq(Integer.parseInt(""+session.getAttribute("loginSeq")));
		if(!boardService.insertVo(vo)) {
			model.addAttribute("message", "포스트 등록이 실패하였습니다");
			return Const.AJAX_PAGE;
		}

		//같이 넘어온 임시 등록 이미지가 존재한다면 실경로로 복사후 임시이미지를 제거하고 db에 insert한다..
		for(int i=0; i<vo.getFileList().size(); i++) {
			FileVo tempVo = vo.getFileList().get(i);
			tempVo.setBoardSeq(vo.getBoardSeq());
			System.out.println("test1234="+tempVo.toString());
		}

		model.addAttribute("message", "포스트가 등록되었습니다.");
		model.addAttribute("returnUrl", "/?categorySeq="+vo.getCategorySeq());
		return Const.REDIRECT_PAGE;
	}

	@RequestMapping(value = "/update/proc", method = RequestMethod.POST)
	public String update(BoardParamVo vo, HttpSession session, Model model, BindingResult result) {
		if(session.getAttribute("loginSeq") == null) {
			model.addAttribute("message", "로그인 후 이용가능합니다");
			return Const.AJAX_PAGE;
		}

		new BoardInsertValidator().validate(vo, result);
		if (result.hasErrors()) {
			model.addAttribute("message", result.getFieldError().getDefaultMessage());
			return Const.AJAX_PAGE;
		}

		vo.setMemberSeq(Integer.parseInt(""+session.getAttribute("loginSeq")));
		if(!boardService.updateVo(vo)) {
			model.addAttribute("message", "포스트 수정이 실패하였습니다");
			return Const.AJAX_PAGE;
		}
		model.addAttribute("message", "포스트가 수정되었습니다.");
		model.addAttribute("returnUrl", "/?categorySeq="+vo.getCategorySeq());
		return Const.REDIRECT_PAGE;
	}

	@RequestMapping(value = "/delete/proc", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String delete(int seq, HttpSession session, Model model) {
		if(session.getAttribute("loginSeq") == null) {
			model.addAttribute("message", "로그인 후 이용가능합니다");
			return Const.AJAX_PAGE;
		}

		if(!boardService.deleteVo(seq)) {
			model.addAttribute("message", "포스트삭제가 실패하였습니다");
			return Const.AJAX_PAGE;
		}
		model.addAttribute("message", "success");
		return Const.AJAX_PAGE;
	}

	@RequestMapping(value = "/editor/image/upload", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody FileVo upload(HttpServletRequest request, MultipartHttpServletRequest mRequest) {
		FileVo vo = new FileVo();
		try {
			vo = boardService.editorUploadTempImages(mRequest);
		} catch (IOException ie) {
			log.error(ie.getMessage() + "서버상의 문제가 발생했습니다. 관리자에게 문의하여 주십시오.");
			ie.printStackTrace();
		} catch (ImageIsNotAvailableException ie) {
			log.error("첨부한 파일은 이미지 파일이 아닙니다");
			ie.printStackTrace();
		}

		vo.setUrl(request.getScheme() + "://" + request.getServerName() + "/board/editor/image/view");
		return vo;
	}

	@RequestMapping(value = "/editor/image/view", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody public void imageView(FileVo vo, HttpServletResponse response) {
		try {
			response.setHeader("Content-Disposition", "attachment; filename\"" + vo.getFileName() + "\"");
			if (MediaUtils.containsImageMediaType(vo.getContentType())) {
				response.setContentType(vo.getContentType());
			}

			String path = Const.UPLOAD_LOCAL_PATH + File.separator + "blogRay" + File.separator + "editor";
			if("temp".equals(vo.getTypeCode())) {
				path += File.separator + "temp" + File.separator + vo.getTempFileName();
			} else {
				path += File.separator + vo.getBoardSeq() + File.separator + vo.getFileName();
			}

			File file = new File(path);

			// Open the file and output streams
			FileInputStream in = new FileInputStream(file);
			OutputStream out = response.getOutputStream();

			// Copy the contents of the file to the output stream
			byte[] buf = new byte[1024];
			int count = 0;
			while ((count = in.read(buf)) >= 0) {
				out.write(buf, 0, count);
			}
			in.close();
			out.close();
		} catch(Exception e) {
				e.printStackTrace();
		}
	}

}
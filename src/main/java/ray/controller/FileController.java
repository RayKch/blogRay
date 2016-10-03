package ray.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import ray.data.FileVo;
import ray.service.FileService;
import ray.util.Const;
import ray.util.MediaUtils;
import ray.util.exception.ImageIsNotAvailableException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by ChanPong on 2016-10-03.
 */
@Slf4j
@Controller
@RequestMapping("/file")
public class FileController {
	@Autowired
	private FileService fileService;

	@RequestMapping(value = "/editor/image/upload", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody
	FileVo upload(HttpServletRequest request, MultipartHttpServletRequest mRequest) {
		FileVo vo = new FileVo();
		try {
			vo = fileService.editorUploadTempImages(mRequest);
		} catch (IOException ie) {
			log.error(ie.getMessage() + "서버상의 문제가 발생했습니다. 관리자에게 문의하여 주십시오.");
			ie.printStackTrace();
		} catch (ImageIsNotAvailableException ie) {
			log.error("첨부한 파일은 이미지 파일이 아닙니다");
			ie.printStackTrace();
		}

		vo.setUrl(request.getScheme() + "://" + request.getServerName() + "/image/editor/view");
		return vo;
	}
}

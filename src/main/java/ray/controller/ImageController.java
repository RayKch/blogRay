package ray.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ray.data.FileVo;
import ray.util.Const;
import ray.util.FileUtil;
import ray.util.MediaUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

/**
 * Created by ChanPong on 2016-10-03.
 */
@Slf4j
@Controller
@RequestMapping("/image")
public class ImageController {
	@RequestMapping(value = "/editor/view/{type}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public void imageView(@PathVariable String type, FileVo vo, HttpServletResponse response) {
		try {
			response.setHeader("Content-Disposition", "attachment; filename\"" + vo.getFileName() + "\"");
			if (MediaUtils.containsImageMediaType(vo.getContentType())) {
				response.setContentType(vo.getContentType());
			}

			/** type값은 임시 이미지일경우 temp 리얼 이미지일경우 게시판 시퀀스값이 넘어올것이다. */
			String path = FileUtil.getUploadPath() + File.separator + "blogRay" + File.separator + "editor" + File.separator + type + File.separator + vo.getFileName();

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

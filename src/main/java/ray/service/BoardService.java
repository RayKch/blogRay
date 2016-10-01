package ray.service;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import ray.data.BoardVo;
import ray.data.FileVo;
import ray.data.param.BoardParamVo;
import ray.repository.BoardDao;
import ray.util.Const;
import ray.util.FileUploadUtil;
import ray.util.StringUtil;
import ray.util.exception.ImageIsNotAvailableException;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ChanPong on 2016-08-10.
 */
@Slf4j
@Service
public class BoardService {
	@Autowired
	@Setter
	private BoardDao boardDao;

	private final Path rootLocation = null;

	public List<BoardVo> getList(BoardParamVo vo) {
		List<BoardVo> list = boardDao.getList(vo);
		for(int i=0; i<list.size(); i++) {
			BoardVo tempVo = list.get(i);
			tempVo.setRegDate(tempVo.getRegDate().substring(0, 10));
		}
		return list;
	}

	public int getListTotalCount(BoardParamVo vo) {
		return boardDao.getListTotalCount(vo);
	}

	public BoardVo getVo(Integer seq) {
		BoardVo vo = boardDao.getVo(seq);
		vo.setRegDate(vo.getRegDate().substring(0, 10));
		return vo;
	}

	@Transactional(propagation= Propagation.REQUIRED, rollbackFor={Exception.class})
	public boolean insertVo(BoardParamVo vo) {
		return boardDao.insertVo(vo) > 0;
	}

	@Transactional(propagation= Propagation.REQUIRED, rollbackFor={Exception.class})
	public boolean updateVo(BoardParamVo vo) {
		return boardDao.updateVo(vo) > 0;
	}

	public boolean deleteVo(Integer seq) {
		return boardDao.deleteVo(seq) > 0;
	}

	public List<BoardVo> getCommentList(BoardParamVo vo) {
		List<BoardVo> list = boardDao.getCommentList(vo);
		for(int i=0; i<list.size(); i++) {
			BoardVo tempVo = list.get(i);
			tempVo.setRegDate(tempVo.getRegDate().substring(0, 10));
		}
		return list;
	}

	public boolean getCommentCnt(Integer loginSeq) {
		return boardDao.getCommentCnt(loginSeq) > 0;
	}

	@Transactional(propagation= Propagation.REQUIRED, rollbackFor={Exception.class})
	public boolean insertCommentVo(BoardParamVo vo) {
		return boardDao.insertCommentVo(vo) > 0;
	}

	public boolean deleteCommentVo(BoardParamVo vo) {
		return boardDao.deleteCommentVo(vo) > 0;
	}

	public int updateViewCnt(int seq) {
		return boardDao.updateViewCnt(seq);
	}

	public FileVo editorUploadImages(HttpServletRequest request) throws IOException, ImageIsNotAvailableException {
		FileVo vo = new FileVo();
		String uploadPath = "";
		String os = System.getProperty("os.name");
		log.info("현재 운영중인 OS :::: "+os);
		if(os.contains("Windows")) {
			uploadPath = Const.UPLOAD_LOCAL_PATH;
		} else {
			uploadPath = Const.UPLOAD_REAL_PATH;
		}

		MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;
		Iterator<String> iter = mpRequest.getFileNames();
		while (iter.hasNext()) {
			MultipartFile file = mpRequest.getFile(iter.next());
			if (file.getSize() > 0) {
				// temp 디렉토리 생성
				File tempDir = new File(uploadPath);
				if (!tempDir.exists()) {
					tempDir.mkdir();
				}
				tempDir = new File(uploadPath + File.separator + "blogRay");
				if (!tempDir.exists()) {
					tempDir.mkdir();
				}
				tempDir = new File( uploadPath + File.separator + "blogRay" + File.separator + "editor");
				if (!tempDir.exists()) {
					tempDir.mkdir();
				}
				tempDir = new File( uploadPath + File.separator + "blogRay" + File.separator + "editor" + File.separator + "temp");
				if (!tempDir.exists()) {
					tempDir.mkdir();
				}

				vo.setContentType(file.getContentType());
				vo.setFileName(file.getOriginalFilename());
				vo.setTempFileName(new FileUploadUtil().uploadImageFile(file, uploadPath  + File.separator + "blogRay" + File.separator + "editor" + File.separator + "temp"));
			}
		}
		return vo;
	}
}

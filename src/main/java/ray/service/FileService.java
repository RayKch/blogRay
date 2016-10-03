package ray.service;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import ray.data.FileVo;
import ray.repository.FileDao;
import ray.util.Const;
import ray.util.FileUploadUtil;
import ray.util.exception.ImageIsNotAvailableException;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by ChanPong on 2016-10-03.
 */
@Slf4j
@Service
public class FileService {
	@Autowired
	@Setter
	private FileDao fileDao;

	@Transactional(propagation= Propagation.REQUIRED, rollbackFor={Exception.class})
	public boolean insertVo(FileVo vo) {
		return fileDao.insertVo(vo) > 0;
	}

	public FileVo editorUploadTempImages(HttpServletRequest request) throws IOException, ImageIsNotAvailableException {
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
				vo.setTempFileName(new FileUploadUtil().uploadImageFile(file, uploadPath + File.separator + "blogRay" + File.separator + "editor" + File.separator + "temp"));
			}
		}
		return vo;
	}

	public void fileCopy(FileVo vo) {
		String uploadPath = "";
		String os = System.getProperty("os.name");
		log.info("현재 운영중인 OS :::: "+os);
		if(os.contains("Windows")) {
			uploadPath = Const.UPLOAD_LOCAL_PATH;
		} else {
			uploadPath = Const.UPLOAD_REAL_PATH;
		}

		String commonPath = uploadPath + File.separator + "blogRay" + File.separator + "editor";
		//여기서 임시파일명으로 계속 유지시키는 이유는 한게시물에 중복이름의 이미지가 올라가서 후에 나도 알수 없는 에러가 터질까봐
		//랜덤생성한 임시 파일명으로 안전하게 가도록한다.
		String orgPath = commonPath + File.separator + "temp" + File.separator + vo.getTempFileName();
		String newPath = commonPath + File.separator + vo.getBoardSeq() + File.separator + vo.getTempFileName();

		File orgFile = new File(orgPath);
		File newFile = new File(newPath);

		if(orgFile.exists()) {
			// "/blogRay/editor" 경로까지는 있을 것이고 "/게시판시퀀스" 경로는 존재하지 않을 것이기때문에 생성한다.
			File tempDir = new File(commonPath + File.separator + vo.getBoardSeq());
			if (!tempDir.exists()) {
				tempDir.mkdir();
			}
			orgFile.renameTo(newFile);
		}
	}

}

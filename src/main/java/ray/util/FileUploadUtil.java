package ray.util;


import org.springframework.web.multipart.MultipartFile;
import ray.util.exception.ImageIsNotAvailableException;

import javax.swing.*;
import java.io.*;
import java.util.UUID;

/**
 * User: 강사무엘
 * Date: 2013. 10. 29.
 * Time: 오후 3:28
 */
public class FileUploadUtil {
	/**
	 * 이미지를 업로드하고 유일한 파일명으로 포장해서 던져주는 메서드
	 * 이 리스트는 업로드된 디렉토리를 포함하지 않음, 오로지 파일명만을 반환한다
	 *
	 * @param formFile
	 * @param realPath
	 * @return
	 * @throws java.io.IOException                  (물리적인 IO에 문제가 발생했을 경우 발생하는 예외, 디스크 용량부족 등...)
	 * @throws ImageIsNotAvailableException (이미지가 아닐 경우 발생하는 예외)
	 */
	public String uploadImageFile(MultipartFile formFile, String realPath) throws IOException, ImageIsNotAvailableException {
		// 랜덤으로 생성된 이미지에 확장자를 붙인다
		String fileName = UUID.randomUUID().toString() + formFile.getOriginalFilename().substring(formFile.getOriginalFilename().lastIndexOf(".")).toLowerCase();

		upload(realPath, formFile, fileName);
		// 이미지 파일인지 아닌지 검사한다 (이 부분은 서버 부하가 있으므로 추후 문제되면 제거한다)
		ImageIcon ii = new ImageIcon(realPath + "/" + fileName);
		if (ii.getIconWidth() == -1 && ii.getIconHeight() == -1) {
			new File(realPath+"/"+fileName).delete();
			throw new ImageIsNotAvailableException();
		}
		return fileName;
	}

	/**
	 * 파일을 업로드하고 유일한 파일명으로 포장해서 던져주는 메서드
	 * 이 리스트는 업로드된 디렉토리를 포함하지 않음, 오로지 파일명만을 반환한다
	 *
	 * @param formFile
	 * @param realPath
	 * @return
	 * @throws java.io.IOException                  (물리적인 IO에 문제가 발생했을 경우 발생하는 예외, 디스크 용량부족 등...)
	 */
	public String uploadFile(MultipartFile formFile, String realPath) throws IOException {
		// 랜덤으로 생성된 이미지에 확장자를 붙인다
		String fileName = UUID.randomUUID().toString() + formFile.getOriginalFilename().substring(formFile.getOriginalFilename().lastIndexOf(".")).toLowerCase();
		upload(realPath, formFile, fileName);
		return fileName;
	}

	private void upload(String realPath, MultipartFile formFile, String fileName) throws IOException {
		File tempDir = new File(realPath);
		if (!tempDir.exists()) {
			tempDir.mkdir();
		}

		InputStream stream = formFile.getInputStream();
		OutputStream bos = new FileOutputStream(realPath + "/" + fileName);
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
			bos.write(buffer, 0, bytesRead);
		}
		bos.close();
		stream.close();
	}


	public String upload(String path, String realPath, MultipartFile file) throws IOException {
		String fileName = UUID.randomUUID().toString();
		File tempDir = new File(realPath);
		if(!tempDir.exists()) {
			tempDir.mkdirs();
		}

		InputStream stream = file.getInputStream();
		String streamPath = tempDir.getAbsoluteFile() + "/" + fileName;

		OutputStream bos = new FileOutputStream(streamPath);
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
			bos.write(buffer, 0, bytesRead);
		}
		bos.close();
		stream.close();

		return path + fileName;
	}
}

package ray.util;

import java.io.File;

public class FileUtil {
	public static String getUploadPath() {
		String uploadPath;
		String os = System.getProperty("os.name");
		if(os.contains("Windows")) {
			uploadPath = Const.UPLOAD_LOCAL_PATH;
		} else {
			uploadPath = Const.UPLOAD_REAL_PATH;
		}
		return uploadPath;
	}

	public void mkdir(File dir) {
		if(!dir.exists()) {
			dir.mkdir();
		}
	}

	public void move(File origin, File target) {
		if(target.exists()) {
			target.delete();
		}
		origin.renameTo( target );
	}

	public void rm(File target) {
		if(target.exists()) {
			target.delete();
		}
	}

	public static void deleteFile(String path){
		File file = new File(path);
		if(file.exists()) {
			file.delete();
		}
	}

	public static void deleteAllFiles(String path){
		File file = new File(path);
		//폴더내 파일을 배열로 가져온다.
		File[] tempFile = file.listFiles();

		if(tempFile.length >0){
			for (int i = 0; i < tempFile.length; i++) {
				if(tempFile[i].isFile()){
					tempFile[i].delete();
				}else{
					//재귀함수
					deleteAllFiles(tempFile[i].getPath());
				}
				tempFile[i].delete();
			}
			file.delete();
		}
	}
}

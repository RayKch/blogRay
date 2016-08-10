package ray.util;

import java.io.File;
import java.io.FilenameFilter;

public class FileTraversalUtil {
	/**
	 * 해당 TASK의 로그 주소
	 *
	 * @param logPath
	 * @param taskSeq
	 * @return
	 */
	public final String getLogPath(String logPath, String taskSeq) {
		return logPath + File.separator + "log" + File.separator + taskSeq + File.separator;
	}

	/**
	 * 파일 리스트를 반환하는 유틸
	 *
	 * @param path 해당 디렉토리의 종단점 (recursive는 하지 않는다)
	 * @param suffix 구두점을 포함한 파일 확장자 (예: .avi)
	 * @return
	 */
	public final synchronized String[] getList(File path, final String suffix) {
		if(!path.isDirectory()) {
			return null;
		}

		return path.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(suffix);
			}
		});
	}
}

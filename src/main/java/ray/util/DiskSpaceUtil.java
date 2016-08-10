package ray.util;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * 디스크 총 용량 및 가용용량을 구하는 클래스
 * 결과는 byte이기 때문에 1024*1024로 나눠야 한다
 *
 */
@Slf4j
public class DiskSpaceUtil {

	/** 디스크 기준 */
	@Getter
	private final String defaultPath;

	/** 디스크 총 사용량 */
	@Getter
	private final long totalSpace;

	/** 빈 공간 */
	@Getter
	private final long freeSpace;

	/** 사용할 수 있는 공간 */
	@Getter
	private final long usableSpace;

	public DiskSpaceUtil() {
		defaultPath = (File.separator.equals("/")) ? "/" : // unix like 일 경우
				(this.getClass().getResource(File.separator).getPath().split(":")[0] + ":\\").replaceAll("^/", ""); // windows 일 경우

		File diskPartition = new File(defaultPath);

		totalSpace = diskPartition.getTotalSpace();
		freeSpace = diskPartition.getFreeSpace();
		usableSpace = diskPartition.getUsableSpace();
	}
}

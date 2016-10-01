package ray.util;

import java.io.File;

/**
 * User: 강사무엘
 * Date: 2013. 10. 30.
 * Time: 오후 3:29
 */
public class FileUtil {
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
}

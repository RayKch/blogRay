package ray.data;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by ChanPong on 2016-10-01.
 */
@Data
@Slf4j
public class FileVo {
	public String typeCode;
	public String contentType;
	public String url;
	public String fileName;
	public String tempFileName;
	public Integer boardSeq;
}

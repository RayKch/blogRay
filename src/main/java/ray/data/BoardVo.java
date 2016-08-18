package ray.data;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by BELL on 2016-08-08.
 */
@Data
@Slf4j
public class BoardVo {
	private Integer seq;
	private Integer categorySeq;
	private Integer memberSeq;
	private String nickname;
	private String title;
	private String content;
	private int recomment;
	private int unRecommend;
	private int viewCnt;
	private String regDate;
	private String modDate;
}

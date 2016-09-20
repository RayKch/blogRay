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
	private Integer parentSeq;
	private Integer boardSeq;
	private Integer categorySeq;
	private Integer memberSeq;
	private Integer loginSeq;
	private String password;
	private String categoryName;
	private int childCount;
	private String nickname;
	private String nonSignUpNickname;
	private String title;
	private String content;
	private String categoryTitle;
	private String categoryDescription;
	private int recomment;
	private int unRecommend;
	private int viewCnt;
	private int commentCount;
	private String delYn;
	private String regDate;
	private String modDate;
}

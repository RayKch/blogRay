package ray.data.param;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import ray.data.FileVo;

import java.util.List;

/**
 * Created by ChanPong on 2016-08-18.
 */
@Data
@Slf4j
@EqualsAndHashCode(callSuper=false)
public class BoardParamVo extends ParamVo {
	private Integer seq;
	private Integer parentSeq;
	private Integer boardSeq;
	private Integer categorySeq;
	private Integer memberSeq;
	private String nickname;
	private String password;
	private String title;
	private String content;
	private int recomment;
	private int unRecommend;
	private int viewCnt;
	private String regDate;
	private String modDate;
	private List<FileVo> fileList;

	@Override
	public String getSearch() {
		return "";
	}
}

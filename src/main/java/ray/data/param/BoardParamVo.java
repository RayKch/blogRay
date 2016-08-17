package ray.data.param;

/**
 * Created by ChanPong on 2016-08-18.
 */
public class BoardParamVo extends ParamVo {
	private int seq;
	private int categorySeq;
	private int memberSeq;
	private String title;
	private String content;
	private int recomment;
	private int unRecommend;
	private int viewCnt;
	private String regDate;
	private String modDate;

	@Override
	public String getSearch() {
		return "";
	}
}

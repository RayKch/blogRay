package ray.data.param;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by ChanPong on 2016-08-11.
 */
@Data
@Slf4j
@EqualsAndHashCode(callSuper=false)
public class CategoryParamVo extends ParamVo {
	/** 시퀀스 */
	private Integer seq;
	/** 그룹 시퀀스 */
	private Integer groupSeq;
	/** 카테고리 타입(L:게시글형, C:댓글형) */
	private String typeCode;
	/** 회원번호 */
	private Integer memberSeq;
	/** 카테고리 명 */
	private String title;
	/** 카테고리 설명 */
	private String description;
	/** 정렬순서 */
	private int orderNo;
	/** 호출타입 */
	private String actionType;

	@Override
	public String getSearch() {
		return "";
	}
}

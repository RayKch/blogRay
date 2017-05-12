package ray.data;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by ChanPong on 2016-08-10.
 */
@Data
@Slf4j
public class CategoryVo {
	/** 시퀀스 */
	private Integer seq;
	/** 그룹 시퀀스 */
	private Integer groupSeq;
	/** 카테고리 타입(L:게시글형, C:댓글형) */
	private String typeCode;
	/** 카테고리 명 */
	private String title;
	/** 카테고리 설명 */
	private String description;
	/** 정렬순서 */
	private int orderNo;
	/** 포스트 전체글 갯수 */
	private int boardCount;
}

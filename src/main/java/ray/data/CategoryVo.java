package ray.data;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by ChanPong on 2016-08-10.
 */
@Data
@Slf4j
public class CategoryVo {
	/** 카테고리 명 */
	private String title;
	/** 카테고리 설명 */
	private String description;
	/** 회원번호 */
	private int memberSeq;
}

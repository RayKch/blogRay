package ray.data;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 공통으로 사용할 페이징 전용 vo (결과를 담는다)
 * 반드시 setTotal(number) 메서드를 사용하여 총합을 넣어야 제대로 페이징이 출력될 것이다
 * 출력시킬 리스트는 setList(vo)를 통해서 구현하면 된다
 *
 */
@Data
@Slf4j
public class PagingResultVo {
	/** 해당 게시물의 총합 */
	private int total = 0;
	/** 게시물을 몇 개씩 뿌려줄 것인가? */
	private int perPage = 20;
	/** 페이징이 몇 개씩 출력되도록 할 것인가? */
	private int pageCount = 5;

	/** 출력될 게시물의 모형 */
	private List list;
}

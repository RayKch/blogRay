package ray.data.param;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 각종 페이징되는 리스트를 구하기 위해 사용하는 파라메터 추상 클래스
 * 반드시 getSearch를 구현해야 한다.
 *
 * MySQL에서는 페이지의 시작 번호와 끝번호를 구할 필요가 없기 때문에 rownum을 계산하는 부분이 없다.
 *
 */
@Slf4j
public abstract class ParamVo {
	/** 현재 페이지 (0부터 시작함) */
	@Getter
	private int pageNum = 0;

	/**
	 * 현재 페이지를 지정한다
	 * @param pageNum (1보다 커야 한다)
	 */
	public void setPageNum(int pageNum) {
		if (pageNum < 1) {
			return;
		}
		this.pageNum = pageNum;
	}

	/** 한 화면에 보여줄 게시물 수 */
	@Setter @Getter
	private int rowCount = 20;

	/** 검색할 카테고리 (예: search=title, name, content) */
	@Setter
	protected String search;
	abstract public String getSearch();
	/** 검색어 */
	@Setter @Getter
	protected String findword;
}

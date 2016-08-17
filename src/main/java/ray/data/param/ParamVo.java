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
	private int pageNum = 0;
	private int rowCount = 20;

	private int startRowNum = 0;
	private int endRowNum = 0;

	private int totalRowCount = 0;
	private int pageCount = 10;


	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	private void calc() {
		startRowNum = pageNum * rowCount + 1;
		endRowNum = startRowNum + rowCount - 1;
	}


	public String drawPagingNavigation(String jsFunctionName) {
		StringBuffer _sb = new StringBuffer();
		int totalPageCnt = totalRowCount / rowCount;

		if (totalRowCount % rowCount > 0) {
			totalPageCnt = totalPageCnt + 1;
		}
		int startPageNum = pageNum / pageCount * pageCount;
		int endPageNum = totalPageCnt;
		if (pageNum / pageCount != totalPageCnt / pageCount) {
			endPageNum = startPageNum + pageCount;
		}

		_sb.append("<ul>");
		if (startPageNum >= pageCount) {
			_sb.append("<li><a href='#' onclick=\"");
			_sb.append(jsFunctionName);
			_sb.append("('");
			_sb.append(startPageNum - 1);
			_sb.append("');return false;\">Prev</a></li>");
		} else {
			_sb.append("<li class='disabled'><a href='#' onclick='return false;'>Prev</a></li>");
		}
		for (int i = startPageNum; i < endPageNum; i++) {
			if (i == pageNum) {
				_sb.append("<li class='active'><a href='#' onclick='return false;'>");
				_sb.append(i + 1);
				_sb.append("</a></li>");
			} else {
				_sb.append("<li><a href='#' onclick=\"");
				_sb.append(jsFunctionName);
				_sb.append("('");
				_sb.append(i);
				_sb.append("');return false;\">");
				_sb.append(i + 1);
				_sb.append("</a></li>");
			}
		}
		if (endPageNum < totalPageCnt) {
			_sb.append("<li><a href='#' onclick=\"");
			_sb.append(jsFunctionName);
			_sb.append("('");
			_sb.append(endPageNum);
			_sb.append("');return false;\">Next</a></li>");
		} else {
			_sb.append("<li class='disabled'><a href='#' onclick='return false;'>Next</a></li>");
		}
		_sb.append("</ul>");
		return _sb.toString();
	}

	public void setStartRowNo(int startRowNo) {
		this.startRowNum = startRowNo;
	}

	public void setEndRowNo(int endRowNo) {
		this.endRowNum = endRowNo;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		if (pageNum < 0) {
			return;
		}
		this.pageNum = pageNum;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getStartRowNum() {
		calc();
		return startRowNum;
	}

	public int getEndRowNum() {
		calc();
		return endRowNum;
	}

	public int getTotalRowCount() {
		return totalRowCount;
	}

	public void setTotalRowCount(int totalRowCount) {
		this.totalRowCount = totalRowCount;
	}

	public void setStartRowNum(int startRowNum) {
		this.startRowNum = startRowNum;
	}

	public void setEndRowNum(int endRowNum) {
		this.endRowNum = endRowNum;
	}

	/** 검색할 카테고리 (예: search=title, name, content) */
	@Setter
	protected String search;
	abstract public String getSearch();
	/** 검색어 */
	@Setter @Getter
	protected String findword;
}

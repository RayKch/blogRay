package ray.util;

public class Const {
	public final static String VERSION = "2.7.4";

	/** 경고창만 뜨는 페이지 */
	public final static String ALERT_PAGE = "/_proc/alert.jsp";
	/** 경고창이 뜬 후에 부모창을 리다이렉트 시키는 페이지 */
	public final static String REDIRECT_PAGE = "/_proc/parent_redirect.jsp";
	/**  경고창이 뜬 후에 뒤로 되돌아가는 페이지 */
	public final static String BACK_PAGE = "/_proc/back_page.jsp";
	/** ajax호출용 빈 페이지 */
	public final static String BLANK_PAGE = "/_proc/blank.jsp";
	/** ajax호출 후에 메시지를 전달하는 페이지 */
	public final static String AJAX_PAGE = "/_proc/ajax_message.jsp";

	public final static String UPLOAD_LOCAL_PATH = "D:\\dev\\workspace\\upload";
	public final static String UPLOAD_REAL_PATH = "/home/ray/web/upload";

	/** (등급) 최고 관리자 */
	public final static int GRADE_SUPER_ADMIN = 1;

	/** (등급) 작업자 */
	public final static int GRADE_WORKER = 9;

	/** (등급) 미승인 */
	public final static int GRADE_NO_APPROVAL = 10;

	/** Aria 암호화 키 (반드시 32byte가 되어야만 한다) */
	public final static byte[] ARIA_KEY = {
			(byte) -20,
			(byte) -105,
			(byte) -112,
			(byte) -20,
			(byte) -118,
			(byte) -92,
			(byte) -20,
			(byte) -105,
			(byte) -96,
			(byte) -19,
			(byte) -108,
			(byte) -124,
			(byte) -21,
			(byte) -95,
			(byte) -100,
			(byte) 32,
			(byte) -21,
			(byte) -77,
			(byte) -76,
			(byte) -20,
			(byte) -107,
			(byte) -120,
			(byte) 32,
			(byte) -20,
			(byte) -105,
			(byte) -112,
			(byte) -20,
			(byte) -118,
			(byte) -92,
			(byte) -21,
			(byte) -117,
			(byte) -91
	};
}

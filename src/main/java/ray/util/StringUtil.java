package ray.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtil {

	/**
	 * 유효한 아이디인가?
	 * @param id
	 * @return
	 */
	public static boolean isValidFormId(String id) {
		return id != null && id.toLowerCase().matches("^[a-z][a-z0-9\\.\\-_]{3,11}$");
	}

	/**
	 * 숫자인가?
	 * @param str
	 * @return
	 */
	public static boolean isNum(String str) {
		return str != null && str.matches("^[\\d]+$");
	}

	/**
	 * email인지 아닌지 체크하는 메서드
	 *
	 * @author 강사무엘
	 * @since 2013-01-14
	 */
	public static boolean isEmail(String email) {
		return email != null && email.matches("^[\\w]+([\\w][-]?[.]?)*@[\\w]*([\\w][-]?[.]?)+[\\w]+$");
	}

	public static String clearXSS(String str) {
		if(str == null) {
			return "";
		}

		str = str.trim();
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&#34;");
		str = str.replaceAll("'", "&#39;");
		str = str.replaceAll("\\(", "&#40;");
		str = str.replaceAll("\\)", "&#41;");
		return str;
	}

	public static String restoreClearXSS(String str) {
		if(str == null) {
			return "";
		}

		str = str.trim();
		str = str.replaceAll("&#35;", "#");
		str = str.replaceAll("&lt;", "<");
		str = str.replaceAll("&gt;", ">");
		str = str.replaceAll("&#34;", "\\\"");
		str = str.replaceAll("&#39;", "'");
		str = str.replaceAll("&#40;", "\\(");
		str = str.replaceAll("&#41;", "\\)");
		return str;
	}

	/**
	 * 입력값 널 필터링
	 * @param str
	 * @return
	 */
	public static String mapper(String str) {
		return (str == null) ? "" : str.trim();
	}

	/**
	 * 비밀번호 해시화
	 * (해시는 암호화시키는 것이 아니다)
	 *
	 * @param password
	 * @return
	 * @throws java.security.NoSuchAlgorithmException
	 */
	public static String hashPassword(String password) throws NoSuchAlgorithmException {
		// MD5 인스턴스
		MessageDigest md = MessageDigest.getInstance("MD5");

		// 원본 데이터를 바이트 배열로 바꾸어서
		byte[] bytData = (password).getBytes();

		// 데이터를 MessageDigest에 업데이트 하고
		md.update(bytData);

		//암호화
		byte[] digest = md.digest();

		StringBuilder s = new StringBuilder();
		for (byte aDigest : digest) {
			s.append(Integer.toString((aDigest & 0xf0) >> 4, 16));
			s.append(Integer.toString(aDigest & 0x0f, 16));
		}
		return s.toString();
	}


	/**
	 * 라이센스 유효성 체크
	 * 라이센스 구성 : 맥어드레스^작업단말수^대상장비수
	 * @param
	 * @return
	 */
	public static boolean isValidLicense(String license) {
		if(license == null) {
			return false;
		}
		return license.matches("^([0-9a-fA-F]{2}:){5}([0-9a-fA-F]{2}\\^\\d+\\^\\d+)$");
	}

	/**
	 * SHA-2 암호화 처리
	 * @param str
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String encryptSha2(String str) throws NoSuchAlgorithmException {
		StringBuffer sb = new StringBuffer();
		MessageDigest sh = MessageDigest.getInstance("SHA-256");
		sh.update(str.getBytes());
		byte byteData[] = sh.digest();
		for(int i = 0 ; i < byteData.length ; i++){
			sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}
}

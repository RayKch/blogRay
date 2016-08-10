package ray.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PasswordValidator {
//	private ConfigVo cvo;
//	private ConfigService configService;
//
//	public PasswordValidator(ConfigVo cvo, ConfigService configService) {
//		this.cvo = cvo;
//		this.configService = configService;
//	}
//
//	// 비밀번호의 문자열을 검증한다
//	public String validate(MemberVo mvo) {
//		if(cvo == null || mvo == null) {
//			return "데이터베이스와 통신할 수 없었거나 문제가 발생하여 정상적인 처리가 불가능합니다";
//		}
//
//		// 최소 길이 제한
//		if(mvo.getPassword().length() < cvo.getMinLength()) {
//			return "비밀번호는 "+cvo.getMinLength()+"자 보다 길어야 합니다";
//		}
//		// 최대 길이 제한
//		if(mvo.getPassword().length() > cvo.getMaxLength()) {
//			return "비밀번호는 "+cvo.getMaxLength()+"자 보다 더 입력하실 수 없습니다";
//		}
//		// 패턴 분석
//		if(cvo.getPattern() != null && !"".equals(cvo.getPattern())) {
//			// 아이디와 동일한 비밀번호 금지
//			if(cvo.getPattern().indexOf("strictMatch") >= 0) {
//				if(mvo.getPassword().indexOf(mvo.getId()) >= 0) {
//					return "비밀번호에 아이디와 동일한 문자열을 사용하실 수 없습니다";
//				}
//			}
//
//			// 알려진 비밀번호 금지
//			if(cvo.getPattern().indexOf("knownPassword") >= 0) {
//				if(configService.matchWellKnownPassword(mvo.getPassword())) {
//					return "널리 알려진 형태의 비밀번호는 등록할 수 없습니다";
//				}
//			}
//
//			// 세번 이상 동일한 문자열 사용 금지
//			if(cvo.getPattern().indexOf("threeTimes") >= 0) {
//				if(mvo.getPassword().matches(".*(.)\\1{2}.*")) {
//					return "동일한 문자열을 3회 이상 반복하여 사용할 수 없습니다";
//				}
//			}
//
//			// 대문자를 하나 이상 포함
//			if(cvo.getPattern().indexOf("capitalChar") >= 0) {
//				if(!mvo.getPassword().matches(".*[A-Z]+.*")) {
//					return "반드시 대문자가 하나 이상 포함되어야 합니다";
//				}
//			}
//
//			// 숫자를 하나 이상 포함
//			if(cvo.getPattern().indexOf("numericInclude") >= 0) {
//				if(!mvo.getPassword().matches(".*[0-9]+.*")) {
//					return "반드시 숫자가 하나 이상 포함되어야 합니다";
//				}
//			}
//
//			// 특수문자를 하나 이상 포함
//			if(cvo.getPattern().indexOf("specialInclude") >= 0) {
//				if(!mvo.getPassword().matches(".*[!@#\\$%\\^&\\*\\(\\)_\\.\\|\\+\\\\=\\-\\{}\\?<>,\\[\\]:'\"]+.*")) {
//					return "반드시 특수문자가 하나 이상 포함되어야 합니다";
//				}
//			}
//
//			// 숫자만으로 생성 금지
//			if(cvo.getPattern().indexOf("numericExclude") >= 0) {
//				if(mvo.getPassword().matches("[\\d]*")) {
//					return "숫자만 사용하여 비밀번호를 만드실 수 없습니다";
//				}
//			}
//		}
//		return null;
//	}
}

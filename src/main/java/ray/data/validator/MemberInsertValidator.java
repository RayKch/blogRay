package ray.data.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


/**
 * MemberVo가 insert하기 위해서 필요한 검증 로직 수행
 */
//public class MemberInsertValidator implements Validator {
//	@Override
//	public boolean supports(Class<?> clazz) {
//		return MemberVo.class.isAssignableFrom(clazz);
//	}
//
//	@Override
//	public void validate(Object target, Errors errors) {
//		MemberVo vo = (MemberVo) target;
//
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "null", "아이디가 입력되지 않았습니다");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "null", "비밀번호가 입력되지 않았습니다");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "null", "이름이 입력되지 않았습니다");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gradeCode", "null", "등급이 입력되지 않았습니다");
//
//		if(vo.getId() != null && !vo.getId().matches("^[a-zA-Z][a-zA-Z0-9\\.\\-_]{3,11}$")) {
//			errors.rejectValue("id", "match", "아이디가 유효하지 않습니다. 아이디는 영어로 시작하고 숫자와 특수문자(.-_)를 사용할 수 있습니다");
//		}
//		if(vo.getId() != null && (vo.getId().length() < 4 || vo.getId().length() > 12)) {
//			errors.rejectValue("id", "size", "아이디는 4~12자리까지 입력하실 수 있습니다");
//		}
//		if(vo.getEmpNo() != null && vo.getEmpNo().length() > 20) {
//			errors.rejectValue("empNo", "size", "사원번호는 20글자 이상 입력하실 수 없습니다");
//		}
//		if(vo.getName() != null && (vo.getName().length() < 2 || vo.getName().length() > 20)) {
//			errors.rejectValue("name", "size", "이름은 1글자 또는 20글자 이상 입력하실 수 없습니다");
//		}
//		if(vo.getCompany() != null && (vo.getCompany().length() > 20)) {
//			errors.rejectValue("company", "size", "소속은 20글자 이상 입력하실 수 없습니다");
//		}
//		if(vo.getPosition() != null && (vo.getPosition().length() > 20)) {
//			errors.rejectValue("position", "size", "직급은 20글자 이상 입력하실 수 없습니다");
//		}
//		if(vo.getTel() != null && !"".equals(vo.getTel()) && !vo.getTel().matches("^[0-9]{2,4}[\\-][0-9]{3,4}[\\-][0-9]{3,4}$")) {
//			errors.rejectValue("tel", "match", "올바른 전화번호가 아닙니다 (02-1234-5678과 같이 입력하여 주세요)");
//		}
//		if(vo.getCell() != null && !"".equals(vo.getCell()) && !vo.getCell().matches("^[0-9]{2,4}[\\-][0-9]{3,4}[\\-][0-9]{3,4}$")) {
//			errors.rejectValue("cell", "match", "올바른 휴대폰 번호가 아닙니다 (010-1234-5678과 같이 입력하여 주세요)");
//		}
//
//		// 비밀번호는 저장된 설정에 따라 별도의 validation을 거친다
//	}
//}

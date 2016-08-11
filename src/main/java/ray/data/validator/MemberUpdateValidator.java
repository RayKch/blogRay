package ray.data.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import smpro.data.MemberVo;

/**
 * MemberVo가 update하기 위해서 필요한 검증 로직 수행
 *
 * Created by erobeat on 2014. 4. 24.
 */
public class MemberUpdateValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return MemberVo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		MemberVo vo = (MemberVo) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "seq", "null", "비정상적인 접근입니다");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "null", "이름이 입력되지 않았습니다");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gradeCode", "null", "등급이 입력되지 않았습니다");

		if(vo.getSeq() == 0) {
			errors.rejectValue("seq", "null", "비정상적인 접근입니다");
		}
		if(vo.getEmpNo() != null && vo.getEmpNo().length() > 20) {
			errors.rejectValue("empNo", "size", "사원번호는 20글자 이상 입력하실 수 없습니다");
		}
		if(vo.getName() != null && (vo.getName().length() < 2 || vo.getName().length() > 20)) {
			errors.rejectValue("name", "size", "이름은 20글자 이상 입력하실 수 없습니다");
		}
		if(vo.getCompany() != null && (vo.getCompany().length() > 20)) {
			errors.rejectValue("company", "size", "소속은 20글자 이상 입력하실 수 없습니다");
		}
		if(vo.getPosition() != null && (vo.getPosition().length() > 20)) {
			errors.rejectValue("position", "size", "직급은 20글자 이상 입력하실 수 없습니다");
		}
		if(vo.getTel() != null && !"".equals(vo.getTel()) && !vo.getTel().matches("^[0-9]{2,4}[\\-][0-9]{3,4}[\\-][0-9]{3,4}$")) {
			errors.rejectValue("tel", "match", "올바른 전화번호가 아닙니다 (02-1234-5678과 같이 입력하여 주세요)");
		}
		if(vo.getCell() != null && !"".equals(vo.getCell()) && !vo.getCell().matches("^[0-9]{2,4}[\\-][0-9]{3,4}[\\-][0-9]{3,4}$")) {
			errors.rejectValue("cell", "match", "올바른 휴대폰 번호가 아닙니다 (010-1234-5678과 같이 입력하여 주세요)");
		}
	}
}

package ray.data.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ray.data.MemberVo;
import ray.data.param.LoginParamVo;

public class MemberLoginValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return LoginParamVo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LoginParamVo vo = (LoginParamVo) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "null", "아이디가 입력되지 않았습니다");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "null", "비밀번호가 입력되지 않았습니다");

		if(vo.getId() != null && !vo.getId().matches("^[a-zA-Z][a-zA-Z0-9\\.\\-_]{3,11}$")) {
			errors.rejectValue("id", "match", "아이디가 유효하지 않습니다. 아이디는 영어로 시작하고 숫자와 특수문자(.-_)를 사용할 수 있습니다");
		}
		if(vo.getId() != null && (vo.getId().length() < 4 || vo.getId().length() > 12)) {
			errors.rejectValue("id", "size", "아이디는 4~12자리까지 입력하실 수 있습니다");
		}
	}
}

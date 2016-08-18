package ray.data.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ray.data.param.BoardParamVo;

/**
 * Created by BELL on 2016-08-18.
 */
public class BoardInsertValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return BoardParamVo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		BoardParamVo vo = (BoardParamVo) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "categorySeq", "", "카테고리가 선택되지 않았습니다");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "", "제목이 입력되지 않았습니다");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "", "내용이 입력되지 않았습니다");

		if(vo.getTitle() != null && vo.getTitle().length() > 100) {
			errors.rejectValue("title", "size", "제목은 100글자 이상 입력하실 수 없습니다");
		}
	}
}

package ray.data.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ray.data.param.CategoryParamVo;

/**
 * Created by ChanPong on 2016-08-15.
 */
public class CategoryInsertValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return CategoryParamVo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CategoryParamVo vo = (CategoryParamVo) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "null", "카테고리명이 입력되지 않았습니다");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "null", "카테고리설명이 입력되지 않았습니다");

		if(vo.getTitle() != null && vo.getTitle().length() > 100) {
			errors.rejectValue("title", "size", "카테고리명는 100글자 이상 입력하실 수 없습니다");
		}
		if(vo.getDescription() != null && vo.getDescription().length() > 100) {
			errors.rejectValue("description", "size", "카테고리설명은 100글자 이상 입력하실 수 없습니다");
		}
	}
}
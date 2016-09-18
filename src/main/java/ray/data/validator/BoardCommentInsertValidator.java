package ray.data.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ray.data.param.BoardParamVo;

/**
 * Created by BELL on 2016-08-18.
 */
public class BoardCommentInsertValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return BoardParamVo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		BoardParamVo vo = (BoardParamVo) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "boardSeq", "", "게시판 번호가 존재하지 않습니다");
		if(vo.getMemberSeq() == null) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nickname", "", "닉네임이 입력되지 않았습니다");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "비밀번호가 입력되지 않았습니다");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "", "댓글 내용이 입력되지 않았습니다");
	}
}

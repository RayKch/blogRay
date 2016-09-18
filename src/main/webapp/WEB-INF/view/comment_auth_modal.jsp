<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="modal fade" id="authModal" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">댓글(답글) 비밀번호 확인</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label for="commentPassword" class="control-label">비밀번호</label>
					<input type="password" class="form-control" id="commentPassword" name="password" placeholder="비밀번호를 입력하세요" alt="비밀번호">
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				<button type="button" id="authSubmitBtn" class="btn btn-primary">삭제</button>
			</div>
		</div>
	</div>
</div>
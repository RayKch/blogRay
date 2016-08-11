<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">로그인</h4>
			</div>
			<div class="modal-body">
				<form>
					<div class="form-group">
						<label for="id" class="control-label">이메일</label>
						<input type="text" class="form-control" id="id" name="id" placeholder="이메일을 입력하세요">
					</div>
					<div class="form-group">
						<label for="password" class="control-label">비밀번호</label>
						<input type="password" class="form-control" id="password" name="password" placeholder="비밀번호를 입력하세요">
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				<button type="button" class="btn btn-primary">로그인</button>
			</div>
		</div>
	</div>
</div>
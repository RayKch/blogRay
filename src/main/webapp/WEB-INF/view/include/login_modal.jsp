<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">로그인</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label for="loginId" class="control-label">아이디(이메일)</label>
					<input type="text" class="form-control" id="loginId" name="id" placeholder="아이디(이메일)를 입력하세요" alt="아이디">
				</div>
				<div class="form-group">
					<label for="loginPassword" class="control-label">비밀번호</label>
					<input type="password" class="form-control" id="loginPassword" name="password" placeholder="비밀번호를 입력하세요" alt="비밀번호">
				</div>
				<div class="checkbox">
					<label class="pull-right" style="font-size:14px;"><input type="checkbox" id="rememberId"> 아이디 저장</label>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				<button type="button" id="loginSubmitBtn" class="btn btn-primary">로그인</button>
			</div>
		</div>
	</div>
</div>
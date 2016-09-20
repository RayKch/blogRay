<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<script id="commentFormTemplate" type="text/html">
	<div class="comment-form-wrapper">
		<c:if test="${sessionScope.loginSeq eq null}">
			<div class="form-group">
				<label class="col-sm-2 control-label">닉네임</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="nickname" alt="닉네임">
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label">비밀번호</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" name="password" alt="비밀번호">
				</div>
			</div>
		</c:if>
		<div class="form-group">
			<label class="col-sm-2 control-label">내용</label>
			<div class="col-sm-10">
				<textarea class="form-control" name="content" rows="5" alt="내용"></textarea>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="button" class="btn btn-success btn-circle text-uppercase pull-right submit-comment" onclick="BoardCommentSubmitUtil.submit(BoardCommentSubmitUtil.proc, 'insert', this)"><span class="glyphicon glyphicon-send"></span> 등록</button>
			</div>
		</div>
	</div>
</script>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="modal fade" id="updateCategoryModal" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">카테고리 수정</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label for="updateTitle" class="control-label">카테고리명</label>
					<input type="text" class="form-control" id="updateTitle" data-name="title" placeholder="카테고리명을 입력하세요" alt="카테고리명">
				</div>
				<div class="form-group">
					<label for="updateDescription" class="control-label">카테고리설명</label>
					<input type="text" class="form-control" id="updateDescription" data-name="description" placeholder="카테고리설명을 입력하세요" alt="카테고리설명">
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				<button type="button" id="categoryUpdateBtn" class="btn btn-primary">수정하기</button>
			</div>
		</div>
	</div>
</div>
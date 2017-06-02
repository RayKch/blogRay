<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="modal fade" id="submitModal" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 id="modalTitle" class="modal-title"></h4>
			</div>
			<div class="upload-category-modal modal-body">
				<div class="form-group">
					<label>유형</label>
					<div style="font-size:14px;">
						<label for="typeCodeL" class="radio-inline">
							<input type="radio" name="typeCode" id="typeCodeL" value="L"> 게시글형
						</label>
						<label for="typeCodeC" class="radio-inline">
							<input type="radio" name="typeCode" id="typeCodeC" value="C"> 댓글형
						</label>
						<label for="typeCodeG" class="radio-inline">
							<input type="radio" name="typeCode" id="typeCodeG" value="G"> 그룹
						</label>
					</div>
				</div>
				<div class="form-group">
					<label for="title" class="control-label">제목</label>
					<input type="text" class="form-control" id="title" data-name="title" placeholder="제목을 입력하세요" alt="제목">
				</div>
				<div class="form-group">
					<label for="description" class="control-label">설명</label>
					<input type="text" class="form-control" id="description" data-name="description" placeholder="설명을 입력하세요" alt="설명">
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" id="closeBtn" class="btn btn-default" data-dismiss="modal">닫기</button>
				<button type="button" id="saveBtn" class="btn btn-primary">저장하기</button>
			</div>
		</div>
	</div>
</div>
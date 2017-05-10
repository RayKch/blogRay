<%--
  Created by IntelliJ IDEA.
  User: BELL
  Date: 2016-08-10
  Time: 오전 9:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@ include file="/WEB-INF/view/include/header.jsp" %>
	<link href="${pageContext.request.contextPath}/styles/category/category.css" rel="stylesheet">
</head>

<body>
<div id="wrapper">
	<%@ include file="/WEB-INF/view/include/side_menu.jsp" %>
	<div class="scrollable-wrapper">
		<%@ include file="/WEB-INF/view/include/header_hero.jsp" %>
		<div class="container">
			<div class="row">
				<div class="post-preview list-top-margin list-header-wrap">
					<h2 class="text-center">카테고리 관리</h2>
					<hr class="list-hr">
					<div class="clearfix"></div>
				</div>
				<div class="area-wrapper">
					<div class="area-left">
						<label>목록</label>
						<div class="alert alert-info alert-wrap">
							카테고리명을 Drag & Drop으로 순서를 변경하고 <button type="button" class="btn btn-success btn-sm" onclick="CategoryUtil.saveOrderNo('#tbodyList')">순서 변경</button>
							버튼을 클릭하여 변경정보가 변경됩니다. <br/><br/>
							<ul class="info-text">
								<li>순서변경은 PC에서만 가능합니다.</li>
								<li>카테고리 삭제시 해당 카테고리에 등록된 게시물도 모두 삭제됩니다.</li>
								<li>신규 등록시 선택된 카테고리를 다시 클릭하여 해제하거나 폼리셋버튼을 클릭하세요.</li>
							</ul>
						</div>
						<div style="border:1px solid #ddd; border-radius: 10px">
							<table class="category-list-table">
								<colgroup>
									<col width="*"/>
									<col width="15%"/>
								</colgroup>
								<script id="tbodyTemplate" type="text/html">
									<tr data-seq="<%="${seq}"%>">
										<td class="tr-title text-left select" onclick="CategoryUtil.select(this)">
											<%="${title}"%>
										</td>
										<td class="text-center">
											<button type="button" class="btn btn-danger btn-sm pull-right" style="margin-right: 10px" onclick="CategoryDeleteUtil.proc('<%="${seq}"%>');">삭제</button>
										</td>
									</tr>
								</script>
								<tbody id="tbodyList" data-seq="0">
									<tr><td class="text-center" colspan="2"><img src="/image/common/ajaxloader.gif"/></td></tr>
								</tbody>
							</table>
						</div>
						<div class="text-center" style="margin-top: 25px;"><div id="divPaging"></div></div>
					</div>
					<div id="mainForm" class="area-right form-horizontal">
						<div class="form-group">
							<label class="col-sm-2 control-label">유형</label>
							<div class="col-sm-10" style="font-size:14px;">
								<label for="typeCodeL" class="radio-inline">
									<input type="radio" name="typeCode" id="typeCodeL" value="L"> 게시글형
								</label>
								<label for="typeCodeC" class="radio-inline">
									<input type="radio" name="typeCode" id="typeCodeC" value="C"> 댓글형
								</label>
							</div>
						</div>
						<div class="form-group">
							<label for="title" class="col-sm-2 text-right">제목</label>
							<div class="col-sm-10">
								<input type="text" class="form-control col-sm-10" id="title" data-name="title" placeholder="카테고리 제목을 입력하세요" alt="카테고리명">
							</div>
						</div>
						<div class="form-group">
							<label for="description" class="col-sm-2 text-right">설명</label>
							<div class="col-sm-10">
								<textarea class="form-control" id="description" data-name="description" placeholder="카테고리 설명을 입력하세요" alt="카테고리설명" rows="10"></textarea>
							</div>
						</div>
						<div class="text-center">
							<button type="button" id="saveBtn" class="btn btn-info btn-lg">저장하기</button>
							<button type="button" id="formResetBtn" class="btn btn-warning btn-lg">리셋하기</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="/WEB-INF/view/include/footer.jsp" %>
	</div>
</div>
<%--사용하지 않으나 주석처리--%>
<%--<%@ include file="/WEB-INF/view/category/update_modal.jsp" %>--%>
<%@ include file="/WEB-INF/view/category/update_orderno_progress_modal.jsp" %>
<script src="/scripts/plugin/jquery.tablednd.js"></script>
<script src="/scripts/category/category.js"></script>
</body>
</html>
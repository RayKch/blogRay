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
					<div class="alert alert-info alert-wrap">
						카테고리명을 Drag & Drop으로 순서를 변경하고 <button type="button" class="btn btn-primary btn-sm">순서 변경</button>
						버튼을 클릭하여 변경정보가 변경됩니다. <br/>
						<ul class="info-text">
							<li>순서변경은 PC에서만 가능합니다.</li>
							<li>카테고리/그룹 삭제시 해당 카테고리에 등록된 게시물도 모두 삭제됩니다.</li>
						</ul>
					</div>
					<div class="area-left">
						<%-- 카테고리 그룹 --%>
						<div>
							<label>그룹</label>
							<button type="button" class="btn btn-primary btn-sm pull-right" onclick="CategoryUtil.saveOrderNo('#tbodyGroupList')">순서 변경</button>
							<button type="button" class="btn btn-info btn-sm pull-right" style="margin-right: 5px" onclick="CategoryUtil.categoryInit('Group')">등록하기</button>
						</div>
						<div style="border:1px solid #ddd; border-radius: 10px">
							<table class="category-list-table">
								<colgroup>
									<col width="*"/>
									<col width="25%"/>
								</colgroup>
								<script id="tbodyGroupTemplate" type="text/html">
									<tr data-seq="<%="${seq}"%>">
										<td class="tr-title text-left select" data-seq="<%="${seq}"%>" onclick="CategoryUtil.select(this, 'Group')">
											<%="${title}"%>
										</td>
										<td class="text-center">
											<button type="button" class="btn btn-warning btn-sm" onclick="CategoryUtil.modalShow('<%="${seq}"%>', 'Group')">수정</button>
											<button type="button" class="btn btn-danger btn-sm" style="margin-right: 10px" onclick="CategoryDeleteUtil.proc('<%="${seq}"%>', 'Group');">삭제</button>
										</td>
									</tr>
								</script>
								<tbody id="tbodyGroupList" data-action-type="Group" data-seq="0">
								<tr><td class="text-center" colspan="2"><img src="/image/common/ajaxloader.gif"/></td></tr>
								</tbody>
							</table>
						</div>
						<button type="button" class="btn btn-success btn-lg btn-block" onclick="CategoryRenderUtil.renderAddArticle('Group')" style="margin: 10px 0 40px 0">
							<i class="fa fa-plus-circle" aria-hidden="true"></i>&nbsp;더보기
						</button>
						<%-- //카테고리 그룹 --%>
					</div>
					<div class="area-right">
						<%-- 카테고리 리스트 --%>
						<div>
							<label>카테고리</label>
							<button type="button" class="btn btn-primary btn-sm pull-right" onclick="CategoryUtil.saveOrderNo('#tbodyCategoryList')">순서 변경</button>
							<button type="button" class="btn btn-info btn-sm pull-right" style="margin-right: 5px" onclick="CategoryUtil.categoryInit('Category')">등록하기</button>
						</div>
						<div style="border:1px solid #ddd; border-radius: 10px">
							<table class="category-list-table">
								<colgroup>
									<col width="*"/>
									<col width="25%"/>
								</colgroup>
								<script id="tbodyCategoryTemplate" type="text/html">
									<tr data-seq="<%="${seq}"%>">
										<td class="tr-title text-left select" onclick="CategoryUtil.select(this, 'Category')">
											<%="${title}"%>
										</td>
										<td class="text-center">
											<button type="button" class="btn btn-warning btn-sm" onclick="CategoryUtil.modalShow('<%="${seq}"%>', 'Category')">수정</button>
											<button type="button" class="btn btn-danger btn-sm" style="margin-right: 10px" onclick="CategoryDeleteUtil.proc('<%="${seq}"%>', 'Category');">삭제</button>
										</td>
									</tr>
								</script>
								<tbody id="tbodyCategoryList" data-action-type="Category" data-seq="0">
								<tr><td class="text-center" colspan="2"><img src="/image/common/ajaxloader.gif"/></td></tr>
								</tbody>
							</table>
						</div>
						<button type="button" class="btn btn-success btn-lg btn-block" onclick="CategoryRenderUtil.renderAddArticle('Category')" style="margin-top: 10px">
							<i class="fa fa-plus-circle" aria-hidden="true"></i>&nbsp;더보기
						</button>
						<%-- //카테고리 리스트 --%>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="/WEB-INF/view/include/footer.jsp" %>
	</div>
</div>
<%--사용하지 않으나 주석처리--%>
<%@ include file="/WEB-INF/view/category/update_modal.jsp" %>
<%@ include file="/WEB-INF/view/category/update_orderno_progress_modal.jsp" %>
<script src="/scripts/plugin/jquery.tablednd.js"></script>
<script src="/scripts/category/category.js"></script>
</body>
</html>
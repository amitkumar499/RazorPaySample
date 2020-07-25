<%@ include file="/tld_includes.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html ng-app="aggregator">
<meta charset="utf-8">
<title>Aggregator Admin Panel</title>

<link rel="stylesheet" type="text/css" href="/static/css/jquery.blockUI.overrides.css" />
<link rel="stylesheet" type="text/css" href="/static/css/bootstrap_3.3.5.min.css" />
<link rel="stylesheet" type="text/css" href="/static/css/angular-ui-tree/angular-ui-tree.min.css" />
<link rel="stylesheet" href="/static/css/css_spinner_dots.css" type="text/css">
<link rel="stylesheet" type="text/css" href="/static/css/cacheManagerStyling.css" />
<link rel="stylesheet" type="text/css" href="/static/css/main-styles.css" />
<!--	include js files	  -->

<script	src="/static/lib/jquery/jquery_1.10.1.min.js"></script>
<script src="/static/lib/blockUI/jquery.blockUI.min.js"></script>
<script src="/static/lib/angular/angular_1.4.7.js"></script>
<script	src="/static/lib/bootstrap/ui-bootstrap-tpls-0.14.3.js"></script>
<script type="text/javascript" src="/static/lib/angular/angular-route.min.js"></script>
<script type="text/javascript" src="/static/lib/angular-ui-tree/angular-ui-tree.min.js"></script>
<script	src="/static/lib/bootstrap/bootstrap_3.3.5.min.js"></script>
<script type="text/javascript" src="/static/js/angular-drag-and-drop-lists.js"></script>
<script	src="/static/lib/angular/angular-animate_1.4.7.js"></script>

<script type="text/javascript" src="/static/js/app.js"></script>
<script type="text/javascript" src="/static/js/controllers/controllers.js"></script>
<script type="text/javascript" src="/static/js/pieNodeController.js"></script>
<script type="text/javascript" src="/static/js/services.js"></script>
<script type="text/javascript" src="/static/lib/angular/ngStorage.js"></script>

<!-- include controllers  -->
<script type="text/javascript" src="/static/js/controllers/addPermissionFormController.js"></script>
<script type="text/javascript" src="/static/js/controllers/cacheAndServerManagerController.js"></script>
<script type="text/javascript" src="/static/js/controllers/ccPropertyFormController.js"></script>
<script type="text/javascript" src="/static/js/controllers/ccPropertyListController.js"></script>
<script type="text/javascript" src="/static/js/controllers/customMessageFormController.js"></script>
<script type="text/javascript" src="/static/js/controllers/customMessageListController.js"></script>
<script type="text/javascript" src="/static/js/controllers/deleteConfirmationController.js"></script>
<script type="text/javascript" src="/static/js/controllers/emailTemplateFormController.js"></script>
<script type="text/javascript" src="/static/js/controllers/emailTemplateListController.js"></script>
<script type="text/javascript" src="/static/js/controllers/emailToggleStatusConfirmationController.js"></script>
<script type="text/javascript" src="/static/js/controllers/errorPageController.js"></script>
<script type="text/javascript" src="/static/js/controllers/filterByTypeController.js"></script>
<script type="text/javascript" src="/static/js/controllers/getRolesAndPermissionsController.js"></script>
<script type="text/javascript" src="/static/js/controllers/ivrRefundReasonMappingFormController.js"></script>
<script type="text/javascript" src="/static/js/controllers/ivrRefundReasonMappingListController.js"></script>
<script type="text/javascript" src="/static/js/controllers/loadPermissionsController.js"></script>
<script type="text/javascript" src="/static/js/controllers/manageServersFormController.js"></script>
<script type="text/javascript" src="/static/js/controllers/manageAgentsFormController.js"></script>
<script type="text/javascript" src="/static/js/controllers/refundReasonFormController.js"></script>
<script type="text/javascript" src="/static/js/controllers/refundReasonListController.js"></script>
<script type="text/javascript" src="/static/js/controllers/smsTemplateFormController.js"></script>
<script type="text/javascript" src="/static/js/controllers/smsTemplateListController.js"></script>
<script type="text/javascript" src="/static/js/controllers/smsToggleStatusConfirmationController.js"></script>
<script type="text/javascript" src="/static/js/controllers/toggleStatusConfirmationController.js"></script>
<script type="text/javascript" src="/static/js/controllers/aggregatorClientValidateController.js"></script>
<script type="text/javascript" src="/static/js/controllers/otsLabelFormController.js"></script>
<script type="text/javascript" src="/static/js/controllers/otsLabelListController.js"></script>
<script type="text/javascript" src="/static/js/controllers/sfAttributeListController.js"></script>
<script type="text/javascript" src="/static/js/controllers/sfAttributeFormController.js"></script>
</head>

<body>
	<sec:authentication property="principal" var="user" />
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="navbar-content">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${path.http}/admin/#/"
					title="Agg-Admin" rel="home"> <img src="/static/img/logo.png"
					width="120" height="30" alt="snapdeal"></img>
				</a>
			</div>

			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="${path.http}/admin/#/">Home</a></li>
					
					<sec:authorize access="hasAuthority('CC_PROPERTY_READ')">
					<li><a href="#/customMessage">Custom Messages</a></li>							
					</sec:authorize>
					
					<li class="dropdown"><a href="${path.http}/admin/#/"
						class="dropdown-toggle" data-toggle="dropdown">Templates <i
							class="glyphicon glyphicon-menu-down"></i></a>
						<ul class="dropdown-menu">
							<sec:authorize access="hasAuthority('ACKNOWLEDGE_EMAIL_READ')">
								<li><a href="#/emailTemplate">Email Templates</a></li>
							</sec:authorize>
							<sec:authorize access="hasAuthority('ACKNOWLEDGE_SMS_READ')">
								<li><a href="#/smsTemplate">SMS Templates</a></li>
							</sec:authorize>
							<sec:authorize access="hasAuthority('REFUND_REASON_READ')">
								<li><a href="#/refundReason">Refund Reasons</a></li>
							</sec:authorize>
						</ul></li>
						
					<li class="dropdown"><a href="${path.http}/admin/#/"
						class="dropdown-toggle" data-toggle="dropdown">Manage <i
							class="glyphicon glyphicon-menu-down"></i></a>
						<ul class="dropdown-menu">
							<sec:authorize access="hasAuthority('CC_PROPERTY_READ')">
								<li><a href="#/ccProperty">Admin Property</a></li>
							</sec:authorize>
							<sec:authorize access="hasAuthority('MANAGE_PERMISSION')">
								<li><a href="#/permissions">Manage Permissions</a></li>
							</sec:authorize>
							<sec:authorize access="hasAuthority('MANAGE_CACHE_READ')">
								<li><a href="#/manageCache">Servers and Caches</a></li>
							</sec:authorize>
						</ul></li>
					
					<sec:authorize access="hasAuthority('IVR_REFUND_MAP_READ')">
						<li><a href="#/ivrRefundReasonMapping">IVR Refund Reason
								Mapping</a></li>
					</sec:authorize>
					
					<sec:authorize access="hasAuthority('PIE_NODES_READ')">
						<li><a href="#/pieNode">Pie Nodes</a></li>
					</sec:authorize>
					
					<!-- TODO : Create a permission and Add authorization -->
					<li><a href="#/aggregatorClientValidate">Validate Client</a></li>
					
					<li class="log_out"><a href="${path.http }/logout"><b>Log
								Out</b></a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<section class="container main-content" ng-view></section>
	</div>

	<div class="footer-content">
		<div class="footer-text text-muted">Site Developed and
			Maintained By CX-Team@Snapdeal</div>
	</div>
</body>
<script type="text/javascript">
	$( ".nav li" ).on( "click", function () {
		$( ".nav li" ).removeClass( "active" );
		$( this ).addClass( "active" );
	} );

	$( 'ul.nav li.dropdown' ).hover( function () {
		$( this ).find( '.dropdown-menu' ).stop( true, true ).delay( 100 ).fadeIn( 300 );
	}, function () {
		$( this ).find( '.dropdown-menu' ).stop( true, true ).delay( 100 ).fadeOut( 300 );
	} );
</script>
</html>
<%@page import="Model.Author"%>
<%@page import="Action.AuthorDatabase" %>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%request.setCharacterEncoding("UTF-8");%>
<script src="http://s1.bdstatic.com/r/www/cache/global/js/BaiduHttps_20150714_zhanzhang.js"></script>
<script>
    function checkHttps () {
        BaiduHttps.useHttps();    
    };
    function baiduWithHttps (formname) {
        var data = BaiduHttps.useHttps();
        if (data.s === 0) {
            return true;
        }
        else {
            formname.action = 'https://www.baidu.com/baidu' + '?ssl_s=1&ssl_c' + data.ssl_code;
            return true;
        }
    };
</script>
<script type="text/javascript" src="scripts/jquery-1.8.2.js"></script>
<script type="text/javascript">
		var flag=new Boolean();
       
        function check_authorid(){
            $.ajax({
                type:"POST",
                url:"ajaxcheck!Check_authorids",
                data:$("#authorid").serializeArray()
            }).done(function(data,statusText){
            	var value = document.getElementById("authorid").value;
                if(data.result=="0"){
                  	 if (value == "")
                  	 {
                  		document.getElementById("authoridHint").innerHTML = "<font color='red'>" +
                  		"Error: The AuthorID is empty"+ "</font>";
                  		flag=false;
                       }
                  	 else
                  	{
                	document.getElementById("authoridHint").innerHTML="<font color='red'>" +
                	"Error: The AuthorID is not avaliable" + "</font>";
                	flag=false;
                	}
                }
                else{
                	var r = /^[0-9]+$/;
                	 if (r.test(value))
                	{
                	document.getElementById("authoridHint").innerHTML="<font color='green'>" +
                	"OK: The AuthorID is  avaliable" + "</font>";
                	flag=true;
                	}
                	 else
                	{
               		 document.getElementById("authoridHint").innerHTML="<font color='red'>" +
                    	"ERROR: The AuthorID is not a number" + "</font>";
                    flag=false;
                	}
                }
            }).fail(function(){
                alert("error!");
            })
        }
        function check_name(){
        	var value = document.getElementById("name").value;
            var ele = document.getElementById("nameHint");
            if (value == "") {
                ele.innerHTML = "<font color='red'>" + "Error: The Name is empty"+ "</font>";
                return false;
            } else {
                ele.innerHTML = "<font color='green'>" +"OK: The Name is checked"+ "</font>";
                return true;
            }
        }
        function check_country(){
        	var value = document.getElementById("country").value;
            var ele = document.getElementById("countryHint");
            if (value == "") {
                ele.innerHTML = "<font color='red'>" + "Error: The Country is empty"+ "</font>";
                return false;
            } else {
                ele.innerHTML = "<font color='green'>" +"OK: The Country is checked"+ "</font>";
                return true;
            }
        }
        function check_age(){
                var value = document.getElementById("age").value;
                var ele = document.getElementById("ageHint");
                if (value == "") {
                    ele.innerHTML = "<font color='red'>" + "Error: The Age is empty"+ "</font>";
                    return false;
                } else {
                    var r = /^[0-9]{1,3}$/;
                    if (r.test(value)) {
                        ele.innerHTML = "<font color='green'>" +"OK: The Age is checked"+ "</font>";
                       return true;
                    } else {
                        ele.innerHTML = "<font color='red'>" + 
                        "Error: The Age is not correct(must be a positive number)"+ "</font>";
                        return false;
                    }
                }
        }
        function check()
        {
        	var bool=new Boolean();
        	check_authorid();
			bool=flag&check_name()&check_age()&check_country();
			if(bool)
			{
		    	return true;
			}
			else
				return false;
        }
 </script>
<!DOCTYPE html>
<html lang="en">
<%
	String session_user = (String) session.getAttribute("user");
	if (session_user == null) 
		response.sendRedirect("login.jsp");
%>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>LMS Page</title>
  <!-- Bootstrap core CSS-->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!-- Custom fonts for this template-->
  <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <!-- Page level plugin CSS-->
  <link href="vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
  <!-- Custom styles for this template-->
  <link href="css/sb-admin.css" rel="stylesheet">
</head>

<body class="fixed-nav sticky-footer bg-dark" id="page-top">
  <!-- Navigation-->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
    <a class="navbar-brand" href="index.jsp">Home</a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Dashboard">
          <a class="nav-link" href="index.jsp">
            <i class="fa fa-fw fa-dashboard"></i>
            <span class="nav-link-text">StartPage</span>
          </a>
        </li>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Tables">
          <a class="nav-link" href="tables.jsp">
            <i class="fa fa-fw fa-table"></i>
            <span class="nav-link-text">ViewAll</span>
          </a>
        </li>
        
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Charts">
          <a class="nav-link" href="bookcreate.jsp">
            <i class="fa fa-pencil-square"></i>
            <span class="nav-link-text">AddBook</span>
          </a>
        </li>
        
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Charts">
          <a class="nav-link" href="authorcreate.jsp">
            <i class="fa fa-pencil-square-o"></i>
            <span class="nav-link-text">AddAuthor</span>
          </a>
        </li>
        
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Charts">
          <a class="nav-link" href="bookupdate.jsp">
            <i class="fa fa-refresh"></i>
            <span class="nav-link-text">UpdateBook</span>
          </a>
        </li>
        
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Charts">
          <a class="nav-link" href="booksearch.jsp">
            <i class="fa fa-search"></i>
            <span class="nav-link-text">SearchBook</span>
          </a>
        </li>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Components">
          <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseComponents" data-parent="#exampleAccordion">
            <i class="fa fa-fw fa-wrench"></i>
            <span class="nav-link-text">Components</span>
          </a>
          <ul class="sidenav-second-level collapse" id="collapseComponents">
            <li>
              <a href="calendar.jsp">Calendar</a>
            </li>
            <li>
              <a href="cards.html">Cards</a>
            </li>
          </ul>
        </li>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Link">
          <a class="nav-link" href="https://mail.qq.com">
            <i class="fa fa-fw fa-link"></i>
            <span class="nav-link-text">LeaveMessage</span>
          </a>
       </li>
      </ul>
      <ul class="navbar-nav sidenav-toggler">
        <li class="nav-item">
          <a class="nav-link text-center" id="sidenavToggler">
            <i class="fa fa-fw fa-angle-left"></i>
          </a>
        </li>
      </ul>
      <ul class="navbar-nav ml-auto">
        
        <li class="nav-item">
          <form class="form-inline my-2 my-lg-0 mr-lg-2" onsubmit="return baiduWithHttps(this)" action="http://www.baidu.com/baidu" target="_blank">
            <div class="input-group">
            <a href="https://www.baidu.com/" target="_blank">
		    </a>
              <input class="form-control" type="text" placeholder="Search for..." onfocus="checkHttps" name="word">
              <span class="input-group-btn">
                <button class="btn btn-primary" type="submit" >
                  <i class="fa fa-search"></i>
                </button>
              </span>
            </div>
          </form>
        </li>
        <li class="nav-item">
          <a class="nav-link" data-toggle="modal" data-target="#exampleModal">
            <i class="fa fa-fw fa-sign-out"></i>Logout</a>
        </li>
      </ul>
    </div>
  </nav>
  <div class="content-wrapper">
    <div class="container-fluid">
      <!-- Breadcrumbs-->
      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="#">Option</a>
        </li>
        <li class="breadcrumb-item active">AddAuthor</li>
      </ol>
      <!-- Example DataTables Card-->
      <div id="wrap">
		<div class="container">

			<p>Author Create</p>
			<%@page import="java.sql.*"%>
		
			
<body>
    <div id="Div1" >
        <!-- Start Sign Up Form -->
        <s:form action="authoraction!Author_create" id="Form" onSubmit="return check()">
        <h2>Create A New Author</h2>
        <fieldset>
        <table>
        <tr><td><label for="authorid">AuthorID</label></td>
        <td><input  id="authorid" class="form_element" type="text" name="author.authorid"   onblur="check_authorid();"/></td>
        <td><span class='availability_status' id="authoridHint"></span></td>
        </tr>
        <tr>
            <td>
                <label for="name">
                    Name</label>
                    </td>
                    <td>
                <input  id="name" class="form_element" type="text" name="author.name"   onblur="check_name();"/>
                </td>
                <td>
                <span class='availability_status' id="nameHint"></span>
                </td>
            </tr>
            <tr>
            <td>
                <label for="age">
                    Age</label>
                    </td>
                    <td>
                <input  id="age" class="form_element" type="text" name="author.age" onblur="check_age();" />
                </td>
                <td>
                <span class='availability_status' id="ageHint"></span>
                </td>
            </tr>
            <tr>
            <td>
                <label for="country">
                    Country</label>
                    </td>
                    <td>
                <input  id="country" class="form_element" type="text" name="author.country"  onblur="check_country();"/>
                </td>
                <td>
                <span class='availability_status' id="countryHint"></span>
                </td>
            </tr>
            <td></td>
            <td></td>
               <td> <input type="submit" id="submit" class="submit" value="Create"></td>
            </tr>
        </table>
        </fieldset>
        </s:form>
        <!-- End Sign Up Form -->
    </div>
</body>	
			
		</div>
		<!-- /container -->
	</div>
      
    </div>
    <!-- /.container-fluid-->
    <!-- /.content-wrapper-->
    <footer class="sticky-footer">
      <div class="container">
        <div class="text-center">
          <small>Copyright Â© 1264596728@qq.com 2017</small>
        </div>
      </div>
    </footer>
    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
      <i class="fa fa-angle-up"></i>
    </a>
    <!-- Logout Modal-->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">Ã</span>
            </button>
          </div>
          <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
          <div class="modal-footer">
            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
            <a class="btn btn-primary" href="logout.jsp">Logout</a>
          </div>
        </div>
      </div>
    </div>
    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/popper/popper.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
    <!-- Page level plugin JavaScript-->
    <script src="vendor/datatables/jquery.dataTables.js"></script>
    <script src="vendor/datatables/dataTables.bootstrap4.js"></script>
    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin.min.js"></script>
    <!-- Custom scripts for this page-->
    <script src="js/sb-admin-datatables.min.js"></script>
  </div>
</body>

</html>

<%@page import="Model.Book"%>
<%@page import="Action.BookDatabase" %>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
        function check_isbn(){
        	$.ajax({
                type:"POST",
                url:"ajaxcheck!Check_isbn",
                data:$("#isbn").serializeArray()
            }).done(function(data,statusText){
            	var value = document.getElementById("isbn").value;
            	var ele=document.getElementById("isbnHint");
                if(data.result=="1"){
                	ele.innerHTML="<font color='red'>" +
                	"Error: The ISBN is already exist" + "</font>";
                	flag=false;
                }
                else{
                	 if (value == "")
                	 {
                		 ele.innerHTML = "<font color='red'>" +
                		 "Error: The input is empty"+ "</font>";
                		 flag=false;
                     }
                	 else{
                		 var r =/^[0123456789-]+$/;
                		 if(r.test(value))
                		{
		                	ele.innerHTML="<font color='green'>" +
		                	"OK: The ISBN is avaliable" + "</font>";
		                	flag=true;
                		}
                		 else
                		{
                			 ele.innerHTML="<font color='red'>" +
 		                	"ERROR: The ISBN is not a number" + "</font>";
 		                	flag=false;
                		}
                	}
                }
            }).fail(function(){
                alert("error!");
            })
        }
        function check_title(){
        	var value = document.getElementById("title").value;
            var ele = document.getElementById("titleHint");
            if (value == "") {
                ele.innerHTML = "<font color='red'>" + "Error: The input is empty"+ "</font>";
                return false;
            } else {
                ele.innerHTML = "<font color='green'>" +"OK: The Title is checked"+ "</font>";
                return true;
            }
        }
        function check_authorid(){
            $.ajax({
                type:"POST",
                url:"ajaxcheck!Check_authorid",
                data:$("#authorid").serializeArray()
            }).done(function(data,statusText){
            	var value = document.getElementById("authorid").value;
            	var ele= document.getElementById("authoridHint");
                if(data.result=="0"){
                  	 if (value == "")
                  	 {
                  		ele.innerHTML = "<font color='red'>" +
                  		"Error: The input is empty"+ "</font>";
                  		flag=false;
                     }
                  	 else
                  	{
                  		 var r = /^[0-9]+$/;
                  		 if(r.test(value))
	                	ele.innerHTML="<font color='red'>" +
	                	"Error: The author is not exist,you should create the author first" + "</font>";
	                	else
	                		ele.innerHTML="<font color='red'>" +
	                    	"Error: The authorid is not a number" + "</font>";
                		flag=false;
                	}
                }
                else{
                	ele.innerHTML="<font color='green'>" +
                	"OK: The author is avaliable" + "</font>";;
                	flag=true;
                }
            }).fail(function(){
                alert("error!");
            })
        }
        function check_publisher(){
        	var value = document.getElementById("publisher").value;
            var ele = document.getElementById("publisherHint");
            if (value == "") {
                ele.innerHTML = "<font color='red'>" + "Error: The input is empty"+ "</font>";
                return false;
            } else {
                ele.innerHTML = "<font color='green'>" +"OK: The Publisher is checked"+ "</font>";
                return true;
            }
        }
        function check_publishdate(){
        	var value = document.getElementById("publishdate").value;
            var ele = document.getElementById("publishdateHint");
            if (value == "") {
                ele.innerHTML = "<font color='red'>" + "Error: The input is empty"+ "</font>";
               return false;
            } else {
            	var r = value.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/); 
                if (r) {
                    ele.innerHTML = "<font color='green'>" +
                    "OK: The Publishdate is checked"+ "</font>";
                   return true;
                } else {
                    ele.innerHTML = "<font color='red'>" + 
                    "Error: The Publishdate is not correct(must be YYYY/MM/DD)"+"</font>";
                    return false;
                }
            }
        }
        function check_price(){
                var value = document.getElementById("price").value;
                var ele = document.getElementById("priceHint");
                if (value == "") {
                    ele.innerHTML = "<font color='red'>" + "Error: The input is empty"+ "</font>";
                    return false;
                } else {
                    var r = /^[0-9]+[\.]?[0-9]*$/;
                    if (r.test(value)) {
                        ele.innerHTML = "<font color='green'>" +
                        "OK: The Price is checked"+ "</font>";
                       return true;
                    } else {
                        ele.innerHTML = "<font color='red'>" + 
                        "Error: The Price is not correct(must be a positive number)"+ "</font>";
                        return false;
                    }
                }
        }
        function check()
        {
        	var bool=new Boolean();
        	check_isbn();
        	check_authorid();
			bool=flag&check_title()&check_publisher()&check_publishdate()&check_price();
			//var ele = document.getElementById("priceHint");
			//ele.innerHTML = "<font color='red'>" + bool+ "</font>";
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
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>LBM Page</title>
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
        <li class="breadcrumb-item active">AddBook</li>
      </ol>
      <!-- Example DataTables Card-->
      <div id="wrap">
		<div class="container">

			<p>Book Create</p>
			<%@page import="java.sql.*"%>
			<%
			
			String submit = null;
			submit = request.getParameter("submit");
			if (submit != null) 
			{
				String isbn = request.getParameter("book.isbn");
				String title = request.getParameter("book.title");
				String authorid = request.getParameter("book.authorid");
				String publisher = request.getParameter("book.publisher");
				String publishdate = request.getParameter("book.publishdate");
				float price = Float.valueOf(request.getParameter("book.price"));
				out.println(isbn + " " + title + " " + authorid + " " + publisher + " " + publishdate + " "
						+ price);
			}
						
			%>
			<s:fielderror fieldName="book_create_message"/>	
			
<body>
    <div id="Div1">
        <!-- Start Sign Up Form -->
        <s:form action="bookaction!Book_create" id="Form" onSubmit="return check()"> 
        <h2>Create A New Book</h2>
        <fieldset>
        <table frame="void" >
        	<tr><td width="80px"><label for="isbn">ISBN</label></td>
            <td><input  id="isbn" class="form_element" type="text" name="book.isbn"  onblur="check_isbn();"/></td>
            <td><span class='availability_status' id="isbnHint"></span></td>
            </tr>
            <tr><td width="80px"><label for="title">Title</label></td>
            <td><input  id="title" class="form_element" type="text" name="book.title"   onblur="check_title();"/></td>
            <td><span class='availability_status' id="titleHint"></span></td>
            </tr>
            <tr>
            <td width="80px"><label for="authorid">AuthorID</label></td>
   			<td><input  id="authorid" class="form_element" type="text" name="book.authorid"   onblur="check_authorid();"/></td>
            <td><span class='availability_status' id="authoridHint"></span></td>
            </tr>
            <tr>
            <td width="80px"><label for="publish">Publisher</label></td>
            <td><input  id="publisher" class="form_element" type="text" name="book.publisher"   onblur="check_publisher();"/></td>
            <td><span class='availability_status' id="publisherHint"></span></td>
            </tr>
            <tr>
            <td width="80px"><label for="publishdate">PublishDate:</label></td>
            <td><input  id="publishdate" class="form_element" type="text" name="book.publishdate" onblur="check_publishdate();" /></td>
            <td><span class='availability_status' id="publishdateHint"></span></td>
            </tr>
            <tr><td width="80px"><label for="price">Price:</label></td><td>
            <input  id="price" class="form_element" type="text" name="book.price"  onblur="check_price();"/></td>
            <td><span class='availability_status' id="priceHint"></span></td>
            </div>
            <tr>
            <td></td><td></td><td><input type="submit" id="submit" class="submit" value="Create"></td>
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
            <a class="btn btn-primary" href="login.html">Logout</a>
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

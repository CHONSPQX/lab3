<%@page import="tool.Tool"%>
<%@page import="Model.Book"%>
<%@page import="Model.Author"%>
<%@page import="Action.BookDatabase" %>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%request.setCharacterEncoding("UTF-8");%>
<%response.setCharacterEncoding("utf-8");%>
<style type="text/css">
 .ui_div{
 	display:none;
 }
 .bl_div{
 	display:block;
 }
</style>
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
	function show(name)
	{
		var obj=document.getElementById(name);
		if(obj.style.display=='block')
			obj.style.display='none';
		else
			obj.style.display='block';
	}
	function Deletebook(value)
	{
		var isbn=value;
		var request=new XMLHttpRequest();
		request.open("POST","ajaxcheck!Delete_isbn?isbn="+isbn);
		request.send();
		var obj=document.getElementById(value);
		if(obj.style.display=='none')
			obj.style.display='block';
		else
			obj.style.display='none';
	}
</script>
<!DOCTYPE html>
<html >
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
        <li class="breadcrumb-item active">Search</li>
      </ol>
      <!-- Example DataTables Card-->
      <div id="wrap">
		<div class="container">

			<p>Book Search</p>
			<s:fielderror fieldName="list_retrieve_message"/>
			<s:form action="listaction!List_retrieve">
			<s:textfield name="authorname" key="AuthorName" />
			<s:submit value="Submit"  name ="submit"/>
			</s:form>
			
			<%
			ArrayList<Book> bookresult=(ArrayList<Book>)request.getAttribute("bookresult");
			ArrayList<Author> authorresult=(ArrayList<Author>)request.getAttribute("authorresult");
			if(bookresult!=null&&!bookresult.isEmpty())
			{
				for(Book bo:bookresult)
				{
					Author au=null;
					for(int i=0;i<authorresult.size();i++)
					{
						if(authorresult.get(i).authorid.equals(bo.authorid))
						{
							au=authorresult.get(i);
							break;
						}
					}
					//out.println("BookTitle:<p><input type=\"button\" id=\""+bo.title+"\" value=\""+bo.title+"\" onclick=\"show(\'"+bo.isbn+bo.isbn+"\');\"></p>");
					%>
					<div class="bl_div" id="<%=bo.isbn %>">
					BookTitle:
					<p>
					<input type="button" style="width:25%;" id="<%=bo.title %>" value="<%=bo.title %>" onclick="show('<%=bo.isbn+bo.isbn %>')" ></p>
					<div class="ui_div" id="<%=bo.isbn+bo.isbn %>">
					<p>The Book Information</p>
					<table width="100%"  border="1"  align="center">
					<tr bgcolor="#66ff33" >							
					<th>ISBN</th><th>Title</th><th>AuthorID</th><th>Publisher</th>
					<th>PublishDate</th><th>Price</th><th>Delete</th><th>Update</th></tr>
					<tr>
					<td align="center"><%=bo.isbn%></td>
					<td align="center"><%=bo.title%></td>
					<td align="center"><%=bo.authorid%></td>
					<td align="center"><%=bo.publisher%></td>
					<td align="center"><%=bo.publishdate%></td>
					<td align="center"><%=bo.price%></td>
					<td align="center">
					<input type="button" value="Delete"  name ="submit" onclick="Deletebook('<%=bo.isbn %>')" />
					</td>
					<td align="center">
					<s:form action="bookupdate.jsp" style="width:90px;padding-top:15px;">
					<input type=hidden name="book.isbn" value=<%=bo.isbn %> >
					<input type=hidden name="book.title" value=<%=bo.title%> >
					<input type=hidden name="book.authorid" value=<%=bo.authorid %> >
					<input type=hidden name="book.publisher" value=<%=bo.publisher %> >
					<input type=hidden name="book.publishdate" value=<%=bo.publishdate %> >
					<input type=hidden name="book.price" value=<%=bo.price%> >
					<s:submit value="Update" name ="submit" />
					</s:form>
					</td>
					</tr>
					</table>
					<p>The Author Information</p>
					<table width="100%" border="1" align="center">
					<tr bgcolor="#ffff00">	
					<th>AuthorID</th>
					<th>Name</th>
					<th>Age</th>
					<th>Country</th>
					</tr>
					<tr>
					<td align="center"><%=au.authorid %></td>
					<td align="center"><%=au.name%></td>
					<td align="center"><%=au.age%></td>
					<td align="center"><%=au.country%></td>
					</tr>
					</table>
					</div>
					</div>
			<%
				}
			}
			%>
									
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

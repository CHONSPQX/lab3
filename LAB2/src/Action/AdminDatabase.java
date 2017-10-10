package Action;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import Model.Admin;
import Model.Author;

public class AdminDatabase {

	String drivename = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost/test?useSSL=false";
	String user = "root";
	String password = "126459";
	Connection conn;
	ResultSet rs = null;

	
	public Connection ConnectMysql() {
		try {
			Class.forName(drivename);
			conn = (Connection) DriverManager.getConnection(url, user, password);
			if (!conn.isClosed()) {
				System.out.println("Succeeded connecting to the Database!");
			} else {
				System.out.println("Falled connecting to the Database!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return conn;
	}

	public void CutConnection(Connection conn) throws SQLException {
		try {
			if (rs != null)
				;
			if (conn != null)
				;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			conn.close();
		}
	}

	public boolean SelectSql(String sql) {
		try {
			Statement statement = conn.createStatement();
			rs = statement.executeQuery(sql);
			if(rs.next())
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean InsertSql(Admin admin) {
		try {
			String insql = "insert into admin(name,pwd) values(?,?)";
			
			PreparedStatement ps = conn.prepareStatement(insql);
			
			ps.setString(1, admin.getUsername());
			ps.setString(2, admin.getUserpwd());
			int result = ps.executeUpdate();
			
			if (result > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean UpdateSql(String upsql) {
		try {
			PreparedStatement ps = conn.prepareStatement(upsql);
			int result = ps.executeUpdate();
			if (result > 0)
				return true;
		} catch (SQLException ex) {
			Logger.getLogger(AuthorDatabase.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}

	public boolean DeleteSql(String delsql) {

		try {
			Statement ps = conn.createStatement();
			int result = ps.executeUpdate(delsql);
			if (result > 0)
				return true;
		} catch (SQLException ex) {
			Logger.getLogger(AuthorDatabase.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}

}

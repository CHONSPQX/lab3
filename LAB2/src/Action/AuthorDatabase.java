package Action;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import Model.Author;

public class AuthorDatabase {

	String drivename = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost/test?useSSL=false";
	String user = "root";
	String password = "126459";
	String insql;
	String upsql;
	String delsql;
	String sql = "select * from author";
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

	
	public boolean InsertSql(Author author) {
		try {
			insql = "insert into author(AuthorID,Name,Age,Country) values(?,?,?,?)";
			
			PreparedStatement ps = conn.prepareStatement(insql);
			
			ps.setString(1, author.getAuthorid());
			ps.setString(2, author.getName());
			ps.setInt(3, author.getAge());
			ps.setString(4, author.getCountry());
			int result = ps.executeUpdate();
			
			if (result > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	
	public ArrayList<Author> SelectSql(String sql) {
		ArrayList<Author> authors = new ArrayList<>();
		System.out.println(sql);
		try {
			Statement statement = conn.createStatement();
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				String authorid = rs.getString("AuthorID");
				String name = rs.getString("Name");
				int age = rs.getInt("Age");
				String country = rs.getString("Country");
				authors.add(new Author(authorid, name, age, country));
				System.out.println(authorid + " " + name + " " + age + " " + country);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return authors;
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

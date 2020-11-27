package server;

import java.sql.*;

public class CreateDB {
	private Connection con = null;

	public Connection getConnection() {

		try {
			if (con == null) {
				Class.forName("org.hsqldb.jdbcDriver");
				con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/OneDB", "SA", "");
				Statement stmt = con.createStatement();
				stmt.executeUpdate(
						"CREATE TABLE IF NOT EXISTS CAR (id INTEGER identity,name VARCHAR(32),numb VARCHAR(32),area INTEGER)");
				stmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}
}

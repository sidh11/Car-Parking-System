package server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ParkingCRUD {

	public static List<ParkingModel> getAllParkedCarsInformation() {

		List<ParkingModel> itemsList = null;
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/OneDB", "SA", "");
			Statement stmt = con.createStatement();
			ResultSet rs;
			String n = "y";
			rs = stmt.executeQuery("Select * from car;");
			itemsList = new ArrayList<ParkingModel>();

			while (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("NAME");
				int area = rs.getInt("AREA");
				String numb = rs.getString("NUMB");
//				ParkingModel mobile = new ParkingModel(id, name, numb, area);
//				itemsList.add(mobile);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return itemsList;

	}

	public static ParkingModel getParkedCarInfo(int id) throws ClassNotFoundException, SQLException {
		List<ParkingModel> users = getAllParkedCarsInformation();

		Class.forName("org.hsqldb.jdbcDriver");
		Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/oneDB", "SA", "");
		ResultSet rs;
		ParkingModel user = null;
		//String sql = "SELECT id,name,numb,area FROM car where id = ?";
		PreparedStatement st = con.prepareStatement("SELECT id,name,numb,area FROM car where id = ?");
		st.setInt(1, id);
		rs = st.executeQuery();
		while (rs.next()) {

			int id1 = rs.getInt("ID");
			String name1 = rs.getString("NAME");
			int area1 = rs.getInt("AREA");
			String numb1 = rs.getString("NUMB");
			ParkingModel car = new ParkingModel();
		}

		return user;

	}

	public static int parkCar(String name, String numb, String area) {
		int num = 0;
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/OneDB", "SA", "");
			String sql = "insert into car(name,numb,area) values(?,?,?)";
			PreparedStatement st = con.prepareStatement(sql);
			st = con.prepareStatement(sql);
			st.setString(1, name);
			st.setString(2, numb);
			st.setInt(3, Integer.parseInt(area));
			num = st.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}

	public static void updateParkedCarInfo(String id, String name, String numb, String area) {
		try {
			Class.forName("org.hsqldb.jdbcDriver");

			Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/oneDB", "SA", "");
			PreparedStatement st = con.prepareStatement("UPDATE car SET name =?,numb =?,area =? WHERE id =?");
			st.setString(1, name);
			st.setString(2, numb);
			st.setString(3, area);
			st.setInt(4, Integer.parseInt(id));
			st.executeUpdate();
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static int deliverCar(String id) throws ClassNotFoundException, SQLException {
		Class.forName("org.hsqldb.jdbcDriver");

		Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/oneDB", "SA", "");
		// Statement stmt = con.createStatement();
		// String sql = "Delete from car where id = ?";
		PreparedStatement st = con.prepareStatement("Delete from car where id = ?");
		st.setInt(1, Integer.parseInt(id));
		int num = st.executeUpdate();
		st.close();
		return num;

	}

	private void saveCarList(List<ParkingModel> userList) throws IOException {

		File file = new File("Users.dat");
		FileOutputStream fos;

		fos = new FileOutputStream(file);

		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(userList);
		oos.close();
	}
}

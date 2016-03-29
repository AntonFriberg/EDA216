
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListModel;

/**
 * Database is a class that specifies the interface to the movie database. Uses
 * JDBC and the MySQL Connector/J driver.
 */
public class Database {
	/**
	 * The database connection.
	 */
	private Connection conn;

	/**
	 * Create the database interface object. Connection to the database is
	 * performed later.
	 */
	public Database() {
		conn = null;
	}

	/**
	 * Open a connection to the database, using the specified user name and
	 * password.
	 * 
	 * @param userName
	 *            The user name.
	 * @param password
	 *            The user's password.
	 * @return true if the connection succeeded, false if the supplied user name
	 *         and password were not recognized. Returns false also if the JDBC
	 *         driver isn't found.
	 */
	public boolean openConnection(String userName, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://puccini.cs.lth.se/" + userName, userName, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Close the connection to the database.
	 */
	public void closeConnection() {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
		}
		conn = null;
	}

	/**
	 * Check if the connection to the database has been established
	 * 
	 * @return true if the connection has been established
	 */
	public boolean isConnected() {
		return conn != null;
	}

	/* --- insert own code here --- */
	private ResultSet execPrepQuery(String q, ArrayList<String> input, int unknowns) {
		if (isConnected()) {
			try {
				PreparedStatement execStat = conn.prepareStatement(q);
				for (int i = 1; i < unknowns + 1; i++) {
					execStat.setString(i, input.get(i - 1));
				}
				ResultSet cookieName = execStat.executeQuery("SELECT cookiename FROM Cookie");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e);
			}
		}
		return null;
	}

	public void getCookieNames(DefaultListModel<String> cookieListModel) {
		if (isConnected()) {
			try {
				Statement execStat = conn.createStatement();
				ResultSet cookieName = execStat.executeQuery("SELECT cookiename FROM Cookie");
				while (cookieName.next()) {
					cookieListModel.addElement(cookieName.getString(1));
				}
				cookieName.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e);
			}
		}
	}

	public Pallet producePallet(String cookieName) {
		// TODO Auto-generated method stub
		int id=-1;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String date = df.format(new Date());
		if (isConnected()) {
			try {
				PreparedStatement execStat = conn.prepareStatement("INSERT INTO pallet values (0,?,?,?,?,?);",
						Statement.RETURN_GENERATED_KEYS);
				String loc = "Freezer";
				execStat.setString(1, cookieName);
				execStat.setString(2, date);
				execStat.setString(3, null);
				execStat.setString(4, loc);
				execStat.setString(5, null);
				System.out.println(execStat.toString());
				execStat.executeUpdate();
				ResultSet result=execStat.getGeneratedKeys();
				while(result.next()){
					id=result.getInt(1);
				}
				execStat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e);
			}
		} else {
			return null;
		}
		return new Pallet(id, cookieName, "", date, false, null);
	}

}

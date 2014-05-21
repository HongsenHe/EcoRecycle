package projTest;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import com.mysql.jdbc.PreparedStatement;

public class RmosDB {
	// JDBC driver name and database URL
	static Connection conn;
	static Statement stmt;
	static PreparedStatement ps;

	// create a database "rmos"
	static final String URL = "jdbc:mysql://localhost/rmos";
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

	// ID and password
	static final String USER = "root";
	static final String PASS = "";

	static int activateNum = 0; // 1 means the activate RCM
	static double defBalance = 50.0;

	// methods

	// ****************THIS IS FOR RMOS UI *****************************
	// *********** add item into database **************
	public static void addItem(Items AItem) {
		// connection database using getConnection method;
		conn = getConnection();
		// for executing SQL statement
		try {

			String sql;
			sql = "INSERT INTO itemsList (ItemNum, ItemType, ItemPrice) VALUES(?, ?, ?)";

			// using this way to insert data
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, AItem.getItemNum());
			ps.setString(2, AItem.getItemsType());
			ps.setDouble(3, AItem.getPrice());

			// mySQL starts
			ps.executeUpdate();

			// close database connection
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ******** remove the item from database *********
	public static void removeItem(int itemNum) {
		// connection database using getConnection method;
		conn = getConnection();

		String sql;
		sql = "DELETE FROM itemsList WHERE ItemNum = ?";

		try {
			// delete the chosen one
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, itemNum);

			ps.execute();
			ps.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ********* change the item price from database *********
	public static void changeItem(int itemNum, double itemPrice) {
		// connection database using getConnection method;
		conn = getConnection();

		String sql;
		sql = "UPDATE itemsList SET ItemPrice = ? WHERE ItemNum = ?";

		try {
			// change the price
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setDouble(1, itemPrice);
			ps.setInt(2, itemNum);

			// close them
			ps.execute();
			ps.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ****** Add RCM part **********
	public static void addRCM(RCM aRcm) {
		// connection database using getConnection method;
		conn = getConnection();
		int ZERO = 0;

		String sql;
		sql = "INSERT INTO RcmList (RcmNum, ActNum, RcmLocation, RcmID, MonthlyIssued, CurBalance,"
				+ "MonthlyUse, MonthlyWeight, CurrentCapacity) "
				+ "VALUES (?,?,?,?,?,?,?,?,?)";

		try {
			// change the price
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, aRcm.getRCMNum());
			ps.setInt(2, activateNum);
			ps.setString(3, aRcm.getLocation());
			ps.setInt(4, aRcm.getRCMID());
			ps.setDouble(5, ZERO); // monthly issued
			ps.setDouble(6, defBalance); // current balance
			ps.setDouble(7, ZERO); // monthly use
			ps.setDouble(8, ZERO); // monthly weight
			ps.setDouble(9, ZERO); // current capacity

			// close them
			ps.execute();
			ps.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ******** Remove RCM part ***********
	public static void removeRCM(int rcmNum) {
		// connection database using getConnection method;
		conn = getConnection();

		String sql;
		sql = "DELETE FROM RcmList WHERE RcmNum = ?";

		try {
			// delete the chosen one
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, rcmNum);

			ps.execute();
			ps.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ********* Activate RCM part *********
	public static void activateRCM(int rcmNum) throws SQLException {
		// set activate number = 1 with a specific RCM
		activateNum = 1;

		// connection database using getConnection method;
		conn = getConnection();

		String sql;

		sql = "UPDATE RcmList SET actNum = ? WHERE RcmNum = ?";

		try {
			// delete the chosen one
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, activateNum);
			ps.setInt(2, rcmNum);

			ps.execute();
			ps.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		conn.close();

		activateNum = 0;
	}

	// *********** Monthly Money part *************
	public static void setMonthlyIssued(int rcmNum, double monthlyMoney)
			throws SQLException {
		conn = getConnection();
		String sql;

		sql = "UPDATE RcmList SET MonthlyIssued = ? WHERE RcmNum = ?";

		ps = (PreparedStatement) conn.prepareStatement(sql);
		ps.setDouble(1, monthlyMoney);
		ps.setInt(2, rcmNum);

		ps.execute();
		ps.close();
		conn.close();

	}

	// ************** Get monthly money part **********
	public static double getMonthlyIssued(int rcmNum) throws SQLException {
		double monthlyMoney = 0;

		conn = getConnection();
		String sql;

		sql = "SELECT MonthlyIssued FROM RcmList WHERE RcmNum = ?";

		ps = (PreparedStatement) conn.prepareStatement(sql);
		ps.setInt(1, rcmNum);

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			monthlyMoney = rs.getDouble("MonthlyIssued");
		}

		return monthlyMoney;
	}

	// ************** THIS IS FOR PIE CHART ***************************
	public static LinkedHashMap<Integer, Double> cashPieInDB() throws SQLException {
		// create a LinkedHashMap
		Map<Integer, Double> cashMap = new LinkedHashMap<Integer, Double>();

		// JDBC PART
		conn = getConnection();
		String sql = "";
		
		sql = "SELECT RcmNum, MonthlyIssued FROM RcmList";
		
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		// load and store data into cashMap
		int rcmNum = 0;
		double monthlyCash = 0;
		
		while(rs.next()){
			rcmNum = rs.getInt("RcmNum");
			monthlyCash = rs.getDouble("MonthlyIssued");
			
			cashMap.put(rcmNum, monthlyCash);
		}
				
		stmt.close();
		rs.close();
		conn.close();
		
		// return the result including RCM number and its monthly cash issued
		return (LinkedHashMap<Integer, Double>) cashMap;
	}
	
	// ************** THIs is usage chart *********************
	public static LinkedHashMap<Integer, Integer> usagePieInDB() throws SQLException {
		// create a LinkedHashMap
		Map<Integer, Integer> usageMap = new LinkedHashMap<Integer, Integer>();

		// JDBC PART
		conn = getConnection();
		String sql = "";
		
		sql = "SELECT RcmNum, MonthlyUse FROM RcmList";
		
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		// load and store data into cashMap
		int rcmNum = 0;
		int monthlyUse = 0;
		
		while(rs.next()){
			rcmNum = rs.getInt("RcmNum");
			monthlyUse = rs.getInt("MonthlyUse");
			
			usageMap.put(rcmNum, monthlyUse);
		}
				
		stmt.close();
		rs.close();
		conn.close();
		
		// return the result including RCM number and its monthly cash issued
		return (LinkedHashMap<Integer, Integer>) usageMap;
	}

	// ********* This is Monthly Weight *****************
	public static LinkedHashMap<Integer, Double> weightPieInDB() throws SQLException {
		// create a LinkedHashMap
		Map<Integer, Double> weightMap = new LinkedHashMap<Integer, Double>();

		// JDBC PART
		conn = getConnection();
		String sql = "";
		
		sql = "SELECT RcmNum, MonthlyWeight FROM RcmList";
		
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		// load and store data into cashMap
		int rcmNum = 0;
		double monthlyWeight = 0;
		
		while(rs.next()){
			rcmNum = rs.getInt("RcmNum");
			monthlyWeight = rs.getDouble("MonthlyWeight");
			
			weightMap.put(rcmNum, monthlyWeight);
		}
				
		stmt.close();
		rs.close();
		conn.close();
		
		// return the result including RCM number and its monthly cash issued
		return (LinkedHashMap<Integer, Double>) weightMap;
	}
	
	
	
	// ******************** THIS IS FOR RCM UI *************************
	// check if the user input RcmNum is in DB
	public static boolean existRcmNum(int RcmNum) throws SQLException {
		// default value
		boolean result = false;

		// connection database using getConnection method;
		conn = getConnection();
		try {
			stmt = conn.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// setup a SQL
		String sql;
		sql = "SELECT RcmNum FROM RcmList";

		// return RcmNum set maybe zero
		try {
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				// get each RCM number in DB
				int tempNum = rs.getInt("RcmNum");

				// judge
				if (tempNum == RcmNum) {
					result = true;
					break;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		stmt.close();
		conn.close();

		return result;
	}

	public static String itemsListDB() throws SQLException {
		String result = "";
		// setup DB connection
		conn = getConnection();
		stmt = conn.createStatement();

		String sql;
		sql = "SELECT * FROM itemsList";

		// load into result set
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int aItemNum = rs.getInt("ItemNum");
			String aItemType = rs.getString("ItemType");
			double aItemPrice = rs.getDouble("ItemPrice");

			result += "Item Num: " + aItemNum + "   Item Type: " + aItemType
					+ "   Item Unit Price: $ " + aItemPrice + "\n";
		}

		rs.close();
		stmt.close();
		conn.close();

		return result;
	}

	public static boolean existItemNum(int itemNum) throws SQLException {
		// default value
		boolean result = false;

		// connection database using getConnection method;
		conn = getConnection();
		try {
			stmt = conn.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// setup a SQL
		String sql;
		sql = "SELECT ItemNum FROM itemsList";

		// return itemNum set
		try {
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				// get each item number in DB
				int tempNum = rs.getInt("ItemNum");

				// judge
				if (tempNum == itemNum) {
					result = true;
					break;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		stmt.close();
		conn.close();

		return result;
	}

	// check current capacity
	public static double getCurCapacity(int rcmNum) throws SQLException {
		double curCap = 0;

		// connection database using getConnection method;
		conn = getConnection();

		String sql;
		sql = "SELECT CurrentCapacity FROM rcmList WHERE RcmNum = ?";
		ps = (PreparedStatement) conn.prepareStatement(sql);
		ps.setInt(1, rcmNum);

		ResultSet rs = ps.executeQuery();
		while (rs.next()) { // ****** THIS IS REQUIRED !!!!!!!!**********
			curCap = rs.getDouble("CurrentCapacity");
		}

		// ps.close();
		// conn.close();

		return curCap;
	}

	// set current capacity
	public static void setCurCapacity(int RcmNum, double totalWeight)
			throws SQLException {
		// set up
		conn = getConnection();

		double tempW = totalWeight + getCurCapacity(RcmNum);

		String sql;
		sql = "UPDATE RcmList SET CurrentCapacity = ? WHERE RcmNum = ?";
		ps = (PreparedStatement) conn.prepareStatement(sql);
		ps.setDouble(1, tempW);
		ps.setInt(2, RcmNum);

		ps.execute();

		ps.close();
		conn.close();
	}

	// set current capacity
	public static void clearCurCap(int RcmNum) throws SQLException {
		// set up
		conn = getConnection();

		String sql;
		sql = "UPDATE RcmList SET CurrentCapacity = 0 WHERE RcmNum = ?";
		ps = (PreparedStatement) conn.prepareStatement(sql);
		ps.setInt(1, RcmNum);

		ps.execute();

		ps.close();
		conn.close();
	}

	// set Monthly use
	public static void setMonthlyUse(int RcmNum) throws SQLException {
		// set up
		conn = getConnection();
		String sql;
		sql = "UPDATE RcmList SET MonthlyUse = ? WHERE RcmNum = ?";

		int count = 0;
		count = RmosDB.getMonthlyUse(RcmNum) + 1;

		ps = (PreparedStatement) conn.prepareStatement(sql);
		ps.setInt(1, count);
		ps.setInt(2, RcmNum);

		ps.execute();
		ps.close();
		conn.close();

	}

	// get Monthly use
	public static int getMonthlyUse(int RcmNum) throws SQLException {
		// set up
		int count = 0;

		conn = getConnection();
		String sql;
		sql = "SELECT MonthlyUse FROM RcmList WHERE RcmNum = ?";

		ps = (PreparedStatement) conn.prepareStatement(sql);
		ps.setInt(1, RcmNum);

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			count = rs.getInt("MonthlyUse");
		}

		return count;
	}

	// set Monthly Weight
	public static void setMonthlyWeight(int RcmNum, double curCap)
			throws SQLException {
		// set up
		conn = getConnection();
		String sql;
		sql = "UPDATE RcmList SET MonthlyWeight = ? WHERE RcmNum = ?";

		curCap = getMonthlyWeight(RcmNum) + getCurCapacity(RcmNum);

		ps = (PreparedStatement) conn.prepareStatement(sql);
		ps.setDouble(1, curCap);
		ps.setInt(2, RcmNum);

		ps.execute();
		ps.close();
		conn.close();
	}

	// get Monthly Weight
	public static int getMonthlyWeight(int RcmNum) throws SQLException {
		// set up
		int monthlyW = 0;

		conn = getConnection();
		String sql;
		sql = "SELECT MonthlyWeight FROM RcmList WHERE RcmNum = ?";

		ps = (PreparedStatement) conn.prepareStatement(sql);
		ps.setInt(1, RcmNum);

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			monthlyW = rs.getInt("MonthlyWeight");
		}

		return monthlyW;
	}

	// get the item unit price
	public static double getPriceDB(int itemNum) throws SQLException {
		double price = 0;

		conn = getConnection();
		String sql = "SELECT ItemPrice FROM itemsList WHERE ItemNum = ?";

		ps = (PreparedStatement) conn.prepareStatement(sql);
		ps.setInt(1, itemNum);

		// load the price
		ResultSet rs = ps.executeQuery();

		// store the price
		while (rs.next()) {
			price = rs.getDouble("ItemPrice");
		}

		ps.close();
		conn.close();

		return price;
	}

	public static void setMoneyBalance(double moneyBalance) {
		RmosDB.defBalance = moneyBalance;
	}

	// update balance money in database
	public static void setCurBalanceInDB(int rcmNum, double balance)
			throws SQLException {
		// set up
		conn = getConnection();

		String sql;
		sql = "UPDATE RcmList SET CurBalance = ? WHERE RcmNum = ?";

		// update balance
		ps = (PreparedStatement) conn.prepareStatement(sql);
		ps.setDouble(1, balance);
		ps.setInt(2, rcmNum);

		ps.execute();
		ps.close();
		conn.close();
	}

	// get Money Balance from DB
	public static double getBalanceInDB(int rcmNum) throws SQLException {
		// local balance
		double tempBal = 0;
		// set up
		conn = getConnection();

		String sql;
		sql = "SELECT CurBalance FROM RcmList WHERE RcmNum = ?";

		ps = (PreparedStatement) conn.prepareStatement(sql);
		ps.setInt(1, rcmNum);

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			tempBal = rs.getDouble("CurBalance");
		}

		ps.close();
		conn.close();

		return tempBal;
	}

	// get most busy one
	public static String getBusyRCMInDB() throws SQLException {
		// local balance
		double monthlyUse = 0;
		double MonthlyWeight = 0;
		int busyRcm = 0;

		double point = 0; // point = 1000*MonthlyUse + MonthlyWeight
		double maxPoint = 0;
		int mostRCM = 0;
		double maxUse = 0;
		double maxMonthlyWeight = 0;

		String result = "";

		// set up
		conn = getConnection();

		String sql;
		sql = "SELECT MonthlyUse, RcmNum, MonthlyWeight " + "FROM RcmList "
				+ "ORDER BY MOnthlyUse DESC, MonthlyWeight DESC";

		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		// set up a result

		while (rs.next()) {
			monthlyUse = rs.getInt("MonthlyUse");
			busyRcm = rs.getInt("RcmNum");
			MonthlyWeight = rs.getDouble("MonthlyWeight");

			point = monthlyUse * 1000 + MonthlyWeight;

			// load the max point RCM number
			if (point > maxPoint) {
				maxPoint = point;
				mostRCM = busyRcm;
				maxUse = monthlyUse;
				maxMonthlyWeight = MonthlyWeight;
			}
		}

		result = "RCM " + mostRCM + "\n" + maxUse + " times/month"
				+ "\nMonthly Weight: " + maxMonthlyWeight + "lb.";
		stmt.close();
		conn.close();

		return result;
	}

	// *********** connect database method ***************
	public static Connection getConnection() {
		Connection con = null;

		// load mySQL driver
		try {
			// register JDBC driver
			Class.forName(JDBC_DRIVER);

			// open connection
			con = DriverManager.getConnection(URL, USER, PASS);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// return the connection we build
		return con;
	}
}
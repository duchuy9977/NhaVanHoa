package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionUtil {
	private static final String URL = "jdbc:sqlserver://localhost:1433;encrypt=true;trustServerCertificate=true;";
	private static final String DATABASE = "databaseName=NhaVanHoa";
	private static final String USER_NAME = "sa";
	private static final String PASSWORD = "vietnam12345";

	public static Connection getConnection() {
		Connection con = null;
		try {
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(URL + DATABASE, USER_NAME, PASSWORD);
			// 10.133.13.231:1433 - <IP may co DB, truong hop cai tai may ca nhan thi la
			// localhost>:<port - mac dinh luc cai SQL server ko thay doi gi thi la 1433>
		} 
//		catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return con;
	}
	
	public static void testConnection() {
		Connection conn = ConnectionUtil.getConnection();
		if(conn == null) {
			System.out.println("Kết nối thất bại");
		} else {
			System.out.println("Kết nối thành công!!!");
		}
	}
	
	public static void closeConnection(ResultSet rs, PreparedStatement prstmt, Connection conn) {
		try {
			if(rs != null) {
				rs.close();
			}
			if(prstmt != null) {
				prstmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("Lỗi Đóng kết nối");
			e.printStackTrace();
		}
	}
}

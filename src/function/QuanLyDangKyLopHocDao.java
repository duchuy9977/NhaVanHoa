package function;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConnectionUtil;

public class QuanLyDangKyLopHocDao {
	public static int selectByStatus(String status) {
		Connection conn = null;
		PreparedStatement prsPreparedStatement = null;
		ResultSet rs = null;
		
		String sql = "SELECT COUNT(IDDangKy) as SL FROM DANGKYLOPHOC WHERE Status = ?";
		
		try {
			conn = ConnectionUtil.getConnection();
			prsPreparedStatement = conn.prepareStatement(sql);
			prsPreparedStatement.setString(1, status);
			rs = prsPreparedStatement.executeQuery();
			
			if (!rs.isBeforeFirst()) {
				return 0;
			}
			
			while(rs.next()) {
				return rs.getInt("SL");
			}
		} catch (SQLException i) {
			i.printStackTrace();
			System.out.println("Có Lỗi trong lúc tương tác dữ liệu");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Chọn Trẻ thất bại");
		} finally {
			ConnectionUtil.closeConnection(null, prsPreparedStatement, conn);
		}
		return 0;
	}
	
	public static int selectByPendingOrUnseenStatus() {
		Connection conn = null;
		PreparedStatement prsPreparedStatement = null;
		ResultSet rs = null;
		
		String sql = "SELECT COUNT(IDDangKy) as SL \r\n"
				+ "FROM DANGKYLOPHOC \r\n"
				+ "WHERE \r\n"
				+ "	Status != 'Approved' AND \r\n"
				+ "	Status != 'Declined'";
		
		try {
			conn = ConnectionUtil.getConnection();
			prsPreparedStatement = conn.prepareStatement(sql);
			rs = prsPreparedStatement.executeQuery();
			
			if (!rs.isBeforeFirst()) {
				return 0;
			}
			
			while(rs.next()) {
				return rs.getInt("SL");
			}
		} catch (SQLException i) {
			i.printStackTrace();
			System.out.println("Có Lỗi trong lúc tương tác dữ liệu");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Chọn Trẻ thất bại");
		} finally {
			ConnectionUtil.closeConnection(null, prsPreparedStatement, conn);
		}
		return 0;
	}
}

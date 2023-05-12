package function;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import connection.ConnectionUtil;
import entities.LopNangKhieu;

public class QuanLyDangKyLopHocDao {
	static Scanner sc = new Scanner(System.in);
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

			while (rs.next()) {
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

		String sql = "SELECT COUNT(IDDangKy) as SL \r\n" + "FROM DANGKYLOPHOC \r\n" + "WHERE \r\n"
				+ "	Status != 'Approved' AND \r\n" + "	Status != 'Declined'";

		try {
			conn = ConnectionUtil.getConnection();
			prsPreparedStatement = conn.prepareStatement(sql);
			rs = prsPreparedStatement.executeQuery();

			if (!rs.isBeforeFirst()) {
				return 0;
			}

			while (rs.next()) {
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

	public static ArrayList<String> chonDonDangKy() {
		Connection conn = null;
		PreparedStatement prsPreparedStatement = null;
		ResultSet rs = null;
		ArrayList<String> listIDDangKy = null;

		String sql = "SELECT *\r\n"
				+ "FROM DANGKYLOPHOC \r\n"
				+ "WHERE \r\n"
				+ "	Status != 'Approved' AND \r\n"
				+ "	Status != 'Declined'";

		try {
			conn = ConnectionUtil.getConnection();
			prsPreparedStatement = conn.prepareStatement(sql);
			rs = prsPreparedStatement.executeQuery();

			if (!rs.isBeforeFirst()) {
				System.out.println("Hiện không có đơn nào cần duyệt");
				return null;
			}

			System.out.println("===================================================");
			System.out.println("|   ------> Các Lớp có thể đăng kí được <------   |");
			System.out.println("===================================================");
			System.out.println("|STT| IDDangKy |IDTre|ID Lớp|Ngày Đăng Ký|  Tình Trạng  |");
			System.out.println("===================================================");

			int row = 0;
			listIDDangKy = new ArrayList<String>();
			while (rs.next()) {
				LopNangKhieu lopHoc = new LopNangKhieu();
				row++;
				String result = rs.getString("Status");
				if(result.equals("Unseen")){
					result = "MỚI!!!    ";
				}
				else if(result.equals("Pending")){
					result = "Đang Đợi Duyệt";
				} else {
					result = "Khác";
				}
				System.out.printf("|%3d|%10s|%5s|%6s|%12s|%14s|\n", row, rs.getString("IDDangKy"),rs.getString("IDTre"),rs.getString("IDLop"),rs.getDate("NgayDangKy") + "",result);
				listIDDangKy.add(rs.getString("IDDangKy"));
			}
			System.out.println("===============================================");
			System.out.println("Mời chọn đơn đăng ký bạn muốn sử lý (1->" + listIDDangKy.size()  + "): ");
			
			int choosse = -1;
			while (true) {
				String choice = sc.nextLine(); // Chọn môn đăng ký
				try {
					choosse = Integer.parseInt(choice);
					if (choosse > 0 && choosse <= listIDDangKy.size() ) {
						choosse--;
						System.out.println("Bạn Đã chọn đơn đăng kí số " + listIDDangKy.get(choosse));
						QuanLyDangKyLopHocDao.xuLyDonDangKy();
						break;
					} else {
						System.out.println("Bạn đã nhập sai, mời nhập lại!");
					}

				} catch (NumberFormatException e) {
					System.out.println("Bạn Đã nhập sai mời nhập lại");
				} catch (Exception e) {
					System.out.println("Đã có lỗi xảy ra, mời nhập lại");

				}
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
		return null;
	}

	private static void xuLyDonDangKy() {
		// TODO Auto-generated method stub
		
	}
}

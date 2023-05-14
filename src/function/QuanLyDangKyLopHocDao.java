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
				+ "	Status != 'Approved' AND \r\n" + "	Status != 'Declined' AND Status != 'Withdrawn'";

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

	public static void chonDonDangKy() {
		Connection conn = null;
		PreparedStatement prsPreparedStatement = null;
		ResultSet rs = null;
		ArrayList<String> listIDDangKy = null;

		String sql = "SELECT *\r\n" + "FROM DANGKYLOPHOC \r\n" + "WHERE \r\n" + "	Status != 'Approved' AND \r\n"
				+ "	Status != 'Declined' AND Status != 'Withdrawn'";

		try {
			conn = ConnectionUtil.getConnection();
			prsPreparedStatement = conn.prepareStatement(sql);
			rs = prsPreparedStatement.executeQuery();

			if (!rs.isBeforeFirst()) {
				System.out.println("Hiện không có đơn nào cần duyệt");
				return;
			}

			System.out.println("=========================================================");
			System.out.println("|      ------> Các Lớp có thể đăng kí được <------      |");
			System.out.println("=========================================================");
			System.out.println("|STT| IDDangKy |IDTre|ID Lớp|Ngày Đăng Ký|  Tình Trạng  |");
			System.out.println("=========================================================");

			int row = 0;
			listIDDangKy = new ArrayList<String>();
			while (rs.next()) {
				LopNangKhieu lopHoc = new LopNangKhieu();
				row++;
				String result = rs.getString("Status");
				if (result.equals("Unseen")) {
					result = "MỚI!!!    ";
				} else if (result.equals("Pending")) {
					result = "Đang Đợi Duyệt";
				} else {
					result = "Khác";
				}
				System.out.printf("|%3d|%10s|%5s|%6s|%12s|%14s|\n", row, rs.getString("IDDangKy"),
						rs.getString("IDTre"), rs.getString("IDLop"), rs.getDate("NgayDangKy") + "", result);
				listIDDangKy.add(rs.getString("IDDangKy"));
			}
			System.out.println("=========================================================");
			System.out.println("Mời chọn đơn đăng ký bạn muốn sử lý (1->" + listIDDangKy.size() + "): ");

			int choosse = -1;
			while (true) {
				String choice = sc.nextLine(); // Chọn môn đăng ký
				try {
					choosse = Integer.parseInt(choice);
					if (choosse > 0 && choosse <= listIDDangKy.size()) {
						choosse--;
						QuanLyDangKyLopHocDao.xuLyDonDangKy(listIDDangKy.get(choosse));
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
		return;
	}

	private static void xuLyDonDangKy(String idDonDangKy) {
		// TODO Auto-generated method stub

		Connection conn = null;
		PreparedStatement prsPreparedStatement = null;
		ResultSet rs = null;
		String sql = "SELECT \r\n"
				+ "	tre.TenTre,\r\n"
				+ "	tre.GioiTinh, \r\n"
				+ "	tre.NgaySinh, \r\n"
				+ "	tre.TruongDangHoc, \r\n"
				+ "	acc.Name, \r\n"
				+ "	ph.DiaChi,\r\n"
				+ "	ph.Email, \r\n"
				+ "	ph.SDT1, \r\n"
				+ "	lop.TenLop, \r\n"
				+ "	lop.IDLop,\r\n"
				+ "	lop.SoBuoi, \r\n"
				+ "	lop.NgayBatDau, \r\n"
				+ "	lop.NgayKetThuc,\r\n"
				+ "	DATEDIFF(YEAR, tre.NgaySinh, GETDATE()) as tuoi\r\n"
				+ "FROM DANGKYLOPHOC as dk\r\n"
				+ "	JOIN TREEM as tre ON dk.IDTre = tre.IDTre\r\n"
				+ "	JOIN PHUHUYNH as ph ON ph.IDPhuHuynh = tre.IDPhuHuynh\r\n"
				+ "	JOIN ACCOUNT as acc ON acc.Username = ph.Username\r\n"
				+ "	JOIN LOPNANGKHIEU as lop ON lop.IDLop = dk.IDLop\r\n"
				+ "	JOIN MONHOC as mh ON lop.IDMonHoc = mh.IDMonHoc\r\n"
				+ "WHERE dk.Status != 'Approved' AND dk.Status != 'Declined' AND dk.Status != 'Withdrawn' AND dk.IDDangKy = ?";

		try {
			conn = ConnectionUtil.getConnection();
			prsPreparedStatement = conn.prepareStatement(sql);
			prsPreparedStatement.setString(1, idDonDangKy);
			rs = prsPreparedStatement.executeQuery();

			if (!rs.isBeforeFirst()) {
				System.out.println("Hiện không có đơn nào cần duyệt");
			}

			System.out.println("Bạn Đã chọn đơn đăng kí số " + idDonDangKy);

			while (rs.next()) {
				System.out.println("===========================================================================");
				System.out.println("|");
				System.out.println("|                 ĐƠN ĐĂNG KÍ HỌC MÔN NĂNG KHIẾU TỰ CHỌN");
				System.out.println("|");
				System.out.println("|     Kính gửi trung tâm năng khiếu tp Đà Nẵng ");
				System.out.println("|     Tôi Tên là: " + rs.getString("Name"));
				System.out.println("|     Thường trú tại: " + rs.getString("DiaChi"));
				System.out.println("|     Số điện thoại: " + rs.getString("SDT1"));
				System.out.println("|     Email: " + rs.getString("Email"));
				System.out.println("|     ");
				System.out.println("|     Nay tôi viết đơn này đăng kí cho cháu " + rs.getString("TenTre"));
				System.out.println("|     Giới tính: " + rs.getString("GioiTinh"));
				System.out.println("|     Sinh ngày: " + (rs.getDate("NgaySinh") + ""));
				System.out.println("|     Tuổi: " + rs.getInt("tuoi"));
				System.out.println("|     Hiện đang học tại trường " + rs.getString("TruongDangHoc"));
				System.out.println("|");
				System.out.println("|     Nay tôi đăng kí cho cháu học lớp " + rs.getString("IDLop") + " - "
						+ rs.getString("TenLop"));
				System.out.println("|     Ngày Bắt Đầu: " + rs.getString("NgayBatDau"));
				System.out.println("|     Ngày kết thúc: " + rs.getString("NgayKetThuc"));
				System.out.println("|     ");
				System.out.println("|     Mong quý thầy cô xem xét qua và chấp thuận đơn đăng ký này! ");
				System.out.println("|      ");
				System.out.println("|     Chấp Thuận (Y)        Từ Chối(N)         Để sau(bất kì kí tự nào) ");
				System.out.println("|      ");
				System.out.println("|     Mời bạn nhập lựa chọn: ");
				System.out.println("===========================================================================");
			}
			
			int idDon = 0;
			try {
				idDon = Integer.parseInt(idDonDangKy);
			} catch (NumberFormatException e) {
				System.out.println("Chuyển Kiểu dữ liệu sai");
			} catch (Exception e) {
				System.out.println("Lỗi");
				return;
			}
			
			while (true) {
				String choice = sc.nextLine(); // Chọn môn đăng ký
				switch (choice) {
				case "y":
				case "Y":
					QuanLyDangKyLopHocDao.updateStatusDonDangKy("Approved", idDon);
					break;
				case "n":
				case "N":
					QuanLyDangKyLopHocDao.updateStatusDonDangKy("Declined", idDon);
					break;
				default:
					QuanLyDangKyLopHocDao.updateStatusDonDangKy("Pending", idDon);
					break;
				}
				break;
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
		return;
	}

	private static void updateStatusDonDangKy(String status, int idDonDangKy) {
		Connection conn = null;
		PreparedStatement prsPreparedStatement = null;

		String sql = "Update DANGKYLOPHOC SET Status = ? WHERE IDDangKy = ?";

		try {
			conn = ConnectionUtil.getConnection();
			prsPreparedStatement = conn.prepareStatement(sql);
			prsPreparedStatement.setString(1, status);
			prsPreparedStatement.setInt(2, idDonDangKy);
			int row = prsPreparedStatement.executeUpdate();

			if (row > 0) {
				if (status.equals("Approved")) {
					System.out.println("Bạn đã chấp thuận đơn đăng ký ");
				} else if (status.equals("Declined")) {
					System.out.println("Bạn đã từ chối đơn đăng ký ");
				} else {
					System.out.println("Bạn đã bỏ qua đơn này, chờ lần khác xét duyệt!");
				}
			}
		} catch (SQLException i) {
			i.printStackTrace();
			System.out.println("Có Lỗi trong lúc tương tác dữ liệu");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Update đơn đăng ký thất bại");
		} finally {
			ConnectionUtil.closeConnection(null, prsPreparedStatement, conn);
		}
		return;
	}
}

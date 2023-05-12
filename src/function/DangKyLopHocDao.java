package function;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import connection.ConnectionUtil;
import entities.LopNangKhieu;
import treEm.TreEm;

public class DangKyLopHocDao {
	public static void chonTreDangKy(String idLop, String userName) {
		Connection conn = null;
		PreparedStatement prsPreparedStatement = null;
		ResultSet rs = null;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Mời bạn chọn đứa trẻ bạn muốn đăng ký: ");
		//Chọn các trẻ thỏa điều kiện gồm các trẻ có 5 <= tuổi <= 15 và trạng thái hoạt động là active 
		String sql = "SELECT tre.IDTre, tre.TenTre, DATEDIFF(YEAR, tre.NgaySinh, GETDATE()) as tuoi FROM TREEM as tre\r\n"
				+ "JOIN PHUHUYNH \r\n"
				+ "ON tre.IDPhuHuynh = PHUHUYNH.IDPhuHuynh\r\n"
				+ "JOIN ACCOUNT\r\n"
				+ "ON PHUHUYNH.Username = ACCOUNT.Username\r\n"
				+ "WHERE ACCOUNT.Username = ? \r\n"
				+ "AND DATEDIFF(YEAR, tre.NgaySinh, GETDATE()) >= 5 \r\n"
				+ "AND DATEDIFF(YEAR, tre.NgaySinh, GETDATE()) <= 15 \r\n"
				+ "And tre.Status = 'Active'";
		
		try {
			conn = ConnectionUtil.getConnection();
			prsPreparedStatement = conn.prepareStatement(sql);
			prsPreparedStatement.setString(1, userName);
			rs = prsPreparedStatement.executeQuery();
			//Hiển thị các trẻ đủ điều kiện
			System.out.println("================================");
			System.out.println("|  Các Trẻ có thể đăng kí được |");
			System.out.println("================================");
			System.out.println("| STT |     Tên Trẻ     | Tuổi |");
			System.out.println("================================");
			int row = 0;
			ArrayList<String> idTreList = new ArrayList<String>();
			ArrayList<String> tenTreList = new ArrayList<String>();
			while(rs.next()) {
				row++;
				System.out.printf("|%5d|%17s|%6s|\n",row, rs.getString("TenTre"),rs.getString("tuoi"));
				idTreList.add(rs.getString("IDTre"));
				tenTreList.add(rs.getString("TenTre"));
			}
			System.out.println("===============================================");
			System.out.println("Mời chọn trẻ bạn muốn đăng kí (1->" + (idTreList.size()) +"): ");
			
			while(true) {
				//Chọn trẻ muốn đăng ký
				String choice = sc.nextLine(); 
				try {
					int choosse = Integer.parseInt(choice);
					if(choosse > 0 && choosse <= idTreList.size()) {
						choosse--;
						System.out.println("Bạn chọn trẻ " + tenTreList.get(choosse) + " để đăng ký");
						
						insertDangKyLopHoc(idTreList.get(choosse),idLop);
						
						break;
					} else {
						System.out.println("Bạn đã nhập sai, mời nhập lại!");
					}
					
				} catch (NumberFormatException e) {
					System.out.println("Bạn Đã nhập sai mời nhập lại");
				}catch (Exception e) {
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
	}

	private static void insertDangKyLopHoc(String idTre, String idLop) {
		Connection conn = null;
		PreparedStatement prsPreparedStatement = null;
		
	
		String sql = "INSERT INTO DANGKYLOPHOC VALUES(?, ?, 'Unseen',0,GETDATE()); ";
		
		try {
			System.out.println(idTre);
			System.out.println(idLop);
			conn = ConnectionUtil.getConnection();
			prsPreparedStatement = conn.prepareStatement(sql);
			prsPreparedStatement.setString(1, idTre);
			prsPreparedStatement.setString(2, idLop);
			int row = prsPreparedStatement.executeUpdate();
			
			if(row > 0) {
				System.out.println("Đã Đăng ký thành công cho trẻ!");
			}
			
		} catch (SQLException i) {
			i.printStackTrace();
			System.out.println("Có Lỗi trong lúc tương tác dữ liệu");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Đăng ký thất bại");
		} finally {
			ConnectionUtil.closeConnection(null, prsPreparedStatement, conn);
		}
		
	}

	public static void checkDonDangKy(String user) {
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
				+ "	DATEDIFF(YEAR, tre.NgaySinh, GETDATE()) as tuoi,\r\n"
				+ "	dk.Status\r\n"
				+ "FROM DANGKYLOPHOC as dk\r\n"
				+ "	JOIN TREEM as tre ON dk.IDTre = tre.IDTre\r\n"
				+ "	JOIN PHUHUYNH as ph ON ph.IDPhuHuynh = tre.IDPhuHuynh\r\n"
				+ "	JOIN ACCOUNT as acc ON acc.Username = ph.Username\r\n"
				+ "	JOIN LOPNANGKHIEU as lop ON lop.IDLop = dk.IDLop\r\n"
				+ "	JOIN MONHOC as mh ON lop.IDMonHoc = mh.IDMonHoc\r\n"
				+ "WHERE dk.Status != 'Approved' AND dk.Status != 'Declined' AND acc.Username = ?";

		try {
			conn = ConnectionUtil.getConnection();
			prsPreparedStatement = conn.prepareStatement(sql);
			prsPreparedStatement.setString(1, user);
			rs = prsPreparedStatement.executeQuery();

			if (!rs.isBeforeFirst()) {
				System.out.println("Hiện không có đơn nào để xem");
			}

			while (rs.next()) {
				System.out.println("===========================================================================");
				System.out.println("|");
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
}

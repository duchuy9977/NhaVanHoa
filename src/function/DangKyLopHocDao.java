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
			conn = ConnectionUtil.getConnection();
			prsPreparedStatement = conn.prepareStatement(sql);
			prsPreparedStatement.setString(1, idTre);
			prsPreparedStatement.setString(2, idLop);
			int row = prsPreparedStatement.executeUpdate();
			
			if(row > 0) {
				System.out.println("Đã Đăng ký thành công cho trẻ!");
			} else {
				System.out.println("Đăng ký thất bại!");
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
	
	

	public static ArrayList<Integer> checkDonDangKy(String user) {
		Connection conn = null;
		PreparedStatement prsPreparedStatement = null;
		ArrayList<Integer> listIDDangKy = new ArrayList<Integer>();
		ResultSet rs = null;
		String sql = "SELECT \r\n"
				+ "	dk.IDDangKy,\r\n"
				+ "	tre.IDTre,\r\n"
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
				+ "	mh.TenMon,\r\n"
				+ "	DATEDIFF(YEAR, tre.NgaySinh, GETDATE()) as tuoi,\r\n"
				+ "	dk.Status\r\n"
				+ "FROM DANGKYLOPHOC as dk\r\n"
				+ "	JOIN TREEM as tre ON dk.IDTre = tre.IDTre\r\n"
				+ "	JOIN PHUHUYNH as ph ON ph.IDPhuHuynh = tre.IDPhuHuynh\r\n"
				+ "	JOIN ACCOUNT as acc ON acc.Username = ph.Username\r\n"
				+ "	JOIN LOPNANGKHIEU as lop ON lop.IDLop = dk.IDLop\r\n"
				+ "	JOIN MONHOC as mh ON lop.IDMonHoc = mh.IDMonHoc\r\n"
				+ "WHERE dk.Status != 'Approved' AND dk.Status != 'Declined' AND dk.Status != 'Withdrawn' AND acc.Username = ?";

		try {
			conn = ConnectionUtil.getConnection();
			prsPreparedStatement = conn.prepareStatement(sql);
			prsPreparedStatement.setString(1, user);
			rs = prsPreparedStatement.executeQuery();

			if (!rs.isBeforeFirst()) {
				System.out.println("Hiện không có đơn nào để xem");
			}

			int row = 0;
			System.out.println("========================================================================================================================================");
			System.out.println("|STT|Id Đăng ký| ID Trẻ |       Tên Trẻ      | Tuổi | ID Lớp |     Tên Lớp     |   Môn học   |Ngày Bắt Đầu|Ngày Kêt Thúc|Tình trạng đơn|");
			System.out.println("========================================================================================================================================");
			while (rs.next()) {
				String tinhTrang = null;
				if(rs.getString("Status").equals("Withdrawn")) {
					tinhTrang = "Đã rút đơn";
				} else if (rs.getString("Status").equals("Unseen")) {
					tinhTrang = "Chưa xem";
				} else if (rs.getString("Status").equals("Pending")) {
					tinhTrang = "Đang Đợi Duyệt";
				} else {
					tinhTrang = "Khác";
				}
				row++;
				System.out.printf("|%3d|%10d|%8s|%20s|%6d|%8s|%17s|%13s|%12s|%13s|%14s|\n",row,rs.getInt("IDDangKy"),rs.getString("IDTre"),rs.getString("TenTre"),rs.getInt("tuoi"),rs.getString("IDLop"),rs.getString("TenLop"),rs.getString("TenMon"),rs.getDate("NgayBatDau") + "",rs.getDate("NgayKetThuc") + "",tinhTrang);
				listIDDangKy.add(rs.getInt("IDDangKy"));
			}
			System.out.println("========================================================================================================================================");
			
			


		} catch (SQLException i) {
			i.printStackTrace();
			System.out.println("Có Lỗi trong lúc tương tác dữ liệu");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Chọn Trẻ thất bại");
		} finally {
			ConnectionUtil.closeConnection(null, prsPreparedStatement, conn);
		}
		return listIDDangKy;
		
	}
	
	public static ArrayList<Integer> hienThiDonDangKyTheoID(int idDangKy) {
		Connection conn = null;
		PreparedStatement prsPreparedStatement = null;
		ArrayList<Integer> listIDDangKy = new ArrayList<Integer>();
		ResultSet rs = null;
		String sql = "SELECT \r\n"
				+ "	dk.IDDangKy,\r\n"
				+ "	tre.IDTre,\r\n"
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
				+ "	mh.TenMon,\r\n"
				+ "	DATEDIFF(YEAR, tre.NgaySinh, GETDATE()) as tuoi,\r\n"
				+ "	dk.Status\r\n"
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
			prsPreparedStatement.setInt(1, idDangKy);
			rs = prsPreparedStatement.executeQuery();

			if (!rs.isBeforeFirst()) {
				System.out.println("Hiện không có đơn nào để xem");
			}

			int row = 0;
			System.out.println("========================================================================================================================================");
			System.out.println("|STT|Id Đăng ký| ID Trẻ |       Tên Trẻ      | Tuổi | ID Lớp |     Tên Lớp     |   Môn học   |Ngày Bắt Đầu|Ngày Kêt Thúc|Tình trạng đơn|");
			System.out.println("========================================================================================================================================");
			while (rs.next()) {
				String tinhTrang = null;
				if(rs.getString("Status").equals("Withdrawn")) {
					tinhTrang = "Đã rút đơn";
				} else if (rs.getString("Status").equals("Unseen")) {
					tinhTrang = "Chưa xem";
				} else if (rs.getString("Status").equals("Pending")) {
					tinhTrang = "Đang Đợi Duyệt";
				} else {
					tinhTrang = "Khác";
				}
				row++;
				System.out.printf("|%3d|%10d|%8s|%20s|%6d|%8s|%17s|%13s|%12s|%13s|%14s|\n",row,rs.getInt("IDDangKy"),rs.getString("IDTre"),rs.getString("TenTre"),rs.getInt("tuoi"),rs.getString("IDLop"),rs.getString("TenLop"),rs.getString("TenMon"),rs.getDate("NgayBatDau") + "",rs.getDate("NgayKetThuc") + "",tinhTrang);
				
			}
			System.out.println("========================================================================================================================================");
			
			


		} catch (SQLException i) {
			i.printStackTrace();
			System.out.println("Có Lỗi trong lúc tương tác dữ liệu");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Chọn Trẻ thất bại");
		} finally {
			ConnectionUtil.closeConnection(null, prsPreparedStatement, conn);
		}
		return listIDDangKy;
		
	}

	public static void rutDonDangKy(ArrayList<Integer> listIDDonDangKy) {
		System.out.println("Mời bạn nhập đơn đăng kí cần xóa");
		int choosse = 0;
		while(true) {
			Scanner sc = new Scanner(System.in);
			//Chọn đơn đăng ký cần rút
			String choice = sc.nextLine(); 
			
			try {
				choosse = Integer.parseInt(choice);
				if(choosse > 0 && choosse <= listIDDonDangKy.size()) {
					choosse--;
					System.out.println("Bạn chọn rút đơn đăng ký " + listIDDonDangKy.get(choosse));					
				} else {
					System.out.println("Bạn đã nhập sai, mời nhập lại!");
				}
				
			} catch (NumberFormatException e) {
				System.out.println("Bạn Đã nhập sai mời nhập lại");
			}catch (Exception e) {
				System.out.println("Đã có lỗi xảy ra, mời nhập lại");

			}
			
			hienThiDonDangKyTheoID(listIDDonDangKy.get(choosse));
			System.out.println("|");
			System.out.println("|     Bạn có chắc chăn muốn rút đơn đăng ký này? ");
			System.out.println("|      ");
			System.out.println("|     Rút Đơn (Y)        Không rút(N)");
			System.out.println("|      ");
			System.out.println("|     Mời bạn nhập lựa chọn: ");
			System.out.println("========================================================================================================================================");
			break;
			
		}
		
		
		while (true) {
			Scanner sc= new Scanner(System.in);
			String choice = sc.nextLine(); // Chọn môn đăng ký
			switch (choice) {
				case "y":
				case "Y":
					System.out.println("Bạn đã chọn Hủy đơn đăng ký này");
					huyDon(listIDDonDangKy.get(choosse));
					break;
				case "n":
				case "N":
					System.out.println("Bạn đã chọn không hủy đơn đăng ký này");
					return;
				default:
					System.out.println("Bạn đã nhập sai, mời nhập lại");
					break;
			}
			break;
		}
		
		
		
	}

	private static void huyDon(Integer idDonDangKy) {
		Connection conn = null;
		PreparedStatement prsPreparedStatement = null;
		
	
		String sql = "Update DANGKYLOPHOC SET Status = 'Withdrawn' WHERE IDDangKy = ?; ";
		
		try {
			conn = ConnectionUtil.getConnection();
			prsPreparedStatement = conn.prepareStatement(sql);
			prsPreparedStatement.setInt(1, idDonDangKy);
			int row = prsPreparedStatement.executeUpdate();
			
			if(row > 0) {
				System.out.println("Bạn đã rút Đơn với ID " + idDonDangKy);
			} else {
				System.out.println("Rút đơn thất bại!");
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
}

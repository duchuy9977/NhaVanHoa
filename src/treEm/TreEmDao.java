package treEm;

import java.awt.DisplayMode;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Scanner;

import utils.ConnectionUtil;
import validate.validate;

public class TreEmDao {
	public void inserIntoDB(TreEm tre) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(
					"INSERT INTO TreEm(IDTre,IDPhuHuynh,STT,TenTre,NgaySinh ,TruongDangHoc,GioiTinh) VALUES (?,?,?,?,?,?,?)");
			ps.setString(1, tre.IDTre);
			ps.setString(2, tre.IDPhuHuynh);
			ps.setInt(3, tre.STT);
			ps.setString(4, tre.TenTre);
			ps.setDate(5, tre.NgaySinh);
			ps.setString(6, tre.TruongDangHoc);
			ps.setString(7, tre.GioiTinh);
			int x = ps.executeUpdate();
			if (x != 0) {
				System.out.println("Insert succesfully");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	public boolean checkIDTre(String IDTre) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement("SELECT * FROM TREEM WHERE IDTre = ?");
			ps.setString(1, IDTre);
			rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeConnection(rs, ps, con);
		}
		return true;
	}

	public static Date checkNgaySinh() {
		Date NgaySinh = null;
		boolean choice = true;
		while (choice) {
			NgaySinh = validation.inputNgaySinh("Nhập ngày sinh của trẻ "); // Ngày sinh của người

			LocalDate today = LocalDate.now(); // Ngày hiện tại
			LocalDate birthDate = NgaySinh.toLocalDate(); // Chuyển đổi Date sang LocalDate

			int age = Period.between(birthDate, today).getYears(); // Tính số năm giữa ngày hiện tại và ngày sinh

			if (age >= 5 && age <= 15) {
				choice = false;
			} else {
				System.out.println("Tuổi của trẻ phải từ 5 đến 15 tuổi. Mời nhập lại ngày sinh");
			}
		}
		return NgaySinh;
	}

	public void seachByTen() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			validation validate = new validation();
			String TenTre = validate.inputString("Xin hãy nhập tên trẻ");
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement("SELECT * FROM TREEM where TenTre = ?");
			ps.setString(1, TenTre);
			rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) {
				System.out.println("Không có tên trẻ trong bảng");
				return;
			}
			displayRS(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeConnection(rs, ps, con);
		}
	}

	public ArrayList<TreEm> displayRS(ResultSet rs) {
		ArrayList<TreEm> list = new ArrayList<>();

		try {

			while (rs.next()) {
				String IDTre = rs.getString("IDTre");
				String IDPhuHuynh = rs.getString("IDPhuHuynh");
				int STT = rs.getInt("STT");
				String TenTre = rs.getString("TenTre");
				Date NgaySinh = rs.getDate("NgaySinh");
				String TruongDangHoc = rs.getString("TruongDangHoc");
				String GioiTinh = rs.getString("GioiTinh");
				String Status = rs.getString("Status");
				TreEm x = new TreEm(IDTre, IDPhuHuynh, STT, TenTre, NgaySinh, TruongDangHoc, GioiTinh, Status);
				System.out.println(x.toString());
				list.add(x);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//Của Chủ tịch, Đừng Xóa :v
	public ArrayList<TreEm> displayRSVersion2(ResultSet rs) {
		ArrayList<TreEm> list = new ArrayList<>();

		try {

			while (rs.next()) {
				String IDTre = rs.getString("IDTre");
				String IDPhuHuynh = rs.getString("IDPhuHuynh");
				int STT = rs.getInt("STT");
				String TenTre = rs.getString("TenTre");
				Date NgaySinh = rs.getDate("NgaySinh");
				String TruongDangHoc = rs.getString("TruongDangHoc");
				String GioiTinh = rs.getString("GioiTinh");
				String Status = rs.getString("Status");
				TreEm x = new TreEm(IDTre, IDPhuHuynh, STT, TenTre, NgaySinh, TruongDangHoc, GioiTinh, Status);
				list.add(x);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int inputSTT(String username) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int STT = 0;
		try {
			validation validate = new validation();
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement("SELECT * FROM PHUHUYNH WHERE Username = ? ");
			ps.setString(1, username);
			rs = ps.executeQuery();
			String dem = null;
			while (rs.next()) {
				dem = rs.getString("IDPhuHuynh");
			}

			ps = con.prepareStatement("SELECT COUNT(IDPhuHuynh) as COUNT FROM TREEM  WHERE IDPhuHuynh= ?");
			ps.setString(1, dem);
			rs = ps.executeQuery();

			while (rs.next()) {
				STT = rs.getInt("Count");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeConnection(rs, ps, con);
		}
		return ++STT;
	}

	public void updateTruongDangHoc() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			validation validate = new validation();
			String TenTre = validate.inputString("Xin hãy nhập tên trẻ");
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement("SELECT * FROM TREEM where TenTre = ?");
			ps.setString(1, TenTre);
			rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) {
				System.out.println("Không có tên trẻ trong bảng. Mời nhập lại");
				return;
			}
			ps.close();
			rs.close();
			String TruongDangHoc = validate.inputString("Nhập vào Trường Đang Học mới:");
			PreparedStatement ps1 = con.prepareStatement("UPDATE TREEM set TruongDangHoc = ? where TenTre = ?");
			ps1.setString(1, TruongDangHoc);
			ps1.setString(2, TenTre);
			int x = ps1.executeUpdate();
			if (x != 0) {
				System.out.println("Đã update thành công");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeConnection(rs, ps, con);
		}
	}

	public void deleteIDTre() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			validation validate = new validation();
			String IDTre = validate.inputIDTre("Xin hãy nhập IDTre muốn xóa");
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement("SELECT * FROM TREEM where IDTre = ?");
			ps.setString(1, IDTre);
			rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) {
				System.out.println("Không có IDTre trong bảng. Mời nhập lại");
				return;
			}
			ps.close();
			rs.close();
			PreparedStatement ps1 = con.prepareStatement(
					"delete from HOCPHI where IDDangKy IN (Select IDDangKy from DANGKYLOPHOC  WHERE IDTre = ?)\r\n"
							+ "  delete from DANGKYLOPHOC where IDTre IN (Select IDTre from TREEM  WHERE IDTre = ? );\r\n"
							+ "  delete from TREEM where IDTre = ?");
			ps1.setString(1, IDTre);
			ps1.setString(2, IDTre);
			ps1.setString(3, IDTre);
			int x = ps1.executeUpdate();
			if (x != 0) {
				System.out.println("Đã xóa thành công IDTre");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeConnection(rs, ps, con);
		}
	}

	public void sortGioiTinh() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			validation validate = new validation();
			String GioiTinh = validate.inputGioiTinh("Xin hãy nhập giới tính muốn liệt kê");
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement("SELECT * FROM TREEM where GioiTinh = ?");
			ps.setString(1, GioiTinh);
			rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) {
				System.out.println("Không có thông tin phù hợp. Mời nhập lại");
				return;
			}
			displayRS(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeConnection(rs, ps, con);
		}
	}

	public void seachDaThongQua() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			validation validate = new validation();
			String Status = "Approved";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(
					"Select * from TreEm where IDTre in (Select IDTre from DANGKYLOPHOC where Status = ?)");
			ps.setString(1, Status);
			rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) {
				System.out.println("Không có thông tin trẻ em đã duyệt đăng ký.");
				return;
			}
			displayRS(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeConnection(rs, ps, con);
		}
	}

	public void seachTop3MonHoc() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			validation validate = new validation();
			int x = validate.inputSoThang("Vui lòng nhập tháng trong năm 2023");
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement("SELECT  top 3  MonHoc.TenMon from MonHoc \r\n"
					+ "join LOPNANGKHIEU on MONHOC.IDMonHoc = LOPNANGKHIEU.IDMonHoc\r\n"
					+ "join DANGKYLOPHOC on LOPNANGKHIEU.IDLop = DANGKYLOPHOC.IDLop\r\n"
					+ "where month(LOPNANGKHIEU.NgayKhaiGiang) = ? AND YEAR(LOPNANGKHIEU.NgayKhaiGiang) = 2023\r\n"
					+ "group by MonHoc.TenMon");
			ps.setInt(1, x);
			rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) {
				System.out.println("Không có môn học nào trong tháng " + x + "/2023");
				return;
			} else {
				while (rs.next()) {
					System.out.println(rs.getString("TenMon"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeConnection(rs, ps, con);
		}
	}

	public void sortAnhEm() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement("SELECT *\r\n" + "FROM TREEM\r\n" + "WHERE IDPhuHuynh IN (\r\n"
					+ "	SELECT IDPhuHuynh\r\n" + "	FROM TREEM\r\n" + "	GROUP BY IDPhuHuynh\r\n"
					+ "	HAVING COUNT(*) > 1\r\n" + ")\r\n" + "ORDER BY IDPhuHuynh , STT");
			rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) {
				System.out.println("Không có thông tin phù hợp. Mời nhập lại");
				return;
			}
			displayRS(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeConnection(rs, ps, con);
		}
	}

	public boolean checkTableTreEm() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement("select * from TREEM");
			rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) {
				System.out.println("Hiện chưa có thông tin trẻ em nào. Xin hãy nhập thông tin trẻ em trước.");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeConnection(rs, ps, con);
		}
		return true;
	}
}
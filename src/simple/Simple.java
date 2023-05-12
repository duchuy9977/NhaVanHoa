package simple;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import connection.ConnectionUtil;
import entities.LopNangKhieu;
import entities.MonHoc;
import function.DangKyLopHocDao;
import function.MonHocDao;
import validate.validate;

public class Simple {
	private static Scanner sc = new Scanner(System.in);


	public String insertdata(entities.LopNangKhieu lop) {
		Connection con = ConnectionUtil.getConnection();
		String sql = "insert into LOPNANGKHIEU(IDLop,IDMonHoc,TenLop,SoBuoi,NgayBatDau,NgayKetThuc,SoLuongHocVienToiDa)values (\r\n"
				+ "?,?,?,?,?,?,?)";
		try {
			PreparedStatement prsttm = con.prepareStatement(sql);
			prsttm.setString(1, lop.getIdlop());
			prsttm.setString(2, lop.getIdmonhoc());
			prsttm.setString(3, lop.getTenlop());
			prsttm.setInt(4, lop.getSobuoi());
			prsttm.setDate(5, lop.getNgaybatdau());
			prsttm.setDate(6, lop.getNgayketthuc());
			prsttm.setInt(7, lop.getSoLuongHocVienToiDa());
			int numberRecords = prsttm.executeUpdate();
			if (numberRecords == 0) {
				System.out.println("insert Thất Bại");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "insert Thành công";
	}

	public List<entities.LopNangKhieu> selectall() {
		List<entities.LopNangKhieu> list = new ArrayList<>();
		Connection con = ConnectionUtil.getConnection();
		String sql = "select * from LOPNANGKHIEU";
		try {
			Statement sttm = con.createStatement();
			ResultSet rs = sttm.executeQuery(sql);
			while (rs.next()) {
				entities.LopNangKhieu lop = new entities.LopNangKhieu();
				lop.setIdlop(rs.getString("IDLop"));
				lop.setIdmonhoc(rs.getString("IDMonHoc"));
				lop.setTenlop(rs.getString("TenLop"));
				lop.setSobuoi(rs.getInt("SoBuoi"));
				lop.setNgaybatdau(rs.getDate("NgayBatDau"));
				lop.setNgayketthuc(rs.getDate("NgayKetThuc"));
				lop.setSoLuongHocVienToiDa(rs.getInt("SoLuongHocVienToiDa"));
				list.add(lop);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public void delete() {
		Connection conn = null;
		PreparedStatement prstmt = null;
		ResultSet rs = null;
		try {
			validate validation = new validate();
			String idlop = validation.inputidlop("hay nhap ma so id lop mà bạn muốn xoá");
			conn = ConnectionUtil.getConnection();
			String sql = "select * from LOPNANGKHIEU where IDLop = ?";
			prstmt = conn.prepareStatement(sql);
			prstmt.setString(1, idlop);
			rs = prstmt.executeQuery();
			if (!rs.isBeforeFirst()) {
				System.out.println("Ma id lop khong ton tai!");
				return;
			}
			prstmt.close();
			rs.close();
			String sql1 = "delete from HOCPHI where IDDangKy in (select IDDangKy from DANGKYLOPHOC where IDLop = ?)\r\n"
					+ "delete from DANGKYLOPHOC where IDLop = ?\r\n" + "delete from BUOIHOC where IDLop = ?\r\n"
					+ "delete from THONGTINTUYENSINH where IDLop = ?\r\n" + "delete from LOPNANGKHIEU where IDLop= ?";
			PreparedStatement pr = conn.prepareStatement(sql1);
			pr.setString(1, idlop);
			pr.setString(2, idlop);
			pr.setString(3, idlop);
			pr.setString(4, idlop);
			pr.setString(5, idlop);
			int rowsDeleted = pr.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("Dữ liệu đã được xóa thành công.");
			}
		} catch (SQLException i) {
			i.printStackTrace();
			System.out.println("delete thất bại");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("delete thất bại");
		} finally {
			ConnectionUtil.closeConnection(null, prstmt, conn);
		}
		System.out.println("-----------------------------------------------------------------------------");
	}

	public String updatedata() {
		Connection con = ConnectionUtil.getConnection();
		String sql = "update LOPNANGKHIEU set IDMonHoc = ?, TenLop=?,SoBuoi=?,NgayBatDau=?,NgayKetThuc=?,SoLuongHocVienToiDa=? where IDLop = ?";
		try {
			validate vali = new validate();
			Simple sim = new Simple();
			String idmonhoc = sim.Checkexistidmonhoc();
			String tenlop = vali.inputstring("Nhập vào tên lớp");
			int sobuoi = vali.inputsobuoi("Nhập vào số buổi");
			Date ngaybatdau = vali.inputdate("Nhập vào ngày bắt đầu");
			Date ngayketthuc = vali.inputdate("Nhập vào ngày kết thúc");
			int soluonghocvientoida = vali.inputsobuoi("Nhập vào só lượng học viên tối đa");
			String idlop = sim.Checkidlop();
			PreparedStatement pr = con.prepareStatement(sql);
			pr.setString(1, idmonhoc);
			pr.setString(2, tenlop);
			pr.setInt(3, sobuoi);
			pr.setDate(4, ngaybatdau);
			pr.setDate(5, ngayketthuc);
			pr.setInt(6, soluonghocvientoida);
			pr.setString(7, idlop);
			int rowsUpdated = pr.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Dữ liệu đã được cập nhật thành công.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "update thành công";
	}

	public List<entities.LopNangKhieu> selectsobuoi() {
		ArrayList<entities.LopNangKhieu> sobuoi = new ArrayList<>();
		Connection con = null;
		PreparedStatement pr = null;
		ResultSet rs = null;
		try {
			con = ConnectionUtil.getConnection();
			validate vali = new validate();
			int sobuoi1 = vali.inputsobuoi("Mời bạn nhập vào số buổi học 1");
			int sobuoi2 = vali.inputsobuoi("Mời bạn nhập vào số buổi học 2");
			String sql = "select * from LOPNANGKHIEU where SoBuoi > ? and SoBuoi < ?";
			pr = con.prepareStatement(sql);
			pr.setInt(1, sobuoi1);
			pr.setInt(2, sobuoi2);
			rs = pr.executeQuery();
			if (!rs.isBeforeFirst()) {
				System.out.println("không có yêu cầu nào thỏa mãn yêu cầu");
			}
			while (rs.next()) {
				entities.LopNangKhieu lop = new entities.LopNangKhieu();
				lop.setIdlop(rs.getString("IDLop"));
				lop.setIdmonhoc(rs.getString("IDMonHoc"));
				lop.setTenlop(rs.getString("TenLop"));
				lop.setSobuoi(rs.getInt("SoBuoi"));
				lop.setNgaybatdau(rs.getDate("NgayBatDau"));
				lop.setNgayketthuc(rs.getDate("NgayKetThuc"));
				lop.setSoLuongHocVienToiDa(rs.getInt("SoLuongHocVienToiDa"));
				sobuoi.add(lop);
			}
		} catch (SQLException i) {
			i.printStackTrace();
			System.out.println("select thất bại ");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("select thất bại");
		} finally {
			ConnectionUtil.closeConnection(null, pr, con);
		}
		return sobuoi;

	}

	public String Checkexistidmonhoc() {
		Connection con = null;
		PreparedStatement pr = null;
		ResultSet rs = null;
		String idmonhoc = null;
		try {
			con = ConnectionUtil.getConnection();
			do {
				validate vali = new validate();
				idmonhoc = vali.inputidmonhoc("Mời bạn nhập vào ID môn học");
				String sql = "select * from MONHOC where IDMonHoc=?";
				pr = con.prepareStatement(sql);
				pr.setString(1, idmonhoc);
				rs = pr.executeQuery();
				if (!rs.isBeforeFirst()) {
					System.out.println("ID môn học không tồn tại");
				} else {
					return idmonhoc;
				}
			} while (true);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeConnection(rs, pr, con);
		}
		return idmonhoc;
	}


	public ArrayList<LopNangKhieu> inramanhinh1(ResultSet rs) {
		ArrayList<LopNangKhieu> list = new ArrayList<>();



		try {

			while (rs.next()) {
				String IDLop = rs.getString("IDLop");
				String IDMonHoc = rs.getString("IDMonHoc");
				String TenLop = rs.getString("Tenlop");
				int SoBuoi = rs.getInt("SoBuoi");
				Date NgayBatDau = rs.getDate("NgayBatDau");
				Date NgayKetThuc = rs.getDate("NgayKetThuc");
				int SoLuongTreTheoHoc = rs.getInt("SoLuongTreTheoHoc");
				LopNangKhieu x = new LopNangKhieu(IDLop, IDMonHoc, TenLop, SoBuoi, NgayBatDau, NgayKetThuc, SoLuongTreTheoHoc);
				x.setSohocsinhtheohoc(SoLuongTreTheoHoc);
				System.out.println(x.toString1());
				list.add(x);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<entities.LopNangKhieu> selec2() {
		List<entities.LopNangKhieu> list2 = new ArrayList<>();
		Connection con = null;
		Statement st = null;

		String sql = "select lnk.IDLop,lnk.IDMonHoc,lnk.TenLop,lnk.SoBuoi,lnk.NgayBatDau,lnk.NgayKetThuc,lnk.SoLuongHocVienToiDa, count (dklh.IDTre) as SoLuongTreTheoHoc\r\n"
				+ "from LOPNANGKHIEU as lnk\r\n"
				+ "inner join DANGKYLOPHOC as dklh on lnk.IDLop=dklh.IDLop\r\n"
				+ "group by lnk.IDLop,lnk.IDMonHoc,lnk.TenLop,lnk.SoBuoi,lnk.NgayBatDau,lnk.NgayKetThuc,lnk.SoLuongHocVienToiDa";

		try {
			con = ConnectionUtil.getConnection();
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				entities.LopNangKhieu lop1 = new entities.LopNangKhieu();
				lop1.setIdlop(rs.getString("IDLop"));
				lop1.setIdmonhoc(rs.getString("IDMonHoc"));
				lop1.setTenlop(rs.getString("TenLop"));
				lop1.setSobuoi(rs.getInt("SoBuoi"));
				lop1.setNgaybatdau(rs.getDate("NgayBatDau"));
				lop1.setNgayketthuc(rs.getDate("NgayKetThuc"));
				lop1.setSoLuongHocVienToiDa(rs.getInt("SoLuongHocVienToiDa"));
				lop1.setSohocsinhtheohoc(rs.getInt("SoLuongTreTheoHoc"));
				list2.add(lop1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list2;
	}

	public ArrayList<String> monhoct1003() {
		ArrayList<String> list = new ArrayList<String>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String lop1 = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "select TenMon from MONHOC";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				lop1 = rs.getString("TenMon");
				list.add(lop1);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public ArrayList<entities.LopNangKhieu> inranamhinh(ResultSet rs) {
		ArrayList<entities.LopNangKhieu> list = new ArrayList<>();
		try {
			while (rs.next()) {
				String IDLop = rs.getString("IDLop");
				String IDMonHoc = rs.getString("IDMonHoc");
				String Tenlop = rs.getString("TenLop");
				int SoBuoi = rs.getInt("SoBuoi");
				Date NgayBatDau = rs.getDate("NgayBatDau");
				Date NgayKetThuc = rs.getDate("NgayKetThuc");

				int soluonghocvientoida = rs.getInt("SoLuongHocVienToiDa");
				entities.LopNangKhieu lop = new entities.LopNangKhieu(IDLop, IDMonHoc, Tenlop, SoBuoi, NgayBatDau, NgayKetThuc, soluonghocvientoida);

				System.out.println(lop.toString());
				list.add(lop);
			}
		} catch (SQLException e) {
			// TODO: handle exception
		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;
	}

	public void ClickMonHoc(String message, ArrayList<MonHoc> monHoc, int countMonHoc, String userName) {
		Connection con = null;
		PreparedStatement pr = null;
		ResultSet rs = null;
		try {
			System.out.println(message);
			int choosse = -1;
			while (true) {
				String choice = sc.nextLine(); // Chọn môn đăng ký
				try {
					choosse = Integer.parseInt(choice);
					if (choosse > 0 && choosse <= countMonHoc) {
						choosse--;
						System.out.println("Bạn Đã chọn đăng kí môn " + monHoc.get(choosse).getTenMon());
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

			con = ConnectionUtil.getConnection();
			// Select ra các lớp thỏa điều kiện đăng ký gồm ngày bắt đầu ko trễ quá 2 tuần và còn trống chỗ
			String sql = "SELECT lop.IDLop, lop.TenLop, lop.NgayBatDau, lop.SoLuongHocVienToiDa, COUNT(lop.IDLop) as SL FROM LOPNANGKHIEU as lop\r\n"
					+ "	JOIN MONHOC as mh \r\n"
					+ "	ON lop.IDMonHoc = mh.IDMonHoc \r\n"
					+ "	JOIN DANGKYLOPHOC as dk\r\n"
					+ "	ON dk.IDLop = lop.IDLop\r\n"
					+ "	WHERE mh.IDMonHoc = ? AND Status = 'Approved' AND DATEDIFF(DAY, lop.NgayBatDau, GETDATE())<=14\r\n"
					+ "	GROUP BY lop.IDLop, lop.SoLuongHocVienToiDa, lop.TenLop, lop.NgayBatDau, lop.SoLuongHocVienToiDa\r\n"
					+ "	HAVING COUNT(lop.IDLop) < lop.SoLuongHocVienToiDa";
			pr = con.prepareStatement(sql);
			pr.setString(1, monHoc.get(choosse).getIdMonHoc());
			rs = pr.executeQuery();

			ArrayList<LopNangKhieu> lopList = new ArrayList<LopNangKhieu>();
			if (!rs.isBeforeFirst()) {
				System.out.println("không có môn học nào thỏa mãn yêu cầu");
				System.out.println("===============================================");
				return;
			} else {
				//Hiển thị các môn học đủ điều kiện đăng kí gồm còn chỗ trống và 
				System.out.println("===============================================");
				System.out.println("| ------> Các Lớp có thể đăng kí được <------ |");
				System.out.println("===============================================");
				System.out.println("|STT|      Tên Lớp    |Ngày Bắt Đầu|Đã Đăng Ký|");
				System.out.println("===============================================");
				int row = 0;
				while (rs.next()) {
					LopNangKhieu lopHoc = new LopNangKhieu();
					row++;
					System.out.printf("|%3d|%17s|%12s|     %2d/%2d|\n", row, rs.getString("TenLop"),
							rs.getDate("NgayBatDau") + "", rs.getInt("SL"), rs.getInt("SoLuongHocVienToiDa"));
					lopHoc.setIdlop(rs.getString("IDLop"));
					lopHoc.setTenlop(rs.getString("TenLop"));
					lopList.add(lopHoc);
				}
				System.out.println("===============================================");
				System.out.println("Mời nhập lớp bạn muốn đăng kí (1->" + (lopList.size()) + "): ");
			}
			while (true) {
				// Chọn Lớp muốn đăng ký
				String choice = sc.nextLine(); 
				try {
					choosse = Integer.parseInt(choice);
					if (choosse > 0 && choosse <= lopList.size()) {
						choosse--;
						System.out.println("Bạn Đã chọn đăng kí lớp " + lopList.get(choosse).getIdlop() + " - "
								+ lopList.get(choosse).getTenlop());

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
			// Đăng ký lớp theo ID
			DangKyLopHocDao.chonTreDangKy(lopList.get(choosse).getIdlop(), userName);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static boolean checkTenMH(String maDeThi) {
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int row = 0;
		try {
			String sql = "select COUNT(*) as soluong from MONHOC where TenMon=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, maDeThi);
			rs = pstm.executeQuery();
			rs.next();
			row = rs.getInt("soluong");

		} catch (Exception e) {
			e.fillInStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstm != null) {
					pstm.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e2) {
				e2.fillInStackTrace();
			}
		}
		if (row > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void dangkihoc(String userName) {
		Simple sim1 = new Simple();

		System.out.println("==========================================="); // Hiển thị các môn học có thể đăng kí
		System.out.println("|    Mời bạn chọn môn học muốn đăng ký    |");
		System.out.println("===========================================");
		ArrayList<MonHoc> monHoc = MonHocDao.getMonHoc();
		int countMonHoc = 0;
		if (monHoc != null) {
			for (MonHoc item : monHoc) {
				System.out.printf("%8s. %s", (countMonHoc + 1), item.getTenMon());
				System.out.println();
				countMonHoc++;
			}
		}
		System.out.println("===========================================");
		sim1.ClickMonHoc("Mời bạn chọn môn học muốn đăng ký(1->" + (countMonHoc - 1) + ") : ", monHoc, countMonHoc,userName);//Chọn môn học
		
	}

	// ----------tim kiếm thông tin theo usename giáo viên
	public List<entities.LopNangKhieu> timkiemthongtintheousename() {
		List<entities.LopNangKhieu> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pr = null;
		ResultSet rs = null;
		try {
			System.out.println("mời bạn nhập usename giáo viên cần tìm ");
			String usename = sc.nextLine();
			con = ConnectionUtil.getConnection();
			String sql = "select lnk.*,gv.Username from LOPNANGKHIEU as lnk \r\n" + "inner join MONHOC as mh\r\n"
					+ "on lnk.IDMonHoc=mh.IDMonHoc\r\n" + "inner join GIAOVIEN as gv\r\n"
					+ "on mh.IDMonHoc=gv.IDMonHoc\r\n" + "where gv.Username=?";
			pr = con.prepareStatement(sql);
			pr.setString(1, usename);
			rs = pr.executeQuery();
			if (!rs.isBeforeFirst()) {
				System.out.println("không có giáo viên theo yêu cầu của bạn");
			}
			inranamhinh(rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public boolean checktontaiidlop(String IDLop) {
		Connection con = null;
		PreparedStatement pr = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "select count(*) as soluong from LOPNANGKHIEU where IDLop=?;";
			pr = con.prepareStatement(sql);
			pr.setString(1, IDLop);
			rs = pr.executeQuery();
			rs.next();
			count = rs.getInt("soluong");

		} catch (Exception e) {
			e.fillInStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (pr != null) {
					pr.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e2) {
				e2.fillInStackTrace();
			}
		}
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checktontaiidmonhoc(String MonHoc) {
		Connection con = null;
		PreparedStatement pr = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "select count(*) as soluong from MONHOC where IDMonHoc=?";
			pr = con.prepareStatement(sql);
			pr.setString(1, MonHoc);
			rs = pr.executeQuery();
			rs.next();
			count = rs.getInt("soluong");

		} catch (Exception e) {
			e.fillInStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (pr != null) {
					pr.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e2) {
				e2.fillInStackTrace();
			}
		}
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	public String Checkidlop() {
		Connection con = null;
		PreparedStatement pr = null;
		ResultSet rs = null;
		String idlop = null;
		try {
			con = ConnectionUtil.getConnection();
			do {
				validate vali = new validate();
				idlop = vali.inputidlop("Mời bạn nhập vào ID Lớp cần update");
				String sql = "select * from LOPNANGKHIEU where IDLop=?";
				pr = con.prepareStatement(sql);
				pr.setString(1, idlop);
				rs = pr.executeQuery();
				if (!rs.isBeforeFirst()) {
					System.out.println("ID lớp không tồn tại");
				} else {
					return idlop;
				}
			} while (true);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeConnection(rs, pr, con);
		}
		return idlop;
	}

	
	public List<LopNangKhieu> caclopthieuhocsinh(){
		List<LopNangKhieu>list1 = new ArrayList<>();
		Connection con =null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con=ConnectionUtil.getConnection();
			String sql= "select lnk.IDLop,lnk.IDMonHoc,lnk.TenLop,lnk.NgayBatDau,lnk.NgayKetThuc,lnk.SoBuoi,lnk.SoLuongHocVienToiDa, count(dk.IDTre) as sohocsinhtheohoc from LOPNANGKHIEU lnk\r\n"
					+ "inner join DANGKYLOPHOC dk \r\n"
					+ "on lnk.IDLop=dk.IDLop\r\n"
					+ "where GETDATE()>lnk.NgayBatDau \r\n"
					+ "group by lnk.IDLop,lnk.IDMonHoc,lnk.TenLop,lnk.NgayBatDau,lnk.NgayKetThuc,lnk.SoBuoi,lnk.SoLuongHocVienToiDa\r\n"
					+ "having lnk.SoLuongHocVienToiDa>count(dk.IDTre)";
			Statement sttm = con.createStatement();
			rs = sttm.executeQuery(sql);
			if(!rs.isBeforeFirst()) {
				System.out.println("Không hoặc chưa có lớp thõa mãn yêu cầu của bạn");
			}
			while (rs.next()) {
				entities.LopNangKhieu lop1 = new entities.LopNangKhieu();
				lop1.setIdlop(rs.getString("IDLop"));
				lop1.setIdmonhoc(rs.getString("IDMonHoc"));
				lop1.setTenlop(rs.getString("TenLop"));
				lop1.setSobuoi(rs.getInt("SoBuoi"));
				lop1.setNgaybatdau(rs.getDate("NgayBatDau"));
				lop1.setNgayketthuc(rs.getDate("NgayKetThuc"));
				lop1.setSoLuongHocVienToiDa(rs.getInt("SoLuongHocVienToiDa"));
				lop1.setSohocsinhtheohoc(rs.getInt("sohocsinhtheohoc"));
				list1.add(lop1);
		}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list1;
}
}
	



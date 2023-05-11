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

import app.LopNangKhieu;
import connection.ConnectionUtil;
import entities.MonHoc;
import function.MonHocDao;
import validate.validate;

public class Simple {
	private static Scanner sc = new Scanner(System.in);
	public String insertdata (entities.LopNangKhieu lop) {
		Connection con = ConnectionUtil.getConnection() ; 
		String sql = "insert into LOPNANGKHIEU(IDLop,IDMonHoc,TenLop,SoBuoi,NgayKhaiGiang,NgayBatDau,NgayKetThuc)values (\r\n"
				+ "?,?,?,?,?,?,?)";
		try {
			PreparedStatement prsttm = con.prepareStatement(sql);
			prsttm.setString(1, lop.getIdlop());
			prsttm.setString(2, lop.getIdmonhoc());
			prsttm.setString(3, lop.getTenlop());
			prsttm.setInt(4, lop.getSobuoi());
			prsttm.setDate(5, lop.getNgaykhaigiang());
			prsttm.setDate(6, lop.getNgaybatdau());
			prsttm.setDate(7, lop.getNgayketthuc());
			int numberRecords = prsttm.executeUpdate();
			if (numberRecords == 0) {
				System.out.println("insert that bai");
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
		return "insert thanh cong";
	}
	
	public List<entities.LopNangKhieu> selectall() {
		List <entities.LopNangKhieu> list= new ArrayList<>();
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
				lop.setNgaykhaigiang(rs.getDate("NgayKhaiGiang"));
				lop.setNgaybatdau(rs.getDate("NgayBatDau"));
				lop.setNgayketthuc(rs.getDate("NgayKetThuc"));
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
				+ "delete from DANGKYLOPHOC where IDLop = ?\r\n"
				+ "delete from BUOIHOC where IDLop = ?\r\n"
				+ "delete from THONGTINTUYENSINH where IDLop = ?\r\n"
				+ "delete from LOPNANGKHIEU where IDLop= ?";
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
			System.out.println("delete that bai");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("delete that bai");
		} finally {
			ConnectionUtil.closeConnection(null, prstmt, conn);
		}
		System.out.println("-----------------------------------------------------------------------------");
	}
	
	public String updatedata() {
		Connection con = ConnectionUtil.getConnection();
		String sql = "update LOPNANGKHIEU set IDMonHoc = ?, TenLop=?,SoBuoi=?,NgayKhaiGiang=?,NgayBatDau=?,NgayKetThuc=? where IDLop = ?";
		try {
			validate vali = new validate();
			Simple sim = new Simple();
			String idmonhoc = sim.Checkexistidmonhoc();
			String tenlop = vali.inputstring("nhap vao ten lop");
			int sobuoi = vali.inputsobuoi("nhap vao so buoi");
			Date ngaykhaigiang = vali.inputdate("nhap vao ngay khai giang");
			Date ngaybatdau = vali.inputdate("nhap vao ngay bat dau");
			Date ngayketthuc = vali.inputdate("nhap vao ngay ket thuc");
			String idlop = vali.inputidlop("nhap vao id lop");
			PreparedStatement pr = con.prepareStatement(sql);
			pr.setString(1, idmonhoc);
			pr.setString(2, tenlop);
			pr.setInt(3, sobuoi);
			pr.setDate(4, ngaykhaigiang);
			pr.setDate(5, ngaybatdau);
			pr.setDate(6, ngayketthuc);
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
	return "update thanh cong";
	}
	
	public List<entities.LopNangKhieu> selectsobuoi (){
		ArrayList<entities.LopNangKhieu> sobuoi = new ArrayList<>();
		Connection con = null;
		PreparedStatement pr = null;
		ResultSet rs = null;
		try {
			con = ConnectionUtil.getConnection();
			validate vali = new validate();
			int sobuoi1 = vali.inputsobuoi("moi ban nhap so buoi hoc 1");
			int sobuoi2 = vali.inputsobuoi("moi ban nhap so buoi hoc 2");
			String sql = "select * from LOPNANGKHIEU where SoBuoi > ? and SoBuoi < ?";
			pr = con.prepareStatement(sql);
			pr.setInt(1, sobuoi1);
			pr.setInt(2, sobuoi2);
			rs=pr.executeQuery();
			if(!rs.isBeforeFirst()) {
				System.out.println("khong co giao vien nao thoa man yeu cau");
			}
			while (rs.next()) {
				entities.LopNangKhieu lop = new entities.LopNangKhieu();
				lop.setIdlop(rs.getString("IDLop"));
				lop.setIdmonhoc(rs.getString("IDMonHoc"));
				lop.setTenlop(rs.getString("TenLop"));
				lop.setSobuoi(rs.getInt("SoBuoi"));
				lop.setNgaykhaigiang(rs.getDate("NgayKhaiGiang"));
				lop.setNgaybatdau(rs.getDate("NgayBatDau"));
				lop.setNgayketthuc(rs.getDate("NgayKetThuc"));
				sobuoi.add(lop);
		}
		}catch (SQLException i) {
			i.printStackTrace();
			System.out.println("select giao vien that bai ");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("select giao vien that bai");
		} finally {
			ConnectionUtil.closeConnection(null, pr, con);
		}
		return sobuoi ;
		
	}
	
	public String Checkexistidmonhoc() {
		Connection con = null;
		PreparedStatement pr = null;
		ResultSet rs = null;
		String IDMonHoc = null;
		try {
			con = ConnectionUtil.getConnection();
			do {
				validate vali = new validate();
				String TenMonHoc = vali.inputidmonhoc("moi ban nhap vao ten mon hoc");
				String sql = "select * from MONHOC where TenMon = ?";
				pr = con.prepareStatement(sql);
				pr.setString(1, TenMonHoc);
				rs=pr.executeQuery();
				if(rs.next()) {
					 IDMonHoc = rs.getString("IDMonHoc");
					 return IDMonHoc;
				} System.out.println("Ten Mon hoc chua ton tai");
			}
			while(true);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeConnection(rs, pr, con);
		}
		return IDMonHoc;
	}
		
	public ArrayList<LopNangKhieu> inramanhinh(ResultSet rs) {
		ArrayList<LopNangKhieu> list = new ArrayList<>();

		try {

			while (rs.next()) {
				String IDLop = rs.getString("IDLop");
				String IDMonHoc = rs.getString("IDMonHoc");
				String TenLop = rs.getString("Tenlop");
				int SoBuoi = rs.getInt("SoBuoi");
				Date NgayKhaiGiang = rs.getDate("NgayKhaiGiang");
				Date NgayBatDau = rs.getDate("NgayBatDau");
				Date NgayKetThuc = rs.getDate("NgayKetThuc");
				int SoLuongTreTheoHoc = rs.getInt("SoLuongTreTheoHoc");
				LopNangKhieu x = new LopNangKhieu(IDLop, IDMonHoc, TenLop, SoBuoi, NgayKhaiGiang, NgayBatDau, NgayKetThuc);
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
		List <entities.LopNangKhieu> list2= new ArrayList<>();
		Connection con = null;
		Statement st = null;
		String sql = "select lnk.IDLop,lnk.IDMonHoc,lnk.TenLop,lnk.SoBuoi,lnk.NgayKhaiGiang,lnk.NgayBatDau,lnk.NgayKetThuc, count (dklh.IDTre) as SoLuongTreTheoHoc\r\n"
				+ "from LOPNANGKHIEU as lnk\r\n"
				+ "inner join DANGKYLOPHOC as dklh on lnk.IDLop=dklh.IDLop\r\n"
				+ "group by lnk.IDLop,lnk.IDMonHoc,lnk.TenLop,lnk.SoBuoi,lnk.NgayKhaiGiang,lnk.NgayBatDau,lnk.NgayKetThuc";
		try {
			con=ConnectionUtil.getConnection();
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				entities.LopNangKhieu lop1 = new entities.LopNangKhieu();
				lop1.setIdlop(rs.getString("IDLop"));
				lop1.setIdmonhoc(rs.getString("IDMonHoc"));
				lop1.setTenlop(rs.getString("TenLop"));
				lop1.setSobuoi(rs.getInt("SoBuoi"));
				lop1.setNgaykhaigiang(rs.getDate("NgayKhaiGiang"));
				lop1.setNgaybatdau(rs.getDate("NgayBatDau"));
				lop1.setNgayketthuc(rs.getDate("NgayKetThuc"));
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

	public ArrayList<LopNangKhieu> inranamninh(ResultSet rs) {
		ArrayList<LopNangKhieu> list = new ArrayList<>();
		try {
			while (rs.next()) {
				String IDLop = rs.getString("IDLop");
				String IDMonHoc = rs.getString("IDMonHoc");
				String Tenlop = rs.getString("TenLop");
				int SoBuoi = rs.getInt("SoBuoi");
				Date NgayKhaiGiang = rs.getDate("NgayKhaiGiang");
				Date NgayBatDau = rs.getDate("NgayBatDau");
				Date NgayKetThuc = rs.getDate("NgayKetThuc");
				LopNangKhieu lop = new LopNangKhieu(IDLop, IDMonHoc, Tenlop, SoBuoi, NgayKhaiGiang, NgayBatDau,
						NgayKetThuc);
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

	public void ClickMonHoc(String message) {
		Connection con = null;
		PreparedStatement pr = null;
		ResultSet rs = null;
		try {
			System.out.println(message);
			String tenmonhoc;
			while (true) {
				tenmonhoc = sc.nextLine();
				if(checkTenMH(tenmonhoc)==true) {
					break;
				}else {
					System.out.println("moi ban nhap dung ky tu ten mon hoc tren man hinh");
				}
			}
			con = ConnectionUtil.getConnection();
			String sql = "select lnk.* from LOPNANGKHIEU as lnk,MONHOC as mh where lnk.IDMonHoc=mh.IDMonHoc and mh.TenMon=? \r\n"
					+ "  and lnk.NgayKhaiGiang >= DATEADD(DAY, -14, GETDATE())\r\n"
					+ "  AND lnk.NgayKetThuc > GETDATE()";
			pr = con.prepareStatement(sql);
			pr.setString(1, tenmonhoc);
			rs = pr.executeQuery();
			if(!rs.isBeforeFirst()) {
				System.out.println("khong co mon hoc thoa man yeu cau");
				return;
			}
			inranamninh(rs);

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
	public void dangkihoc(){
		Simple sim1 = new Simple();
		
		System.out.println("===========================================");
		System.out.println("|    Mời bạn chọn môn học muốn đăng ký    |");  
		System.out.println("===========================================");
		ArrayList<MonHoc> monHoc = MonHocDao.getMonHoc();
		int countMonHoc = 0;
		if(monHoc != null) {
			for (MonHoc item : monHoc) {
				System.out.printf("%8s. %s", (countMonHoc + 1),item.getTenMon());
				System.out.println();
				countMonHoc++;
			}
		}
		System.out.println("===========================================");
		sim1.ClickMonHoc("Mời bạn chọn môn học muốn đăng ký");
		while(true) {
			
		}
}
}

	

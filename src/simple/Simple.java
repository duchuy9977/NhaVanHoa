package simple;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import connection.ConnectionUtil;
import validate.validate;

public class Simple {
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
			String idmonhoc = vali.inputidmonhoc("nhap vao id mon hoc");
			String tenlop = vali.inputstring("nhap vao id ten lop");
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
	
	
}

	

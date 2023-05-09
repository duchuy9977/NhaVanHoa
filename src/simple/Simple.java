package simple;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.LopNangKhieu;
import connection.ConnectionUtil;
import validate.validate;

public class Simple {
	public String insertdata (LopNangKhieu lop) {
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
	
	public List<LopNangKhieu> selectall() {
		List <LopNangKhieu> list= new ArrayList<>();
		Connection con = ConnectionUtil.getConnection();
		String sql = "select * from LOPNANGKHIEU";
		try {
			Statement sttm = con.createStatement();
			ResultSet rs = sttm.executeQuery(sql);
			while (rs.next()) {
				LopNangKhieu lop = new LopNangKhieu();
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
	
//	public String updatedata1() {
//		Connection con = ConnectionUtil.getConnection();
//		String sql = "update LOPNANGKHIEU set ? = ? where IDLop=?";
//		validate validate =new validate();
//		try {
//			String idlop = validate.inputidlop("nhap vao id lop");
//			String x=validate.abc();
//			String y=null;
//			Date y2 = null;
//			Date y3 = null;
//			Date y4 = null;
//			int y1=0;
//			PreparedStatement pr = con.prepareStatement(sql);
//			if(x.equals("IDMonHoc")) {
//				 y=validate.inputidmonhoc("moi nhap mon hoc");
//			pr.setString(1, x);
//			pr.setString(2, y);
//			pr.setString(3, idlop);
//			}
//			if(x.equals("TenLop")) {
//				 y=validate.inputstring("moi nhap ten lop");
//			pr.setString(1, x);
//			pr.setString(2, y);
//			pr.setString(3, idlop);
//			}
//			
//			if(x.equals("SoBuoi")) {
//				 y1=validate.inputsobuoi("moi nhap so buoi");
//			pr.setString(1, x);
//			pr.setInt(2, y1);
//			pr.setString(3, idlop);
//			}
//			
//			if(x.equals("NgayKhaiGiang")) {
//				 y2=validate.inputdate("moi nhap ngay khai giang");
//			pr.setString(1, x);
//			pr.setDate(2, y2);
//			pr.setString(3, idlop);
//			}
//			
//			if(x.equals("NgayBatDau")) {
//				 y3=validate.inputdate("moi nhap ngay bat dau");
//			pr.setString(1, x);
//			pr.setDate(2, y3);
//			pr.setString(3, idlop);
//			}
//			
//			if(x.equals("NgayKhaiGiang")) {
//				 y4=validate.inputdate("moi nhap ket thuc");
//			pr.setString(1, x);
//			pr.setDate(2, y4);
//			pr.setString(3, idlop);
//			}
//			int rowsUpdated = pr.executeUpdate();
//			if (rowsUpdated > 0) {
//			    System.out.println("Dữ liệu đã được cập nhật thành công.");
//			}
//			
//			
//			
//			PreparedStatement pr = con.prepareStatement(sql);
//			pr.setString(1, x);
//			if(x.equals("TenLop")){
//			pr.setString(2, y);
//			}
//			if(x.equals("SoBuoi")){
//				pr.setInt(2, y1);
//			}
//			
//			if(x.equals("NgayKhaiGiang")){
//				pr.setDate(2, y2);
//			}
//			if(x.equals("NgayBatDau")){
//				pr.setDate(2, y3);
//			}
//			if(x.equals("NgayKetThuc")){
//				pr.setDate(2, y4);
//			}
//			else{
//				pr.setString(2, y);
//			}
//			pr.setString(3, idlop);
//			pr.executeUpdate();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			try {
//				con.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	
//		return "update thanh cong";
//}
	
	public String delete() {
		Connection con = ConnectionUtil.getConnection();
		String sql = "delete from HOCPHI where IDDangKy in (select IDDangKy from DANGKYLOPHOC where IDLop = ?)\r\n"
				+ "delete from DANGKYLOPHOC where IDLop = ?\r\n"
				+ "delete from BUOIHOC where IDLop = ?\r\n"
				+ "delete from THONGTINTUYENSINH where IDLop = ?\r\n"
				+ "delete from LOPNANGKHIEU where IDLop= ?";
		try {
			validate vali = new validate();
			String idlop = vali.inputidlop("moi ban nhap vao id lop can xoa");
			PreparedStatement pr = con.prepareStatement(sql);
			pr.setString(1, idlop);
			pr.setString(2, idlop);
			pr.setString(3, idlop);
			pr.setString(4, idlop);
			pr.setString(5, idlop);
			int rowsDeleted = pr.executeUpdate();
			if (rowsDeleted > 0) {
			    System.out.println("Dữ liệu đã được xóa thành công.");
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
		}return "delete thanh cong";
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

	

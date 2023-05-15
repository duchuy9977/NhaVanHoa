package buoiHoc_PhucHL1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConnectionUtil;

public class BuoiHocDao {
	public void Function() {
	}
	//function thêm mới buổi học. 
	public void insertBuoiHoc(ValidationBuoiHoc nvh) {
		Connection conn = null;
		PreparedStatement prstmt = null;
		int numberRecords = 0;
		try {
			
			conn = ConnectionUtil.getConnection();
			String sql = "INSERT INTO dbo.BUOIHOC VALUES (?,?,?,?,?,?,?,?)";
			prstmt = conn.prepareStatement(sql);
			prstmt.setString(1, nvh.getIdBuoiHoc());
			prstmt.setString(2, nvh.getIdLop());
			prstmt.setInt(3, nvh.getIdGiaoVien());
			prstmt.setString(4, nvh.getThu());
			prstmt.setString(5, nvh.getTgianHoc());
			prstmt.setString(6, nvh.getPhongHoc());
			prstmt.setString(7, nvh.getNgayHoc());
			prstmt.setInt(8, nvh.getStatus());
			numberRecords = prstmt.executeUpdate();
			if (numberRecords == 0) {
				System.out.println("Thêm mới buổi học thất bại");
			} else {
				System.out.println("Thêm mới buổi học thành công");
			}

		} catch (SQLException i) {
			i.printStackTrace();
			System.out.println("Them moi buoi hoc that bai");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Them moi buoi hoc that bai");
		} finally {
			ConnectionUtil.closeConnection(null, prstmt, conn);
		}
		System.out.println("Thêm mới buổi học thành công");
	}

	// Update
	public void updateBuoiHoc() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ValidationBuoiHoc validate = new ValidationBuoiHoc();
			String IDBuoiHoc = validate.inputString("Xin hãy nhập buổi học");
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement("SELECT * FROM THU where Thu = ?");
			ps.setString(1, IDBuoiHoc);
			rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) {
				System.out.println("Không có buổi học trong bảng. Mời nhập lại");
				return;
			}
			ps.close();
			rs.close();
			String Thu = validate.inputString("Nhập vào Thứ ngày mới:");
			PreparedStatement ps1 = con.prepareStatement("UPDATE THU set IdBuoiHoc = ? where Thu = ?");
			ps1.setString(1, Thu);
			ps1.setString(2, IDBuoiHoc);
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

	//	Delete buổi học
	public void deleteBuoiHoc() {
		Connection conn = null;
		PreparedStatement prstmt = null;
		ResultSet rs = null;
		try {
			ValidationBuoiHoc validation = new ValidationBuoiHoc();
			String idBuoiHoc = validation.inputIdBuoiHoc("hay nhap ma so id buoi hoc mà bạn muốn xoá");
			conn = ConnectionUtil.getConnection();
			String sql = "select * from BUOIHOC where IDBuoiHoc = ?";
			prstmt = conn.prepareStatement(sql);
			prstmt.setString(1, idBuoiHoc);
			rs = prstmt.executeQuery();
			// hàm này chỉ ra con trỏ ở đầu dòng nếu có kết quả trả về, nếu k có kết quả,
			// con trỏ k đc đẩy lên đâu dòng.
			if (!rs.isBeforeFirst()) {
				System.out.println("Ma Id khong ton tai!");
				return;
			}
			prstmt.close();
			rs.close();
//			displayResultSet(rs);
	
//		String sql2 = "delete from THU where IDBuoiHoc = ?";
//		PreparedStatement prstmt2 = conn.prepareStatement(sql2);
//		prstmt2.setString(1, idBuoiHoc);
//		int rs2 = prstmt2.executeUpdate();
//		if(rs2 != 0) {
//			System.out.println("delete IDBuoiHoc bên bảng Thu thanh cong");
//		}
//	
//		String sql3 = "delete from BUOIHOC where IDBuoiHoc = ?";
//		PreparedStatement prstmt3 = conn.prepareStatement(sql3);
//		prstmt3.setString(1, idBuoiHoc);
//		int rs3 = prstmt2.executeUpdate();
//		if(rs3 != 0) {
//			System.out.println("delete IDBuoiHoc bên bảng BuoiHoc thanh cong");
//		}
//		
//		String sql1 = "delete from BUOIHOC where IDBuoiHoc = ?";
//		PreparedStatement prstmt1 = conn.prepareStatement(sql1);
//		prstmt1.setString(1, idBuoiHoc);
//		int rs1 = prstmt1.executeUpdate();
//		if(rs1 != 0) {
//			System.out.println("delete thanh cong");
//		}

	String sql1 = "  delete from THU where IDBuoiHoc IN (Select IDBuoiHoc from BUOIHOC  WHERE IDBuoiHoc = ?)\r\n"
			+ "  delete from BUOIHOC where IDBuoiHoc IN (Select IDBuoiHoc from BUOIHOC  WHERE IDBuoiHoc = ? )\r\n"
			+ "  delete from BUOIHOC where IDBuoiHoc = ?";
	PreparedStatement prstmt1 = conn.prepareStatement(sql1);
	prstmt1.setString(1, idBuoiHoc);
	prstmt1.setString(2, idBuoiHoc);
	prstmt1.setString(3, idBuoiHoc);
	int rs1 = prstmt1.executeUpdate();
	if (rs1 != 0) {
		System.out.println("delete thanh cong");
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

// code public(sửa lỗi)
	
public String getIdBuoiHoc() {
	// TODO Auto-generated method stub
	return null;
}

public String getIdLop() {
	// TODO Auto-generated method stub
	return null;
}

public int getIdGiaoVien() {
	// TODO Auto-generated method stub
	return 0;
}
public String getThu() {
	// TODO Auto-generated method stub
	return null;
}

public String getTgianHoc() {
	// TODO Auto-generated method stub
	return null;
}

public String getPhongHoc() {
	// TODO Auto-generated method stub
	return null;
}

public String getNgayHoc() {
	// TODO Auto-generated method stub
	return null;
}

public int getStatus() {
	// TODO Auto-generated method stub
	return 0;
	}
public static void ValidationBuoiHoc() {
	// TODO Auto-generated method stub
	
	}
public void inserIntoDB(BuoiHoc tr) {
	// TODO Auto-generated method stub
	

}
public static void insertBH(BuoiHoc a) {
	// TODO Auto-generated method stub
	
	}
public void insertBuoiHoc() {
	// TODO Auto-generated method stub
	
}
	
}

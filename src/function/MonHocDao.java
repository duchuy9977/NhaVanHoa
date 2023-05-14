package function;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import connection.ConnectionUtil;
import entities.MonHoc;
import validate.ValidateLuong;

public class MonHocDao {
	public static Scanner sc =  new Scanner(System.in);
	public static ArrayList<MonHoc> getMonHoc() {
		ArrayList<MonHoc> list = new ArrayList<MonHoc>();
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "select IDMonHoc, TenMon from MONHOC";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();

			if(!rs.isBeforeFirst()) {
				System.out.println("   Hiện Chưa có môn học nào");
				return null;
			}
			while (rs.next()) {
				MonHoc monHoc = new MonHoc();
				monHoc.setTenMon(rs.getString("TenMon"));
				monHoc.setIdMonHoc(rs.getString("IDMonHoc"));
				list.add(monHoc);
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

	//thêm môn học vào data
	public static void  insertMonHoc(MonHoc m) {
		Connection conn = ConnectionUtil.getConnection();		
		int kq = 0;
		try {		
			String sql= "insert into MONHOC VALUES(?,?,?,?)";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, m.getIdMonHoc());
			pstm.setString(2, m.getTenMon());
			pstm.setFloat(3, m.getSoTienMoiBuoiHoc());
			pstm.setString(4, m.getMoTa());
			kq = pstm.executeUpdate();

			if (kq >0) {
				System.out.println("Bạn thêm thành công môn học");
			}
		} catch (Exception e) {
			e.fillInStackTrace();
		}
		finally {
			if (conn != null) {
				try {
					conn.close();

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}

	//xóa môn học trong Data
	public static void deteleMoHoc(MonHoc m) {
		int ketqua = 0;
		Connection conn = null;
		PreparedStatement ptsm = null;
		ValidateLuong vali = new ValidateLuong();
		String idMonHoc ;
		while (true) {
			idMonHoc = vali.inputIDMonHoc("Nhập Id môn học bạn muốn xóa");
			if (checkIDMonHoc(idMonHoc)==true) {
				break;
			}
			else {
				System.out.println("id môn học không có trong bảng mon học");
			}
		}
		System.out.println(idMonHoc);
		System.out.println("thao tác xóa của bạn sẽ dẫn đến xóa rất nhiều dữ liệu, để tiếp tục xóa nhấn phím 1, để quay lại nhấn phím 2.");
		String a = sc.nextLine(); 	
		if (a.equals("1")) {
			try {		

				String sql = "DELETE from HOCPHI WHERE IDDangKy in (select IDLop from LOPNANGKHIEU where IDMonHoc = ?)\n"
						+ "DELETE from DANGKYLOPHOC WHERE IDLop in (select IDLop from LOPNANGKHIEU where IDMonHoc = ?)\n"
						+ "DELETE from THONGTINTUYENSINH WHERE IDLop in (select IDLop from LOPNANGKHIEU where IDMonHoc = ?)\n"
						+ "DELETE from BUOIHOC WHERE IDLop in (select IDLop from LOPNANGKHIEU where IDMonHoc = ?)\n"
						+ "DELETE from LOPNANGKHIEU where IDMonHoc=? \n"
						+ "DELETE from LUONG WHERE IDGiaoVien in (select IDGiaoVien from GIAOVIEN where IDMonHoc = ?)\n"
						+ "DELETE from GIAOVIEN where IDMonHoc=? \n"
						+ "DELETE from MONHOC where IDMonHoc= ? ";
				conn = ConnectionUtil.getConnection();
				ptsm =  conn.prepareStatement(sql);
				ptsm.setString(1, idMonHoc);
				ptsm.setString(2, idMonHoc);
				ptsm.setString(3, idMonHoc);
				ptsm.setString(4, idMonHoc);
				ptsm.setString(5, idMonHoc);
				ptsm.setString(6, idMonHoc);
				ptsm.setString(7, idMonHoc);
				ptsm.setString(8, idMonHoc);

				ketqua= ptsm.executeUpdate();		
				if (ketqua>0) {
					System.out.println("Bạn đã xóa " + idMonHoc+" ra khỏi database");
					System.out.println("Có " + ketqua + " dòng bị xóa !");
				}
			} catch (SQLException e) {
				e.fillInStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		} 
	}

	//sửa dữ liệu trong môn học
	public static void  updateMonHoc(MonHoc m) {
		Connection conn = ConnectionUtil.getConnection();		
		int kq = 0;
		try {		
			String sql= "update MONHOC set TenMon = ?,SoTienMoiBuoiHoc=?,MoTa=? WHERE IDMonHoc= ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, m.getTenMon());
			pstm.setFloat(2, m.getSoTienMoiBuoiHoc());
			pstm.setString(3, m.getMoTa());
			pstm.setString(4, m.getIdMonHoc());
			kq = pstm.executeUpdate();

			if (kq >0) {
				System.out.println("Bạn vừa update dữ liệu của");
			}
		} catch (Exception e) {
			e.fillInStackTrace();
		}
		finally {
			if (conn != null) {
				try {
					conn.close();

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}

	public static void  updateTenMonHoc(MonHoc m) {
		Connection conn = ConnectionUtil.getConnection();		
		int kq = 0;
		try {		
			String sql= "update MONHOC set TenMon = ? WHERE IDMonHoc= ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, m.getTenMon());
			pstm.setString(2, m.getIdMonHoc());
			kq = pstm.executeUpdate();

			if (kq >0) {
				System.out.println("Bạn vừa update tên của 1 Môn học");
			}
		} catch (Exception e) {
			e.fillInStackTrace();
		}
		finally {
			if (conn != null) {
				try {
					conn.close();

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}


	public static void  updateTienDayMoiGioMonHoc(MonHoc m) {
		Connection conn = ConnectionUtil.getConnection();		
		int kq = 0;
		try {		
			String sql= "update MONHOC set SoTienMoiBuoiHoc=? WHERE IDMonHoc= ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setFloat(1, m.getSoTienMoiBuoiHoc());
			pstm.setString(2, m.getIdMonHoc());
			kq = pstm.executeUpdate();

			if (kq >0) {
				System.out.println("Bạn vừa update Số tiền dạy mỗi giờ");
			}
		} catch (Exception e) {
			e.fillInStackTrace();
		}
		finally {
			if (conn != null) {
				try {
					conn.close();

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}

	public static void  updateMoTaMonHoc(MonHoc m) {
		Connection conn = ConnectionUtil.getConnection();		
		int kq = 0;
		try {		
			String sql= "update MONHOC set MoTa=? WHERE IDMonHoc= ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, m.getMoTa());
			pstm.setString(2, m.getIdMonHoc());
			kq = pstm.executeUpdate();

			if (kq >0) {
				System.out.println("Bạn vừa update Mô Tả");
			}
		} catch (Exception e) {
			e.fillInStackTrace();
		}
		finally {
			if (conn != null) {
				try {
					conn.close();

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}

	//check tồn tại của id môn học
	public static boolean checkIDMonHoc(String idMonHoc) {
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement pstm = null;
		ResultSet res = null;
		int row = 0;
		try {
			String sql = "Select COUNT(*) as soluong from MONHOC where IDMonHoc =?;";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, idMonHoc);
			res = pstm.executeQuery();
			res.next();
			row = res.getInt("soluong");

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
				if (res != null) {
					res.close();
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

	public static void searchHienThiThongTinMonHoc(MonHoc m) {
		Connection conn = null;
		PreparedStatement ptsm = null;
		ResultSet rss =null;
		try {

			String sql= "SELECT MONHOC.IDMonHoc,MONHOC.TenMon,LOPNANGKHIEU.IDLop,THONGTINTUYENSINH.TieuDe\n"
					+ "from MONHOC,LOPNANGKHIEU,THONGTINTUYENSINH\n"
					+ "where  MONHOC.IDMonHoc = LOPNANGKHIEU.IDMonHoc and LOPNANGKHIEU.IDLop = THONGTINTUYENSINH.IDLop";
			conn= ConnectionUtil.getConnection();
			ptsm = conn.prepareStatement(sql);
			rss =ptsm.executeQuery();

			if (!rss.isBeforeFirst()) {
				System.out.println("Không có Thông tin nào thỏa mãn");
				return ;
			}
			else {
				System.out.println("-----------------------------------------------------------------------");
				while (rss.next()) {
					System.out.println("  "+"ID Môn Học="+rss.getString("IDMonHoc")+"      "+"Tên Môn Học =" + rss.getString("TenMon")+"      "+"ID Lớp="+rss.getString("IDLop")+"      "+"Tiêu Đề: "+rss.getString("TieuDe"));
				}
				System.out.println("-----------------------------------------------------------------------");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}


	public static void searchLopKhaiGiang(MonHoc m) {
		Connection conn = null;
		PreparedStatement ptsm = null;
		ResultSet rss =null;
		try {

			String sql= "SELECT IDThongTin, THONGTINTUYENSINH.IDLop,TieuDe,NoiDung,[Status] \n"
					+ "from THONGTINTUYENSINH,LOPNANGKHIEU\n"
					+ "where LOPNANGKHIEU.IDLop=THONGTINTUYENSINH.IDLop AND  LOPNANGKHIEU.NgayBatDau > GETDATE() AND LOPNANGKHIEU.NgayBatDau < DATEADD(day,30,GETDATE())";
			conn= ConnectionUtil.getConnection();
			ptsm = conn.prepareStatement(sql);
			rss =ptsm.executeQuery();

			if (!rss.isBeforeFirst()) {
				System.out.println("Không có Thông tin nào thỏa mãn");
				return ;
			}
			else {
				System.out.println("-----------------------------------------------------------------------");
				while (rss.next()) {
					System.out.println("  "+"ID thông tin: "+ rss.getInt("IDThongTin")+"    "+"ID Lớp="+rss.getString("IDLop")+"    "+"Tiêu Đề :" + rss.getString("TieuDe")+"    "+"Nội Dung"+rss.getString("NoiDung")+"    "+"Trạng Thái : "+rss.getString("Status"));
				}
				System.out.println("-----------------------------------------------------------------------");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}



	public static void searchTop3Mh(MonHoc m) {
		Connection conn = null;
		PreparedStatement ptsm = null;
		ResultSet rss =null;
		try {
			ValidateLuong vali = new ValidateLuong();
			String quy;
			String year;

			quy = vali.inputQuy("Nhập quý bạn muốn hiển thị");
			year = vali.inputYear("Năm");

			String sql= "SELECT TOP(3) MONHOC.IDMonHoc,MONHOC.TenMon,COUNT(DANGKYLOPHOC.IDLop) AS SoLuongDangKy\n"
					+ "FROM MONHOC \n"
					+ "JOIN LOPNANGKHIEU on MONHOC.IDMonHoc = LOPNANGKHIEU.IDMonHoc\n"
					+ "JOIN DANGKYLOPHOC ON LOPNANGKHIEU.IDLop = DANGKYLOPHOC.IDLop\n"
					+ "where DATEPART(quarter,DANGKYLOPHOC.NgayDangKy)  = ?  AND YEAR(DANGKYLOPHOC.NgayDangKy) = ? \n"
					+ "GROUP BY MONHOC.IDMonHoc, DANGKYLOPHOC.IDLop,MONHOC.TenMon\n"
					+ "ORDER BY SoLuongDangKy DESC;";
			conn= ConnectionUtil.getConnection();
			ptsm = conn.prepareStatement(sql);
			ptsm.setString(1, quy);
			ptsm.setString(2, year);
			rss =ptsm.executeQuery();

			if (!rss.isBeforeFirst()) {
				System.out.println("Không có Thông tin nào thỏa mãn");
				return ;
			}
			else {

				System.out.println("---------------------");
				System.out.println("    3 Môn được Đăng ký nhiều nhất quý: "+ quy);
				System.out.println("-----------------------------------------------------------------------");
				while (rss.next()) {
					System.out.println("  "+"mã môn học="+rss.getString("IDMonHoc")+"      "+"tên môn hoc=" + rss.getString("TenMon")+"      "+"số lượng đăng ký="+rss.getInt("SoLuongDangKy"));
				}
				System.out.println("-----------------------------------------------------------------------");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

	}


	
	
}















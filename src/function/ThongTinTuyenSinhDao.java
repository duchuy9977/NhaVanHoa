package function;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import connection.ConnectionUtil;
import entities.ThongTinTuyenSinh;
import validate.ValidateLuong;

public class ThongTinTuyenSinhDao {
	public static Scanner sc =  new Scanner(System.in);
	
	//thêm thông tin vào bảng thông tin tuyển sinh
		public static int  insertThongTinTuyenSinh(ThongTinTuyenSinh ttts) {
			Connection conn = ConnectionUtil.getConnection();
			String sql= "insert into THONGTINTUYENSINH(IDLop,TieuDe,NoiDung,[Status]) VALUEs (?,?,?,?)";
			int kq = 0;

			try {		
				PreparedStatement pstm = conn.prepareStatement(sql);
				pstm.setString(1, ttts.getIdLop());
				pstm.setString(2, ttts.getTieuDe());
				pstm.setString(3, ttts.getNoiDung());
				pstm.setString(4, ttts.getSatatus());
				kq = pstm.executeUpdate();

				if (kq >0) {
					System.out.println("Bạn thêm thành công thông 1 tin tuyển sinh");
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
			return kq; 
		}

		//check ID lớp có tồn tại chưa trong bảng thông tin tuyển sinh
		public static boolean checkIDLop(String idLop) {
			Connection conn = ConnectionUtil.getConnection();
			PreparedStatement pstm = null;
			ResultSet res = null;
			int row = 0;
			try {
				String sql = "Select COUNT(*) as soluong from THONGTINTUYENSINH where IDLop =?;";
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, idLop);
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

		//xóa thông tin tuyển sinh
		public static void deteleThongTinTuyenSinh(ThongTinTuyenSinh ttts) {
			int ketqua = 0;
			Connection conn = null;
			PreparedStatement ptsm = null;
			ValidateLuong vali = new ValidateLuong();
			String idLop ;
			while (true) {
				idLop = vali.inputIDLop("Nhập Id Lớp học bạn muốn xóa thông tin tuyển sinh");
				if (checkIDLop(idLop)==true) {
					break;
				}
				else {
					System.out.println("Lớp học không có trong Thông tin tuyển sinh");
				}
			}
			System.out.println("thao tác xóa của bạn sẽ dẫn đến xóa rất nhiều dữ liệu, để tiếp tục xóa nhấn phím 1, để quay lại nhấn phím 2.");
			String a = sc.nextLine(); 	
			if (a.equals("1")) {
				try {		

					String sql = "DELETE THONGTINTUYENSINH WHERE IDLop =? ";
					conn = ConnectionUtil.getConnection();
					ptsm =  conn.prepareStatement(sql);
					ptsm.setString(1, idLop);

					ketqua= ptsm.executeUpdate();		
					if (ketqua>0) {
						System.out.println("Bạn đã xóa " + idLop+" ra khỏi database");
						System.out.println("Có " + ketqua + " dòng đã bị Xóa !");
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
		//update tất cả các colum qua IDLop
		public static void  updateThongTinTuyenSinhall(ThongTinTuyenSinh ttts) {
			Connection conn = ConnectionUtil.getConnection();		
			int kq = 0;
			try {		
				String sql= "update THONGTINTUYENSINH set TieuDe = ?,NoiDung=? ,[Status]=? WHERE IDLop=?";
				PreparedStatement pstm = conn.prepareStatement(sql);
				pstm.setString(1, ttts.getTieuDe());
				pstm.setString(2, ttts.getNoiDung());
				pstm.setString(3, ttts.getSatatus());
				pstm.setString(4, ttts.getIdLop());
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
		// update tiêu đề
		public static void  updateTieuDeThongTinTuyenSinh(ThongTinTuyenSinh ttts) {
			Connection conn = ConnectionUtil.getConnection();		
			int kq = 0;
			try {		
				String sql= "update THONGTINTUYENSINH set TieuDe = ? WHERE IDLop= ?";
				PreparedStatement pstm = conn.prepareStatement(sql);
				pstm.setString(1, ttts.getTieuDe());
				pstm.setString(2, ttts.getIdLop());
				kq = pstm.executeUpdate();

				if (kq >0) {
					System.out.println("Bạn vừa update dữ liệu của phần tiêu đề");
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
		//update nội dung
		public static void  updateNoiDungThongTinTuyenSinh(ThongTinTuyenSinh ttts) {
			Connection conn = ConnectionUtil.getConnection();		
			int kq = 0;
			try {		
				String sql= " update THONGTINTUYENSINH set NoiDung =? WHERE IDLop=?";
				PreparedStatement pstm = conn.prepareStatement(sql);
				pstm.setString(1, ttts.getNoiDung());
				pstm.setString(2, ttts.getIdLop());
				kq = pstm.executeUpdate();

				if (kq >0) {
					System.out.println("Bạn vừa update dữ liệu của phần Nội dung");
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
		//update trạng thái 
		public static void  updateStatusThongTinTuyenSinh(ThongTinTuyenSinh ttts) {
			Connection conn = ConnectionUtil.getConnection();		
			int kq = 0;
			try {		
				String sql= "update THONGTINTUYENSINH set [Status] = ? WHERE IDLop= ?";
				PreparedStatement pstm = conn.prepareStatement(sql);
				pstm.setString(1, ttts.getSatatus());
				pstm.setString(2, ttts.getIdLop());
				kq = pstm.executeUpdate();

				if (kq >0) {
					System.out.println("Bạn vừa update dữ liệu của Status");
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

		public static List<ThongTinTuyenSinh> selectThongTinTuyeSinh (){
			ArrayList<ThongTinTuyenSinh> ThongTin = new ArrayList<>();
			Connection con = null;
			PreparedStatement pr = null;
			ResultSet rs = null;
			try {
				con = ConnectionUtil.getConnection();
				String sql = "SELECT * FROM THONGTINTUYENSINH";
				pr = con.prepareStatement(sql);
				rs=pr.executeQuery();
				while (rs.next()) {
					ThongTinTuyenSinh ttts = new ThongTinTuyenSinh();
					ttts.setIdThongTin(rs.getInt("IDThongTin"));
					ttts.setIdLop(rs.getString("IDLop"));
					ttts.setTieuDe(rs.getString("TieuDe"));
					ttts.setNoiDung(rs.getString("NoiDung"));
					ttts.setSatatus(rs.getString("Status"));

					ThongTin.add(ttts);
				}
			}catch (SQLException i) {
				i.printStackTrace();
				System.out.println("select thông tin tuyển sinh thất bại ");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("select thông tin tuyển sinh thất bại");
			} finally {
				ConnectionUtil.closeConnection(null, pr, con);
			}
			return ThongTin ;

		}
}

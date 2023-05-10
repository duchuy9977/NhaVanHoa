package function;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConnectionUtil;
import entities.Account;
import entities.GiaoVien;
import validate.validate;
import validation.ValidationGiaoVien;

public class FunctionGiaoVien {

	public FunctionGiaoVien() {
	}

//function thêm mới giáo viên. 
//	chưa xong
	public void insertGiaoVien(GiaoVien nvh) {
		Connection conn = null;
		PreparedStatement prstmt = null;
		int numberRecords = 0;
		try {
			conn = ConnectionUtil.getConnection();
			String sql = "INSERT INTO dbo.GiaoVien VALUES (?,?,?,?,?,?,?,?)";
			prstmt = conn.prepareStatement(sql);
			prstmt.setString(1, nvh.getIdGiaoVien());
			prstmt.setString(2, nvh.getIdMonHoc());
			prstmt.setInt(3, nvh.getLuongMoiBuoiDay());
			prstmt.setString(4, nvh.getUserName());
			prstmt.setString(5, nvh.getDiaChi());
			prstmt.setString(6, nvh.getEmail());
			prstmt.setString(7, nvh.getSdt());
			prstmt.setInt(8, nvh.getSoNamKinhNghiem());
			numberRecords = prstmt.executeUpdate();
			if (numberRecords == 0) {
				System.out.println("Insert giao vien that bai");
			} else {
				System.out.println("Insert giáo viên thành công");
			}

		} catch (SQLException i) {
			i.printStackTrace();
			System.out.println("Insert giao vien that bai");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Insert giao vien that bai");
		} finally {
			ConnectionUtil.closeConnection(null, prstmt, conn);
		}

	}


// function delete thông tin giáo viên
	public void deleteGiaoVien() {
		Connection conn = null;
		PreparedStatement prstmt = null;
		ResultSet rs = null;
		try {
			ValidationGiaoVien validation = new ValidationGiaoVien();
			String idGiaoVien = validation.inputIdGiaoVien("hay nhap ma so id giao vien mà bạn muốn xoá");
			conn = ConnectionUtil.getConnection();
			String sql = "select * from GIAOVIEN where IDGiaoVien = ?";
			prstmt = conn.prepareStatement(sql);
			prstmt.setString(1, idGiaoVien);
			rs = prstmt.executeQuery();
			// hàm này chỉ ra con trỏ ở đầu dòng nếu có kết quả trả về, nếu k có kết quả,
			// con trỏ k đc đẩy lên đâu dòng.
			if (!rs.isBeforeFirst()) {
				System.out.println("Ma id giao vien khong ton tai!");
				return;
			}
//				displayResultSet(rs);
			prstmt.close();
			rs.close();

//				String sql2 = "delete from LUONG where IDGiaoVien = ?";
//				PreparedStatement prstmt2 = conn.prepareStatement(sql2);
//				prstmt2.setString(1, idGiaoVien);
//				int rs2 = prstmt2.executeUpdate();
//				if(rs2 != 0) {
//					System.out.println("delete IDGiaoVien bên bảng Luong thanh cong");
//				}
//			
//				String sql3 = "delete from BUOIHOC where IDGiaoVien = ?";
//				PreparedStatement prstmt3 = conn.prepareStatement(sql3);
//				prstmt3.setString(1, idGiaoVien);
//				int rs3 = prstmt2.executeUpdate();
//				if(rs3 != 0) {
//					System.out.println("delete IDGiaoVien bên bảng BuoiHoc thanh cong");
//				}
//				
//				String sql1 = "delete from GIAOVIEN where IDGiaoVien = ?";
//				PreparedStatement prstmt1 = conn.prepareStatement(sql1);
//				prstmt1.setString(1, idGiaoVien);
//				int rs1 = prstmt1.executeUpdate();
//				if(rs1 != 0) {
//					System.out.println("delete thanh cong");
//				}

			String sql1 = "  delete from LUONG where IDGiaoVien IN (Select IDGiaoVien from GIAOVIEN  WHERE IDGiaoVien = ?)\r\n"
					+ "  delete from BUOIHOC where IDGiaoVien IN (Select IDGiaoVien from GIAOVIEN  WHERE IDGiaoVien = ? )\r\n"
					+ "  delete from GIAOVIEN where IDGiaoVien = ?";
			PreparedStatement prstmt1 = conn.prepareStatement(sql1);
			prstmt1.setString(1, idGiaoVien);
			prstmt1.setString(2, idGiaoVien);
			prstmt1.setString(3, idGiaoVien);
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

//Function check tồn tại của user name khi tạo mới giáo viên
	public boolean checkUserName(String username) {
		Connection conn = null;
		PreparedStatement prstmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionUtil.getConnection();

			String sql = "Select * from Account where Username = ?";
			prstmt = conn.prepareStatement(sql);
			prstmt.setString(1, username);
			rs = prstmt.executeQuery();
			// hàm này chỉ ra con trỏ ở đầu dòng nếu có kết quả trả về, nếu k có kết quả,
			// con trỏ k đc đẩy lên đâu dòng.
			if (!(rs.isBeforeFirst())) {
				System.out.println("không tìm thấy username này, hãy nhập lại hoặc tạo mới account rồi quay lại!");
				return false;
			}
//			displayResultSet(rs);
			System.out.println("username có thể sử dụng được!");
			return true;

		} catch (SQLException i) {
			i.printStackTrace();
			System.out.println("check that bai");
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("check that bai");
			return false;
		} finally {
			ConnectionUtil.closeConnection(null, prstmt, conn);
		}
	}
//	
// function check tồn tại của id môn học khi tạo mới giáo viên
	public boolean checkIDMonHoc(String idMonHoc) {
		Connection conn = null;
		PreparedStatement prstmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionUtil.getConnection();

			String sql = "Select * from MONHOC where IDMonHoc = ?";
			prstmt = conn.prepareStatement(sql);
			prstmt.setString(1, idMonHoc);
			rs = prstmt.executeQuery();
			// hàm này chỉ ra con trỏ ở đầu dòng nếu có kết quả trả về, nếu k có kết quả,
			// con trỏ k đc đẩy lên đâu dòng.
			if (!rs.isBeforeFirst()) {
				System.out.println("idMonHoc này chưa có trong database");
				return false;
			}
//			displayResultSet(rs);
			System.out.println("idMonHoc có trong database");
			return true;

		} catch (SQLException i) {
			i.printStackTrace();
			System.out.println("check that bai");
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("check that bai");
			return false;
		} finally {
			ConnectionUtil.closeConnection(null, prstmt, conn);
		}
	}
	
	// function check tồn tại của id giáo viên khi tạo mới giáo viên
		public boolean checkIDGiaoVien(String idGiaoVien) {
			Connection conn = null;
			PreparedStatement prstmt = null;
			ResultSet rs = null;
			try {
				conn = ConnectionUtil.getConnection();

				String sql = "Select * from GIAOVIEN where IDGiaoVien = ?";
				prstmt = conn.prepareStatement(sql);
				prstmt.setString(1, idGiaoVien);
				rs = prstmt.executeQuery();
				// hàm này chỉ ra con trỏ ở đầu dòng nếu có kết quả trả về, nếu k có kết quả,
				// con trỏ k đc đẩy lên đâu dòng.
				if (!rs.isBeforeFirst()) {
					System.out.println("IDGiaoVien chưa có trong database!");
					return true;
				}
//				displayResultSet(rs);
				System.out.println("IDGiaoVien đã có trong database!");
				return false;

			} catch (SQLException i) {
				i.printStackTrace();
				System.out.println("check that bai");
				return false;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("check that bai");
				return false;
			} finally {
				ConnectionUtil.closeConnection(null, prstmt, conn);
			}
		}
		
//Function update id môn học trong bảng giáo viên
		public void updateIdMonHoc(GiaoVien gv) {
			Connection conn = null;
			PreparedStatement prstmt = null;
			int numberRecords = 0;
			try {
				conn = ConnectionUtil.getConnection();
				String sql = "Update GIAOVIEN set IDMonHoc = ? where IDGiaoVien = ?";
				prstmt = conn.prepareStatement(sql);
				prstmt.setString(1, gv.getIdMonHoc());
				prstmt.setString(2, gv.getIdGiaoVien());
				numberRecords = prstmt.executeUpdate();
				if (numberRecords == 0) {
					System.out.println("Update mã id môn học that bai");
				} else {
					System.out.println("Update mã id môn học thành công");
				}

			} catch (SQLException i) {
				i.printStackTrace();
				System.out.println("Update mã id môn học that bai");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Update mã id môn học that bai");
			} finally {
				ConnectionUtil.closeConnection(null, prstmt, conn);
			}
		}
		
// Function update Luong moi buoi day trong bang giao vien
		public void updateLuongMoiBuoiDay(GiaoVien gv) {
			Connection conn = null;
			PreparedStatement prstmt = null;
			int numberRecords = 0;
			try {
				conn = ConnectionUtil.getConnection();
				String sql = "Update GIAOVIEN set LuongMoiBuoiDay = ? where IDGiaoVien = ?";
				prstmt = conn.prepareStatement(sql);
				prstmt.setInt(1, gv.getLuongMoiBuoiDay());
				prstmt.setString(2, gv.getIdGiaoVien());
				numberRecords = prstmt.executeUpdate();
				if (numberRecords == 0) {
					System.out.println("Update Luong moi buoi day that bai");
				} else {
					System.out.println("Update Luong moi buoi day thành công");
				}

			} catch (SQLException i) {
				i.printStackTrace();
				System.out.println("Update Luong moi buoi day that bai");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Update Luong moi buoi day that bai");
			} finally {
				ConnectionUtil.closeConnection(null, prstmt, conn);
			}
		}
		
		// Function update user mane trong bang giao vien
				public void updateUserName(GiaoVien gv) {
					Connection conn = null;
					PreparedStatement prstmt = null;
					int numberRecords = 0;
					try {
						conn = ConnectionUtil.getConnection();
						String sql = "Update GIAOVIEN set Username = 'huytv28' where IDGiaoVien = 'GV018'";
						prstmt = conn.prepareStatement(sql);
						prstmt.setString(1, gv.getUserName());
						prstmt.setString(2, gv.getIdGiaoVien());
						numberRecords = prstmt.executeUpdate();
						if (numberRecords == 0) {
							System.out.println("Update username cho giao vien that bai");
						} else {
							System.out.println("Update username cho giao vien thành công");
						}

					} catch (SQLException i) {
						i.printStackTrace();
						System.out.println("Update username cho giao vien that bai");
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Update username cho giao vien that bai");
					} finally {
						ConnectionUtil.closeConnection(null, prstmt, conn);
					}
				}
}

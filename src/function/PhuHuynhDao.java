package function;


	import java.sql.Connection;
	import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.print.DocFlavor.INPUT_STREAM;

import connection.ConnectionUtil;
	import entities.PhuHuynh;
	import validate.validation;

	public class PhuHuynhDao {
		
		public void insertPH(PhuHuynh PH) {
			Connection conn = null;
			PreparedStatement ps = null;
			try {
				conn = ConnectionUtil.getConnection();
				ps = conn.prepareStatement(
						"Insert into PHUHUYNH(IDPhuHuynh,DiaChi,Email,Username,SDT1,SDT2,SDT3)  VALUES (?,?,?,?,?,?,?)");
				ps.setString(1, PH.getIDPhuHuynh());
				ps.setString(2, PH.getDiaChi());
				ps.setString(3, PH.getEmail());
				ps.setString(4, PH.getUsername());
				ps.setString(5, PH.getSDT1());
				ps.setString(6, PH.getSDT2());
				ps.setString(7, PH.getSDT3());
				
				int x = ps.executeUpdate();
				if (x != 0) {
					System.out.println("Insert successfully");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				ConnectionUtil.closeConnection(null, ps, conn);
			}
		}
		// Update
		public void updatePhuHuynh() {
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				validation validate = new validation();
				String IDPhuHuynh = validate.inputString("Xin hãy nhập ID Phụ huynh ");
				con = ConnectionUtil.getConnection();
				ps = con.prepareStatement("SELECT * FROM PHUHUYNH where IDPhuHuynh = ?");
				ps.setString(1, IDPhuHuynh);
				rs = ps.executeQuery();
				if (!rs.isBeforeFirst()) {
					System.out.println("Không có Phụ huynh phù hợp. Mời nhập lại");
					return;
				}
				ps.close();
				rs.close();
				String DiaChi = validate.inputString("Nhập vào Địa chỉ mới:");
				PreparedStatement ps1 = con.prepareStatement("UPDATE PHUHUYNH set DiaChi = ? where IDPhuHuynh = ?");
				ps1.setString(1, DiaChi );
				ps1.setString(2, IDPhuHuynh);
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
		public void deleteIDPhuHuynh() {
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				validation validate = new validation();
				String IDPhuHuynh = validate.inputIDPhuHuynh("Xin hãy nhập ID Phụ huynh muốn xóa");
				con = ConnectionUtil.getConnection();
				ps = con.prepareStatement(" select *  FROM PHUHUYNH where IDPhuHuynh = ?");
				ps.setString(1, IDPhuHuynh);
				rs = ps.executeQuery();
				if (!rs.isBeforeFirst()) {
					System.out.println("Không có ID Phụ huynh phù hợp. Mời nhập lại");
					return;
				}
				
				rs.close();
				rs.close();
				PreparedStatement ps1 = con.prepareStatement(
					"delete from TREEM where IDPhuHuynh IN (Select IDPhuHuynh from PHUHUYNH  WHERE IDPhuHuynh = ?)"
				+ "  delete from PHUHUYNH where IDPhuHuynh = ?");
								
 			  ps1.setString(1, IDPhuHuynh);
 			  ps1.setString(2, IDPhuHuynh);
			 
				int x = ps1.executeUpdate();
				if (x != 0) {
					System.out.println("Đã xóa thành công IDPhuHuynh");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				ConnectionUtil.closeConnection(rs, ps, con);
			}
		}

	
	}

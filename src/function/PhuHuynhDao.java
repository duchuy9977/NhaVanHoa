package function;


	import java.sql.Connection;
	import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

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
		//Liệt kê thông tin học phí của các phụ huynh chưa đóng học phí
		public void displayHocPhiCanDong() {
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				con = ConnectionUtil.getConnection();
				String sql = "select PHUHUYNH.IDPhuHuynh, ACCOUNT.Name, PHUHUYNH.SDT1, TREEM.IDTre,TREEM.TenTre, HOCPHI.Status, HOCPHI.HocPhiSauChietKhau\r\n"
						+ "From PHUHUYNH join ACCOUNT on PHUHUYNH.Username = ACCOUNT.Username\r\n"
						+ "join TREEM on PHUHUYNH.IDPhuHuynh = TREEM.IDPhuHuynh\r\n"
						+ "join DANGKYLOPHOC on TREEM.IDTre = DANGKYLOPHOC.IDTre\r\n"
						+ "join HOCPHI on DANGKYLOPHOC.IDDangKy = HOCPHI.IDDangKy\r\n" + "where HOCPHI.Status = 'ChuaDong'";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				if (!rs.isBeforeFirst()) {
					System.out.println("Không có thông tin");
					return;
				} else {
					while (rs.next()) {
						System.out.println(rs.getString("IDPhuHuynh") + " " + rs.getString("Name") + " "
								+ rs.getString("SDT1") + " " + rs.getString("IDTre") + " " + rs.getString("TenTre") + " "
								+ rs.getString("Status") + " " + rs.getInt("HocPhiSauChietKhau"));
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
		
		
	// Top 3 phụ huynh có số lượng đăng kí lớp cho trẻ học nhiều nhất
		public void displayTop3() {
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			Scanner sc = new Scanner(System.in);
			try {
				
				con = ConnectionUtil.getConnection();
				String sql = "SELECT TOP(3) PHUHUYNH.IDPhuHuynh,ACCOUNT.Name,COUNT(DANGKYLOPHOC.IDLop) AS SoLuongDangKy\r\n"
						+ "FROM PHUHUYNH join ACCOUNT on PHUHUYNH.Username = ACCOUNT.Username \r\n"
						+ "join TREEM on PHUHUYNH.IDPhuHuynh = TREEM.IDPhuHuynh\r\n"
						+ "JOIN DANGKYLOPHOC ON TREEM.IDTre = DANGKYLOPHOC.IDTre\r\n"
						+ "where DATEPART(quarter,DANGKYLOPHOC.NgayDangKy) = ?  AND YEAR(DANGKYLOPHOC.NgayDangKy) = 2023\r\n"
						+ "GROUP BY PHUHUYNH.IDPhuHuynh, DANGKYLOPHOC.IDLop,ACCOUNT.Name\r\n"
						+ "ORDER BY SoLuongDangKy DESC";
				System.out.print("Nhập vào quý cần tìm: ");
				int quy = Integer.parseInt(sc.nextLine());
				ps = con.prepareStatement(sql);
				ps.setInt(1, quy);
				rs = ps.executeQuery();
				if (!rs.isBeforeFirst()) {
					System.out.println("Không có thông tin");
					return;
				} else {
					while (rs.next()) {
						System.out.println(
								rs.getString("IDPhuHuynh") + " " + rs.getString("Name") + " " + rs.getInt("SoLuongDangKy"));

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
		
		
		// Hiển thị Phụ Huynh có từ 2 con trở lên học chung trường
		public void displayPH() {
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				con = ConnectionUtil.getConnection();
				String sql ="select  PHUHUYNH.IDPhuHuynh, ACCOUNT.Name, PHUHUYNH.SDT1, PHUHUYNH.DiaChi\r\n"
						+ "from ACCOUNT join PHUHUYNH on ACCOUNT.Username = PHUHUYNH.Username\r\n"
						+ "where PHUHUYNH.IDPhuHuynh IN (\r\n"
						+ "select TREEM.IDPhuHuynh\r\n"
						+ "from TREEM\r\n"
						+ "where TREEM.IDPhuHuynh = PHUHUYNH.IDPhuHuynh\r\n"
						+ "group by TREEM.IDPhuHuynh\r\n"
						+ "having count (TREEM.IDTre) >=2\r\n"
						+ ")";
						ps = con.prepareStatement(sql);
						rs = ps.executeQuery();
						if (!rs.isBeforeFirst()) {
							System.out.println("Không có thông tin");
							return;
						} else {
							while (rs.next()) {
								System.out.println(rs.getString("IDPhuHuynh") + " " + rs.getString("Name") + " "
										+ rs.getString("SDT1")  + " " + rs.getString("DiaChi"));
							}
	}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				ConnectionUtil.closeConnection(rs, ps, con);
			}
	}
	}


	
	

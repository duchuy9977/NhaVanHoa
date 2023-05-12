package app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import connection.ConnectionUtil;
import entities.Account;
import entities.GiaoVien;
import function.FunctionAccount;
import function.FunctionGiaoVien;
import menuChucNangCon.MenuPhuHuynh;

import menuChucNangCon.MenuQuanLyDangKyLop;
import menuChucNangCon.MenuSelectGiaoVien;
import menuChucNangCon.MenuUpdateGiaoVien;
import validation.ValidationAccount;
import validation.ValidationGiaoVien;
import simple.Simple;
import treEm.TreEmDao;
//import function.Manage;
import treEm.TreEmManager;
import function.FuncitionLopNangKhieu;

public class MyApp {
	public static String user = null;
	public static String password = null;
	public static String name = null;
	public static String role = null;
	public static String roleName = null;

	public static boolean login(String user, String pass) {
		Connection conn = ConnectionUtil.getConnection();
		ResultSet rs = null;
		PreparedStatement prstmt = null;

		// Kết quả trả về
		boolean result = false;

		String sql = "select * from Account where username = ? and password = ? and Status = 'Active'";

		try {
			prstmt = conn.prepareStatement(sql);
			prstmt.setString(1, user);
			prstmt.setString(2, pass);
			rs = prstmt.executeQuery();

			while (rs.next()) {
				result = true;
				// Lấy tên người dùng và role
				name = rs.getString("name");
				role = rs.getString("IDRole");
				roleName = rs.getString("NameRole");
			}
			if (!result) {
				System.out.println("Bạn đã nhập sai user hoặc password!!!");
			}
		} catch (SQLException e) {
			System.out.println("Lỗi trong lúc tương tác với dữ liệu");
			System.out.println(e.getMessage());
		} finally {
			ConnectionUtil.closeConnection(rs, prstmt, conn);
		}
		return result;
	}

	public static void main(String[] args) {
		Connection conn = ConnectionUtil.getConnection();
		// Chức năng Login
		Scanner sc = new Scanner(System.in);
		FunctionGiaoVien functionGV = new FunctionGiaoVien();
		FunctionAccount functionAcc = new FunctionAccount();
		MenuUpdateGiaoVien menuUpdateGV = new MenuUpdateGiaoVien();

		MenuSelectGiaoVien menuSelectGV = new MenuSelectGiaoVien();
		while (true) {

			do {
				System.out.println("===============================================");
				System.out.println("   Đăng Nhập");
				System.out.print("      user    : ");
				user = sc.nextLine();
				System.out.print("      password: ");
				password = sc.nextLine();
				System.out.println("===============================================");

			} while (!login(user, password));

			if (role.equals("admin")) {
				String choice = "a";
				while (!choice.equals("0")) {
					System.out.println("Chào mừng trở lại " + roleName + " " + name + "!!!");
					System.out.println("Mời nhập chức năng bạn muốn sử dụng");
					System.out.println("   1. Cập nhật thông tin cá nhân");
					System.out.println("   2. Quản lý Môn học");
					System.out.println("   3. Quản lý lớp học");
					System.out.println("   4. Đăng Thông tin tuyển sinh");
					System.out.println("   5. Xem tình trạng đơn đăng kí Lớp học");
					System.out.println("   6. Thêm buổi học");
					System.out.println("   7. Thêm mới giáo viên");

					System.out.println("   8. Tạo mới Account");
					System.out.println("   9. xoá thông tin giáo viên.");
					System.out.println("  10. Update thông tin giáo viên.");
					System.out.println("  11. Quản Lý Phụ Huynh");
					System.out.println("  12. select thông tin giáo viên theo điều kiện");
					System.out.println("   0. Đăng Xuất..");
					System.out.println("===============================================");
					System.out.println("  Mời bạn chọn tính năng: ");

					choice = sc.nextLine();

					switch (choice) {
					case "1":
						System.out.println("Bạn đã chọn chức năng cập nhật thông tin các nhân!");
						break;
					case "2":
						System.out.println("Bạn đã chọn chức năng Quản lý Môn học!");
						break;
					case "3":
						System.out.println("Bạn đã chọn chức năng Quản lý lớp học!");
						FuncitionLopNangKhieu.FuncitionLopNangKhieu();
						break;
					case "4":
						System.out.println("Bạn đã chọn chức năng Đăng Thông tin tuyển sinh!");
						break;
					case "5":
						System.out.println("Bạn đã chọn chức năng Xem tình trạng đơn đăng kí Lớp học!");
						MenuQuanLyDangKyLop.openMenu();
						break;
					case "6":
						System.out.println("Bạn đã chọn chức năng Xem tình trạng đơn đăng kí Ca dạy!");
						break;
					case "7":
						System.out.println("Bạn đã chọn chức năng Xem thêm mới giáo viên!");
						GiaoVien x = new GiaoVien();
						x.inputInfoAddGV();
						functionGV.insertGiaoVien(x);
						break;
					case "8":
						System.out.println("Bạn đã chọn chức năng tạo mới Account!");
						Account y = new Account();
						y.inputInfoCreateAcc();
						functionAcc.addAccount(y);

						break;

					case "9":

						System.out.println("Bạn đã chọn chức năng Xoá thông tin giáo viên!");
						functionGV.deleteGiaoVien();
						break;

					case "10":
						System.out.println("Bạn đã chọn chức năng update thông tin giáo viên!");
						menuUpdateGV.menuUpdate();
						break;

						
					case "11":
						MenuPhuHuynh.quanLyPhuHuynh();
						break;

					case "12":
						System.out.println("Bạn đã chọn chức năng select thông tin giáo viên theo điều kiện!");
						menuSelectGV.menuSelect();
						break;

					case "0":

						System.out.println("Bạn đã chọn Đăng xuất khỏi chương trình!!!");
						break;
					default:
						System.out.println("Không hợp lệ, mời nhập lại!");
						;
					}
				}

			}
			if (role.equals("phuhuynh")) {
				String choice = "a";
				while (!choice.equals("0")) {
					System.out.println("Chào mừng trở lại " + roleName + " " + name + "!!!");
					System.out.println("Mời nhập chức năng bạn muốn sử dụng");
					System.out.println("   1. Cập nhật thông tin phụ huynh");
					System.out.println("   2. Quản lý trẻ");
					System.out.println("   3. Xem thông tin Tuyển Sinh");
					System.out.println("   4. Đăng kí lớp học");
					System.out.println("   5. Xem tình trạng đơn đăng kí");
					System.out.println("   0. Đăng Xuất");
					System.out.println("===============================================");
					System.out.println("  Mời bạn chọn tính năng: ");

					choice = sc.nextLine();

					switch (choice) {
					case "1":
						System.out.println("Bạn đã chọn chức năng Cập nhật thông tin phụ huynh!");
						break;

					case "2":
						TreEmManager.TreEmManager(user);

						break;
					case "3":
						System.out.println("Bạn đã chọn chức năng Xem thông tin Tuyển Sinh!");
						break;
					case "4":
						System.out.println("Bạn đã chọn chức năng Đăng kí lớp học!");

						//Đầu tiên check xem phụ huynh đó đã có user chưa, nếu có trẻ thì mới chạy vào trong if
						if(TreEmDao.checkTablePhuHuynhCoTre(user)) {
							Simple sim1 = new Simple();
							sim1.dangkihoc(user);
						}						
						break;
					case "5":
						System.out.println("Bạn đã chọn chức năng Xem tình trạng đơn đăng kí!");
						break;
					case "0":
						System.out.println("Bạn đã chọn Đăng xuất khỏi chương trình!!!");
						break;
					default:
						System.out.println("Không hợp lệ, mời nhập lại!");
					}
				}

			}
			if (role.equals("giaovien")) {
				String choice = "a";
				while (!choice.equals("0")) {
					System.out.println("Chào mừng trở lại " + roleName + " " + name + "!!!");
					System.out.println("Mời nhập chức năng bạn muốn sử dụng");
					System.out.println("   1. Xem thông tin giáo viên");
					System.out.println("   2. Cập nhật id môn học cho giáo viên");
					System.out.println("   3. Xem ca dạy");
					System.out.println("   4. Đăng kí Ca dạy");
					System.out.println("   5. Xem tình trạng đơn đăng kí");
					System.out.println("   0. Đăng Xuất");
					System.out.println("===============================================");
					System.out.println("  Mời bạn chọn tính năng: ");

					choice = sc.nextLine();

					switch (choice) {
					case "1":
						System.out.println("Bạn đã chọn chức năng Xem thông tin giáo viên!");
						break;

					case "2":
						System.out.println("Bạn đã chọn chức năng Cập nhật id môn học cho giáo viên!");

						break;
					case "3":
						System.out.println("Bạn đã chọn chức năng Xem ca dạy!");
						break;
					case "4":
						System.out.println("Bạn đã chọn chức năng Đăng kí Ca dạy!");
						break;
					case "5":
						System.out.println("Bạn đã chọn chức năng Xem tình trạng đơn đăng kí!");
						break;

					case "6":
						System.out.println("Bạn đã chọn chức năng Xoá thông tin giáo viên!");
						functionGV.deleteGiaoVien();
						break;
					case "0":

						System.out.println("Bạn đã chọn Đăng xuất khỏi chương trình!!!");
						break;
					default:
						System.out.println("Không hợp lệ, mời nhập lại!");
					}
				}

			}

			System.out.println("Chương trình kết thúc");

		}

	}
}

package app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import connection.ConnectionUtil;
import entities.Account;
import entities.GiaoVien;
import function.FunctionAccount;
import function.FunctionGiaoVien;
import validation.ValidationAccount;
import validation.ValidationGiaoVien;
import simple.Simple;
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
<<<<<<< HEAD
		FunctionGiaoVien functionGV = new FunctionGiaoVien();
=======
		FunctionGiaoVien functionGV = new FunctionGiaoVien();

>>>>>>> branch 'master' of https://github.com/duchuy9977/NhaVanHoa
		FunctionAccount functionAcc = new FunctionAccount();
		ValidationGiaoVien validationGV = new ValidationGiaoVien();
		ValidationAccount validationAcc = new ValidationAccount();
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
					System.out.println("   6. Xem tình trạng đơn đăng kí Ca dạy");
					System.out.println("   7. Thêm mới giáo viên");
					System.out.println("   8. Tạo mới Account");
					System.out.println("   9. xoá thông tin giáo viên.");
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
						break;
					case "6":
						System.out.println("Bạn đã chọn chức năng Xem tình trạng đơn đăng kí Ca dạy!");
						break;
					case "7":
						System.out.println("Bạn đã chọn chức năng thêm mới giáo viên!");
						GiaoVien x = new GiaoVien();
						while (true) {
							x.setIdGiaoVien(validationGV.inputIdGiaoVien("hay nhap ma id giao vien"));
							if (functionGV.checkIDGiaoVien(x.getIdGiaoVien())) {
								break;
							} else {
								System.out.println("id giáo viên bị trùng");
							}
						}
						while (true) {
							x.setIdMonHoc(validationGV.inputIdMonHoc("hay nhap ma id mon hoc"));
							if (functionGV.checkIDMonHoc(x.getIdMonHoc()) == false) {
								break;
							} else {
								System.out.println("id môn học chưa có, hay nhập lại");
							}
						}
						x.setLuongMoiBuoiDay(validationGV.inputLuongMoiBuoiDay("hay nhap he so luong mỗi buỗi dạy"));
						while (true) {
							x.setUserName(validationGV.inputUserName("hay nhap username"));
							if (functionGV.checkUserName(x.getUserName()) == false) {
								break;
							} else {
								System.out.println("user name chưa có, hay nhập lại");
							}
						}
						x.setDiaChi(validationGV.inputDiaChi("hay nhap dia chi"));
						x.setEmail(validationGV.inputEmail("hay nhap email"));
						x.setSdt(validationGV.inputSDT("hay nhap so dien thoai"));
						x.setSoNamKinhNghiem(validationGV.inputSoNamKinhNghiem("hay nhap so nam kinh nghiem"));

//						x.inputInfo();
						functionGV.insertGiaoVien(x);
						break;
					case "8":
						System.out.println("Bạn đã chọn chức năng tạo mới Account!");
						Account y = new Account();

						while (true) {
							y.setUserName(validationAcc.inputUserName("hay nhap username tao moi"));
							if (functionAcc.checkUserName(y.getUserName())) {
								break;
							} else {
								System.out.println("username bị trùng");
							}
						}
						y.setPassWork(validationAcc.inputPassWork("hay nhap passwork tao moi"));
						y.setName(validationAcc.inputName("hay nhap ho va ten tao moi"));
						y.setIdRole(validationAcc.inputIdRole(
								"hay nhap id chuc vu tao moi, idrole chi nhan 3 gia tri la admin, giaovien hoac phuhuynh"));
						y.setNameRole(validationAcc.inputNameRole("hay nhap ten chuc vu tao moi"));
						y.setStatus(validationAcc
								.inputStatus("hay nhap trang thai, trang thai chi nhan 2 gia tri la Ban hoac Active"));
//						y.inputInfo();

						functionAcc.addAccount(y);

						break;

					case "9":

						System.out.println("Bạn đã chọn chức năng Xoá thông tin giáo viên!");
						functionGV.deleteGiaoVien();
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
						Simple sim1 = new Simple();
						sim1.dangkihoc();
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

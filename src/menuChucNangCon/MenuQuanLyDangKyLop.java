package menuChucNangCon;

import java.util.Scanner;

import function.FuncitionLopNangKhieu;
import function.QuanLyDangKyLopHocDao;

public class MenuQuanLyDangKyLop {
	static Scanner sc = new Scanner(System.in);
	public static void openMenu() {
		String choice = "a";
		int donMoi = 0, dangChoDuyet = 0;
		while (!choice.equals("0")) {
			donMoi = QuanLyDangKyLopHocDao.selectByStatus("Unseen");
			dangChoDuyet = QuanLyDangKyLopHocDao.selectByPendingOrUnseenStatus();
			System.out.println("=======================================================================================");
			System.out.println("        Quản Lý Đơn Đăng ký học");
			System.out.println("   1. Duyệt đơn đăng ký (Có " + donMoi + " đơn đăng ký mới, tổng " +  dangChoDuyet + " đơn chưa chuyệt)");
			System.out.println("   2. Thống Kê Lượng học sinh đăng ký theo từng tháng của năm");
			System.out.println("   3. Liệt kê học viên còn thiếu của mỗi lớp học ");
			System.out.println("   4. Tìm Đơn đăng ký ");
			System.out.println();
			System.out.println("   0. Thoát khỏi menu");
			System.out.println("=======================================================================================");
			System.out.println("  Mời bạn chọn tính năng: ");

			choice = sc.nextLine();

			switch (choice) {
			case "1":
				System.out.println("Bạn đã chọn chức năng Duyệt đơn đăng ký!");
				QuanLyDangKyLopHocDao.chonDonDangKy();
				break;
			case "2":
				System.out.println("Bạn đã chọn chức năng Thống Kê Lượng học sinh đăng ký theo từng tháng của năm");
				QuanLyDangKyLopHocDao.thongKeSoLuongHocSinhTheoNam();
				break;
			case "3":
				System.out.println("Bạn đã chọn chức năng Liệt kê học viên còn thiếu của mỗi lớp học");
				QuanLyDangKyLopHocDao.thongKeSoLuongHocSinhConThieuTheoLop();
				break;
			case "4":
				System.out.println("Bạn đã chọn chức năng tìm đơn");
				QuanLyDangKyLopHocDao.timDon();
				break;
			case "0":

				System.out.println("Bạn đã chọn thoát ra ngoài menu!!!");
				break;
			default:
				System.out.println("Không hợp lệ, mời nhập lại!");
				;
			}
		}
	}
}

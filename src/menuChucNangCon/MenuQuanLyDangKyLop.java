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
			System.out.println("===============================================");
			System.out.println("        Quản Lý Đơn Đăng ký học");
			System.out.println("   1. Duyệt đơn đăng ký (Có " + donMoi + " đơn đăng ký mới, tổng " +  dangChoDuyet + " đơn chưa chuyệt)");
			System.out.println("   2.  ");
			System.out.println();
			System.out.println("   0. Thoát khỏi menu");
			System.out.println("===============================================");
			System.out.println("  Mời bạn chọn tính năng: ");

			choice = sc.nextLine();

			switch (choice) {
			case "1":
				System.out.println("Bạn đã chọn chức năng Duyệt đơn đăng ký!");
				QuanLyDangKyLopHocDao.chonDonDangKy();
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

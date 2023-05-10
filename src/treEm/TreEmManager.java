package treEm;

import java.util.ArrayList;
import java.util.Scanner;

public class TreEmManager {

	public static void TreEmManager(String username) {
		Scanner input = new Scanner(System.in);
		ArrayList<TreEm> list = new ArrayList<TreEm>();
		TreEmDao teDao = new TreEmDao();
		do {
			try {
				System.out.println("Chương trình Quản lý trẻ em:");
				System.out.println("__________________________________");
				System.out.println("1.Thêm thông tin trẻ em.");
				System.out.println("2.Xem thông tin trẻ em theo tên.");
				System.out.println("3.Cập nhật trường đang học trẻ em theo tên.");
				System.out.println("4.Xóa thông tin trẻ em theo IDTre");
				System.out.println("5.Liệt kệ tất cả Trẻ Em theo Giới Tính");
				System.out.println("6.Liệt kê tất cả Trẻ Em đã duyệt đăng ký ");
				System.out.println(
						"7.Hiển thị top 3 môn học có số lượng học sinh học nhiều nhất trong tháng x/2023 (X nhập vào từ bàn phím)");
				System.out.println("8.Liệt kê các học sinh là anh em của nhau (cùng phụ huynh)");
				System.out.println("0.Thoát khỏi chương trinh.");
				System.out.println();
				System.out.println("Nhập lựa chọn của bạn:");
				String choice = input.nextLine();
				switch (choice) {
				case "1":
					TreEm tr = new TreEm();
					tr.inputInfo(username);
					teDao.inserIntoDB(tr);
					list.add(tr);
					break;
				case "2":
					teDao.seachByTen();
					break;
				case "3":
					teDao.updateTruongDangHoc();
					break;
				case "4":
					teDao.deleteIDTre();
					break;
				case "5":
					teDao.sortGioiTinh();
					break;
				case "6":
					teDao.seachDaThongQua();
					break;
				case "7":
					teDao.seachTop3MonHoc();
					break;
				case "8":
					teDao.sortAnhEm();
					break;
				case "0":
					System.out.println("Bạn đã thoát khỏi chương trình!!!");
					break;
				default:
					System.out.println("Không hợp lệ, mời nhập lại!");
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} while (true);
	}
}

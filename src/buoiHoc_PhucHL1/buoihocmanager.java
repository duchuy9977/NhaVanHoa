package buoiHoc_PhucHL1;

import java.util.Scanner;

import function.FunctionGiaoVien;

public class buoihocmanager {
	public static void menuBuoiHoc() {
		Scanner scanner = new Scanner(System.in);
		BuoiHocDao BuoiHocDao = new BuoiHocDao();
		boolean flag = true;
		do {
			try {
				System.out.println("Chương trình tìm kiếm thông tin buổi học");
				System.out.println();
				System.out.println("1. Thêm thông tin buổi học");
				System.out.println("2. Cập nhật buổi học");
				System.out.println("3. Xóa thông tin buổi học.");
				System.out.println("4. Liệt kê các giáo viên đã có lớp dạy");
				System.out.println("5. Thống kê lương của các giáo viên nhận được trong tháng X");
				System.out.println("6. thoát khỏi chương trình");

				int choice = Integer.parseInt(scanner.nextLine());

				switch (choice) {
				case 1:
					System.out.println("Thêm thông tin Id Buổi học");
					BuoiHocDao.updateBuoiHoc();
					break;
				case 2:
					System.out.println("Cập nhật thêm Buổi học");
					BuoiHocDao.updateBuoiHoc();
					break;

				case 3:
					System.out.println("Hiển thị khung giờ giáo viên đăng ký nhiều nhất trong buổi học");
					BuoiHocDao.insertBuoiHoc();
					break;

				case 4:
					System.out.println("Thời gian đăng ký buổi học và trạng thái");
					BuoiHocDao.insertBuoiHoc();
					break;
//				case 5:
//					System.out.println("Bạn đã chọn chức năng Thống kê lương của các giáo viên nhận được trong tháng");
//					functionBH.monthlySalaryStatistics();
//					break;

				case 6:
					System.out.println("Bạn đã chọn thoát khỏi chương trình!");
					flag = false;
					break;

				default:
					System.out.println("bạn nhập chưa đúng, vui lòng nhập lại");
					break;
				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} while (flag);
		System.out.println("Chuong trinh ket thuc");
		System.out.println("================================== END ===============================================\n");
	}
}

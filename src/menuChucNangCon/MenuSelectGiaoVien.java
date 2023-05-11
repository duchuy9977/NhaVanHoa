package menuChucNangCon;

import java.util.Scanner;

import entities.GiaoVien;
import function.FunctionGiaoVien;

public class MenuSelectGiaoVien {
	public static void menuSelect() {
		Scanner scanner = new Scanner(System.in);
		FunctionGiaoVien functionGV = new FunctionGiaoVien();
		boolean flag = true;
		do {
			try {
				System.out.println("Chuong trinh select thông tin giao vien theo điều kiện");
				System.out.println("===============================================================");
				System.out.println("1. Hiển thị top 3 giáo viên tham gia dạy học nhiều nhất trong tháng x/2020 (X nhập vào từ bàn phím)");
				System.out.println("2. Liệt kê thông tin giáo viên theo từng môn học");
				System.out.println("3. Tìm và hiển thị các giáo viên cùng địa chỉ.");
				System.out.println("4. Liệt kê các giáo viên đã có lớp dạy");
				System.out.println("5. Thống kê lương của các giáo viên nhận được trong tháng X");
				System.out.println("6. thoát khỏi chương trình");

				int choice = Integer.parseInt(scanner.nextLine());

				switch (choice) {
				case 1:
					System.out.println("Bạn đã chọn chức năng Hiển thị top 3 giáo viên tham gia dạy học nhiều nhất trong tháng của năm 2020!");
					functionGV.sortTop3GVDayNhieuNhat();
					break;
				case 2:
					System.out.println("Bạn đã chọn chức năng Liệt kê thông tin giáo viên theo từng môn học!");
					functionGV.searchInfoGVByIdMonHoc();
					break;

				case 3:
					System.out.println("Bạn đã chọn chức năng Tìm và hiển thị các giáo viên cùng địa chỉ");
					functionGV.searchInfoGVByDiaChi();
					break;

				case 4:
					System.out.println("Bạn đã chọn chức năng Liệt kê các giáo viên đã có lớp dạy");
			
					break;
				case 5:
					System.out.println("Bạn đã chọn chức năng Thống kê lương của các giáo viên nhận được trong tháng");
					
					break;

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

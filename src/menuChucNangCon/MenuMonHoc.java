package menuChucNangCon;

import java.util.Scanner;

import entities.MonHoc;
import function.MonHocDao;

public class MenuMonHoc {
	public static Scanner scanner = new Scanner(System.in); 
	//menu con của Môn Học
	public void switchsMonHoc() {
		int choice = 0;
		while (choice != 4) {

			System.out.println("Mời bạn chọn chức năng:");
			System.out.println("1. Thêm Môn Học Vào Cơ Sở Dữ Liệu");
			System.out.println("2. Xóa Môn Học Khỏi Cơ Sở Dữ Liệu");
			System.out.println("3. Sửa Dữ Liệu Của Môn Học");
			System.out.println("4. Đăng Xuất");
			System.out.println("===============================================");
			System.out.println("  Mời bạn chọn tính năng: ");

			choice = Integer.parseInt(scanner.nextLine());

			switch (choice) {
			case 1:
				System.out.println("Bạn đã chọn thêm Môn Học.");
				MonHoc monhoc1 = new MonHoc(); 
				MonHocDao.insertMonHoc(monhoc1.inputData());
				break;
			case 2:
				System.out.println("Bạn đã chọn xóa Môn Học.");
				MonHocDao.deteleMoHoc(null);
				break;
			case 3:
				System.out.println("Bạn đã chọn Update thông tin cho Môn học.");
				MemuUpdateMonHoc menu = new MemuUpdateMonHoc();
				menu.switchsupdateMonHoc();
				break;
			case 4:
				System.out.println("Bạn đã chọn Đăng xuất khỏi chương trình!!!");
				break;
			default:
				System.out.println("Không hợp lệ, mời nhập lại!");
				continue;
			}
		}
	}
	
	
	
	
	
	
	
}

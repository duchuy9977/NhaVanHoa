package menuChucNangCon;

import java.util.Scanner;

import entities.ThongTinTuyenSinh;
import function.ThongTinTuyenSinhDao;

public class MenuConTTTS {
	public static Scanner scanner = new Scanner(System.in); 
	//menu con của thông tin tuyển sinh
		public void switchsThongTinTuyenSinh() {
			int choice = 0;
			while (choice != 4) {
				
				System.out.println("Mời bạn chọn chức năng:");
				System.out.println("1. Thêm Thông tín tuyển sinh Vào Cơ Sở Dữ Liệu");
				System.out.println("2. Xóa Thông tín tuyển sinh khỏi Cơ Sở Dữ Liệu");
				System.out.println("3. Sửa Dữ Liệu Của Thông tin tuyển sinh");
				System.out.println("4. Đăng Xuất");
				System.out.println("===============================================");
				System.out.println("  Mời bạn chọn tính năng: ");

				choice = Integer.parseInt(scanner.nextLine());
				//		}
				switch (choice) {
				case 1:
					ThongTinTuyenSinh ttts = new ThongTinTuyenSinh(); 
					ThongTinTuyenSinhDao.insertThongTinTuyenSinh(ttts.inputData());
					break;
				case 2:
					ThongTinTuyenSinhDao.deteleThongTinTuyenSinh(null);
					break;
				case 3:
					MenuconupdateTTTS menu = new MenuconupdateTTTS();
					menu.switchsupdateThongTinTuyenSinh();
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

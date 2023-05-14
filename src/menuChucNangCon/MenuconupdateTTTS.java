package menuChucNangCon;

import java.util.Scanner;

import entities.ThongTinTuyenSinh;
import function.ThongTinTuyenSinhDao;

public class MenuconupdateTTTS {

	public static Scanner scanner = new Scanner(System.in); 
	//menu con của update Thông tin tuyển sinh
	public void switchsupdateThongTinTuyenSinh() {
		ThongTinTuyenSinh ttts1 = new ThongTinTuyenSinh();
		int choice = 0;
		while (choice != 5) {

			System.out.println("Mời bạn chọn chức năng:");
			System.out.println("1. Update toàn bộ dữ liệu qua ID Lớp");
			System.out.println("2. Update Tiêu đề");
			System.out.println("3. Update Nội Dung");
			System.out.println("4. Update Status");
			System.out.println("5. Đăng Xuất");
			System.out.println("===============================================");
			System.out.println("  Mời bạn chọn tính năng: ");

			choice = Integer.parseInt(scanner.nextLine());
			
			switch (choice) {
			case 1:
				System.out.println("Bạn đã chọn Update toàn bộ dữ liệu thông qua ID lớp.");
				ThongTinTuyenSinhDao.updateThongTinTuyenSinhall(ttts1.updateDataAll());
				break;
			case 2:
				System.out.println("Bạn đã chọn Update Tiêu đề.");
				ThongTinTuyenSinhDao.updateTieuDeThongTinTuyenSinh(ttts1.updateDataTieuDe());
				break;
			case 3:
				System.out.println("Bạn đã chọn Update Nội dung.");
				ThongTinTuyenSinhDao.updateNoiDungThongTinTuyenSinh(ttts1.updateDataNoiDung());
				break;
			case 4:
				System.out.println("Bạn đã chọn Update Trạng thái.");
				ThongTinTuyenSinhDao.updateStatusThongTinTuyenSinh(ttts1.updateDataStatus());
				break;
			case 5:
				System.out.println("Bạn đã chọn Đăng xuất khỏi chương trình!!!");
				break;
			default:
				System.out.println("Không hợp lệ, mời nhập lại!");
				continue;

			}
		}
	}
}

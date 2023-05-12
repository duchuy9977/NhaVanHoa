package menuChucNangCon;

import java.util.List;
import java.util.Scanner;

import entities.MonHoc;
import entities.ThongTinTuyenSinh;
import function.MonHocDao;
import function.ThongTinTuyenSinhDao;

public class MenuConSeachLuong {
	public static Scanner scanner = new Scanner(System.in); 
	//menu con của update MonHoc
		public void switchsupdateMonHoc() {
			int choice = 0;
			MonHoc monhoc= new MonHoc();
			while (choice !=5 ) {

				//		while (!choice.equals("0")) {
				System.out.println("Mời bạn chọn chức năng:");
				System.out.println("1. Update toàn bộ dữ liệu qua ID Môn Học");
				System.out.println("3. Update Tên Môn Học");
				System.out.println("2. Update Số Tiền Dạy mỗi giờ");
				System.out.println("4. Update Mô tả môn học");
				System.out.println("5. Đăng Xuất");
				System.out.println("===============================================");
				System.out.println("  Mời bạn chọn tính năng: ");

				choice = Integer.parseInt(scanner.nextLine());

				switch (choice) {
				case 1:
					
					MonHocDao.updateMonHoc(monhoc.updateDataAll());
					break;
				case 2:
				
					MonHocDao.updateTenMonHoc(monhoc.updateDataTenMH());
					break;
				case 3:
					MonHocDao.updateTienDayMoiGioMonHoc(monhoc.updateDataSoTienMoiBuoiHoc());
					break;
				case 4:
					MonHocDao.updateMoTaMonHoc(monhoc.updateDataMota());
					break;
				case 5:
					System.out.println("kết thúc chương trình");
					break;
				default:
					System.out.println("Không hợp lệ, mời nhập lại!");
					continue;
				}
			}

		}
	//menu con của  Xem Thông Tin Tuyển Sinh
		public void timkiemTTTS() {
			int choice = 0;
			while (choice !=4 ) {

				System.out.println("Mời bạn chọn chức năng:");
				System.out.println("1. Hiển thị toàn bộ thông tin tuyển sinh");
				System.out.println("2. Hiển thị Top 3 môn học được đăng ký nhiều nhất theo quý");
				System.out.println("3. Hiển thị thông tin tuyển sinh của từng môn học");
				System.out.println("4. Hiển thị thông tin của các lớp khai giảng trong 30 ngày tới");
				System.out.println("5. Đăng Xuất");
				System.out.println("===============================================");
				System.out.println("  Mời bạn chọn tính năng: ");

				choice = Integer.parseInt(scanner.nextLine());

				switch (choice) {
				case 1:
					List<ThongTinTuyenSinh> list = ThongTinTuyenSinhDao.selectThongTinTuyeSinh();
					for (ThongTinTuyenSinh thongTinTuyenSinhClass : list) {
						System.out.println(thongTinTuyenSinhClass.toString());
					}
					break;
				case 2:
					MonHocDao.searchTop3Mh(null);
					break;
				case 3:
					MonHocDao.searchHienThiThongTinMonHoc(null);

					break;
				case 4:
					MonHocDao.searchLopKhaiGiang(null);
					break;
				case 5:
					System.out.println("kết thúc chương trình");
					break;
				default:
					System.out.println("Không hợp lệ, mời nhập lại!");
					continue;
				}
			}

		}
}

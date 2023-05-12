package menuChucNangCon;
import java.util.Scanner;
import entities.MonHoc;
import function.MonHocDao;
public class MemuUpdateMonHoc {
	public static Scanner scanner = new Scanner(System.in); 
	//menu con của update MonHoc
	public void switchsupdateMonHoc() {
		int choice = 0;
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
				MonHoc monhoc= new MonHoc();
				MonHocDao.updateMonHoc(monhoc.updateDataAll());
				break;
			case 2:
				MonHoc monhoc1= new MonHoc();
				MonHocDao.updateTenMonHoc(monhoc1.updateDataTenMH());
				break;
			case 3:
				MonHoc monhoc2= new MonHoc();
				MonHocDao.updateTienDayMoiGioMonHoc(monhoc2.updateDataSoTienMoiBuoiHoc());
				break;
			case 4:
				MonHoc monhoc3= new MonHoc();
				MonHocDao.updateMoTaMonHoc(monhoc3.updateDataMota());
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

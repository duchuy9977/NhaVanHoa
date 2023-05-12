package menuChucNangCon;

import java.util.ArrayList;
import java.util.Scanner;

import entities.PhuHuynh;
import function.PhuHuynhDao;

public class MenuPhuHuynh {
	public static void quanLyPhuHuynh() {
		Scanner input = new Scanner(System.in);
		ArrayList<PhuHuynh> list = new ArrayList<PhuHuynh>();
		PhuHuynhDao PHdao = new PhuHuynhDao();
		
		do {
			try {
				System.out.println();
				System.out.println("Thông tin Phụ huynh");
				System.out.println("___________________________________");
				System.out.println("1. Thêm thông tin Phụ huynh");
				System.out.println("2. Cập nhật thông tin Phụ huynh");
				System.out.println("3. Xóa thông tin Phụ huynh");
				System.out.println("4. Liệt kê thông tin học phí của các phụ huynh chưa đóng học phí");
				System.out.println("5. Top 3 phụ huynh có số lượng đăng kí lớp cho trẻ học nhiều nhất");
				System.out.println("6. Hiển thị Phụ huynh có từ 2 con trở lên học chung trường");
				System.out.println();
				System.out.println("7. Thoat khoi chuong trinh");
				System.out.println();
				System.out.println("Nhap lua chon cua ban: ");
				String choice = input.nextLine();
				switch (choice) {
				case "1":
					PhuHuynh x = new PhuHuynh();
					x.inputInfo();
					PHdao.insertPH(x);
					break;
				case "2":
					PhuHuynhDao x1 = new PhuHuynhDao();
					x1.updatePhuHuynh();
					break;
				case "3":
					PhuHuynhDao x2 = new PhuHuynhDao();
					x2.deleteIDPhuHuynh();
					break;
				 case "4":
					PhuHuynhDao x3 = new PhuHuynhDao();
					x3.displayHocPhiCanDong();
				 break;
				case "5":
					PhuHuynhDao x4 = new PhuHuynhDao();
					x4.displayTop3();
					break;
				case "6":
					PhuHuynhDao x5 = new PhuHuynhDao();
					x5.displayPH();
					break;
				case "7":
					return;
				default:
					System.out.println("thong tin ban nhap chua dung, xin hay kiem tra lai");
					break;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		} while (true);

	}
}

	
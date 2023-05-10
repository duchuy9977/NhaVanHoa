package app;

import java.util.Scanner;

import entities.GiaoVien;
import function.FunctionAccount;
import function.FunctionGiaoVien;
import validation.ValidationAccount;
import validation.ValidationGiaoVien;

public class MenuUpdateGiaoVien {
	public static void menuUpdate() {
		Scanner scanner = new Scanner(System.in);
		FunctionGiaoVien functionGV = new FunctionGiaoVien();
		FunctionAccount functionAcc = new FunctionAccount();
		ValidationGiaoVien validationGV = new ValidationGiaoVien();
		ValidationAccount validationAcc = new ValidationAccount();
		boolean flag = true;
		do {
			try {
				System.out.println("Chuong trinh quan ly giao vien");
				System.out.println("===============================================================");
				System.out.println("1. update id môn học của Giao Vien");
				System.out.println("2. update lương mỗi buổi dạy của giáo viên");
				System.out.println("3. update username của giáo viên");
				System.out.println("4. update địa chỉ giao vien");
				System.out.println("5. update email giáo viên");
				System.out.println("6. update số điện thoại giáo viên");
				System.out.println("7. update số năm kinh nghiệm giáo viên");
				System.out.println("9. thoát khỏi trình update");

				int choice = Integer.parseInt(scanner.nextLine());

				switch (choice) {
				case 1:
					System.out.println("Bạn đã chọn chức năng cập nhật id môn học của Giao Vien!");
					GiaoVien x = new GiaoVien();
					
					break;
				case 2:
				
					break;

				case 3:
				
					break;

				case 4:

					break;
				case 5:

					break;

				case 6:
				
					break;
				case 7:
					
					break;
				case 8:
				
					break;
				case 9:
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

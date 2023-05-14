package menuChucNangCon;

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
		boolean flag = true;
		do {
			try {
				System.out.println("Chuong trinh update thoong tin giao vien");
				System.out.println("===============================================================");
				System.out.println("1. update id môn học của Giao Vien");
				System.out.println("2. update lương mỗi buổi dạy của giáo viên");
				System.out.println("3. update username của giáo viên");
				System.out.println("4. update địa chỉ giao vien");
				System.out.println("5. update email giáo viên");
				System.out.println("6. update số điện thoại giáo viên");
				System.out.println("7. update số năm kinh nghiệm giáo viên");
				System.out.println("8. thoát khỏi trình update");

				int choice = Integer.parseInt(scanner.nextLine());

				switch (choice) {
				case 1:
					System.out.println("Bạn đã chọn chức năng cập nhật id môn học của Giao Vien!");
					GiaoVien x = new GiaoVien();
					x.inputInfoUpdateIdMonhoc();
					functionGV.updateIdMonHoc(x);
					break;
				case 2:
					System.out.println("Bạn đã chọn chức năng cập nhật lương mỗi buổi dạy của Giao Vien!");
					GiaoVien y = new GiaoVien();
					y.inputInfoUpdateLuongMoiBuoiDay();
					functionGV.updateLuongMoiBuoiDay(y);
					break;

				case 3:
					System.out.println("Bạn đã chọn chức năng cập nhật user name của Giao Vien!");
					GiaoVien a = new GiaoVien();
					a.inputInfoUpdateUserName();
					functionGV.updateLuongMoiBuoiDay(a);
					break;

				case 4:
					System.out.println("Bạn đã chọn chức năng cập nhật địa chỉ của Giao Vien!");
					GiaoVien b = new GiaoVien();
					b.inputInfoUpdateDiaChi();
					functionGV.updateLuongMoiBuoiDay(b);
					break;
				case 5:
					System.out.println("Bạn đã chọn chức năng cập nhật email của Giao Vien!");
					GiaoVien c = new GiaoVien();
					c.inputInfoUpdateEmail();
					functionGV.updateLuongMoiBuoiDay(c);
					break;

				case 6:
					System.out.println("Bạn đã chọn chức năng cập nhật SĐT của Giao Vien!");
					GiaoVien d = new GiaoVien();
					d.inputInfoUpdateSDT();
					functionGV.updateLuongMoiBuoiDay(d);
					break;
				case 7:
					System.out.println("Bạn đã chọn chức năng cập nhật số năm kinh nghiệm của Giao Vien!");
					GiaoVien e = new GiaoVien();
					e.inputInfoUpdateSoNamKinhNghiem();
					functionGV.updateLuongMoiBuoiDay(e);
					break;
				case 8:
					flag = false;
					break;

				default:
					System.out.println("bạn nhập chưa đúng, vui lòng nhập lại");
					break;
				}

			} catch (NumberFormatException e) {
				// TODO: handle exception
				System.out.println("bạn nhập chưa đúng, vui lòng nhập lại");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} while (flag);
		System.out.println("Chuong trinh ket thuc");
		System.out.println("================================== END ===============================================\n");
	}

}

package validation;

import java.util.Scanner;
import java.util.regex.Pattern;

import function.FunctionGiaoVien;

public class ValidationGiaoVien {

	public ValidationGiaoVien() {
	}

	Scanner input = new Scanner(System.in);

	public String inputIdGiaoVien(String message) {
		do {
			System.out.println(message);
			String idGiaoVien = input.nextLine();
			Pattern pattern = Pattern.compile("^GV[0-9]{3,3}$");
			if (pattern.matcher(idGiaoVien).find()) {
					return idGiaoVien;
				}
				System.out.println("ban nhap sai ma ID Giao Vien, hay nhap lai theo format (GV***)");
			
		} while (true);
	}

	public String inputIdMonHoc(String message) {
		do {
			System.out.println(message);
			String idMonHoc = input.nextLine();
				Pattern pattern = Pattern.compile("^MH[0-9]{3,3}$");
				if (pattern.matcher(idMonHoc).find()) {
					return idMonHoc;
				}
				System.out.println("ban nhap sai ma ID Mon HOc, hay nhap lai theo format (MH***)");
			
		} while (true);
	}

	public int inputLuongMoiBuoiDay(String message) {
		do {
			try {
				System.out.println(message);
				int luongMoiBuoiDay = Integer.parseInt(input.nextLine());
				return luongMoiBuoiDay;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("hãy nhập lại lương mỗi buổi dạy bằng chữ số");
			}
		} while (true);
	}

	public String inputUserName(String message) {
		do {
			System.out.println(message);
			String userName = input.nextLine();

				Pattern pattern = Pattern.compile("^[a-zA-Z_0-9]*$");
				if (pattern.matcher(userName).find()) {
					return userName;
				}
				System.out.println("ban nhap sai định dang của username, username k chứa ký tự đặc biệt, hay nhap lai");
			
		} while (true);
	}

	public int inputSoNamKinhNghiem(String message) {
		do {
			try {
				System.out.println(message);
				int soNamKinhNghiem = Integer.parseInt(input.nextLine());
				return soNamKinhNghiem;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("hay nhap lai diem danh gia bang chu so");
			}
		} while (true);
	}

	public String inputDiaChi(String message) {
		do {
			System.out.println(message);
			String diaChi = input.nextLine();
			if (diaChi.length() >= 2) {

				return diaChi;
			}
			System.out.println("ban nhap sai, hay nhap lai thon tin dia chi it nhat 2 ky tu!");
		} while (true);
	}

	public String inputSDT(String message) {
		do {
			System.out.println(message);
			String sdt = input.nextLine();
			Pattern pattern = Pattern.compile("^0[0-9]{9}$");
			if (pattern.matcher(sdt).find()) {

				return sdt;
			}
			System.out.println("ban nhap sai ma so giao vien, hay nhap lai theo format 10 chu so");
		} while (true);
	}

	public String inputEmail(String message) {
		do {
			System.out.println(message);
			String email = input.nextLine();
			Pattern pattern = Pattern.compile("^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$");
			if (pattern.matcher(email).find()) {

				return email;
			}
			System.out.println("ban nhap sai định dang của email, email cần phải có định dạng "
					+ "- Bắt đầu bằng chữ cái.\r\n" + "- Chỉ chứa chữ cái, chữ số và dấu gạch ngang (-).\r\n"
					+ "- Chứa một ký tự @, sau @ là tên miền.\r\n"
					+ "- Tên miền có thể là domain.xxx.yyy hoặc domain.xxx. Trong đó xxx và yyy là các chữ cái và có độ dài từ 2 trở lên., "
					+ "Hay nhap lai");
		} while (true);
	}

}

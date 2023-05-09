package validation;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ValidationThongTinTuyenSinh {
	Scanner input = new Scanner(System.in);
	
	public ValidationThongTinTuyenSinh() {
		
	}

	public String inputIDLop(String message) {

		do {
			System.out.println(message);
			String idLop = input.nextLine();
			Pattern x = Pattern.compile("^L[0-9]{3}$");
			if (x.matcher(idLop).find()) {
				return idLop;
			}
			System.out.println("Nhập IdMonHoc có dạng Lxxx ");
		} while (true);
	}
	
	public String inputString(String message) {

		do {
			System.out.println(message);
			String s = input.nextLine();
			if (s.length()>3) {
				return s;
			}
		} while (true);
	}
	
	public String inputStatus(String message) {
		do {
			System.out.println(message);
			String status = input.nextLine();
			if ((status.equals("dangmo") || status.equals("dangdong"))) {

				return status;
			}
			System.out.println("ban nhap sai, hay nhap lai trạng thái là dangmo hoặc dangdong!");
		} while (true);
	}
	
}

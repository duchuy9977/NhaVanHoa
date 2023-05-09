package validation;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ValidationAccount {

	public ValidationAccount() {
	}
	Scanner input = new Scanner(System.in);
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
	
	public String inputPassWork(String message) {
		do {
			System.out.println(message);
			String passWork = input.nextLine();
			Pattern pattern = Pattern.compile("^[0-9]{6,6}$");
			if (pattern.matcher(passWork).find()) {

				return passWork;
			}
			System.out.println("ban nhap sai dinh dang mat khau, hay nhap lai");
		} while (true);
	}
	
	public String inputName(String message) {
		do {
			System.out.println(message);
			String name = input.nextLine();
			if (name.length() >= 2) {

				return name;
			}
			System.out.println("ban nhap sai, hay nhap lai ho va ten dai it nhat 2 ky tu!");
		} while (true);
	}
	
	public String inputIdRole(String message) {
		do {
			System.out.println(message);
			String idRole = input.nextLine();
			if ((idRole.equals("admin") || idRole.equals("giaovien") || idRole.equals("phuhuynh"))) {

				return idRole;
			}
			System.out.println("ban nhap sai, hay nhap lai ho va ten dai it nhat 2 ky tu!");
		} while (true);
	}
	
	public String inputNameRole(String message) {
		do {
			System.out.println(message);
			String nameRole = input.nextLine();
			if (nameRole.length() >= 2) {

				return nameRole;
			}
			System.out.println("ban nhap sai, hay nhap lai tên chức vụ dài it nhat 2 ky tu!");
		} while (true);
	}
	
	public String inputStatus(String message) {
		do {
			System.out.println(message);
			String status = input.nextLine();
			if ((status.equals("Ban") || status.equals("Active"))) {

				return status;
			}
			System.out.println("ban nhap sai, hay nhap lai trạng thái là Ban hoặc Active!");
		} while (true);
	}
}

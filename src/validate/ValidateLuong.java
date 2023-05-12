package validate;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ValidateLuong {
	
		private static Scanner input = new Scanner(System.in);

		public ValidateLuong() {

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

		public String inputIDMonHoc(String message) {
			do {
				System.out.println(message);
				String idMonHoc = input.nextLine();
				Pattern x = Pattern.compile("^MH[0-9]{3}$");
				if (x.matcher(idMonHoc).find()) {
					return idMonHoc;
				}
				System.out.println("Nhập IdMonHoc có dạng MHxxx");
			} while (true);
		}

		public String inputIDLop(String message) {

			do {
				System.out.println(message);
				String idLop = input.nextLine();
				Pattern x = Pattern.compile("^L[0-9]{3}$");
				if (x.matcher(idLop).find()) {
					return idLop;
				}
				System.out.println("Nhập IdMonHoc có dạng Lxxx");
			} while (true);
		}

		public String inputNull(String message) {
			do {
				System.out.println(message);
				String s = input.nextLine();

				if (s.length() >= 0) {
					return s;
				}

			} while (true);
		}

		public float inputMoney(String message) {
			do {
				try {
					System.out.println(message);
					float money = Float.parseFloat(input.nextLine());
					return money;

				} catch (Exception e) {
					System.out.println("Xin hãy nhập số");
				} 
			} while (true);
		}

		public String inputStatus(String message) {
			do {
				try {
					System.out.println(message);
					String s = input.nextLine();
					if (s.equals("dangmo") || s.equals("dangdong")) {
						return s;
					}

				} catch (Exception e) {
					System.out.println("Nhập Status là dangmo hoặc dangdong");			}

			} while (true);
		}
		public String inputQuy(String message) {
			do {
				try {
					System.out.println(message);
					String a = input.nextLine();
					if (a.equals("1") || a.equals("2") || a.equals("3") || a.equals("4") ) {
						return a;
					}
							
				} catch (Exception e) {
					System.out.println("Nhập Quý = 1,2,3,4 ");
				}
			} while (true);
		}
		
		public String inputYear(String message) {
			do {
				System.out.println(message);
				String idMonHoc = input.nextLine();
				Pattern x = Pattern.compile("^[0-9]{4}$");
				if (x.matcher(idMonHoc).find()) {
					return idMonHoc;
				}
				System.out.println("Nhập Năm VD 2000");
			} while (true);
		}
	}


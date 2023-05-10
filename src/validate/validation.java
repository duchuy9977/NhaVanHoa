package validate;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class validation {
	private static Scanner input = new Scanner(System.in);

	public validation() {
	}

	public static String inputIDPhuHuynh(String message) {
		do {
			System.out.print(message);
			String iDPhuHuynh = input.nextLine();
			Pattern x = Pattern.compile("^PH[0-9]{3}$");
			if (x.matcher(iDPhuHuynh).find()) {
				return iDPhuHuynh;
			}
			System.out.println("Bạn đã nhập sai, xin nhập lại");

		} while (true);
	}

	public String inputString(String message) {
		
		do {
			System.out.print(message);
			String s = input.nextLine();
			if (s.length() >= 5) {
				
				return s;
			}
			System.out.println("Bạn đã nhập sai, xin nhập lại");
		} while (true);

	}

	public String inputEmail(String message) {
		
		do {
			System.out.print(message);
			String email = input.nextLine();
			Pattern e = Pattern.compile("^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$");
			if (e.matcher(email).find()) {
				
				return email;
			}
			System.out.println("Bạn đã nhập sai, xin nhập lại");
		} while (true);
	}

	public String inputsDT1(String message) {
		
		do {
			System.out.print(message);
			String sDT1 = input.nextLine();
			Pattern y = Pattern.compile("^0[0-9]{10}$");
			if (y.matcher(sDT1).find()) {
				
				return sDT1;
			}
			System.out.println("Bạn đã nhập sai, xin nhập lại");

		} while (true);
	}

	public String inputsDT2(String message) {
		
		do {
			System.out.print(message);
			String sDT2 = input.nextLine();
			if (sDT2 != "" && sDT2 != null) {
				Pattern y = Pattern.compile("^0[0-9]{10}$");
				if (y.matcher(sDT2).find()) {
					
					return sDT2;
				}
				System.out.println("Bạn đã nhập sai, xin nhập lại");
			}
		} while (true);
	}

	public String inputsDT3(String message) {
		
		do {
			System.out.print(message);
			String sDT3 = input.nextLine();
			if (sDT3 != "" && sDT3 != null) {
				Pattern y = Pattern.compile("^0[0-9]{10}$");
				if (y.matcher(sDT3).find()) {
					
					return sDT3;
				}
				System.out.println("Bạn đã nhập sai, xin nhập lại");
			}

		} while (true);
	}

}

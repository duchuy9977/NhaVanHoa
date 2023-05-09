package validation;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ValidationMonHoc {
	private static Scanner input = new Scanner(System.in);
	public ValidationMonHoc() {
		
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
}

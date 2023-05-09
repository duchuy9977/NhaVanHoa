package validation;

import java.util.Scanner;

public class validation_account {
public static Scanner input = new Scanner(System.in);
	
	public void Validate() {
		
	}
	public String inputUsedname(String message) {
		do {
			System.out.println(message);
			String s = input.nextLine();
			if (s.length()>3) {
				return s;
			}
		}while (true);
		
	}
}

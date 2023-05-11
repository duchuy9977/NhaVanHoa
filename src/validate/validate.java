package validate;

import java.sql.Date;
import java.util.Scanner;
import java.util.regex.Pattern;



public class validate {
	public static Scanner sc = new Scanner(System.in);
	
	public String inputidlop(String message) {
		System.out.println(message);
		do {
			String idlop = sc.nextLine();
			Pattern x = Pattern.compile("^L[0-9]{3}$");
			if (x.matcher(idlop).find()) {
				return idlop;
			}System.out.println("ID lớp phải 4 kí tự và bắt đầu bằng kí tự L");
		} while (true);
	}
	
	public String inputidmonhoc(String message) {
		System.out.println(message);
		do {
			String idmonhoc = sc.nextLine();	
			Pattern x = Pattern.compile("^MH[0-9]{3}$");
			if (x.matcher(idmonhoc).find()) {
				return idmonhoc;
			}System.out.println("ID Môn Học phải 5 kí tự và bắt đầu bằng kí tự MH");
		} while (true);
	}
	
	public String inputstring (String message) {
		System.out.println(message);
		do {
			String string = sc.nextLine();
			if(string.length()>5) {
				return string;
			}System.out.println("Phải nhập trên 5 kí tự");
		} while (true);
	}
	
	public int inputsobuoi(String message) {
		System.out.println(message);
		do {
			try {
				int sobuoi=Integer.parseInt(sc.nextLine());
				return sobuoi;
			} catch (Exception e) {
				System.out.println("Kí tự cần nhập là số");
				// TODO: handle exception
			}
		} while (true);
	}
	
	public Date inputdate(String message) {
		System.out.println(message);
		do {
			try {
				
				Date x  = Date.valueOf(sc.nextLine());
				return x ;
			} catch (Exception e) {
				System.out.println("Nhập ở định dạng yyyy-mm-dd");
				// TODO: handle exception
			}
		} while (true);
	}


}

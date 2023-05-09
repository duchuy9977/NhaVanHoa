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
			}System.out.println("id lop phai 4 ki tu va bat dau bang ki tu L");
		} while (true);
	}
	
	public String inputidmonhoc(String message) {
		System.out.println(message);
		do {
			String idmonhoc = sc.nextLine();
			Pattern x = Pattern.compile("^MH[0-9]{3}");
			if(x.matcher(idmonhoc).find()) {
				return idmonhoc ; 
			}System.out.println("id mon hoc gom 5 ki tu va bat dau bang MH");
		} while (true);
	}
	
	public String inputstring (String message) {
		System.out.println(message);
		do {
			String string = sc.nextLine();
			if(string.length()>5) {
				return string;
			}System.out.println("phai nhap tren 5 ki tu");
		} while (true);
	}
	
	public int inputsobuoi(String message) {
		System.out.println(message);
		do {
			try {
				int sobuoi=Integer.parseInt(sc.nextLine());
				return sobuoi;
			} catch (Exception e) {
				System.out.println("ki tu cna nhap la so");
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
				System.out.println("nhap lai o dinh dinh yyyy-mm-dd");
				// TODO: handle exception
			}
		} while (true);
	}
	public String abc() {
		while (true) {
			System.out.println("moi ban nhap cot can update");
			String x=sc.nextLine();
			if(x.equals("TenLop") || x.equals("IDMonHoc")|| x.equals("SoBuoi") || x.equals("NgayKhaiGiang") || x.equals("NgayKetThuc") ||x.equals("NgayBatDau")) {
				return x ;
			}System.out.println("moi ban nhap lai");
		}
	}
//	public String main(String[] args) {
//		System.out.println("nhap 1 de update id mon hoc");
//		System.out.println("nhap 2 de update ten lop");
//		System.out.println("nhap 3 de update so buoi");
//		System.out.println("nhap 4 de update ngay khai giang");
//		System.out.println("nhap 5 de update ngay bat dau");
//		System.out.println("nhap 6 de update ngay ket thuc");
//		System.out.println("nhap 7 de thoat");
//		int choice = Integer.parseInt(sc.nextLine());
//		if (choice == 1) {
//			return MonHoc;
//		}
//		if (choice == 2) {
//			return "MonHoc";
//		}
//		if (choice == 3) {
//			return "MonHoc";
//		}
//		if (choice == 4) {
//			return "MonHoc";
//		}
//		if (choice == 5) {
//			return "MonHoc";
//		}
//		if (choice == 6) {
//			return "MonHoc";
//		}
//		if (choice == 7) {
//			return "MonHoc";
//		}
//	}

}

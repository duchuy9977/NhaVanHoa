package validation;

import java.util.Scanner;
import java.util.regex.Pattern;

public class buoihoc_PhucHL1 {
	public static Scanner input = new Scanner(System.in);
	
	public void Validate() {
		
	}
	public String inputString(String message) {
		do {
			System.out.println(message);
			String s = input.nextLine();
			if (s.length()>3) {
				return s;
			}
		}while (true);
		
}
	public String inputIDBuoiHoc(String message) {
		do {
			System.out.println(message);
			String idBuoiHoc = input.nextLine();
			Pattern x = Pattern.compile("^BH[0-9]{3}$");
			if(x.matcher(idBuoiHoc).find()) {
			return idBuoiHoc();
			}
			System.out.println("Nhập IdBuoiHoc có dạng BHxxx");	
	} while (true);
	
}
	public String inputIDLop(String message) {
		do {
			System.out.println(message);
			String idLop = input.nextLine();
			Pattern x = Pattern.compile("^BH[0-9]{3}$");
			if(x.matcher(idLop).find()) {
			return idLop();
			}
			System.out.println("Nhập IdLop có dạng Lxxx");	
	} while (true);
	
}
	public String inputIDGiaoVien(String message) {
		do {
			System.out.println(message);
			String idGiaoVien = input.nextLine();
			Pattern x = Pattern.compile("^BH[0-9]{3}$");
			if(x.matcher(idGiaoVien).find()) {
			return idGiaoVien();
			}
			System.out.println("Nhập IdGiaoVien có dạng GVxxx");	
	} while (true);
	
}
	public String inputThu(String message) {
		do {
			System.out.println(message);
			String Thu = input.nextLine();
			Pattern x = Pattern.compile("^BH[1-7]{3}$");
			if(x.matcher(Thu).find()) {
			return Thu();
			}
			System.out.println("Nhập Thu có dạng xxx");	
	} while (true);
	
}
	public String inputTgianHoc(String message) {
		do {
			System.out.println(message);
			String TGianHoc = input.nextLine();
			Pattern x = Pattern.compile("^([2][0-3]|[0-1][0-9]|[1-9]):[0-5][0-9]:([0-5][0-9]|[6][0])\"");
			if(x.matcher(TGianHoc).find()) {
			return TGianHoc();
			}
			System.out.println("Nhập TgianHoc có dạng xxhxx - xxhxx");	
	} while (true);
	
}
	public String inputPhongHoc(String message) {
		do {
			System.out.println(message);
			String PhongHoc = input.nextLine();
			Pattern x = Pattern.compile("^BH[0-9]{3}$");
			if(x.matcher(PhongHoc).find()) {
			return PhongHoc();
			}
			System.out.println("Nhập IdLop có dạng Pxxx");	
	} while (true);
	
}
	public String inputNgayHoc(String message) {
		do {
			System.out.println(message);
			String NgayHoc = input.nextLine();
			Pattern x = Pattern.compile("\\d{1,2}[-|/]\\d{1,2}[-|/]\\d{4}");
			if(x.matcher(NgayHoc).find()) {
			return NgayHoc();
			}
			System.out.println("Nhập NgayHoc có dạng dd-mm-yyyy");	
	} while (true);
	
}
	public String inputStatus(String message) {
		do {
			System.out.println(message);
			String Status = input.nextLine();
			Pattern x = Pattern.compile("^[a-zA-Z0-9]+$");
			if(x.matcher(Status).find()) {
			return Status();
			}
			System.out.println("Nhập Status có dạng Text");	
	} while (true);
	
}
	private String Status() {
		// TODO Auto-generated method stub
		return null;
	}
	private String NgayHoc() {
		// TODO Auto-generated method stub
		return null;
	}
	private String PhongHoc() {
		// TODO Auto-generated method stub
		return null;
	}
	private String TGianHoc() {
		// TODO Auto-generated method stub
		return null;
	}
	private String Thu() {
		// TODO Auto-generated method stub
		return null;
	}	
	private String idGiaoVien() {
		// TODO Auto-generated method stub
		return null;
	}
	private String idLop() {
		// TODO Auto-generated method stub
		return null;
	}
	private String idBuoiHoc() {
		// TODO Auto-generated method stub
		return null;
	}
}

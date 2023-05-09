package validation;

import java.sql.Date;
import java.util.Scanner;

public class buoihoc {
	public class Validate {
		private Scanner sc = new Scanner(System.in);
// Bang vatidation_PhucHL1
		public Validate() {

		}
		public void main (String[] args) {
			String BuoiHoc = "IDBuoiHoc";
			System.out.println(valBuoiHoc(BuoiHoc));
		}
		private char[] valBuoiHoc(String buoihoc) {
			// TODO Auto-generated method stub
			return null;
		} 
			String BuoiHoc = "IDLop";
		
			
		public String inputString(String a) {
			System.out.println(a);
			while (true) {
				String n = sc.nextLine();
				if (n.length() >= 1) {
					return n;
				} else {
					System.out.println("Đã có lỗi xin nhập lại : ");
				}
			}
		}
		
		public Date checkDate(String a) {
			System.out.println(a);
			while(true) {
				try {
					String n = sc.nextLine();
					Date d = Date.valueOf(n);
					return d;
					}catch(Exception e) {
						e.printStackTrace();
						System.out.println("Đã xảy ra lỗi xin nhập đúng định dang yyyy-mm-dd");
					}
				
			}
		}
		
		public int inputInt(String a) {
			System.out.println(a);
			while(true) {
				try {
					int n = Integer.parseInt(sc.nextLine());
					return n;
				}catch(NumberFormatException e) {
					System.out.println("Xin chỉ nhập số không nhập kí tự chữ. \nMời nhập lại : ");
				}catch(Exception e) {
					e.printStackTrace();
					System.out.println("Đã xảy ra lỗi xin nhập lại : ");
				}
			}
		}
	}
}

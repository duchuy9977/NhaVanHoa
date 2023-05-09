package function;


import java.util.List;
import java.util.Scanner;

import entities.LopNangKhieu;
import simple.Simple;
import validate.validate;

public class Manage {
	private static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		validate validate=new validate();
		Simple sim = new Simple();
		do {
			try {
				System.out.println("chuong trinh lop nang khieu");
				System.out.println("__________________________");
				System.out.println("1. them thong tin ");
				System.out.println("2. hien thi tat ca thong tin");
				System.out.println("3. cap nhap thong tin");
				System.out.println("4. xoa thong tin");
				System.out.println();
				System.out.println("6. thoat khoi chuong trinh");
				System.out.println();
				System.out.println("nhap lua chon cua ban");
				int choice =Integer.parseInt(sc.nextLine());
				switch (choice) {
				case 1:
					LopNangKhieu lop = new LopNangKhieu();
					lop.intutdata();
					sim.insertdata(lop);
					System.out.println(lop);
					break;
				case 2 :
					List<LopNangKhieu> list = sim.selectall();
					for (LopNangKhieu lopNangKhieu : list) {
						System.out.println(lopNangKhieu.toString());
					}
					break;
				case 3:
					sim.updatedata();
					break;
				case 4:
					sim.delete();
				default:
					break;
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		} while (true);
		
	}
	
}

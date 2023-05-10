package function;


import java.util.List;
import java.util.Scanner;

import entities.LopNangKhieu;
import simple.Simple;
import validate.validate;

public class Manage {
public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
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
				System.out.println("5. hien thi thong tin co so buoi hoc > ? va nho hon ?");
				System.out.println("6. hien thi thong tin lop hoc va so luong hoc sinh theo hoc lop do");
				System.out.println();
				System.out.println("7. thoat khoi chuong trinh");
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
					break;
				case 5 :
					List<LopNangKhieu> list1 = sim.selectsobuoi();
					for (LopNangKhieu lopNangKhieu : list1) {
						System.out.println(lopNangKhieu.toString());
					}
					break;
				case 6 :
					List<LopNangKhieu> list2 = sim.selec2();
					for (LopNangKhieu lopNangKhieu : list2) {
						System.out.println(lopNangKhieu.toString1());}
					break;
				case 7:
				default:
					System.out.println("thong tin ban nhap chua dung xin kiem tra lai");
					break;
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		} while (true);
		
	}
	
}

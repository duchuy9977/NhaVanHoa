package function;


import java.util.List;
import java.util.Scanner;

import entities.LopNangKhieu;
import simple.Simple;
import validate.validate;

public class FuncitionLopNangKhieu {
public static void FuncitionLopNangKhieu(){
	
		Scanner sc = new Scanner(System.in);
		validate validate=new validate();
		Simple sim = new Simple();
		do {
			try {

				System.out.println("Chương trình lớp Năng khiếu");
				System.out.println("___________________________________________________________________________-");
				System.out.println("1. Thêm Thông tin");
				System.out.println("2. Hiển thị tất cả thông tin");
				System.out.println("3. Cập nhập thông tin");
				System.out.println("4. Xóa thông tin");
				System.out.println("5. Hiển thị thông tin có số buổi học > ? va số buổi học < ?");
				System.out.println("6. Hiển thị thông tin lớp học và số lượng học sinh theo học lớp đó");
				System.out.println("7. Hiển thị thông tin lóp học theo usename giáo viên");
				System.out.println("8. Hiển thị thông tin lóp học đến ngày khai giảng mà vẫn chưa đủ học viên đăng kí học");
				System.out.println("9. Hiển thị học phí của từng lớp theo tháng");
				System.out.println();
				System.out.println("10. Thoát khỏi chương trình");
				System.out.println();
				System.out.println("Nhập sự lựa chọn của bạn");
				int choice =Integer.parseInt(sc.nextLine());
				switch (choice) {
				case 1:
					LopNangKhieu lop = new LopNangKhieu();
					lop.inputdata();
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
						System.out.println(lopNangKhieu.toString1());
						}
					break;
				case 7:
					sim.timkiemthongtintheousename();
					break;
				case 8:
					List<LopNangKhieu> list3 = sim.caclopthieuhocsinh();
					for (LopNangKhieu lopNangKhieu : list3) {
						System.out.println(lopNangKhieu.toString1());}
					break;
				case 9:
					sim.hocphitheothang();
					break;
				case 10:
					return;
				default:
					System.out.println("Thông tin bạn nhập chưa đúng xin kiểm tra lại");
					break;
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		} while (true);
		
	}
	
}

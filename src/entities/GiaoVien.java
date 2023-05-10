package entities;

import function.FunctionAccount;
import function.FunctionGiaoVien;
import validation.ValidationAccount;
import validation.ValidationGiaoVien;

public class GiaoVien {
	private String idGiaoVien;
	private String idMonHoc;
	private int luongMoiBuoiDay;
	private String userName;
	private String diaChi;
	private String email;
	private String sdt;
	private int soNamKinhNghiem;

	public GiaoVien() {
	}

	public GiaoVien(String idGiaoVien, String idMonHoc, int luongMoiBuoiDay, String userName, String diaChi,
			String email, String sdt, int soNamKinhNghiem) {
		super();
		this.idGiaoVien = idGiaoVien;
		this.idMonHoc = idMonHoc;
		this.luongMoiBuoiDay = luongMoiBuoiDay;
		this.userName = userName;
		this.diaChi = diaChi;
		this.email = email;
		this.sdt = sdt;
		this.soNamKinhNghiem = soNamKinhNghiem;
	}

	public String getIdGiaoVien() {
		return idGiaoVien;
	}

	public void setIdGiaoVien(String idGiaoVien) {
		this.idGiaoVien = idGiaoVien;
	}

	public String getIdMonHoc() {
		return idMonHoc;
	}

	public void setIdMonHoc(String idMonHoc) {
		this.idMonHoc = idMonHoc;
	}

	public int getLuongMoiBuoiDay() {
		return luongMoiBuoiDay;
	}

	public void setLuongMoiBuoiDay(int luongMoiBuoiDay) {
		this.luongMoiBuoiDay = luongMoiBuoiDay;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public int getSoNamKinhNghiem() {
		return soNamKinhNghiem;
	}

	public void setSoNamKinhNghiem(int soNamKinhNghiem) {
		this.soNamKinhNghiem = soNamKinhNghiem;
	}

	@Override
	public String toString() {
		return "GiaoVien [idGiaoVien=" + idGiaoVien + ", idMonHoc=" + idMonHoc + ", luongMoiBuoiDay=" + luongMoiBuoiDay
				+ ", userName=" + userName + ", diaChi=" + diaChi + ", email=" + email + ", sdt=" + sdt
				+ ", soNamKinhNghiem=" + soNamKinhNghiem + "]";
	}

//	public void inputInfo() {
//		ValidationGiaoVien validation = new ValidationGiaoVien();
//		this.idGiaoVien = validation.inputIdGiaoVien("hay nhap ma id giao vien");
//		this.idMonHoc = validation.inputIdMonHoc("hay nhap ma id mon hoc");
//		this.luongMoiBuoiDay = validation.inputLuongMoiBuoiDay("hay nhap he so luong");
//		this.userName = validation.inputUserName("hay nhap username");
//		this.diaChi = validation.inputDiaChi("hay nhap dia chi");
//		this.email = validation.inputEmail("hay nhap email");
//		this.sdt = validation.inputSDT("hay nhap so dien thoai");
//		this.soNamKinhNghiem = validation.inputSoNamKinhNghiem("hay nhap so nam kinh nghiem");
//	}
	public void inputInfoAddGV() {

		FunctionGiaoVien functionGV = new FunctionGiaoVien();
		ValidationGiaoVien validationGV = new ValidationGiaoVien();
		while (true) {
			setIdGiaoVien(validationGV.inputIdGiaoVien("hay nhap ma id giao vien"));
			if (functionGV.checkIDGiaoVien(getIdGiaoVien()) == true) {
				break;
			} else {
				System.out.println("id giáo viên bị trùng");
			}
		}
		while (true) {
			setIdMonHoc(validationGV.inputIdMonHoc("hay nhap ma id mon hoc"));
			if (functionGV.checkIDMonHoc(getIdMonHoc()) == false) {
				break;
			} else {
				System.out.println("id môn học chưa có, hay nhập lại");
			}
		}
		setLuongMoiBuoiDay(validationGV.inputLuongMoiBuoiDay("hay nhap he so luong mỗi buỗi dạy"));
		while (true) {
			setUserName(validationGV.inputUserName("hay nhap username"));
			if (functionGV.checkUserName(getUserName()) == false) {
				break;
			} else {
				System.out.println("user name chưa có, hay nhập lại");
			}
		}
		setDiaChi(validationGV.inputDiaChi("hay nhap dia chi"));
		setEmail(validationGV.inputEmail("hay nhap email"));
		setSdt(validationGV.inputSDT("hay nhap so dien thoai"));
		setSoNamKinhNghiem(validationGV.inputSoNamKinhNghiem("hay nhap so nam kinh nghiem"));
	}

//Function inputInfo update lương mỗi buổi dạy bảng giáo viên
	public void inputInfoUpdateLuongMoiBuoiDay() {

		FunctionGiaoVien functionGV = new FunctionGiaoVien();
		ValidationGiaoVien validationGV = new ValidationGiaoVien();

		while (true) {
			setIdGiaoVien(validationGV.inputIdGiaoVien("hay nhap ma id giao vien"));
			if (functionGV.checkIDGiaoVien(getIdGiaoVien()) == false) {
				break;
			} else {
				System.out.println("id giáo viên không tồn tại");
			}
		}
		setLuongMoiBuoiDay(validationGV.inputLuongMoiBuoiDay("hay nhap he so luong mỗi buỗi dạy"));
	}

//Function inputInfo update id môn học bảng giáo viên
	public void inputInfoUpdateIdMonhoc() {

		FunctionGiaoVien functionGV = new FunctionGiaoVien();
		ValidationGiaoVien validationGV = new ValidationGiaoVien();
		while (true) {
			setIdGiaoVien(validationGV.inputIdGiaoVien("hay nhap ma id giao vien"));
			if (functionGV.checkIDGiaoVien(getIdGiaoVien()) == false) {
				break;
			} else {
				System.out.println("id giáo viên không tồn tại");
			}
		}
		while (true) {
			setIdMonHoc(validationGV.inputIdMonHoc("hay nhap ma id mon hoc"));
			if (functionGV.checkIDMonHoc(getIdMonHoc()) == false) {
				break;
			} else {
				System.out.println("id môn học chưa có, hay nhập lại");
			}
		}
	}

	// Function inputInfo update username bảng giáo viên
	public void inputInfoUpdateUserName() {

		FunctionGiaoVien functionGV = new FunctionGiaoVien();
		ValidationGiaoVien validationGV = new ValidationGiaoVien();
		while (true) {
			setIdGiaoVien(validationGV.inputIdGiaoVien("hay nhap ma id giao vien"));
			if (functionGV.checkIDGiaoVien(getIdGiaoVien()) == false) {
				break;
			} else {
				System.out.println("id giáo viên không tồn tại");
			}
		}
		while (true) {
			setUserName(validationGV.inputUserName("hay nhap username"));
			if (functionGV.checkUserName(getUserName()) == false) {
				break;
			} else {
				System.out.println("user name chưa có, hay nhập lại");
			}
		}
	}

	// Function inputInfo update địa chỉ bảng giáo viên
	public void inputInfoUpdateDiaChi() {

		FunctionGiaoVien functionGV = new FunctionGiaoVien();
		ValidationGiaoVien validationGV = new ValidationGiaoVien();
		while (true) {
			setIdGiaoVien(validationGV.inputIdGiaoVien("hay nhap ma id giao vien"));
			if (functionGV.checkIDGiaoVien(getIdGiaoVien()) == false) {
				break;
			} else {
				System.out.println("id giáo viên không tồn tại");
			}
		}
		setDiaChi(validationGV.inputDiaChi("hay nhap dia chi"));
	}

	// Function inputInfo update email bảng giáo viên
	public void inputInfoUpdateEmail() {

		FunctionGiaoVien functionGV = new FunctionGiaoVien();
		ValidationGiaoVien validationGV = new ValidationGiaoVien();
		while (true) {
			setIdGiaoVien(validationGV.inputIdGiaoVien("hay nhap ma id giao vien"));
			if (functionGV.checkIDGiaoVien(getIdGiaoVien()) == false) {
				break;
			} else {
				System.out.println("id giáo viên không tồn tại");
			}
		}
		setEmail(validationGV.inputEmail("hay nhap email"));
	}

	// Function inputInfo update SĐT bảng giáo viên
	public void inputInfoUpdateSDT() {

		FunctionGiaoVien functionGV = new FunctionGiaoVien();
		ValidationGiaoVien validationGV = new ValidationGiaoVien();
		while (true) {
			setIdGiaoVien(validationGV.inputIdGiaoVien("hay nhap ma id giao vien"));
			if (functionGV.checkIDGiaoVien(getIdGiaoVien()) == false) {
				break;
			} else {
				System.out.println("id giáo viên không tồn tại");
			}
		}
		setSdt(validationGV.inputSDT("hay nhap so dien thoai"));
	}

	// Function inputInfo update số năm kinh nghiêm bảng giáo viên
	public void inputInfoUpdateSoNamKinhNghiem() {

		FunctionGiaoVien functionGV = new FunctionGiaoVien();
		ValidationGiaoVien validationGV = new ValidationGiaoVien();
		while (true) {
			setIdGiaoVien(validationGV.inputIdGiaoVien("hay nhap ma id giao vien"));
			if (functionGV.checkIDGiaoVien(getIdGiaoVien()) == false) {
				break;
			} else {
				System.out.println("id giáo viên không tồn tại");
			}
		}
		setSoNamKinhNghiem(validationGV.inputSoNamKinhNghiem("hay nhap so nam kinh nghiem"));
	}
}

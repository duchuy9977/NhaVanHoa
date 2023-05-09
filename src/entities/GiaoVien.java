package entities;

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

	public void inputInfo() {
		ValidationGiaoVien validation = new ValidationGiaoVien();
		this.idGiaoVien = validation.inputIdGiaoVien("hay nhap ma id giao vien");
		this.idMonHoc = validation.inputIdMonHoc("hay nhap ma id mon hoc");
		this.luongMoiBuoiDay = validation.inputLuongMoiBuoiDay("hay nhap he so luong");
		this.userName = validation.inputUserName("hay nhap username");
		this.diaChi = validation.inputDiaChi("hay nhap dia chi");
		this.email = validation.inputEmail("hay nhap email");
		this.sdt = validation.inputSDT("hay nhap so dien thoai");
		this.soNamKinhNghiem = validation.inputSoNamKinhNghiem("hay nhap so nam kinh nghiem");
	}
}

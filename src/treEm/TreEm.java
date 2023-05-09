package treEm;

import java.sql.Date;

public class TreEm {
	String IDTre;
	String IDPhuHuynh;
	int STT;
	String TenTre;
	Date NgaySinh;
	String TruongDangHoc;
	String GioiTinh;
	String Status;
	

	@Override
	public String toString() {
		return "TreEm [IDTre=" + IDTre + ", IDPhuHuynh=" + IDPhuHuynh + ", STT=" + STT + ", TenTre=" + TenTre
				+ ", NgaySinh=" + NgaySinh + ", TruongDangHoc=" + TruongDangHoc + ", GioiTinh=" + GioiTinh + ", Status="
				+ Status + "]";
	}


	public String getIDTre() {
		return IDTre;
	}


	public void setIDTre(String iDTre) {
		IDTre = iDTre;
	}


	public String getIDPhuHuynh() {
		return IDPhuHuynh;
	}


	public void setIDPhuHuynh(String iDPhuHuynh) {
		IDPhuHuynh = iDPhuHuynh;
	}


	public int getSTT() {
		return STT;
	}


	public void setSTT(int sTT) {
		STT = sTT;
	}


	public String getTenTre() {
		return TenTre;
	}


	public void setTenTre(String tenTre) {
		TenTre = tenTre;
	}


	public Date getNgaySinh() {
		return NgaySinh;
	}


	public void setNgaySinh(Date ngaySinh) {
		NgaySinh = ngaySinh;
	}


	public String getTruongDangHoc() {
		return TruongDangHoc;
	}


	public void setTruongDangHoc(String truongDangHoc) {
		TruongDangHoc = truongDangHoc;
	}


	public String getGioiTinh() {
		return GioiTinh;
	}


	public void setGioiTinh(String gioiTinh) {
		GioiTinh = gioiTinh;
	}


	public String getStatus() {
		return Status;
	}


	public void setStatus(String status) {
		Status = status;
	}


	public TreEm(String iDTre, String iDPhuHuynh, int sTT, String tenTre, Date ngaySinh, String truongDangHoc,
			String gioiTinh, String status) {
		IDTre = iDTre;
		IDPhuHuynh = iDPhuHuynh;
		STT = sTT;
		TenTre = tenTre;
		NgaySinh = ngaySinh;
		TruongDangHoc = truongDangHoc;
		GioiTinh = gioiTinh;
		Status = status;
	}


	public TreEm() {

	}


	public void inputInfo(String username) {
		validation validate = new validation();
		TreEmDao tre = new TreEmDao();
		this.IDTre = validate.inputIDTre("Xin hãy nhập ID trẻ em");
		this.IDPhuHuynh = validate.inputIDPhuHuynh(username);
		this.STT = tre.inputSTT(username);
		this.TenTre = validate.inputString("Xin hãy nhập tên trẻ");
		this.NgaySinh = tre.checkNgaySinh();
		this.TruongDangHoc = validate.inputString("Xin hãy nhập trường đang học");
		this.GioiTinh = validate.inputGioiTinh("Xin hãy nhập giới tính");
		this.Status = validate.inputStatus("Xin hãy nhập Status");
	}

}

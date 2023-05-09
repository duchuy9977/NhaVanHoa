package entities;

import validation.ValidationMonHoc;

public class MonHoc {
	private String idMonHoc;
	private String tenMon;
	private float soTienMoiBuoiHoc;
	private String moTa;
	
	
	public MonHoc() {
		super();
	}


	public MonHoc(String idMonHoc, String tenMon, float soTienMoiBuoiHoc, String moTa) {
		super();
		this.idMonHoc = idMonHoc;
		this.tenMon = tenMon;
		this.soTienMoiBuoiHoc = soTienMoiBuoiHoc;
		this.moTa = moTa;
	}


	public String getIdMonHoc() {
		return idMonHoc;
	}


	public void setIdMonHoc(String idMonHoc) {
		this.idMonHoc = idMonHoc;
	}


	public String getTenMon() {
		return tenMon;
	}


	public void setTenMon(String tenMon) {
		this.tenMon = tenMon;
	}


	public float getSoTienMoiBuoiHoc() {
		return soTienMoiBuoiHoc;
	}


	public void setSoTienMoiBuoiHoc(float soTienMoiBuoiHoc) {
		this.soTienMoiBuoiHoc = soTienMoiBuoiHoc;
	}


	public String getMoTa() {
		return moTa;
	}


	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}


	@Override
	public String toString() {
		return "MonHoc [idMonHoc=" + idMonHoc + ", tenMon=" + tenMon + ", soTienMoiBuoiHoc=" + soTienMoiBuoiHoc
				+ ", moTa=" + moTa + "]";
	}
	
	public void inputData() {
		ValidationMonHoc vali = new ValidationMonHoc();
		this.idMonHoc = vali.inputIDMonHoc("Nhập mã môn học");
		this.tenMon = vali.inputString("Nhập tên môn học");
		this.soTienMoiBuoiHoc= vali.inputMoney("Nhập học phí mỗi buổi");
		this.moTa =vali.inputNull("Nhập mô tả môn học nếu có");
	}
	
}

package entities;

import validation.ValidationThongTinTuyenSinh;

public class ThongTinTuyenSinh {
	private int idThongTin;
	private String idLop;
	private String tieuDe;
	private String noiDung;
	private String satatus;
	
	
	public ThongTinTuyenSinh() {
		super();
	}


	public ThongTinTuyenSinh(int idThongTin, String idLop, String tieuDe, String noiDung, String satatus) {
		super();
		this.idThongTin = idThongTin;
		this.idLop = idLop;
		this.tieuDe = tieuDe;
		this.noiDung = noiDung;
		this.satatus = satatus;
	}


	public int getIdThongTin() {
		return idThongTin;
	}


	public void setIdThongTin(int idThongTin) {
		this.idThongTin = idThongTin;
	}


	public String getIdLop() {
		return idLop;
	}


	public void setIdLop(String idLop) {
		this.idLop = idLop;
	}


	public String getTieuDe() {
		return tieuDe;
	}


	public void setTieuDe(String tieuDe) {
		this.tieuDe = tieuDe;
	}


	public String getNoiDung() {
		return noiDung;
	}


	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}


	public String getSatatus() {
		return satatus;
	}


	public void setSatatus(String satatus) {
		this.satatus = satatus;
	}


	@Override
	public String toString() {
		return "ThongTinTuyenSinh [idThongTin=" + idThongTin + ", idLop=" + idLop + ", tieuDe=" + tieuDe + ", noiDung="
				+ noiDung + ", satatus=" + satatus + "]";
	}
	
	public void inputData() {
		ValidationThongTinTuyenSinh vali =  new ValidationThongTinTuyenSinh();	
		this.idLop= vali.inputIDLop("Nhập ID lớp");
		this.tieuDe = vali.inputString("Nhập tiêu đề của lớp");
		this.noiDung = vali.inputString("Nhập nội dung lớp học");
		this.satatus = vali.inputStatus("Nhập trạng thái lớp học");

	}
	
	
	
}

package entities;

import java.sql.Date;

import simple.Simple;
import validate.validate;

public class LopNangKhieu {
	private String idlop;
	private String idmonhoc;
	private String tenlop;
	private int sobuoi;
	private Date ngaykhaigiang;
	private Date ngaybatdau;
	private Date ngayketthuc;
	private int sohocsinhtheohoc;
	public LopNangKhieu() {
		super();
	}
	public LopNangKhieu(String idlop, String idmonhoc, String tenlop, int sobuoi, Date ngaykhaigiang, Date ngaybatdau,
			Date ngayketthuc) {
		super();
		this.idlop = idlop;
		this.idmonhoc = idmonhoc;
		this.tenlop = tenlop;
		this.sobuoi = sobuoi;
		this.ngaykhaigiang = ngaykhaigiang;
		this.ngaybatdau = ngaybatdau;
		this.ngayketthuc = ngayketthuc;
	}
	public int getSohocsinhtheohoc() {
		return sohocsinhtheohoc;
	}
	public void setSohocsinhtheohoc(int sohocsinhtheohoc) {
		this.sohocsinhtheohoc = sohocsinhtheohoc;
	}
	public String getIdlop() {
		return idlop;
	}
	public void setIdlop(String idlop) {
		this.idlop = idlop;
	}
	public String getIdmonhoc() {
		return idmonhoc;
	}
	public void setIdmonhoc(String idmonhoc) {
		this.idmonhoc = idmonhoc;
	}
	public String getTenlop() {
		return tenlop;
	}
	public void setTenlop(String tenlop) {
		this.tenlop = tenlop;
	}
	public int getSobuoi() {
		return sobuoi;
	}
	public void setSobuoi(int sobuoi) {
		this.sobuoi = sobuoi;
	}
	public Date getNgaykhaigiang() {
		return ngaykhaigiang;
	}
	public void setNgaykhaigiang(Date ngaykhaigiang) {
		this.ngaykhaigiang = ngaykhaigiang;
	}
	public Date getNgaybatdau() {
		return ngaybatdau;
	}
	public void setNgaybatdau(Date ngaybatdau) {
		this.ngaybatdau = ngaybatdau;
	}
	public Date getNgayketthuc() {
		return ngayketthuc;
	}
	public void setNgayketthuc(Date ngayketthuc) {
		this.ngayketthuc = ngayketthuc;
	}
	@Override
	public String toString() {
		return "LopNangKhieu [idlop=" + idlop + ", idmonhoc=" + idmonhoc + ", tenlop=" + tenlop + ", sobuoi=" + sobuoi
				+ ", ngaykhaigiang=" + ngaykhaigiang + ", ngaybatdau=" + ngaybatdau + ", ngayketthuc=" + ngayketthuc
				+ "]";
	}
	
	public String toString1() {
		return "LopNangKhieu [idlop=" + idlop + ", idmonhoc=" + idmonhoc + ", tenlop=" + tenlop + ", sobuoi=" + sobuoi
				+ ", ngaykhaigiang=" + ngaykhaigiang + ", ngaybatdau=" + ngaybatdau + ", ngayketthuc=" + ngayketthuc
				+ ", sohocsinhtheohoc=" + sohocsinhtheohoc +"]";
	}
	
	public void intutdata() {
		Simple sim = new Simple();
		validate vali = new validate();
		this.idlop = vali.inputidlop("moi ban nhap id lop");
		this.idmonhoc = sim.Checkexistidmonhoc();
		this.tenlop = vali.inputstring("moi ban nhap ten lop");
		this.sobuoi = vali.inputsobuoi("moi ban nhap so buoi");
		this.ngaykhaigiang = vali.inputdate("moi ban nhap ngay khai giang");
		this.ngaybatdau = vali.inputdate("moi ban nhap ngay bat dau");
		this.ngayketthuc = vali.inputdate("moi ban nhap ngay ket thuc");

	}
	
}

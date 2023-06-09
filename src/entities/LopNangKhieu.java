package entities;

import java.sql.Date;

import simple.Simple;
import validate.validate;

public class LopNangKhieu {
	private String idlop;
	private String idmonhoc;
	private String tenlop;
	private int sobuoi;
	private Date ngaybatdau;
	private Date ngayketthuc;
	private int sohocsinhtheohoc;
	private int SoLuongHocVienToiDa;

	public LopNangKhieu() {
		super();
	}

	public LopNangKhieu(String idlop, String idmonhoc, String tenlop, int sobuoi, Date ngaybatdau, Date ngayketthuc,
			int SoLuongHocVienToiDa) {
		super();
		this.idlop = idlop;
		this.idmonhoc = idmonhoc;
		this.tenlop = tenlop;
		this.sobuoi = sobuoi;
		this.ngaybatdau = ngaybatdau;
		this.ngayketthuc = ngayketthuc;
		this.SoLuongHocVienToiDa = SoLuongHocVienToiDa;
	}

	public int getSoLuongHocVienToiDa() {
		return SoLuongHocVienToiDa;
	}

	public void setSoLuongHocVienToiDa(int soLuongHocVienToiDa) {
		SoLuongHocVienToiDa = soLuongHocVienToiDa;
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
				+ ", ngaybatdau=" + ngaybatdau + ", ngayketthuc=" + ngayketthuc + ", SoLuongHocVienToiDa="
				+ SoLuongHocVienToiDa + "]";
	}

	public String toString1() {
		return "LopNangKhieu [idlop=" + idlop + ", idmonhoc=" + idmonhoc + ", tenlop=" + tenlop + ", sobuoi=" + sobuoi
				+ ", ngaybatdau=" + ngaybatdau + ", ngayketthuc=" + ngayketthuc + ", SoLuongHocVienToiDa="
				+ SoLuongHocVienToiDa + ", sohocsinhtheohoc=" + sohocsinhtheohoc + "]";
	}

	public void inputdata() {
		validate vali = new validate();

		Simple sim = new Simple();
		do {
			String idlop = vali.inputidlop("Mời bạn nhập Id Lóp");
			if (sim.checktontaiidlop(idlop) == true) {
				System.out.println("Id Lớp đã tồn tại mời bạn nhập lại Id lớp");
			} else {
				this.idlop = idlop;
				break;
			}
		} while (true);

		do {
			String idmonhoc = vali.inputidmonhoc("Mời bạn nhập ID môn học");
			if (sim.checktontaiidmonhoc(idmonhoc) == false) {
				System.out.println("ID môn học không tồn tại mời bạn nhập lại ID môn học");
			} else {
				this.idmonhoc = idmonhoc;
				break;
			}
		} while (true);

		this.tenlop = vali.inputstring("Mời bạn nhập tên lớp");
		this.sobuoi = vali.inputsobuoi("Mời bạn nhập số buổi");
		this.ngaybatdau = vali.inputdate("Mời bạn nhập ngày bắt đàu");
		this.ngayketthuc = vali.inputdate("Mời bạn nhập ngày kết thúc");
		this.SoLuongHocVienToiDa = vali.inputsobuoi("Mời bạn nhập số học sinh theo học tối đa");

	}

}

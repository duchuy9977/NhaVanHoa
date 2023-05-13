package entities;

import function.ThongTinTuyenSinhDao;
import validate.ValidateLuong;

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
	
	public ThongTinTuyenSinh inputData() {
		ValidateLuong vali =  new ValidateLuong();
		ThongTinTuyenSinh ttts= new ThongTinTuyenSinh();
		String idlop;
		while (true) {
			idlop = vali.inputIDLop("Nhập ID lớp");
			if (ThongTinTuyenSinhDao.checkIDLop(idlop)==true && ThongTinTuyenSinhDao.checkIDLopTTTS(idlop)==false) {
				ttts.setIdLop(idlop);
				break;
			}
			else {
				System.out.println("Lớp đã tồn tại");
			}

		}
		ttts.setTieuDe(vali.inputString("Nhập tiêu đề của lớp"));
		ttts.setNoiDung(vali.inputString("Nhập Nội dung của lớp"));
		ttts.setSatatus(vali.inputStatus("Nhập Trạng thái lớp"));
		
		return ttts;
	}
	
	public ThongTinTuyenSinh updateDataAll() {
		ValidateLuong vali = new ValidateLuong();
		ThongTinTuyenSinh ttts =new ThongTinTuyenSinh();
		String idLop;
		while (true) {
			idLop=vali.inputIDLop("Nhập ID Lớp Cần update data");
			
			if (ThongTinTuyenSinhDao.checkIDLop(idLop)==true) {
				
				ttts.setTieuDe(vali.inputString("Nhập tiêu đề bạn muốn thay đổi"));
				ttts.setNoiDung(vali.inputString("Nhập Nội dung bạn muốn thay đổi"));
				ttts.setSatatus(vali.inputStatus("Nhập trạng thái"));
				ttts.setIdLop(idLop);
				break;
			}
			else {
				System.out.println("mã môn học Không tồn tại");
			}
			
		}	
		return ttts;
	}
	
	public ThongTinTuyenSinh updateDataTieuDe() {
		ValidateLuong vali = new ValidateLuong();
		ThongTinTuyenSinh ttts =new ThongTinTuyenSinh();
		String idLop;
		while (true) {
			idLop=vali.inputIDLop("Nhập ID Lớp Cần update phần tiêu đề");
			
			if (ThongTinTuyenSinhDao.checkIDLop(idLop)==true) {		
				ttts.setTieuDe(vali.inputString("Nhập nội dung tiêu đề bạn muốn thay đổi"));
				ttts.setIdLop(idLop);
				break;
			}
			else {
				System.out.println("mã môn học Không tồn tại");
			}
			
		}	
		return ttts;
	}
	
	public ThongTinTuyenSinh updateDataNoiDung() {
		ValidateLuong vali = new ValidateLuong();
		ThongTinTuyenSinh ttts =new ThongTinTuyenSinh();
		String idLop;
		while (true) {
			idLop=vali.inputIDLop("Nhập ID Lớp Cần update phần nội dung");
			
			if (ThongTinTuyenSinhDao.checkIDLop(idLop)==true) {		
				ttts.setNoiDung(vali.inputString("Nhập nội dung update"));
				ttts.setIdLop(idLop);
				break;
			}
			else {
				System.out.println("mã môn học Không tồn tại");
			}
			
		}	
		return ttts;
	}
	
	public ThongTinTuyenSinh updateDataStatus() {
		ValidateLuong vali = new ValidateLuong();
		ThongTinTuyenSinh ttts =new ThongTinTuyenSinh();
		String idLop;
		while (true) {
			idLop=vali.inputIDLop("Nhập ID Lớp Cần update phần tiêu đề");
			
			if (ThongTinTuyenSinhDao.checkIDLop(idLop)==true) {		
				ttts.setSatatus(vali.inputStatus("Nhập trạng thái"));
				ttts.setIdLop(idLop);
				break;
			}
			else {
				System.out.println("mã môn học Không tồn tại");
			}
			
		}	
		return ttts;
	}

	
	
}

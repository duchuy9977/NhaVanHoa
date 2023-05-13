package buoiHoc_PhucHL1;

import java.sql.Date;

public class BuoiHoc {
	
		private int IdBuoiHoc;
		private String IdLop;
		private String IdGiaoVien;
		private int Thu;
		private String TgianHoc;
		private String PhongHoc;
		private Date NgayHoc;
		private String Status;
		public BuoiHoc() {
			super();
		}
		public BuoiHoc(int idBuoiHoc, String idLop, String idGiaoVien, int thu, String tgianHoc, String phongHoc,
				Date ngayHoc, String status) {
			super();
			this.setIdBuoiHoc(idBuoiHoc);
			this.setIdLop(idLop);
			this.setIdGiaoVien(idGiaoVien);
			this.setThu(thu);
			this.setTgianHoc(tgianHoc);
			this.setPhongHoc(phongHoc);
			this.setNgayHoc(ngayHoc);
		}
		public int getIdBuoiHoc() {
			return IdBuoiHoc;
		}
		public void setIdBuoiHoc(int idBuoiHoc) {
			IdBuoiHoc = idBuoiHoc;
		}
		public String getIdLop() {
			return IdLop;
		}
		public void setIdLop(String idLop) {
			IdLop = idLop;
		}
		public String getIdGiaoVien() {
			return IdGiaoVien;
		}
		public void setIdGiaoVien(String idGiaoVien) {
			IdGiaoVien = idGiaoVien;
		}
		public int getThu() {
			return Thu;
		}
		public void setThu(int thu) {
			Thu = thu;
		}
		public String getTgianHoc() {
			return TgianHoc;
		}
		public void setTgianHoc(String tgianHoc) {
			TgianHoc = tgianHoc;
		}
		public String getPhongHoc() {
			return PhongHoc;
		}
		public void setPhongHoc(String phongHoc) {
			PhongHoc = phongHoc;
		}
		public Date getNgayHoc() {
			return NgayHoc;
		}
		public void setNgayHoc(Date ngayHoc) {
			NgayHoc = ngayHoc;
		}
		public String getStatus() {
			return Status;
		}
		public void setStatus(String status) {
			Status = status;
		}
		@Override
		public String toString() {
			return "BuoiHoc [IdBuoiHoc=" + IdBuoiHoc + ", IdLop=" + IdLop + ", IdGiaoVien=" + IdGiaoVien + ", Thu=" + Thu
					+ ", TgianHoc=" + TgianHoc + ", PhongHoc=" + PhongHoc + ", NgayHoc=" + NgayHoc + ", Status=" + Status
					+ "]";
		}

	public void intudata() {
		validate_PhucHL1 vali = new validate_PhucHL1 ();
		this.IdBuoiHoc = vali.inputIdBuoiHoc("moi ban nhap ma buoi hoc");
		this.IdLop = vali.inputIdLop("moi ban nhap ma lop");
		this.IdGiaoVien = vali.inputGiaoVien("moi ban nhap ma giao vien");
		this.Thu = vali.inputThu("moi ban nhap thu");
		this.TgianHoc = vali.inputTgianHoc("moi ban nhap thoi gian hoc");
		this.PhongHoc = vali.inputPhongHoc("moi ban nhap phong hoc");
		this.NgayHoc = vali.inputdate("moi ban nhap ngay hoc");
		this.Status = vali.inputStatus("moi ban nhap trang thai hoat dong");
		}
	public void inputInfo() {
		// TODO Auto-generated method stub
		
		}
	}

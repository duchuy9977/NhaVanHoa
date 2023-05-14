package entities;
import function.MonHocDao;
import validate.ValidateLuong;
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
	
	public MonHoc inputData() {
		ValidateLuong vali = new ValidateLuong();
		MonHoc mt=new MonHoc();
		String idmonhoc;
		while (true) {
			idmonhoc=vali.inputIDMonHoc("Nhập mã môn học");
			if (MonHocDao.checkIDMonHoc(idmonhoc)==false) {
				mt.setIdMonHoc(idmonhoc);
				break;
			}
			else {
				System.out.println("mã môn học đã tồn tại");
			}
		}
		mt.setTenMon(vali.inputString("Nhập tên môn học"));
		mt.setSoTienMoiBuoiHoc(vali.inputMoney("Nhập học phí mỗi buổi"));
		mt.setMoTa(vali.inputNull("Nhập mô tả môn học nếu có"));
		return mt;
	}

	public MonHoc updateDataAll() {
		ValidateLuong vali = new ValidateLuong();
		MonHoc vt =new MonHoc();
		String idmonhoc;
		while (true) {
			idmonhoc=vali.inputIDMonHoc("Nhập mã môn học");

			if (MonHocDao.checkIDMonHoc(idmonhoc)==true) {

				vt.setTenMon(vali.inputString("Nhập tên môn học"));
				vt.setSoTienMoiBuoiHoc(vali.inputMoney("Nhập học phí mỗi buổi"));
				vt.setMoTa(vali.inputNull("Nhập mô tả môn học nếu có"));
				vt.setIdMonHoc(idmonhoc);
				break;
			}
			else {
				System.out.println("mã môn học Không tồn tại");
			}

		}	
		return vt;
	}


	public MonHoc updateDataTenMH() {
		ValidateLuong vali = new ValidateLuong();
		MonHoc vt =new MonHoc();
		String idmonhoc;
		while (true) {
			idmonhoc=vali.inputIDMonHoc("Nhập mã môn học");
			if (MonHocDao.checkIDMonHoc(idmonhoc)==true) {

				vt.setTenMon(vali.inputString("Nhập tên môn học bạn update"));
				vt.setIdMonHoc(idmonhoc);
				break;
			}
			else {
				System.out.println("mã môn học Không tồn tại");
			}

		}	
		return vt;
	}


	public MonHoc updateDataSoTienMoiBuoiHoc() {
		ValidateLuong vali = new ValidateLuong();
		MonHoc vt =new MonHoc();
		String idmonhoc;
		while (true) {
			idmonhoc=vali.inputIDMonHoc("Nhập mã môn học");
			if (MonHocDao.checkIDMonHoc(idmonhoc)==true) {

				vt.setSoTienMoiBuoiHoc(vali.inputMoney("Nhập số tiền bạn muốn thay"));
				vt.setIdMonHoc(idmonhoc);
				break;
			}
			else {
				System.out.println("mã môn học Không tồn tại");
			}

		}	
		return vt;
	}

	public MonHoc updateDataMota() {
		ValidateLuong vali = new ValidateLuong();
		MonHoc vt = new MonHoc();
		String idmonhoc;
		while (true) {
			idmonhoc=vali.inputIDMonHoc("Nhập mã môn học");
			if (MonHocDao.checkIDMonHoc(idmonhoc)==true) {

				vt.setMoTa(vali.inputNull("Nhập Mô tả bạn muốn update"));
				vt.setIdMonHoc(idmonhoc);
				break;
			}
			else {
				System.out.println("mã môn học Không tồn tại");
			}

		}	
		return vt;
	}
	
}

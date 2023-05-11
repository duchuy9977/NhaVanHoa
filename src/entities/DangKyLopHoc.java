package entities;

public class DangKyLopHoc {
	private int idDangKy;
	private String idTre;
	private String idLop;
	private String status;
	private float diem;
	private String ngayDangKy;

	public DangKyLopHoc() {
		super();
	}

	public DangKyLopHoc(int idDangKy, String idTre, String idLop, String status, float diem, String ngayDangKy) {
		super();
		this.idDangKy = idDangKy;
		this.idTre = idTre;
		this.idLop = idLop;
		this.status = status;
		this.diem = diem;
		this.ngayDangKy = ngayDangKy;
	}

	public int getIdDangKy() {
		return idDangKy;
	}

	public void setIdDangKy(int idDangKy) {
		this.idDangKy = idDangKy;
	}

	public String getIdTre() {
		return idTre;
	}

	public void setIdTre(String idTre) {
		this.idTre = idTre;
	}

	public String getIdLop() {
		return idLop;
	}

	public void setIdLop(String idLop) {
		this.idLop = idLop;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getDiem() {
		return diem;
	}

	public void setDiem(float diem) {
		this.diem = diem;
	}

	public String getNgayDangKy() {
		return ngayDangKy;
	}

	public void setNgayDangKy(String ngayDangKy) {
		this.ngayDangKy = ngayDangKy;
	}

}

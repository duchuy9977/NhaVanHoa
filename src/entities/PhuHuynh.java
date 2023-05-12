package entities;

import java.util.Objects;

import validate.validation;

	public class PhuHuynh {
		String IDPhuHuynh;
		String DiaChi;
		String Email;
		String Username;
		String SDT1;
		String SDT2;
		String SDT3;
		public PhuHuynh() {
		}
		public PhuHuynh(String iDPhuHuynh, String diaChi, String email, String username, String sDT1, String sDT2,
				String sDT3) {
			super();
			IDPhuHuynh = iDPhuHuynh;
			DiaChi = diaChi;
			Email = email;
			Username = username;
			SDT1 = sDT1;
			SDT2 = sDT2;
			SDT3 = sDT3;
		}
		public String getIDPhuHuynh() {
			return IDPhuHuynh;
		}
		public void setIDPhuHuynh(String iDPhuHuynh) {
			IDPhuHuynh = iDPhuHuynh;
		}
		public String getDiaChi() {
			return DiaChi;
		}
		public void setDiaChi(String diaChi) {
			DiaChi = diaChi;
		}
		public String getEmail() {
			return Email;
		}
		public void setEmail(String email) {
			Email = email;
		}
		public String getUsername() {
			return Username;
		}
		public void setUsername(String username) {
			Username = username;
		}
		public String getSDT1() {
			return SDT1;
		}
		public void setSDT1(String sDT1) {
			SDT1 = sDT1;
		}
		public String getSDT2() {
			return SDT2;
		}
		public void setSDT2(String sDT2) {
			SDT2 = sDT2;
		}
		public String getSDT3() {
			return SDT3;
		}
		public void setSDT3(String sDT3) {
			SDT3 = sDT3;
		}
		@Override
		public String toString() {
			return "PhuHuynh [IDPhuHuynh=" + IDPhuHuynh + ", DiaChi=" + DiaChi + ", Email=" + Email + ", Username="
					+ Username + ", SDT1=" + SDT1 + ", SDT2=" + SDT2 + ", SDT3=" + SDT3 + "]";
		}
		public void inputInfo() {
			validation validate = new validation();
			this.IDPhuHuynh = validate.inputIDPhuHuynh("Xin hãy nhập ID Phụ Huynh: ");
			this.DiaChi = validate.inputString("Xin hãy nhập Địa chỉ Phụ huynh");
			this.Email = validate.inputEmail("Xin hãy nhập Email Phụ huynh");
			this.Username = validate.inputString("Xin hãy nhập Username Phụ huynh");
			this.SDT1 = validate.inputsDT1("Xin hãy nhập số điện thoại thứ nhất");
			this.SDT2 = validate.inputsDT2("Xin hãy nhập số điện thoại thứ hai");
			this.SDT3 = validate.inputsDT3("Xin hãy nhập số điện thoại thứ ba");
			
			
	}
	}
		



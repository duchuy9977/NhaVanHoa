package entities;

import function.FunctionAccount;
import function.FunctionGiaoVien;
import menuChucNangCon.MenuUpdateGiaoVien;
import validation.ValidationAccount;
import validation.ValidationGiaoVien;

public class Account {
	private String userName;
	private String passWork;
	private String name;
	private String idRole;
	private String nameRole;
	private String status;
	
	public Account() {
	}

	public Account(String userName, String passWork, String name, String idRole, String nameRole, String status) {
		super();
		this.userName = userName;
		this.passWork = passWork;
		this.name = name;
		this.idRole = idRole;
		this.nameRole = nameRole;
		this.status = status;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWork() {
		return passWork;
	}

	public void setPassWork(String passWork) {
		this.passWork = passWork;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdRole() {
		return idRole;
	}

	public void setIdRole(String idRole) {
		this.idRole = idRole;
	}

	public String getNameRole() {
		return nameRole;
	}

	public void setNameRole(String nameRole) {
		this.nameRole = nameRole;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Account [userName=" + userName + ", passWork=" + passWork + ", name=" + name + ", idRole=" + idRole
				+ ", nameRole=" + nameRole + ", status=" + status + "]";
	}
	
//	public void inputInfo() {
//		ValidationAccount validation = new ValidationAccount();
//		this.userName = validation.inputUserName("hay nhap username tao moi");
//		this.passWork = validation.inputPassWork("hay nhap passwork tao moi");
//		this.name = validation.inputName("hay nhap ho va ten tao moi");
//		this.idRole = validation.inputIdRole("hay nhap id chuc vu tao moi, idrole chi nhan 3 gia tri la admin, giaovien hoac phuhuynh");
//		this.nameRole = validation.inputNameRole("hay nhap ten chuc vu tao moi");
//		this.status = validation.inputStatus("hay nhap trang thai, trang thai chi nhan 2 gia tri la Ban hoac Active");
//	}
	public void inputInfoCreateAcc() {
		FunctionAccount functionAcc = new FunctionAccount();
		ValidationAccount validationAcc = new ValidationAccount();
		Account y = new Account();

		while (true) {
			y.setUserName(validationAcc.inputUserName("hay nhap username tao moi"));
			if (functionAcc.checkUserName(y.getUserName())) {
				break;
			} else {
				System.out.println("username bị trùng");
			}
		}
		y.setPassWork(validationAcc.inputPassWork("hay nhap passwork tao moi"));
		y.setName(validationAcc.inputName("hay nhap ho va ten tao moi"));
		y.setIdRole(validationAcc.inputIdRole(
				"hay nhap id chuc vu tao moi, idrole chi nhan 3 gia tri la admin, giaovien hoac phuhuynh"));
		y.setNameRole(validationAcc.inputNameRole("hay nhap ten chuc vu tao moi"));
		y.setStatus(validationAcc
				.inputStatus("hay nhap trang thai, trang thai chi nhan 2 gia tri la Ban hoac Active"));
	}
}

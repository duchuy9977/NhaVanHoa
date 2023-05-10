package function;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConnectionUtil;
import entities.Account;

public class FunctionAccount {

	public FunctionAccount() {
	}
	//Function check tồn tại của user name khi tạo mới account
		public boolean checkUserName(String username) {
			Connection conn = null;
			PreparedStatement prstmt = null;
			ResultSet rs = null;
			try {
				conn = ConnectionUtil.getConnection();
				String sql = "Select * from Account where Username = ?";
				prstmt = conn.prepareStatement(sql);
				prstmt.setString(1, username);
				rs = prstmt.executeQuery();
				// hàm này chỉ ra con trỏ ở đầu dòng nếu có kết quả trả về, nếu k có kết quả,
				// con trỏ k đc đẩy lên đâu dòng.
				if (!rs.isBeforeFirst()) {
					System.out.println("username có thể sử dụng được!");
					return true;
				}
//				displayResultSet(rs);
				System.out.println("username đã có, hay nhập lại!");
				return false;

			} catch (SQLException i) {
				i.printStackTrace();
				System.out.println("check that bai");
				return false;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("check that bai");
				return false;
			} finally {
				ConnectionUtil.closeConnection(null, prstmt, conn);
			}

		}
		
		//function tạo mới account.
		public void addAccount(Account acc) {
			Connection conn = null;
			PreparedStatement prstmt = null;
			int numberRecords = 0;
			try {

				conn = ConnectionUtil.getConnection();
				String sql = "INSERT INTO ACCOUNT VALUES (?,?,?,?,?,?)";
				prstmt = conn.prepareStatement(sql);
				prstmt.setString(1, acc.getUserName());
				prstmt.setString(2, acc.getPassWork());
				prstmt.setString(3, acc.getName());
				prstmt.setString(4, acc.getIdRole());
				prstmt.setString(5, acc.getNameRole());
				prstmt.setString(6, acc.getStatus());
				numberRecords = prstmt.executeUpdate();
				if (numberRecords == 0) {
					System.out.println("Tao moi Account that bai");
				}

			} catch (SQLException i) {
				i.printStackTrace();
				System.out.println("Tao moi Account that bai");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Tao moi Account that bai");
			} finally {
				ConnectionUtil.closeConnection(null, prstmt, conn);
			}
			System.out.println("Tao moi Account thành công");
		}
}

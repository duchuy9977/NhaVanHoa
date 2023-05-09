package treEm;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.Pattern;

import utils.ConnectionUtil;

public class validation {
	private static Scanner input = new Scanner(System.in);

	public validation() {

	}

	public String inputString(String message) {
		do {
			System.out.println(message);
			String s = input.nextLine();
			if (s.length() >= 1) {
				return s;
			}
			System.out.println("Không được nhập nhỏ hơn 1 kí tự");
		} while (true);
	}

	public String inputGioiTinh(String message) {
		String GioiTinh = null;
		do {
			System.out.println(message);
			GioiTinh = input.nextLine();
			if (GioiTinh.equalsIgnoreCase("Nam")) {
				return "Nam";
			} else if (GioiTinh.equalsIgnoreCase("Nu")) {
				return "Nu";
			} else {
				System.out.println("Vui lòng nhập giới tính là Nam hoặc Nu");
			}
		} while (true);
	}

	public String inputIDTre(String message) {
		do {
			System.out.println(message);
			String IDTre = input.nextLine();
			Pattern x = Pattern.compile("^TR[0-9]{3}$");
			if (x.matcher(IDTre).find()) {
				return IDTre;
			}
			System.out.println("Bạn đã nhập sai định dạng IDTreEm : TRxxx . Mời nhập lại! ");
		} while (true);

	}

	public String inputIDPhuHuynh(String username) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String idPhuHuynh = null;
		try {
			validation validate = new validation();
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement("SELECT * FROM PHUHUYNH WHERE Username = ? ");
			ps.setString(1, username);
			rs = ps.executeQuery();

			while (rs.next()) {
				idPhuHuynh = rs.getString("IDPhuHuynh");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeConnection(rs, ps, con);
		}
		return idPhuHuynh;

	}

	public static Date inputNgaySinh(String message) {
		do {
			try {
				System.out.println(message);
				String NgaySinh = input.nextLine();
				Date NgaySinhsql = Date.valueOf(NgaySinh);
				return NgaySinhsql;
			} catch (Exception e) {
				System.out.println("Ngày sinh không hợp lệ. Mời nhập lại theo định dạng YYYY-MM-DD");
			}
		} while (true);
	}

	public String inputStatus(String message) {
		do {
			System.out.println(message);
			String Status = input.nextLine();
			if (Status.equalsIgnoreCase("Active")) {
				return "Active";
			} else if (Status.equalsIgnoreCase("Ban")) {
				return "Ban";
			} else {
				System.out.println("Vui lòng nhập Status là Active hoặc Ban ");
			}
		} while (true);
	}

	public int inputSoThang(String message) {
		try {
			System.out.println(message);
			int x = input.nextInt();
			if (x >= 1) {
				return x;
			} else if (x >= 12) {
				return x;
			} else {
				System.out.println("Tháng không tồn tại. Vui lòng nhập lại");
			}
		} catch (Exception e) {
			System.out.println("Định dạng không đúng. Vui lòng kiểm tra lại");
			e.printStackTrace();
		}
		while (true)
			;
	}
}

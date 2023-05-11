package function;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.ConnectionUtil;
import entities.MonHoc;

public class MonHocDao {
	public static ArrayList<MonHoc> getMonHoc() {
		ArrayList<MonHoc> list = new ArrayList<MonHoc>();
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "select IDMonHoc, TenMon from MONHOC";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			if(!rs.isBeforeFirst()) {
				System.out.println("   Hiện Chưa có môn học nào");
				return null;
			}
			while (rs.next()) {
				MonHoc monHoc = new MonHoc();
				monHoc.setTenMon(rs.getString("TenMon"));
				monHoc.setIdMonHoc(rs.getString("IDMonHoc"));
				list.add(monHoc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

}

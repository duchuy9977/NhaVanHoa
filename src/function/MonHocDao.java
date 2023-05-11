package function;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.ConnectionUtil;
import entities.MonHoc;

public class MonHocDao {
	public ArrayList<MonHoc> monhoct1003() {
		ArrayList<MonHoc> list = new ArrayList<MonHoc>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String lop1 = null;
		MonHoc monHoc = new MonHoc();
		try {
			con = ConnectionUtil.getConnection();
			String sql = "select IDMonHoc, TenMon from MONHOC";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
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

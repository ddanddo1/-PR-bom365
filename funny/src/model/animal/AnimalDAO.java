package model.animal;

import model.common.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AnimalDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql_insertAll = "insert into animal values((select nvl(max(animal_number),0)+1 from animal),null,?,?,?,?,?,?,?,'false')";

	public void insertAll(ArrayList<AnimalVO> datas) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(sql_insertAll);
			for (AnimalVO data : datas) {
				pstmt.setInt(1, data.getAnimal_number());
				pstmt.setString(2, data.getAnimal_name());
				pstmt.setString(3, data.getAnimal_species());
				pstmt.setString(4, data.getAnimal_type());
				pstmt.setString(5, data.getAnimal_gender());
				pstmt.setString(6, data.getAnimal_age());
				pstmt.setInt(7, data.getAnimal_weight());
				pstmt.setString(8, data.getAnimal_image());
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("init 데이터 insert 중 문제발생");
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
	}

}

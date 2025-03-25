package com.rays.pro4.Model;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import com.rays.pro4.Bean.CustomerBean;
import com.rays.pro4.Bean.UserBean;
import com.rays.pro4.Exception.ApplicationException;
import com.rays.pro4.Exception.DatabaseException;
import com.rays.pro4.Exception.DuplicateRecordException;
import com.rays.pro4.Util.JDBCDataSource;

/**
 * JDBC Implementation of CustomerModel.
 * 
 * @author Nandani Barfa
 *
 */


public class CustomerModel {
	
private static Logger log =Logger.getLogger(CustomerModel.class);
	
public int nextPK() throws DatabaseException {
	
	log.debug("Model nextPK Started");
	String sql = "SELECT MAX(ID) FROM st_customer";

	Connection conn = null;
	int pk = 0;
	
	try {
		conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			pk = rs.getInt(1);
		}
		rs.close();
	} catch (Exception e) {

		throw new DatabaseException("Exception : Exception in getting PK");
	} finally {
		JDBCDataSource.closeConnection(conn);
	}
	log.debug("Model nextPK Started");
	return pk + 1;
	
}
public long add(CustomerBean bean) throws ApplicationException, DuplicateRecordException {
	log.debug("Model add Started");

	String sql = "INSERT INTO st_customer VALUES(?,?,?,?,?)";

	Connection conn = null;
	int pk = 0;

// UserBean existbean = findByLogin(bean.getLogin());
//	if (existbean != null) {
//		throw new DuplicateRecordException("login Id already exists");
//}

	try {
		conn = JDBCDataSource.getConnection();
		pk = nextPK();

		conn.setAutoCommit(false);
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, pk);
		pstmt.setString(2, bean.getClientName());
		pstmt.setString(3, bean.getLocation());
		pstmt.setString(4, bean.getContactNo());
		pstmt.setInt(5, bean.getImportance());

		// date of birth caste by sql date

//	pstmt.setDate(6, new Date(bean.getDob().getTime()));
//
//		pstmt.setString(7, bean.getMobileNo());
//		pstmt.setLong(8, bean.getRoleId());
//		pstmt.setInt(9, bean.getUnSuccessfulLogin());
//		pstmt.setString(10, bean.getGender());
//		pstmt.setTimestamp(11, bean.getLastLogin());
//		pstmt.setString(12, bean.getLock());
//		pstmt.setString(13, bean.getRegisterdIP());
//		pstmt.setString(14, bean.getLastLoginIP());
//		pstmt.setString(15, bean.getCreatedBy());
//		pstmt.setString(16, bean.getModifiedBy());
//		pstmt.setTimestamp(17, bean.getCreatedDatetime());
//		pstmt.setTimestamp(18, bean.getModifiedDatetime());

		int a = pstmt.executeUpdate();
		System.out.println(a);
		conn.commit();
		pstmt.close();

	} catch (Exception e) {
		log.error("Database Exception ...", e);
		try {
			e.printStackTrace();
			conn.rollback();

		} catch (Exception e2) {
			e2.printStackTrace();
			// application exception
			throw new ApplicationException("Exception : add rollback exceptionn" + e2.getMessage());
		}
	}

	finally {
		JDBCDataSource.closeConnection(conn);
	}
	log.debug("Model Add End");
	return pk;

}

}

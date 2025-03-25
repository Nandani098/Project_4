package com.rays.proj4.Test;

import java.text.SimpleDateFormat;

import com.rays.pro4.Bean.CustomerBean;
import com.rays.pro4.Bean.UserBean;
import com.rays.pro4.Exception.ApplicationException;
import com.rays.pro4.Exception.DuplicateRecordException;
import com.rays.pro4.Model.CustomerModel;
import com.rays.pro4.Model.UserModel;

public class CustomerTest {
	public static void main(String[] args) throws ApplicationException,DuplicateRecordException {
		testAdd();
	}
	
	public static void testAdd() {
		try {
			CustomerBean bean =new CustomerBean();
			//SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
			bean.setId(1);
			bean.setClientName("Priya");
			bean.setLocation("Indore");
			bean.setContactNo("9992345691");
			bean.setImportance(2);
			
//			bean.setLogin("kmalviya30@gmail.com");
//			bean.setPassword("kapil@123");
//			bean.setDob(sdf.parse("05-07-1997"));
//			bean.setRoleId(1L);
//			bean.setUnSuccessfulLogin(5);
//			bean.setGender("Male");
//			bean.setLock("yes");
//			bean.setConfirmPassword("kapil@123");

			CustomerModel model= new CustomerModel();

			long pk = model.add(bean);
//			CustomerBean addedbean = model.findByPK(pk);
//			System.out.println("Test add succ");
//
//			if (addedbean == null) {
//				System.out.println("Test add fail");
//			}

			System.out.println("added succesfully");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}

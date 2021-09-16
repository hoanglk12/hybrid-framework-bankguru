package com.bankguru.data;

import utilities.DataHelper;

public class Customer {
	private DataHelper dataHelper;
	public static Customer getCustomer() {
		return new Customer();
	}
	public Customer() {
		dataHelper = DataHelper.getDataHelper();
	}
	public static class New_Customer_01{
		Customer customer = Customer.getCustomer();
		public static New_Customer_01 getNewCustomer01() {
			return new New_Customer_01();
		}
		public String NAME_BLANK = "";
		public String NAME_NUMERIC = customer.dataHelper.getFirstName() + String.valueOf(customer.dataHelper.getRandomNumber());
		public String NAME_SPECIAL_CHAR = customer.dataHelper.getFirstName() + "%$#";
		public String NAME_FIRST_CHAR_BLANK = " " + customer.dataHelper.getFirstName();
		public String ERROR_MSG_NAME_BLANK = "Customer name must not be blank";
		public String ERROR_MSG_NAME_NOT_NUMERIC = "Numbers are not allowed";
		public String ERROR_MSG_NOT_SPECIAL_CHAR = "Special characters are not allowed";
		public String ERROR_MSG_FIRST_CHAR_BLANK = "First character can not have space";
	}
	public static class New_Customer_02{
		Customer customer = Customer.getCustomer();
		public static New_Customer_02 getNewCustomer02() {
			return new New_Customer_02();
		}
		public String ADDRESS_BLANK = "";
		public String ADDRESS_FIRST_CHAR_BLANK = " " + customer.dataHelper.getFullAddress();
		public String ERROR_MSG_ADDRESS_BLANK = "Address Field must not be blank";
		public String ERROR_MSG_ADDRESS_FIRST_CHAR_BLANK = "First character can not have space";
		
	}
	public static class New_Customer_03{
		Customer customer = Customer.getCustomer();
		public static New_Customer_03 getNewCustomer03() {
			return new New_Customer_03();
		}
		public String CITY_BLANK = "";
		public String CITY_NUMERIC = customer.dataHelper.getCity() + String.valueOf(customer.dataHelper.getRandomNumber());
		public String CITY_SPECIAL_CHAR = customer.dataHelper.getCity() + "%$#";
		public String CITY_FIRST_CHAR_BLANK = " " + customer.dataHelper.getCity();
		public String ERROR_MSG_CITY_BLANK = "City Field must not be blank";
		public String ERROR_MSG_CITY_NOT_NUMERIC = "Numbers are not allowed";
		public String ERROR_MSG_NOT_SPECIAL_CHAR = "Special characters are not allowed";
		public String ERROR_MSG_FIRST_CHAR_BLANK = "First character can not have space";
	}
	

}

package utilities;

import com.github.javafaker.Faker;

public class DataHelper {
	private Faker faker;
	public static DataHelper getDataHelper() {
		return new DataHelper();
	}
	public DataHelper() {
		faker = new Faker();
	}
	public String getEmailAddress() {
		return faker.internet().emailAddress();
	}
	public String getFirstName() {
		return faker.name().firstName();
	}
	public String getLastName() {
		return faker.name().lastName();
	}
	public String getFullName() {
		return getFirstName() + " " + getLastName();
	}
	public String getPassword() {
		return faker.internet().password();
	}
	public int getRandomNumber() {
		return faker.number().numberBetween(1,999);
	}
	public String getFullAddress() {
		return faker.address().fullAddress();
	}
	public String getCity() {
		return faker.address().cityName();
	}
}

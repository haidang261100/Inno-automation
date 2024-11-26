package helpers;


import com.github.javafaker.Faker;

import java.util.Date;
import java.util.Random;

public class DataFake {
    Faker faker = new Faker();
    Random rand = new Random();
    public String name = faker.name().fullName();

    public String address = faker.address().fullAddress();
    public String companyName = faker.company().name();
    public String email = faker.internet().emailAddress();


    // phone format for VN
    String number = "3" + new Random().nextInt(9) + new Random().nextInt(10000000);
    public String phoneNumber = "0" + number;

    // Current day

    Date currentDate = new Date();
    public String current = currentDate.toString();

   public  int age = faker.number().numberBetween(18, 100);



}

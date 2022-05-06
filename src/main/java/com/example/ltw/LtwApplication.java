package com.example.ltw;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LtwApplication {

    public static void main(String[] args) {

//		Calendar c = Calendar.getInstance();
//		c.set(2020, 8, 2, 0, 0, 0);
//		c.set(Calendar.MILLISECOND, 0);
//		Calendar c2 = Calendar.getInstance();
//		c2.set(2020, 8, 23, 23, 59, 59);
//		c2.set(Calendar.MILLISECOND, 0);
//		Date startDate = (Date) c.getTime();
//		Date endDate = (Date) c2.getTime();
//		System.out.print(startDate.getTime()+"  "+endDate.getTime());
        SpringApplication.run(LtwApplication.class, args);
    }

}

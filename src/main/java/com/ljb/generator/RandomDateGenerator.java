package com.ljb.generator;

import com.github.javafaker.DateAndTime;
import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class RandomDateGenerator extends AbstractDataGenerator{

    private Locale locale = Locale.CHINA;

    private DateTypeEnum dateTypeEnum;

    private DateFormatEnum dateFormatEnum;

    private String fromDateStr;

    private String toDateStr;


    public RandomDateGenerator(DateTypeEnum dateTypeEnum,
                               DateFormatEnum dateFormatEnum, String fromDateStr,
                               String toDateStr) {
        this.dateTypeEnum = dateTypeEnum;
        this.dateFormatEnum = dateFormatEnum;
        this.fromDateStr = fromDateStr;
        this.toDateStr = toDateStr;
    }

    public RandomDateGenerator(DateTypeEnum dateTypeEnum,
                               DateFormatEnum dateFormatEnum) {
        this.dateTypeEnum = dateTypeEnum;
        this.dateFormatEnum = dateFormatEnum;
    }

    @Override
    public List<String> generate(Integer size) {
        String dateFormat;
        switch (dateFormatEnum) {
            case DATE_TIME:
                dateFormat = "yyyy-MM-dd HH:mm:ss";
                break;
            case DATE:
                dateFormat = "yyyy-MM-dd";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + dateFormatEnum);
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Faker faker = new Faker(this.locale);
        DateAndTime dateAndTime = faker.date();
        List<String> resultList = new ArrayList<>();
        switch (dateTypeEnum) {
            case BIRTHDAY:
                for(int i = 1;i<=size;i++){
                    Date birthdayDate = Date.from(dateAndTime.birthday().toInstant());
                    String formattedDate = sdf.format(birthdayDate);
                    resultList.add(formattedDate);
                }

                break;
            case BETWEEN_RANGES:
                for(int i = 1;i<=size;i++){
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
                    Date fromDate = Date.from(LocalDateTime.parse(fromDateStr,formatter).atZone(ZoneId.systemDefault()).toInstant());
                    Date toDate = Date.from(LocalDateTime.parse(toDateStr,formatter).atZone(ZoneId.systemDefault()).toInstant());

                    Date betweenDate = dateAndTime.between(fromDate, toDate);
                    String formattedDate = sdf.format(betweenDate);
                    resultList.add(formattedDate);
                }
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + dateTypeEnum);
        }

        return resultList;
    }

    @Override
    public List<String> generate() {
        return generate(this.resultSize);
    }


    public enum DateTypeEnum{
        BIRTHDAY,
        BETWEEN_RANGES,
    }


    public enum DateFormatEnum{
        DATE_TIME,
        DATE
    }
}

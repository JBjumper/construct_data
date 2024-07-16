package com.ljb.generator;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PersonNameGenerator extends AbstractDataGenerator{

    private Locale locale = Locale.CHINA;

    public PersonNameGenerator(Locale locale) {
        this.locale = locale;
    }

    public PersonNameGenerator() {
    }

    @Override
    public List<String> generate(Integer size) {
        Faker faker = new Faker(this.locale);
        List<String> names = new ArrayList<>();
        for(int i=1;i<=size;i++){
            String name = faker.name().name();
            //System.out.println(name);
            names.add(name);
        }
        return names;
    }

    @Override
    public List<String> generate() {
        return generate(this.resultSize);
    }


    public static void main(String[] args) {
        PersonNameGenerator personNameGenerator = new PersonNameGenerator(Locale.CHINA);
        personNameGenerator.generate(10);
    }
}

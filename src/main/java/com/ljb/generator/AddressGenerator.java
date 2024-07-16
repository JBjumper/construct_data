package com.ljb.generator;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AddressGenerator extends AbstractDataGenerator{

    public AddressGenerator(Locale locale) {
        this.locale = locale;
    }

    public AddressGenerator() {
    }

    @Override
    public List<String> generate(Integer size) {
        Faker faker = new Faker(this.locale);
        List<String> addresses = new ArrayList<>();
        for(int i=1;i<=size;i++){
            Address address = faker.address();
            String fullAddressName = address.state()+address.cityName()
                    +address.streetName()+address.streetAddress();
            //System.out.println(fullAddressName);
            addresses.add(fullAddressName);
        }
        return addresses;
    }

    @Override
    public List<String> generate() {
        return generate(this.resultSize);
    }

    public static void main(String[] args) {
        AddressGenerator addressGenerator = new AddressGenerator(Locale.CHINA);
        addressGenerator.generate(10);
    }
}

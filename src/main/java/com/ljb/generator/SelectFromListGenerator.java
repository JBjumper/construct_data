package com.ljb.generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SelectFromListGenerator extends AbstractSelectGenerator {

    public SelectFromListGenerator(String ... elements) {
        this.selects.addAll(Arrays.asList(elements));
    }

    @Override
    public List<String> generate(Integer numbers) {
        List<String> resultList = new ArrayList<>();
        for(int i = 1;i<= numbers;i++){
            int randomIndex = ThreadLocalRandom.current().nextInt(this.selects.size());
            String randomElement = this.selects.get(randomIndex);
            resultList.add(randomElement);
        }

        return resultList;
    }

    @Override
    public List<String> generate() {
        return generate(this.resultSize);
    }


    public static void main(String[] args) {
        SelectFromListGenerator generator = new SelectFromListGenerator("新农合", "现金");
        for (int i =0;i<20;i++){
            System.out.println(generator.generate());
        }
    }
}

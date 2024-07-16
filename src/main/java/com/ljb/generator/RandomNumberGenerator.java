package com.ljb.generator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomNumberGenerator extends AbstractDataGenerator {

    private Long min;

    private Long max;

    private RandomNumberTypeEnum randomNumberTypeEnum;

    private Integer floatLength = 2;

    public RandomNumberGenerator(Long min, Long max, RandomNumberTypeEnum randomNumberTypeEnum) {
        this.min = min;
        this.max = max;
        this.randomNumberTypeEnum = randomNumberTypeEnum;
    }

    public RandomNumberGenerator(Long min, Long max, RandomNumberTypeEnum randomNumberTypeEnum, Integer floatLength) {
        this.min = min;
        this.max = max;
        this.randomNumberTypeEnum = randomNumberTypeEnum;
        this.floatLength = floatLength;
    }

    @Override
    public List<String> generate(Integer size) {
        List<String> resultList = new ArrayList<>();
        switch (randomNumberTypeEnum) {
            case LONG:
                for (int i = 1; i <= size; i++) {
                    long randomLong = ThreadLocalRandom.current().nextLong(this.min, this.max + 1); // 上限不包含，需要+1
                    resultList.add(String.valueOf(randomLong));
                }
                break;
            case FLOAT:
                for (int i = 1; i <= size; i++) {
                    double randomFloat = ThreadLocalRandom.current().nextDouble(this.min, this.max);
                    BigDecimal bd = new BigDecimal(Double.toString(randomFloat));
                    bd = bd.setScale(this.floatLength, RoundingMode.HALF_UP);
                    double roundedValue = bd.doubleValue();
                    resultList.add(String.valueOf(roundedValue));
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + randomNumberTypeEnum);
        }
        return resultList;
    }

    @Override
    public List<String> generate() {
        return generate(resultSize);
    }


    public enum RandomNumberTypeEnum {
        LONG,
        FLOAT
    }


    public static void main(String[] args) {
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator(20L, 100L, RandomNumberTypeEnum.FLOAT);
        List<String> generate = randomNumberGenerator.generate();
        System.out.println(generate);
    }
}

package com.ljb.generator;

import java.util.*;

public class RandomStringGenerator extends AbstractDataGenerator {

    private Locale locale = Locale.CHINA;

    private ContentTypeEnum contentTypeEnum;

    private String prefix = "";

    private String suffix = "";

    //生成的字符串总长度
    private Integer length;

    public RandomStringGenerator(ContentTypeEnum contentTypeEnum, String prefix, String suffix, Integer length) {
        this.contentTypeEnum = contentTypeEnum;
        this.prefix = prefix;
        this.suffix = suffix;
        this.length = length;
    }

    public RandomStringGenerator(ContentTypeEnum contentTypeEnum, String prefix, Integer length) {
        this.contentTypeEnum = contentTypeEnum;
        this.prefix = prefix;
        this.length = length;
    }

    public RandomStringGenerator(ContentTypeEnum contentTypeEnum, Integer length, String suffix) {
        this.contentTypeEnum = contentTypeEnum;
        this.suffix = suffix;
        this.length = length;
    }

    public RandomStringGenerator(ContentTypeEnum contentTypeEnum, Integer length) {
        this.contentTypeEnum = contentTypeEnum;
        this.length = length;
    }

    @Override
    public List<String> generate(Integer size) {
        Integer realLength = this.length - this.prefix.length() - this.suffix.length();
        List<String> resultList = new ArrayList<>();
        switch (contentTypeEnum) {
            case LETTER_NUMBER:
                for(int i = 1;i<=size;i++){
                    resultList.add(this.prefix+getRandomLetterOrDigit(realLength)+this.suffix);
                }
                break;
            case LETTER:
                for(int i = 1;i<=size;i++) {
                    resultList.add(this.prefix + getRandomLetter(realLength) + this.suffix);
                }
                break;
            case NUMBER:
                for(int i = 1;i<=size;i++) {
                    resultList.add(this.prefix + getRandomDigit(realLength) + this.suffix);
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + contentTypeEnum);
        }
        return resultList;
    }

    @Override
    public List<String> generate() {
        return generate(this.resultSize);
    }

    private String getRandomLetterOrDigit(Integer length) {

        StringBuilder stringBuilder = new StringBuilder();
        for(int j=1;j<=length;j++){
            Random random = new Random();
            int i = random.nextInt(2);
            String s = i == 0 ? getRandomLetter(1) : getRandomDigit(1);
            stringBuilder.append(s.toUpperCase());
        }
        return stringBuilder.toString();
    }

    private String getRandomLetter(Integer length) {
        Random random = new Random();
        // ASCII码：'a' 是 97, 'z' 是 122, 'A' 是 65, 'Z' 是 90
        char randomChar;

        // 50% 的概率是小写字母，50% 的概率是大写字母
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 1;i<=length;i++){
            if (random.nextBoolean()) {
                randomChar = (char) (random.nextInt(26) + 'a');
            } else {
                randomChar = (char) (random.nextInt(26) + 'A');
            }
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }

    private String getRandomDigit(Integer length) {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 1;i<=length;i++){
            int randomDigit = random.nextInt(10); // 生成 0-9 的随机整数
            stringBuilder.append(randomDigit);
        }
        return stringBuilder.toString();
    }


    public enum ContentTypeEnum {
        LETTER_NUMBER,
        LETTER,
        NUMBER
    }


    public static void main(String[] args) {
        RandomStringGenerator randomStringGenerator = new RandomStringGenerator(ContentTypeEnum.LETTER_NUMBER, "LAC", "BB", 20);
        List<String> generate = randomStringGenerator.generate(20);
        System.out.println(generate);
    }
}

package com.ljb.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConstantGenerator extends AbstractDataGenerator{
    private String constantValue;

    public ConstantGenerator(String constantValue) {
        this.constantValue = constantValue;
    }

    @Override
    public List<String> generate(Integer size) {
        List<String> resultList = new ArrayList<>(Collections.nCopies(size, ""));
        Collections.fill(resultList,this.constantValue);
        return resultList;
    }

    @Override
    public List<String> generate() {
        return generate(this.resultSize);
    }
}

package com.ljb.constructor;

import com.ljb.formatter.IDataFormatter;
import com.ljb.generator.IDataGenerator;

import java.util.ArrayList;
import java.util.List;

public class DataConstructor implements IDataConstructor{

    private Integer resultSize;


    private final List<List<String>> generateResults = new ArrayList<>();


    public  DataConstructor setResultSize(Integer resultSize) {
        this.resultSize = resultSize;
        return this;
    }

    @Override
    public IDataConstructor build(IDataGenerator dataGenerator) {
        this.generateResults.add(dataGenerator.generate(this.resultSize));
        return this;
    }

    @Override
    public List<String> make(IDataFormatter dataFormatter) {
        return dataFormatter.format(this.generateResults,this.resultSize);
    }

    public List<List<String>> getGenerateResults() {
        return generateResults;
    }
}

package com.ljb.formatter;

import java.util.Collections;
import java.util.List;

public class CSVFormatter implements IDataFormatter {
    private String delimiter;


    public CSVFormatter(String delimiter) {
        this.delimiter = delimiter;
    }


    @Override
    public List<String> format(List<List<String>> generateResults,Integer resultSize) {
        return Collections.emptyList();
    }
}

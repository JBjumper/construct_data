package com.ljb.generator;

import java.util.Locale;

public abstract class AbstractDataGenerator implements IDataGenerator{

    public Locale locale = Locale.CHINA;
    public Integer resultSize = 100;

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void setResultSize(Integer resultSize) {
        this.resultSize = resultSize;
    }
}

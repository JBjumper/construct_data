package com.ljb.constructor;

import com.ljb.formatter.IDataFormatter;
import com.ljb.generator.IDataGenerator;

import java.util.List;

public interface IDataConstructor {
    IDataConstructor build(IDataGenerator dataGenerators);

    List<String> make(IDataFormatter dataFormatter);
}

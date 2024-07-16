package com.ljb.generator;

import java.util.List;

public interface IDataGenerator {

    List<String> generate(Integer size);

    List<String> generate();
}

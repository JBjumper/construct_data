package com.ljb.generator;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSelectGenerator extends AbstractDataGenerator{

    final List<String> selects = new ArrayList<>();

    Boolean selectedOnce = false;
}

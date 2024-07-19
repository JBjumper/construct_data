package com.ljb.generator;

import java.util.*;

/**
 * 根据映射key列表，返回映射value列表作为生成的值
 */
public class SelectFromMapGenerator extends AbstractSelectGenerator {

    private final Map<String,String> mapping;

    private final List<String> keyList;

    public SelectFromMapGenerator(Map<String, String> mapping, List<String> keyList) {
        this.mapping = mapping;
        this.keyList = keyList;
    }

    @Override
    public List<String> generate(Integer size) {
        List<String> result = new ArrayList<>();
        for(String key : keyList){
            if(result.size() == size){
                break;
            }
            result.add(mapping.get(key));

        }
        return result;
    }

    @Override
    public List<String> generate() {

        return generate(this.resultSize);
    }
}

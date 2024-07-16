package com.ljb.formatter;

import java.util.*;

public class SQLFormatter implements IDataFormatter {
    private final String tableName;
    private final LinkedHashMap<String, String> columnInfoMap;

    public SQLFormatter(String tableName, LinkedHashMap<String, String> columnInfoMap) {
        this.tableName = tableName;
        this.columnInfoMap = columnInfoMap;
    }


    @Override
    public List<String> format(List<List<String>> generateResults, Integer resultSize) {
        StringBuilder sqlPrefix = constructSQLPrefix();
        checkGenerateResultSize(generateResults, resultSize);
        return constructSQLValue(generateResults,sqlPrefix,resultSize);
    }


    public StringBuilder constructSQLPrefix() {
        StringBuilder sb = new StringBuilder("INSERT INTO ");
        sb.append(this.tableName).append(" (");
        int i = 1;
        for (Map.Entry<String, String> columnInfo : columnInfoMap.entrySet()) {
            String columnName = columnInfo.getKey().trim();
            sb.append(columnName);
            if(i != columnInfoMap.size()){
                sb.append(",");
            }
            i ++;
        }
        sb.append(") VALUES (");
        return sb;
    }

    public List<String> constructSQLValue(List<List<String>> generateResults, StringBuilder paramSqlPrefix, Integer resultSize) {
        List<String> sqlResults = new ArrayList<>();
        List<String> columnTypeList = new ArrayList<>();
        for (Map.Entry<String, String> columnInfo : columnInfoMap.entrySet()) {
            columnTypeList.add(columnInfo.getValue());
        }
        for (int j = 0; j < resultSize; j++) {
            StringBuilder sqlPrefix = new StringBuilder(paramSqlPrefix);
            StringBuilder insertValueSqlSegment = new StringBuilder();
            for (int i = 1; i <= generateResults.size(); i++) {
                String realResultValue;
                List<String> resultSet = generateResults.get(i - 1);
                String resultValue = resultSet.get(j);
                String columnType = columnTypeList.get(i - 1);
                if (columnType.contains("VARCHAR") || columnType.contains("varchar")
                        || columnType.contains("timestamp") || columnType.contains("TIMESTAMP")) {
                    realResultValue = "'" + resultValue + "'";
                } else {
                    realResultValue = resultValue;
                }
                insertValueSqlSegment.append(realResultValue);
                if(i != generateResults.size()){
                    insertValueSqlSegment.append(",");
                }else{
                    insertValueSqlSegment.append(");");
                }
            }
            sqlPrefix.append(insertValueSqlSegment);
            sqlResults.add(sqlPrefix.toString());
        }

        return sqlResults;
    }

    public void checkGenerateResultSize(List<List<String>> generateResults, Integer resultSize) {
        StringBuilder errorMsg = new StringBuilder();
        String errorMsgTemplate = "第[%d]列生成的数据量为[%d],与设置值[%d]不一致;";
        for (int i = 1; i <= generateResults.size(); i++) {
            List<String> result = generateResults.get(i - 1);
            if (resultSize != result.size()) {
                errorMsg.append(String.format(errorMsgTemplate, i, result.size(), resultSize));
            }
        }
        String error = errorMsg.toString();
        if (errorMsg.length() != 0) {
            throw new RuntimeException(error);
        }
    }


}

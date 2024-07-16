package com.ljb.implemet;

import com.ljb.constructor.DataConstructor;
import com.ljb.formatter.SQLFormatter;
import com.ljb.generator.*;

import java.util.LinkedHashMap;
import java.util.List;

import static com.ljb.generator.RandomStringGenerator.ContentTypeEnum.NUMBER;
import static com.ljb.generator.RandomDateGenerator.DateTypeEnum.*;
import static com.ljb.generator.RandomDateGenerator.DateFormatEnum.*;

public class OdsHisGzServerFinIprInMainInfo {
    //ZY010000147116,ZY010000147103


    public static void main(String[] args) {
        LinkedHashMap<String, String> columnInfos = new LinkedHashMap<>();
        columnInfos.put("INPATIENT_NO","varchar(42)");
        columnInfos.put("PATIENT_NO","varchar(30)");
        columnInfos.put("CARD_NO","varchar(30)");
        columnInfos.put("NAME","varchar(150)");
        columnInfos.put("SEX_CODE","varchar(3)");
        columnInfos.put("BIRTHDAY","timestamp");
        columnInfos.put("HOME","varchar(210)");
        columnInfos.put("IN_DATE","timestamp");
        columnInfos.put("DEPT_CODE","varchar(12)");
//        columnInfos.put("DEPT_NAME","varchar(150)");
        columnInfos.put("PACT_CODE","varchar(30)");
        columnInfos.put("PACT_NAME","varchar(150)");
        columnInfos.put("BED_NO","varchar(30)");
        columnInfos.put("IN_SOURCE","varchar(3)");
        columnInfos.put("IN_TIMES","int");
        columnInfos.put("BALANCE_COST","float");
        columnInfos.put("BALANCE_PREPAY","float");
        columnInfos.put("BALANCE_DATE","timestamp");
        columnInfos.put("OUT_DATE","timestamp");
        columnInfos.put("EMPL_CODE","varchar(18)");
        columnInfos.put("TEND","varchar(90)");
        columnInfos.put("OPER_CODE","varchar(18)");
        columnInfos.put("OPER_DATE","timestamp");
        SQLFormatter sqlFormatter = new SQLFormatter("public.ODS_HIS_GZSERVER_FIN_IPR_INMAININFO", columnInfos);

        DataConstructor dataConstructor = new DataConstructor();
        List<String> resultSqls = dataConstructor.setResultSize(20)
                .build(new RandomStringGenerator(NUMBER,"ZY010000",14)) //INPATIENT_NO
                .build(new RandomStringGenerator(NUMBER,"0000",10)) //PATIENT_NO
                .build(new RandomStringGenerator(NUMBER,"0000",10)) //CARD_NO
                .build(new PersonNameGenerator())//NAME
                .build(new SelectFromListGenerator("F","M"))//SEX_CODE
                .build(new RandomDateGenerator(BIRTHDAY, DATE_TIME))//BIRTHDAY
                .build(new AddressGenerator())//HOME
                .build(new RandomDateGenerator(BETWEEN_RANGES,DATE_TIME,"2024-05-20 00:00:00",
                        "2024-05-30 00:00:00"))//IN_DATE
                .build(new SelectFromListGenerator("119001","1119001","104005","1104005","104006","1104006"))//DEPT_CODE
                .build(new SelectFromListGenerator("11","1"))//PACT_CODE
                .build(new SelectFromListGenerator("新农合","现金"))//PACT_NAME
                .build(new RandomStringGenerator(NUMBER,"0",6))//BED_NO
                .build(new ConstantGenerator("1"))//IN_SOURCE
                .build(new ConstantGenerator("1"))//IN_TIMES
                .build(new RandomNumberGenerator(1000L,10000L,RandomNumberGenerator.RandomNumberTypeEnum.FLOAT,2))//BALANCE_COST
                .build(new RandomNumberGenerator(1000L,10000L,RandomNumberGenerator.RandomNumberTypeEnum.LONG))//BALANCE_PREPAY
                .build(new RandomDateGenerator(BETWEEN_RANGES,DATE_TIME,"2024-06-01 00:00:00", "2024-06-10 00:00:00"))
                .build(new RandomDateGenerator(BETWEEN_RANGES,DATE_TIME,"2024-06-01 00:00:00", "2024-06-10 00:00:00"))
                .build(new RandomStringGenerator(NUMBER,"00",6))
                .build(new ConstantGenerator("二级护理"))
                .build(new RandomStringGenerator(NUMBER,"00",6))
                .build(new RandomDateGenerator(BETWEEN_RANGES,DATE_TIME,"2024-06-01 00:00:00", "2024-06-30 00:00:00"))
                .make(sqlFormatter);


        for(String resultSql : resultSqls){
            System.out.println(resultSql);
        }
    }
}

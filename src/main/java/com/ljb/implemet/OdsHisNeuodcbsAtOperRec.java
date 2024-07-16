package com.ljb.implemet;

import com.ljb.constructor.DataConstructor;
import com.ljb.formatter.SQLFormatter;
import com.ljb.generator.*;

import java.util.LinkedHashMap;
import java.util.List;

public class OdsHisNeuodcbsAtOperRec {
    //ID 0003E91B1CF62EAFE0636902A8C00DD9,0003E91A950A2EAFE0636902A8C00DD9
    public static void main(String[] args) {
        LinkedHashMap<String, String> columnInfos = new LinkedHashMap<>();
        columnInfos.put("ID","varchar(120)");
        columnInfos.put("HOSP_CODE","varchar(120)");
        columnInfos.put("BRANCH_CODE","varchar(120)");
        columnInfos.put("OPER_APPLY_ID","varchar(120)");
        columnInfos.put("BMI_INDEX","float");
        columnInfos.put("SURFACE_AREA","float");
        columnInfos.put("BLOOD_TYPE_NAME","varchar(600)");
        columnInfos.put("BLOOD_RH_TYPE_NAME","varchar(600)");
        columnInfos.put("HEART_RATE","varchar(600)");
        columnInfos.put("BLOOD_OXYGEN_SATURATION","varchar(600)");
        columnInfos.put("OPER_LEVEL_CODE","varchar(120)");
        columnInfos.put("OPER_LEVEL_NAME","varchar(600)");
        columnInfos.put("ASA_LEVEL_CODE","varchar(120)");
        columnInfos.put("IS_ASA_EMERGENCY","int");
        columnInfos.put("REQUIRE_ANES_DOCTOR","int");
        columnInfos.put("DIRECTION_CODE","varchar(120)");
        columnInfos.put("VALID","int");
        columnInfos.put("CREATOR","varchar(120)");
        columnInfos.put("CREATE_TIME","timestamp");
        columnInfos.put("UPDATER","varchar(120)");
        columnInfos.put("UPDATE_TIME","timestamp");
        columnInfos.put("WEIGHT","varchar(120)");
        columnInfos.put("HEIGHT","varchar(120)");

        SQLFormatter sqlFormatter = new SQLFormatter("public.ODS_HIS_NEUODCBS_AT_OPER_REC", columnInfos);


        String url = "jdbc:vertica://1.181.141.98:5433/H3C_Database";
        String user = "dbadmin";
        String password = "passwd";
        String tableName = "ODS_HIS_GZSERVER_MET_OPS_APPLY";
        String referenceColumnName = "OPERATIONNO";
        String sortColumnName = "OPERATIONNO";
        String tableName2 = "ODS_HIS_GZSERVER_MET_COM_ICDOPERATION";
        String referenceColumnName2 = "ICD_CODE";

        DataConstructor dataConstructor = new DataConstructor();





        List<String> resultSqls = dataConstructor.setResultSize(200)

//        columnInfos.put("ID","varchar(120)");
                .build(new RandomStringGenerator(RandomStringGenerator.ContentTypeEnum.LETTER_NUMBER,"0003E",32))
//        columnInfos.put("HOSP_CODE","varchar(120)");
                .build(new ConstantGenerator("#hospital"))
//        columnInfos.put("BRANCH_CODE","varchar(120)");
                .build(new ConstantGenerator("#branch"))
//        columnInfos.put("OPER_APPLY_ID","varchar(120)");
                .build(new SelectColumnAllFromDatabaseGenerator(url, user, password, tableName,
                        referenceColumnName, sortColumnName))
//        columnInfos.put("BMI_INDEX","float");
                .build(new RandomNumberGenerator(10L,25L, RandomNumberGenerator.RandomNumberTypeEnum.FLOAT,2))
//        columnInfos.put("SURFACE_AREA","float");
                .build(new RandomNumberGenerator(1L,2L, RandomNumberGenerator.RandomNumberTypeEnum.FLOAT,2))
//        columnInfos.put("BLOOD_TYPE_NAME","varchar(600)");
                .build(new SelectFromListGenerator("A型","B型","O型","AB型"))
//        columnInfos.put("BLOOD_RH_TYPE_NAME","varchar(600)");
                .build(new SelectFromListGenerator("阳性","阴性"))
//        columnInfos.put("HEART_RATE","varchar(600)");
                .build(new RandomNumberGenerator(70L,140L, RandomNumberGenerator.RandomNumberTypeEnum.LONG))
//        columnInfos.put("BLOOD_OXYGEN_SATURATION","varchar(600)");
                .build(new ConstantGenerator("100"))
//        columnInfos.put("OPER_LEVEL_CODE","varchar(120)");
                .build(new ConstantGenerator("1"))
//        columnInfos.put("OPER_LEVEL_NAME","varchar(600)");
                .build(new ConstantGenerator("Ⅰ级"))
//        columnInfos.put("ASA_LEVEL_CODE","varchar(120)");
                .build(new RandomNumberGenerator(1L,5L, RandomNumberGenerator.RandomNumberTypeEnum.LONG))
//        columnInfos.put("IS_ASA_EMERGENCY","int");
                .build(new SelectFromListGenerator("0","1"))
//        columnInfos.put("REQUIRE_ANES_DOCTOR","int");
                .build(new SelectFromListGenerator("0","1"))
//        columnInfos.put("DIRECTION_CODE","varchar(120)");
                .build(new SelectFromListGenerator("2","1"))
//        columnInfos.put("VALID","int");
                .build(new ConstantGenerator("1"))
//        columnInfos.put("CREATOR","varchar(120)");
                .build(new RandomStringGenerator(RandomStringGenerator.ContentTypeEnum.LETTER_NUMBER,32))
//        columnInfos.put("CREATE_TIME","timestamp");
                .build(new RandomDateGenerator(RandomDateGenerator.DateTypeEnum.BETWEEN_RANGES,
                        RandomDateGenerator.DateFormatEnum.DATE_TIME,"2024-05-01 00:00:00","2024-07-01 00:00:00"))
//        columnInfos.put("UPDATER","varchar(120)");
                .build(new RandomStringGenerator(RandomStringGenerator.ContentTypeEnum.LETTER_NUMBER,32))
//        columnInfos.put("UPDATE_TIME","timestamp");
                .build(new RandomDateGenerator(RandomDateGenerator.DateTypeEnum.BETWEEN_RANGES,
                        RandomDateGenerator.DateFormatEnum.DATE_TIME,"2024-05-01 00:00:00","2024-07-01 00:00:00"))
//        columnInfos.put("WEIGHT","varchar(120)");
                .build(new RandomNumberGenerator(40L,90L, RandomNumberGenerator.RandomNumberTypeEnum.LONG))
//        columnInfos.put("HEIGHT","varchar(120)");
                .build(new RandomNumberGenerator(140L,190L, RandomNumberGenerator.RandomNumberTypeEnum.LONG))
                .make(sqlFormatter);

        for(String sql:resultSqls){
            System.out.println(sql);
        }
    }
}

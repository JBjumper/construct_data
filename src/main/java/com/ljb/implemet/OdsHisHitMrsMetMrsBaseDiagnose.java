package com.ljb.implemet;

import com.ljb.constructor.DataConstructor;
import com.ljb.formatter.SQLFormatter;
import com.ljb.generator.RandomNumberGenerator;
import com.ljb.generator.SelectColumnAllFromDatabaseGenerator;
import com.ljb.generator.SelectColumnRandomFromDatabaseGenerator;
import com.ljb.generator.SelectFromListGenerator;

import java.util.LinkedHashMap;
import java.util.List;

public class OdsHisHitMrsMetMrsBaseDiagnose {
    // ZY010000725635,ZY010000725681
    public static void main(String[] args) {
        LinkedHashMap<String, String> columnInfos = new LinkedHashMap<>();
        columnInfos.put("INPATIENT_NO","varchar(42) ");
        columnInfos.put("DIAG_KIND","varchar(6)");
        columnInfos.put("SEQUENCE_NO","int ");
        columnInfos.put("DIAG_CODE","varchar(150)");
        columnInfos.put("IN_CONDITION_FLAG","varchar(3)");
        columnInfos.put("OUT_SITUATION","varchar(3)");
        columnInfos.put("SUSPECTED_DIAG","varchar(3)");
        columnInfos.put("OUT_SITUATION_DOC","varchar(3)");

        SQLFormatter sqlFormatter = new SQLFormatter("public.ODS_HIS_HIT_MRS_MET_MRS_BASE_DIAGNOSE", columnInfos);


        String url = "jdbc:vertica://1.181.141.98:5433/H3C_Database";
        String user = "dbadmin";
        String password = "passwd";
        String tableName = "ODS_HIS_GZSERVER_FIN_IPR_INMAININFO";
        String referenceColumnName = "INPATIENT_NO";
        String sortColumnName = "INPATIENT_NO";
        String tableName2 = "ODS_HIS_GZSERVER_MET_COM_ICD10";
        String referenceColumnName2 = "ICD_CODE";

        DataConstructor dataConstructor = new DataConstructor();
        List<String> resultSqls = dataConstructor.setResultSize(200)
                .build(new SelectColumnAllFromDatabaseGenerator(url, user, password, tableName,
                        referenceColumnName, sortColumnName))
                .build(new RandomNumberGenerator(1L,8L, RandomNumberGenerator.RandomNumberTypeEnum.LONG))
                .build(new RandomNumberGenerator(1L,10L, RandomNumberGenerator.RandomNumberTypeEnum.LONG))
                .build(new SelectColumnRandomFromDatabaseGenerator(url, user, password, tableName2,
                        referenceColumnName2))
                .build(new RandomNumberGenerator(1L,4L, RandomNumberGenerator.RandomNumberTypeEnum.LONG) )
                .build(new SelectFromListGenerator("1","2","3","4","9"))
                .build(new SelectFromListGenerator("1","0"))
                .build(new SelectFromListGenerator("1","2","3","4","9"))
                .make(sqlFormatter);

        for(String sql:resultSqls){
            System.out.println(sql);
        }

    }
}

package rick.pro.codegenerator.service;

import com.google.common.base.CaseFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import rick.pro.codegenerator.config.CodeGeneratorProperties;

import javax.annotation.Resource;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * [  ]
 *
 * @author yandanyang
 * @version 1.0
 * @company 1024lab.net
 * @copyright (c) 2018 1024lab.netInc. All rights reserved.
 * @date 2019/5/11 0011 上午 8:33
 * @since JDK1.8
 */
@Component
public class CodeGeneratorComponent {
    @Resource
    private CodeGeneratorProperties  properties;
    private static Map<String, String> dataMap = new HashMap<>(16);
    private static Map<String, String> numberTypeMap = new HashMap<>(16);

    static {
        dataMap();
    }

    public static void dataMap() {
        numberTypeMap.put("int", "Integer");
        numberTypeMap.put("tinyint", "Integer");
        numberTypeMap.put("smallint", "Integer");
        numberTypeMap.put("integer", "Integer");
        numberTypeMap.put("bigint", "Long");
        numberTypeMap.put("float", "Float");
        numberTypeMap.put("double", "Double");
        numberTypeMap.put("decimal", "BigDecimal");

        dataMap.put("char", "String");
        dataMap.put("varchar", "String");
        dataMap.put("tinytext", "String");
        dataMap.put("text", "String");
        dataMap.put("longtext", "String");

        dataMap.put("date", "Date");
        dataMap.put("datetime", "Date");
        dataMap.put("timestamp", "Date");

    }


    public Map<String, String> codeTemplates(String moduleClass, String basePackage, String modulePackage) {
//        System.out.println(this.templatePath);
//        if (this.templatePath != null && !"".equals(this.templatePath)) {
//            if (this.templatePath.startsWith("classpath:" )) {
//                URL resource = CodeGeneratorService.class.getClassLoader().getResource(this.templatePath.replace("classpath:" , "" ));
//                this.templatePath = resource.getPath();
//            }
//        }
//        String modulePath = modulePackage.replaceAll("\\." , "/" );
//        String javaPackagePath = "java/" + modulePath + File.separator;
//        String xmlPackagePath = "mappers/" + modulePath + File.separator;
        String javaPackagePath = "java" + File.separator;
        String xmlPackagePath = "mappers" + File.separator;
        String frontPackagePath = "web" + File.separator;
        Map<String, String> templateMap = new HashMap<>();
        //后端
        templateMap.put(properties.getTemplatePath() + "/java/Controller.java.vm", javaPackagePath + "controller" + File.separator + moduleClass + "Controller.java");
        templateMap.put(properties.getTemplatePath() + "/java/Dao.java.vm", javaPackagePath + "dao" + File.separator + moduleClass + "Dao.java");
        templateMap.put(properties.getTemplatePath() + "/java/Dao.xml.vm", xmlPackagePath + moduleClass + "Mapper.xml");
        templateMap.put(properties.getTemplatePath() + "/java/AddDTO.java.vm", javaPackagePath + "domain" + File.separator + "dto" + File.separator + moduleClass + "AddDTO.java");
        templateMap.put(properties.getTemplatePath() + "/java/UpdateDTO.java.vm", javaPackagePath + "domain" + File.separator + "dto" + File.separator + moduleClass + "UpdateDTO.java");
        templateMap.put(properties.getTemplatePath() + "/java/Entity.java.vm", javaPackagePath + "domain" + File.separator + "entity" + File.separator + moduleClass + "Entity.java");
        templateMap.put(properties.getTemplatePath() + "/java/VO.java.vm", javaPackagePath + "domain" + File.separator + "vo" + File.separator + moduleClass + "VO.java");
        templateMap.put(properties.getTemplatePath() + "/java/ExcelVO.java.vm", javaPackagePath + "domain" + File.separator + "vo" + File.separator + moduleClass + "ExcelVO.java");
        templateMap.put(properties.getTemplatePath() + "/java/QueryDTO.java.vm", javaPackagePath + "domain" + File.separator + "dto" + File.separator + moduleClass + "QueryDTO.java");
        templateMap.put(properties.getTemplatePath() + "/java/Service.java.vm", javaPackagePath + "service" + File.separator + moduleClass + "Service.java");
        templateMap.put(properties.getTemplatePath() + "/java/ServiceImpl.java.vm", javaPackagePath + "service" + File.separator + "impl" + File.separator + moduleClass + "ServiceImpl.java");
        //前端
        String webPackageName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, moduleClass).replaceAll("_", "-");
        templateMap.put(properties.getTemplatePath() + "/web/Api.js.vm", frontPackagePath + "api" + File.separator + webPackageName + ".js");
        templateMap.put(properties.getTemplatePath() + "/web/Router.js.vm", frontPackagePath + "router" + File.separator + webPackageName + ".js");
        templateMap.put(properties.getTemplatePath() + "/web/List.vue.vm", frontPackagePath + webPackageName + File.separator + webPackageName + "-list.vue");
        templateMap.put(properties.getTemplatePath() + "/web/ListForm.vue.vm", frontPackagePath + webPackageName + File.separator + "components" + File.separator + webPackageName + "-list-form.vue");
        return templateMap;
    }

    public String getJavaType(String mysqlType) {
        String javaType = dataMap.get(mysqlType);
        if (javaType == null) {
            javaType = numberTypeMap.get(mysqlType);
        }
        return javaType;
    }

    public boolean isNumber(String mysqlType) {
        return numberTypeMap.containsKey(mysqlType);
    }


}

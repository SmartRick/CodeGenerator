package rick.pro;

import com.google.common.collect.Lists;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import rick.pro.codegenerator.dao.TableDao;
import rick.pro.codegenerator.domain.CodeGeneratorDTO;
import rick.pro.codegenerator.domain.CodeGeneratorQueryColumnDTO;
import rick.pro.codegenerator.service.CodeGeneratorService;

import java.util.List;

/**
 * @Date: 2021/10/29 10:24
 * @Author: SmartRick
 * @Description: TODO
 */
@SpringBootApplication
@MapperScan(basePackageClasses = TableDao.class)
public class CodeGeneratorMain {
    @Autowired
    private static TableDao tableDao;
    @Autowired
    private static CodeGeneratorService codeGenerator;

    private String author;
    private String tablePrefix;
    private String basePackage;

    public static void main(String[] args) {
        String tablePrefix = "t_";
        List<String> strings = tableDao.selectDatabaseTables();
        strings.stream().forEach(System.out::println);

        List<CodeGeneratorQueryColumnDTO> colnumDtos = Lists.newArrayList();


        CodeGeneratorDTO codeGenerator = CodeGeneratorDTO.builder()
                .author("SmartRick")
                .basePackage("rick.pro")
                .tableName("")
                .tablePrefix(tablePrefix)
                .queryColumnList(colnumDtos).build();
    }
}

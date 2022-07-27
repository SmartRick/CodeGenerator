import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rick.pro.CodeGeneratorMain;
import rick.pro.codegenerator.config.CodeGeneratorProperties;
import rick.pro.codegenerator.constant.SqlOperateTypeEnum;
import rick.pro.codegenerator.dao.TableDao;
import rick.pro.codegenerator.domain.CodeGeneratorDTO;
import rick.pro.codegenerator.domain.CodeGeneratorQueryColumnDTO;
import rick.pro.codegenerator.service.CodeGeneratorService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Date: 2021/10/29 11:06
 * @Author: SmartRick
 * @Description: TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CodeGeneratorMain.class)
public class TestGenerator {
    @Resource
    private TableDao tableDao;
    @Resource
    private CodeGeneratorService codeGeneratorService;
    @Resource
    private CodeGeneratorProperties properties;

    @Test
    public void TestGenerator() {

        List<String> strings = tableDao.selectDatabaseTables();
        strings.stream().forEach(tablename -> {
//            tablename = tablename.replace(tablePrefix, "" );
            List<CodeGeneratorQueryColumnDTO> colnumDtos = Lists.newArrayList();
            tableDao.selectTableColumn(tablename).stream().forEach(columnVO -> {
                colnumDtos.add(CodeGeneratorQueryColumnDTO.builder()
                        .columnName(columnVO.getColumnName())
                        .sqlOperate(SqlOperateTypeEnum.EQUALS)
                        .build());
            });

            CodeGeneratorDTO codeGeneratorDto = CodeGeneratorDTO.builder()
                    .author(properties.getAuthor())
                    .basePackage(properties.getBasePackage())
                    .tableName(tablename)
                    .modulePackage(tablename)
                    .tablePrefix(properties.getTablePrefix())
                    .queryColumnList(colnumDtos)
                    .build();
            try {
                codeGeneratorService.codeGenerator(codeGeneratorDto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

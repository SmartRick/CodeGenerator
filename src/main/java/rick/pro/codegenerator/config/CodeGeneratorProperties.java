package rick.pro.codegenerator.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Date: 2021/11/22 11:05
 * @Author: SmartRick
 * @Description: TODO
 */
@Data
@Component
@EnableConfigurationProperties
@PropertySource("classpath:application.yml")
@ConfigurationProperties(prefix = "codegenerator")
public class CodeGeneratorProperties {
    private String author;
    private String company;
    private String tablePrefix;
    private String basePackage;
    private String templatePath;
    private String projectPath;
    private String projectName;
}

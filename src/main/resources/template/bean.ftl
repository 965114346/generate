package ${beanGenerate@packagePath};

import java.util.Date;

import lombok.Data;

/**
 * @author ${author}
 * ${beanGenerate@name}
 */
@Data
public class ${beanGenerate@name} {
    <#list columnList as column>

    <#if column.columnComment??>
    /**
     * ${column.columnComment}
     */
    </#if>
    private ${column.classSimpleName} ${column.name};
    </#list>
}
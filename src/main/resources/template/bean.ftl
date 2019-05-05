package ${beanPackage};

import lombok.Data;

/**
 * @author ${author}
 * ${beanName}
 */
@Data
public class ${beanName} {
    <#list columnList as column>

    <#if column.columnComment??>
    /**
     * ${column.columnComment}
     */
    </#if>
    private ${column.classSimpleName} ${column.name};
    </#list>
}
package ${beanGenerate@packagePath};

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
<#if tableComment??>
 * ${tableComment}
 *
</#if>
 * @author ${author}
 * @date ${.now}
 */
@Data
@Accessors(chain = true)
public class ${beanGenerate@name} implements Serializable {
    <#list columnList as column>

    <#if column.columnComment??>
    /**
     * ${column.columnComment}
     */
    </#if>
    private ${column.classSimpleName} ${column.name};
    </#list>
}
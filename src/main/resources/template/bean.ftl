package ${bean.path};

import java.util.Date;

import lombok.Data;

/**
 * @author ${author}
 * ${bean.table.upperCaseHumpName}
 */
@Data
public class ${bean.table.upperCaseHumpName} {
    <#list bean.columnList as column>

    <#if column.comment??>
    /**
     * ${column.comment}
     */
    </#if>
    private ${column.convertType} ${column.humpName};
    </#list>
}
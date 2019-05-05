package ${controllerPackage};

import com.elan.question.BasePermissionController;

import ${servicePackage}.${serviceName};
import ${serviceImplPackage}.${serviceImplName};

public class ${controllerName} extends BasePermissionController {

    private ${serviceName} service;

    public ${controllerName}() {
        service = new ${serviceImplName}();
    }
}
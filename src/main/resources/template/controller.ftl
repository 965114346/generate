package ${controllerPackage};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ${servicePackage}.${serviceName};

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/${beanVar}")
public class ${controllerName} {
    <#--private static final Logger log = LoggerFactory.getLogger(${controllerName}.class);-->

    @Autowired
    private ${serviceName} service;
}
package com.neuqsoft.hrmanage.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger-ui配置类
 *
 * @author sunjiarui
 * @Date 2020/8/4
 */
@Configuration
@EnableSwagger2
public class Swagger2Config extends WebMvcConfigurationSupport {

    @Value("${info.app.name:DEMO}")
    private String appName;
    @Value("${info.app.description:description}")
    private String description;
    @Value("${info.app.version:0.0.0}")
    private String appVersion;

    /**
     * 创建swagger-ui配置
     *
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
//                为指定包下的controller生成Api文档
//                .apis(RequestHandlerSelectors.basePackage("com.neuqdoft"))
//                为添加指定注解的controller生成文档
//                .apis(RequestHandlerSelectors.withClassAnnotation(Controller.class))
//                为添加指定注解的方法生成api文档
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//                为所有的接口生成api文档
//                .apis(RequestHandlerSelectors.any())
//                为所有的路径生成api文档
                .paths(PathSelectors.any())
//                为指定路径的接口生成api文档
//                .paths(PathSelectors.ant("/api/"))
                .build();
    }

    /**
     * 设置配置显示的头信息（项目信息）
     *
     * @return
     */
    private ApiInfo apiInfo() {
        Contact contant = new Contact("sheshui", "", "sunjr@neuqsoft.com");
        return new ApiInfoBuilder()
                .title(appName)
                .description(appName + "接口文档描述：" + description)
                .contact(contant)
                .version(appVersion)
                .build();
    }

    /**
     * 配置swagger资源映射
     *
     * @param registry 资源注册器
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


    /**
     * 给API文档接口添加安全认证
     */
    /*private List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeys = new ArrayList<>();
        apiKeys.add(new ApiKey("Authorization", "Authorization", "header"));
        return apiKeys;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("^(?!auth).*$")).build());
        return securityContexts;
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }*/
}

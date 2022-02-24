package com.study.onlineexam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean//配置docket以配置Swagger具体参数
    public Docket docket(Environment environment) {

        //设置要显示的swagger环境
//        Profiles profile = Profiles.of("dev");
        //获取当前的环境是否处在自己设定的环境
//        boolean flag = environment.acceptsProfiles(profile);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(true) //配置是否启用Swagger，如果是false，在浏览器将无法访问
                .select()// 通过.select()方法，去配置扫描接口,RequestHandlerSelectors配置如何扫描接口
                .apis(RequestHandlerSelectors.basePackage("com.study.onlineexam.controller"))
                // 配置如何通过path过滤,即这里只扫描请求以xxx开头的接口
                .build();
    }
    //配置文档信息
    private ApiInfo apiInfo() {
        Contact contact = new Contact("陈则法", "", "15750876535@163.com");
        return new ApiInfo(
                "前后端接口", // 标题
                "在线学习考试系统", // 描述
                "v1.0", // 版本
                "xxx.url/组织链接", // 组织链接
                contact, // 联系人信息
                "Apache 2.0 许可", // 许可
                "许可链接", // 许可连接
                new ArrayList<>()// 扩展
        );
    }
}

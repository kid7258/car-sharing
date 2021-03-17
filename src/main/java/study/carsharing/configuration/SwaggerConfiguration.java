package study.carsharing.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    // https://ahea.wordpress.com/2017/01/18/spring-boot-swagger-%EC%A0%81%EC%9A%A9%EA%B8%B0/
    @Bean
    public Docket swaggerAPI() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(swaggerInfo()).select().apis(RequestHandlerSelectors.basePackage("study.carsharing.controller")).paths(PathSelectors.any()).build();
    }

    private ApiInfo swaggerInfo() {
        return new ApiInfoBuilder().title("Car Sharing API").version("0.0.1").description("차량 관리 시스템 서버 API").build();
    }
}

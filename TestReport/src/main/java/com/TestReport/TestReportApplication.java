package com.TestReport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.thymeleaf.TemplateEngine;

import nz.net.ultraq.thymeleaf.LayoutDialect;



@SpringBootApplication
@EnableAspectJAutoProxy //AOP 설정
@EnableTransactionManagement //어노테이션 기반 트랜잭션 활성화
public class TestReportApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestReportApplication.class, args);
		//Thymeleaf Layout Dialect Configuration v2.0 
		TemplateEngine templateEngine = new TemplateEngine();  // Or SpringTemplateEngine for Spring
		templateEngine.addDialect(new LayoutDialect());
	}
	
    /**
     * HiddenHttpMethodFilter
     * <form> tag에서 '_method'를 사용하여 REST의 PUT와 DELETE를 지원할 경우 사용.
     */
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
    	HiddenHttpMethodFilter filter = new HiddenHttpMethodFilter();
    	return filter;
    }

}

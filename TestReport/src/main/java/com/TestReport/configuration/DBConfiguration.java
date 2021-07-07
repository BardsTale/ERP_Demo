package com.TestReport.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@PropertySource("classpath:/application.properties")
@EnableTransactionManagement
@Configuration
/*MyBatis 매퍼 DAO 연결*/
@MapperScan(value= {"com.TestReport.login.dao",
					"com.TestReport.main.dao",
					"com.TestReport.scanList.dao",
					"com.TestReport.comCode.dao",
					"com.TestReport.jptr.dao",
					"com.TestReport.matr.dao",
					"com.TestReport.mdtr.dao",
					"com.TestReport.usrMst.dao",
				   })
public class DBConfiguration {

	@Autowired
	private ApplicationContext applicationContext;

	/* Hikari DataSource Config 설정 */
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}

	/* DataSource(Hikari) 빈 객체로 등록 */
	@Bean
	public DataSource dataSource() {
		return new HikariDataSource(hikariConfig());
	}

	/* MyBatis SqlSessionFactory 설정
	 * - SqlSession을 생성하고 관리
	 *  */
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		//Mapper 파일 경로 지정
		factoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/**/*.xml"));
		factoryBean.setConfiguration(mybatisConfig());
		return factoryBean.getObject();
	}

	/* mybatisConfig 설정 */
	@Bean
	@ConfigurationProperties(prefix = "mybatis.configuration")
	public org.apache.ibatis.session.Configuration mybatisConfig() {
		return new org.apache.ibatis.session.Configuration();
	}
	
	/* SqlSessionTemplate를 생성자 인자로 SqlSessionFactory를 사용하여 빈 객체로 등록 
	 * - Spring과 연동 시 스레드 Safe한 Sqlsession 객체가 SqlSessionTemplate 통해 스프링빈에 주입됨.
	 * - 사실상 Sqlsession의 생명주기를 관리하는 핵심.
	 * - 반복문을 통한 사용 시 배치 형태로 실행이 된다.
	 * */
	@Bean
	public SqlSessionTemplate sqlSession() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}

	/* transactionManager 설정
	 * - DataSource가 SqlSessionFactoryBean을 생성할때 사용된 것과 반드시 동일해야만한다. 
	 *   그렇지 않으면 트랜잭션 관리가 제대로 되지 않을것.
	 * */
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

}

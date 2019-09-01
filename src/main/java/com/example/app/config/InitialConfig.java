package com.example.app.config;



import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jndi.JndiObjectFactoryBean;



@Configuration
public class InitialConfig {

	
	@Value("${mysql.spring.datasource.url}")
	String mysqlUrl;
	
	@Value("${mysql.spring.datasource.username}")
	String mysqlUser;
	
	@Value("${mysql.spring.datasource.password}")
	String mysqlPassword;
	
	@Value("${mysql.spring.datasource.driver-class-name}")
	String mysqlDriver;
	
	@Value("${mysql.spring.datasource.jndi-name}")
	String mysqlJndiName;

	
	@Bean
	public TomcatServletWebServerFactory tomcatFactory() {
		
		return new TomcatServletWebServerFactory() {
			@Override
			protected TomcatWebServer getTomcatWebServer(Tomcat tomcat) {
				System.out.println("#### TomcatEmbeddedApplication : TomcatServletWebServerFactory.getTomcatWebServer  ");
				tomcat.enableNaming();
				return super.getTomcatWebServer(tomcat);
			}
			
			@Override
			protected void postProcessContext(Context context) {
				System.out.println("#### TomcatEmbeddedApplication : TomcatServletWebServerFactory.postProcessContext  ");
				ContextResource resourceMySQL = new ContextResource();
				resourceMySQL.setName(mysqlJndiName);
				resourceMySQL.setType(DataSource.class.getName());
				resourceMySQL.setProperty("driverClassName", mysqlDriver);
				resourceMySQL.setProperty("url", mysqlUrl);
				resourceMySQL.setProperty("username", mysqlUser);
				resourceMySQL.setProperty("password", mysqlPassword);
//				resourceMySQL.setProperty("maxTotal", mysqlMaxTotal);
//				resourceMySQL.setProperty("maxIdle", mysqlMaxTotal);
//				resourceMySQL.setProperty("minIdle", mysqlInitialSize);
//				resourceMySQL.setProperty("maxWaitMillis", maxWaitMillis);
//				resourceMySQL.setProperty("poolPreparedStatements", poolPreparedStatements);
//				resourceMySQL.setProperty("timeBetweenEvictionRunsMillis", timeBetweenEvictionRunsMillis);
//				resourceMySQL.setProperty("removeAbandonedOnBorrow", removeAbandonedOnBorrow);
//				resourceMySQL.setProperty("removeAbandonedOnMaintenance", removeAbandonedOnMaintenance);
//				resourceMySQL.setProperty("logAbandoned", logAbandoned);
//				resourceMySQL.setProperty("validationQuery", "SELECT 1 ");
//				resourceMySQL.setProperty("minEvictableIdleTimeMillis", minEvictableIdleTimeMillis);
//				resourceMySQL.setProperty("initialSize", mysqlInitialSize);
				context.getNamingResources().addResource(resourceMySQL);
				
				super.postProcessContext(context);
			}
		};
		
	}
	

	@Bean(destroyMethod="")
    public DataSource jndiDataSource() throws IllegalArgumentException, NamingException {
	    JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
		bean.setJndiName("java:comp/env/" + mysqlJndiName);
		bean.setProxyInterface(DataSource.class);
		bean.setLookupOnStartup(false);
		bean.afterPropertiesSet();
		return (DataSource)bean.getObject();
    }
	

    @Bean
    public JdbcTemplate jdbcTemplate(final DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(final DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
	
	
}

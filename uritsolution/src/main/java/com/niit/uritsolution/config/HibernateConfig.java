package com.niit.uritsolution.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = { "com.niit.uritsolution" })
@EnableTransactionManagement
public class HibernateConfig {

	/*
	 * Data Base Configurations
	 */
	private final static String DATABASE_URL = "jdbc:h2:tcp://localhost/~/angular";
	private final static String DATABASE_DRIVER = "org.h2.Driver";
	private final static String DATABASE_DIALECT = "org.hibernate.dialect.H2Dialect";
	private final static String DATABASE_USERNAME = "angular";
	private final static String DATABASE_PASSOWRD = "";

	/*
	 * DataSource Bean
	 */
	@Bean(name = "dataSource")
	public DataSource getDataSource() {

		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();

		/*
		 * Giving Data Source Configuration
		 */
		driverManagerDataSource.setDriverClassName(DATABASE_DRIVER);
		driverManagerDataSource.setUrl(DATABASE_URL);
		driverManagerDataSource.setUsername(DATABASE_USERNAME);
		driverManagerDataSource.setPassword(DATABASE_PASSOWRD);
		
		return driverManagerDataSource;
	}

	/*
	 * Session Factory Bean
	 */
	@Bean
	public SessionFactory getSessionFactory(DataSource dataSource) {

		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);

		sessionBuilder.addProperties(getHibernateProperties());
		sessionBuilder.scanPackages("com.niit.uritsolution");

		return sessionBuilder.buildSessionFactory();
	}

	/*
	 * For Hibernate Properties
	 */
	public Properties getHibernateProperties() {

		Properties properties = new Properties();

		properties.put("hibernate.dialect", DATABASE_DIALECT);
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "update");
		//properties.put("hibernate.connection.pool_size", 5);
		//    <property name="hibernate.connection.pool_size">5</property>  

		return properties;
	}

	/*
	 * Hibernate Transaction Manager
	 */
	@Bean
	public HibernateTransactionManager getHibernateTransactionManager(SessionFactory sessionFactory) {

		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

		return transactionManager;
	}

	/*
	 * Multipart Resolver configuration.
	 
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver getMultipartResolver() {

		long maxUploadSize = 1000000;
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(maxUploadSize);

		return multipartResolver;
	}*/

}

package SpringMVCWeb.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import SpringMVCWeb.dao.AccountDAO;
import SpringMVCWeb.dao.ProductDAO;
import SpringMVCWeb.dao.impl.AccountDAOImpl;
import SpringMVCWeb.dao.impl.ProductDAOImpl;

@Configuration
@ComponentScan("SpringMVCWeb.*")
@EnableTransactionManagement //for transaction manager
@PropertySource("classpath:application.properties") //locate file application.properties, this file is used to load property for beans
public class ApplicationContext {
	
	@Autowired
	private Environment env;
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource rb=new ResourceBundleMessageSource();
		rb.setBasename("messages/validator");//load property in validator file in folder messages
		return rb;
	}
	@Bean(name="viewResolver") //config for view pages
	public InternalResourceViewResolver viewResolver() {
		return new InternalResourceViewResolver(env.getProperty("mvc.views.prefix"), env.getProperty("mvc.views.suffix"));
	}
	@Bean(name="multipartResolver") //config for uploading file
	public CommonsMultipartResolver multipartResolver() {
		return new CommonsMultipartResolver();
	}
	@Bean(name="dataSource") //sql database connection properties
	public DataSource getDataSource() { 
		DriverManagerDataSource dataSource=new DriverManagerDataSource(env.getProperty("ds.url"), env.getProperty("ds.username"), env.getProperty("ds.password"));
		dataSource.setDriverClassName(env.getProperty("ds.database-driver"));
		System.out.println("##SpringMVCWeb get datasource "+dataSource);
		return dataSource;
	}
	@Autowired
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) throws IOException {
		//hibernate properties
		Properties properties=new Properties();
		properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		properties.put("current_session_context_class",env.getProperty("current_session_context_class"));
		
		//session factory
		LocalSessionFactoryBean sessionFactoryBean=new LocalSessionFactoryBean();
		sessionFactoryBean.setPackagesToScan("SpringMVCWeb.entity");
		sessionFactoryBean.setDataSource(dataSource);
		sessionFactoryBean.setHibernateProperties(properties);
		sessionFactoryBean.afterPropertiesSet();
		SessionFactory sf=sessionFactoryBean.getObject();
		System.out.println("SpringMVCWeb Session Factory: "+sf);
		return sf;
	}
	@Autowired
	@Bean(name="transactionManager")
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		return new HibernateTransactionManager(sessionFactory);
	}
	@Bean(name="productDAO")
	public ProductDAO getProductDAO() {
		return new ProductDAOImpl();
	}
	@Bean(name="accountDAO")
	public AccountDAO getAccountDAO() {
		return new AccountDAOImpl();
	}
}
	

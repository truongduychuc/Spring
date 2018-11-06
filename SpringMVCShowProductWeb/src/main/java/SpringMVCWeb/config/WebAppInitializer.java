package SpringMVCWeb.config;


import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {
	@Override
	public void onStartup(ServletContext context) {
		AnnotationConfigWebApplicationContext applicationContext=new AnnotationConfigWebApplicationContext();
		applicationContext.register(ApplicationContext.class);
		ServletRegistration.Dynamic dispatcher=context.addServlet("DispatcherServlet", new DispatcherServlet(applicationContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/"); //add mapping url / for servlet on url bar
		ContextLoaderListener loaderListener=new ContextLoaderListener(applicationContext);
		context.addListener(loaderListener);
		//filter
		FilterRegistration.Dynamic fr=context.addFilter("encodingFilter", CharacterEncodingFilter.class);
		fr.setInitParameter("encoding", "UTF-8");
		fr.setInitParameter("forceEncoding", "true");
		fr.addMappingForUrlPatterns(null, true, "/*");
	}
}

package SpringMVCWeb.config;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class StaticResourcesConfig implements WebMvcConfigurer {
	private static final Charset UTF8=Charset.forName("UTF-8"); //encoding character type
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		StringHttpMessageConverter stringHttpMessageConverter=new StringHttpMessageConverter();
		stringHttpMessageConverter.setSupportedMediaTypes(Arrays.asList(new MediaType("text", "plain", UTF8)));
		converters.add(stringHttpMessageConverter);
	}
	
	//config for static resources file
	//equivalent to <mvc:resources/> tag in xml
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/bootstrap/**").addResourceLocations("/bootstrap-3.1.1/");
		registry.addResourceHandler("/myCss/**").addResourceLocations("/myCss/");
		registry.addResourceHandler("/image/**").addResourceLocations("/image/");
		registry.addResourceHandler("/jquery/**").addResourceLocations("/jquery/");
	}
	//equivalent <mvc:default-servlet-handler/> tag
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
}

package ray.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.mobile.device.DeviceResolverHandlerInterceptor;
import org.springframework.mobile.device.site.SitePreferenceHandlerInterceptor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ray.component.batch.StatsManageBatch;
import ray.interceptor.StatsCheckInterceptorImpl;

/**
 * Created by ChanPong on 2016-05-17.
 */
@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@EnableScheduling
@ComponentScan(basePackages = {"ray.controller", "ray.service", "ray.repository", "ray.configuration", "ray.util.crypt"})
public class WebAdapter extends WebMvcConfigurerAdapter {
	@Bean
	public StatsManageBatch task() {
		return new StatsManageBatch();
	}

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/view/");
		return resolver;
	}

	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setMaxUploadSize(5120000);
		return resolver;
	}

	@Bean
	public DeviceResolverHandlerInterceptor deviceResolverHandlerInterceptor() {
		return new DeviceResolverHandlerInterceptor();
	}

	@Bean
	public SitePreferenceHandlerInterceptor sitePreferenceHandlerInterceptor() {
		return new SitePreferenceHandlerInterceptor();
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
				.addInterceptor(new StatsCheckInterceptorImpl())
				.addPathPatterns("/**")
						// 제외할 목록
				.excludePathPatterns("/login")
				.excludePathPatterns("/logout")
				.excludePathPatterns("/index");
		registry.addInterceptor(deviceResolverHandlerInterceptor());
		registry.addInterceptor(sitePreferenceHandlerInterceptor());
		super.addInterceptors(registry);
	}
}

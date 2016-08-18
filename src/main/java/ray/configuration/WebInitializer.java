package ray.configuration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;


import javax.servlet.*;
import java.util.EnumSet;

/**
 * Created by ChanPong on 2016-05-17.
 */
public class WebInitializer implements WebApplicationInitializer {
	@Override
	public void onStartup(ServletContext context) throws ServletException {
		AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
		dispatcherContext.register(WebAdapter.class);

		// dispatcher 등록
		ServletRegistration.Dynamic appDispatcher;
		appDispatcher = context.addServlet("app", new DispatcherServlet(dispatcherContext));
		appDispatcher.addMapping("/");

		// 인코딩 설정
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		FilterRegistration.Dynamic characterEncoding = context.addFilter("characterEncodingFilter", characterEncodingFilter);
		characterEncoding.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
	}
}
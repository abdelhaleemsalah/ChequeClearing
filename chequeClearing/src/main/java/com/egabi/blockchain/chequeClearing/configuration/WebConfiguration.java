package com.egabi.blockchain.chequeClearing.configuration;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.webflow.mvc.servlet.FlowHandlerAdapter;
//import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.webflow.mvc.servlet.FlowHandlerAdapter;
import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;
import org.springframework.webflow.mvc.view.AjaxUrlBasedViewResolver;
import org.springframework.webflow.mvc.view.FlowAjaxTiles3View;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
@EnableWebMvc
public class WebConfiguration extends WebMvcConfigurerAdapter {

	@Autowired
    private WebFlowConfig webFlowConfig;
	
	
	
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("Registeration");
        registry.addViewController("/RegConfirmation").setViewName("RegConfirmation");
        registry.addViewController("/regSummary").setViewName("regSummary");
       // registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    
    @Bean
	public AjaxUrlBasedViewResolver viewAjaxResolver() {
		AjaxUrlBasedViewResolver resolver = new AjaxUrlBasedViewResolver();
		resolver.setViewClass(FlowAjaxTiles3View.class);
		return resolver;
	}
    
//    @Bean
//    public InternalResourceViewResolver viewResolver() {
//        InternalResourceViewResolver viewResolver
//                = new InternalResourceViewResolver();
//        viewResolver.setViewClass(JstlView.class);
//        viewResolver.setPrefix("/WEB-INF/view/");
//        viewResolver.setSuffix(".jsp");
//        return viewResolver;
//    }
    
//    @Override
//	public void configureViewResolvers(ViewResolverRegistry registry) {
//		TilesViewResolver viewResolver = new TilesViewResolver();
//		registry.viewResolver(viewResolver);
//		
//	}

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/").setCacheControl(CacheControl.noCache());
    }
    @Bean
    public LocaleResolver localeResolver(){
           SessionLocaleResolver localeResolver = new SessionLocaleResolver();
          // localeResolver.setDefaultLocale(Locale.US);
           return  localeResolver;
       }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(localeChangeInterceptor());
    }
    
	@Bean
    public FlowHandlerMapping flowHandlerMapping() {
        FlowHandlerMapping handlerMapping = new FlowHandlerMapping();
        handlerMapping.setOrder(-1);
        handlerMapping.setFlowRegistry(this.webFlowConfig.flowRegistry());
        return handlerMapping;
    }

    @Bean
    public FlowHandlerAdapter flowHandlerAdapter() {
        FlowHandlerAdapter handlerAdapter = new FlowHandlerAdapter();
        handlerAdapter.setFlowExecutor(this.webFlowConfig.flowExecutor());
        handlerAdapter.setSaveOutputToFlashScopeOnRedirect(true);
        return handlerAdapter;
    }
	@Bean(name="view/flows")
	public ChequeRegisterationFlowHandler ChequeRegisterationFlowHandler()
	{
		return new ChequeRegisterationFlowHandler();
	}
	
	@Bean
	public TilesConfigurer tilesConfigurer(){
	    TilesConfigurer tilesConfigurer = new TilesConfigurer();
	    tilesConfigurer.setDefinitions(new String[] {"/WEB-INF/view/**/tiles.xml"});
	    tilesConfigurer.setCheckRefresh(true);
	    return tilesConfigurer;
	}
}

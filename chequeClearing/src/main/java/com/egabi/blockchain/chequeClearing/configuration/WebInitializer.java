package com.egabi.blockchain.chequeClearing.configuration;

import javax.servlet.ServletRegistration.Dynamic;


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    public WebInitializer() {
        super();
    }

    // API
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { WebConfiguration.class, WebFlowConfig.class ,SecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected void customizeRegistration(final Dynamic registration) {
        super.customizeRegistration(registration);
    }


}

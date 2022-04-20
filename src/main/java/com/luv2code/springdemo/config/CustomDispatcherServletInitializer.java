package com.luv2code.springdemo.config;

import org.springframework.lang.NonNull;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/*EVERY request goes to dispatcher servlet as we have used / as default pattern and then
we process that request so every request is a dynamic request as we create that at runtime, more about runtime creation in next comment


It(gives req to view resolver https://stackoverflow.com/a/44380337/17261574) super imp
builds the web pages by appending name prepending strings to create webpage
at runtime hence it is a dynamic page as it is being created at runtime*/
public class CustomDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    @Override
    @NonNull
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}

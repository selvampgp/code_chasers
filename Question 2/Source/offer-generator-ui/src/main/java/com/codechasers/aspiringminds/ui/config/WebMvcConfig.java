package com.codechasers.aspiringminds.ui.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.resource.ContentVersionStrategy;
import org.springframework.web.servlet.resource.VersionResourceResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;


@Configuration
@EnableWebMvc
@ComponentScan(value = { "com.codechasers.aspiringminds.core","com.codechasers.aspiringminds.ui" }, useDefaultFilters = true, 
includeFilters = 
@ComponentScan.Filter(value = org.springframework.stereotype.Controller.class, 
type = FilterType.ANNOTATION))
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	private static final String MESSAGE_SOURCE = "/WEB-INF/i18n/messages";
	private static final String VIEWS = "/WEB-INF/views/";
	private static final String MAIL_PATH = "mail/";
   private static final String CHARACTER_ENCODING="UTF-8";
   private static final String TEMPLATE_MODE="HTML5";
	private static final String RESOURCES_LOCATION = "/resources/";
	private static final String RESOURCES_HANDLER =  RESOURCES_LOCATION + "**";
	private static final int CACHE_PERIOD = 31536000;
	      

	/**
	 *  used to extract the actual location of a resource from URI
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		VersionResourceResolver versionResourceResolver = new VersionResourceResolver()
			        .addVersionStrategy(new ContentVersionStrategy(), "/**");

		registry.addResourceHandler(RESOURCES_HANDLER).addResourceLocations(RESOURCES_LOCATION).setCachePeriod(CACHE_PERIOD).resourceChain(true).addResolver(versionResourceResolver);
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");//classpath:/META-INF/resources/
		
	}

	/**
	 * enable spring to support multipart support for file operations in web application
	 * @return CommonsMultipartResolver
	 */
	@Bean(name="multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multiPartResolver=new CommonsMultipartResolver();
		multiPartResolver.setMaxUploadSizePerFile(31889999);
        return multiPartResolver;
    }

	/**
	 * gives flexible management of static text in an application
	 * @return MessageSource
	 */
	@Bean(name = "messageSource")
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		String[] propertyfilenames={MESSAGE_SOURCE};
		messageSource.setBasenames(propertyfilenames);
		messageSource.setDefaultEncoding(CHARACTER_ENCODING);
		messageSource.setCacheSeconds(5);
		return messageSource;
	}

	/**
	 * it resolves templates with provided prefix and suffix(indicates the location of the view pages within the web application directory)
	 */
	@Bean
	public ServletContextTemplateResolver templateResolver() {
		final ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
		resolver.setPrefix(VIEWS);
		resolver.setSuffix(".html");
		resolver.setTemplateMode(TEMPLATE_MODE);
		resolver.setCharacterEncoding(CHARACTER_ENCODING);
		resolver.setOrder(2);
		resolver.setCacheable(false);
		return resolver;
	}

	/**
	 * it will load the templates from the root of the class path
	 * @return ClassLoaderTemplateResolver
	 */
	@Bean
	public ClassLoaderTemplateResolver emailTemplateResolver() {
		final ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
		resolver.setPrefix(MAIL_PATH);
		resolver.setSuffix(".html");
		resolver.setTemplateMode(TEMPLATE_MODE);
		resolver.setCharacterEncoding(CHARACTER_ENCODING);
		resolver.setOrder(1);
		return resolver;
	}


	/**
	 * used to process spring templates
	 */
	@Bean
	public SpringTemplateEngine templateEngine() {
		final SpringTemplateEngine engine = new SpringTemplateEngine();
	    final Set<TemplateResolver> templateResolvers = new HashSet<>();
	    templateResolvers.add(templateResolver());
	    templateResolvers.add(emailTemplateResolver());
	    engine.setTemplateResolvers(templateResolvers);
	    return engine;
	}

	/**
	 * view resolver maps the view names returned by a controller to actual view objects
	 * @return thymeleafViewResolver
	 */
	@Bean
	public ViewResolver thymeleafViewResolver() {
		ThymeleafViewResolver vr = new ThymeleafViewResolver();
		vr.setTemplateEngine(templateEngine());
		vr.setCharacterEncoding(CHARACTER_ENCODING);
		vr.setOrder(Ordered.HIGHEST_PRECEDENCE);
		// all message/* views will not be handled by this resolver as they are
		// Tiles views
		vr.setExcludedViewNames(new String[] { "message/*" });
		return vr;
	}


	/*************** Content Negotiation ViewResolvers **********/

	/**
	 * it supports multiple views for different content-types
	 * @param ContentNegotiationManager
	 * @throws Exception
	 */
	@Bean(name = "contentNegotiatingViewResolver")
	public ContentNegotiatingViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {

		ContentNegotiationManagerFactoryBean contentNegotiationManager = new ContentNegotiationManagerFactoryBean();

		Map<String, MediaType> medTypes = new HashMap<>();

		medTypes.put("json", MediaType.APPLICATION_JSON);
		medTypes.put("xml", MediaType.APPLICATION_XML);

		contentNegotiationManager.addMediaTypes(medTypes);
		contentNegotiationManager.setDefaultContentType(MediaType.TEXT_HTML);
		contentNegotiationManager.setFavorPathExtension(false);
		contentNegotiationManager.setFavorParameter(false);
		contentNegotiationManager.setIgnoreAcceptHeader(true);

		List<ViewResolver> resolvers = new ArrayList<>();
		resolvers.add(thymeleafViewResolver());

		ContentNegotiatingViewResolver cnresolver = new ContentNegotiatingViewResolver();
		cnresolver.setContentNegotiationManager(contentNegotiationManager.getObject());
		cnresolver.setViewResolvers(resolvers);
		cnresolver.setOrder(Ordered.LOWEST_PRECEDENCE - 20);

		return cnresolver;
	}


	/**
     * it will support the internationalization or multiple languages features
     */
	@Bean(name = "localeResolver")
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(new Locale("en", "US"));
		return localeResolver;
	}

	/**
	 * used to resolve text messages from properties file based on the selected locale(languages) and to reload properties file content automatically
	 */
	@Bean
	@Description("Reloading the property file using Cache")
	public ReloadableResourceBundleMessageSource propertyfilereloader(){
		ReloadableResourceBundleMessageSource bean = new ReloadableResourceBundleMessageSource();
		String[] propertyfilenames={"classpath:i18n/messages.properties"};
		bean.setBasenames(propertyfilenames);
		bean.setCacheSeconds(10);
		return bean;
	}

	/**
	 * used to register view controller and it will map a URL with a view
	 */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
    }

	
	

	/**
	 * used to set whether to apply case sensitivity to the generated paths
	 */
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		AntPathMatcher matcher = new AntPathMatcher();
	    matcher.setCaseSensitive(false);
	    configurer.setPathMatcher(matcher);
	}

}

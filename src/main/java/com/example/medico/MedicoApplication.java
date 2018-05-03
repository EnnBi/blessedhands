package com.example.medico;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

import com.example.medico.model.Company;
import com.example.medico.model.Medicine;
import com.example.medico.model.Type;
import com.example.medico.model.Unit;
import com.example.medico.utils.StringToEntityConverter;

@SpringBootApplication
public class MedicoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MedicoApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder) {
		return applicationBuilder.sources(MedicoApplication.class);
	}
	
	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		System.err.println("in tiles config");
		String def[] = {"/WEB-INF/admin/views.xml"};
		tilesConfigurer.setDefinitions(def);
		return tilesConfigurer;
	}
	
	@Bean
	public UrlBasedViewResolver tilesViewResolver() {
		UrlBasedViewResolver tilesViewResolver = new UrlBasedViewResolver();
		tilesViewResolver.setViewClass(TilesView.class);
		return tilesViewResolver;
	}
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	/*@Bean(name="conversionService")
	public FormattingConversionServiceFactoryBean converter() {
		FormattingConversionServiceFactoryBean conversionService = new FormattingConversionServiceFactoryBean();
		
		StringToEntityConverter medicineConverter = new StringToEntityConverter(Medicine.class);
		StringToEntityConverter typeConverter = new StringToEntityConverter(Type.class);
		StringToEntityConverter unitConverter = new StringToEntityConverter(Unit.class);
		StringToEntityConverter companyConverter = new StringToEntityConverter(Company.class);

		
		Set<StringToEntityConverter> converters = new HashSet<>();
		converters.add(medicineConverter);
		converters.add(typeConverter);
		converters.add(unitConverter);
		converters.add(companyConverter);
		
		conversionService.setConverters(converters);
		return conversionService;
	}
*/	@Bean
	public LocaleResolver localeResolverBean() {
		SessionLocaleResolver localeResolver =  new SessionLocaleResolver();
		 localeResolver.setDefaultLocale(new Locale("en"));
		 return localeResolver;
	}
	@Bean
	@Primary
	public MessageSource messageSourceBean() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setCacheSeconds(10);
		return messageSource;
	}
}

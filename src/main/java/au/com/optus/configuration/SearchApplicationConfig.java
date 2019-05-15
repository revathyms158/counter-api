package au.com.optus.configuration;

import java.util.List;

import au.com.optus.model.SearchData;
import au.com.optus.model.SearchResult;
import au.com.optus.service.SearchService;
import au.com.optus.service.SearchServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by revathy.ms on 13/5/19.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "au.com.optus")
public class SearchApplicationConfig extends WebMvcConfigurerAdapter {

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(mappingJackson2HttpMessageConverter());
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false));
        return converter;
    }

    @Bean
    public SearchResult searchResult() {
        return new SearchResult();
    }

    @Bean
    public SearchService searchService() {
        return new SearchServiceImpl();

    }
}

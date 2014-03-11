package org.inbloom.gateway.configuration;

import org.inbloom.gateway.util.mapper.DomainOperatorModelMapper;
import org.inbloom.gateway.util.mapper.PersistentOperatorModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lloydengebretsen on 3/7/14.
 */
@Configuration
public class ConversionConfiguration {

    @Bean
    public ConversionService conversionService() {
        ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
        bean.setConverters(getConverters());
        bean.afterPropertiesSet();
        ConversionService object = bean.getObject();
        return object;
    }

    private Set<Converter> getConverters() {
        Set<Converter> converters = new HashSet<Converter>();

        converters.add(new DomainOperatorModelMapper());
        converters.add(new PersistentOperatorModelMapper());

        return converters;
    }
}


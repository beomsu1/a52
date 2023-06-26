package org.zerock.a52.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = {"org.zerock.a52.**.mappers"})
public class RootConfig {
    
}

package com.lx.novel_writing_backend.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.lx.novel_writing_backend.mapper")
public class MyBatisConfig {
}

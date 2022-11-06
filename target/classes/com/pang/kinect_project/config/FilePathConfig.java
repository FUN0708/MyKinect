package com.pang.kinect_project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class FilePathConfig implements WebMvcConfigurer {
    /**
     * 配置文件上传的路径
     */
    @Value("${file.path}")
    private String path;

    @Value("${file.location}")
    private String location;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        // 匹配到resourceHandler,将URL映射至address,也就是本地文件夹
        registry.addResourceHandler(path).addResourceLocations("file:"+location);
    }
}

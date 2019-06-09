package br.com.projeto.conecta.config;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.cache.CacheBuilder;

@Configuration
@EnableCaching
public class CacheConfig {
	
	@Bean
	public CacheManager cacheManager() {
		 CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder()
		            .maximumSize(100)
		            .expireAfterAccess(10, TimeUnit.MINUTES);
		        GuavaCacheManager manager = new GuavaCacheManager();
		        manager.setCacheBuilder(builder);

		        return manager;
	}
}

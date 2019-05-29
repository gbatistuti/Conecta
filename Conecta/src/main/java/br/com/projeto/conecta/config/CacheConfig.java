package br.com.projeto.conecta.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {
	
	@Bean
	public CacheManager cacheManager() {
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		List<Cache> cacheList = new ArrayList<Cache>();
		cacheList.add(new ConcurrentMapCache("userCache"));
		cacheList.add(new ConcurrentMapCache("consultorCache"));
		//cacheList.add(new ConcurrentMapCache("disponivelCache"));
		//cacheList.add(new ConcurrentMapCache("pedidosFiltradosCache"));
		//cacheList.add(new ConcurrentMapCache("cacdidaturasCache"));
		cacheManager.setCaches(cacheList);
		return cacheManager;
	}

}

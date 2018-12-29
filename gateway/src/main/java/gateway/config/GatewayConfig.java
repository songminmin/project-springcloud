package gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.core.publisher.Mono;

@Configuration
public class GatewayConfig {
	

	/**
	 *  IP限流
	 * @return
	 */
	
	@Bean
	KeyResolver ipKeyResolver() {
	    return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
	}
	/**
	 * 用户限流  请求路径中必须携带userId参数
	 * @return
	 */
	@Bean
	KeyResolver userKeyResolver() {
	    return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("userId"));
	}
	/**
	 * 接口限流  获取请求地址的uri作为限流key
	 * @return
	 */
	@Bean
	KeyResolver apiKeyResolver() {
	    return exchange -> Mono.just(exchange.getRequest().getPath().value());
	}
}

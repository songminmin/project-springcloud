package gateway.route;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import gateway.repository.RedisRouteDefinitionRepository;
import reactor.core.publisher.Mono;

@Service
public class DynamicRouteService implements ApplicationEventPublisherAware {
 
    @Resource
    private RedisRouteDefinitionRepository routeDefinitionWriter;
 
    private ApplicationEventPublisher publisher;
 
    private void notifyChanged() {
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
    }
 
    /**
     * 删除路由
     *
     */
    public String delete(String id) {
        try {
            this.routeDefinitionWriter.delete(Mono.just(id));
 
            notifyChanged();
            return "delete success";
        } catch (Exception e) {
            e.printStackTrace();
            return "delete fail";
        }
    }
 
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
 
    @Resource
    private StringRedisTemplate redisTemplate;
    
    public void addGateway(String id, String path, String targetIpPort, Integer replenishRate, Integer burstCapacity){
    	RouteDefinition routeDefinition = new RouteDefinition();
        PredicateDefinition predicateDefinition = new PredicateDefinition();
        Map<String, String> predicateParams = new HashMap<>(8);
        Map<String, String> filterParams = new HashMap<>(8);
        FilterDefinition filterDefinition = new FilterDefinition();
        // uri: "http://localhost:9052" 反向指向新ip端口
        URI uri = UriComponentsBuilder.fromUriString(targetIpPort).build().toUri();
        // id: "rateLimitTest" 唯一
        routeDefinition.setId(id);
        // 名称是固定的，spring gateway会根据名称找对应的PredicateFactory
        predicateDefinition.setName("Path");
        // path: "/proxy/myapp28081/mockhello"
        predicateParams.put("pattern", "/health"+path +"/**");
        predicateDefinition.setArgs(predicateParams);
        // 名称是固定的，spring gateway会根据名称找对应的FilterFactory
        filterDefinition.setName("RequestRateLimiter");
        // 每秒最大访问次数
        filterParams.put("redis-rate-limiter.replenishRate", String.valueOf(replenishRate));
        // 令牌桶最大容量
        filterParams.put("redis-rate-limiter.burstCapacity", String.valueOf(burstCapacity));
        // 限流策略(#{@BeanName})
        filterParams.put("key-resolver", "#{@ipKeyResolver}");
        // 自定义限流器(#{@BeanName})
        //filterParams.put("rate-limiter", "#{@redisRateLimiter}");
        filterDefinition.setArgs(filterParams);
        routeDefinition.setPredicates(Arrays.asList(predicateDefinition));
        routeDefinition.setFilters(Arrays.asList(filterDefinition));
        routeDefinition.setUri(uri);
        
        routeDefinitionWriter.save(Mono.just(routeDefinition)).subscribe();
        //this.publisher.publishEvent(new RefreshRoutesEvent(this));
        notifyChanged();
    	
    }
 
    /*@PostConstruct
    public void main() {
        RouteDefinition routeDefinition = new RouteDefinition();
        PredicateDefinition predicateDefinition = new PredicateDefinition();
        Map<String, String> predicateParams = new HashMap<>(8);
        Map<String, String> filterParams = new HashMap<>(8);
        FilterDefinition filterDefinition = new FilterDefinition();
        URI uri = UriComponentsBuilder.fromUriString("http://localhost:9052").build().toUri();
        routeDefinition.setId("rateLimitTest");
        // 名称是固定的，spring gateway会根据名称找对应的PredicateFactory
        predicateDefinition.setName("Path");
        predicateParams.put("pattern", "/proxy/myapp28081/mockhello/**");
        predicateDefinition.setArgs(predicateParams);
        // 名称是固定的，spring gateway会根据名称找对应的FilterFactory
        filterDefinition.setName("RequestRateLimiter");
        // 每秒最大访问次数
        filterParams.put("redis-rate-limiter.replenishRate", "1");
        // 令牌桶最大容量
        filterParams.put("redis-rate-limiter.burstCapacity", "2");
        // 限流策略(#{@BeanName})
        filterParams.put("key-resolver", "#{@ipKeyResolver}");
        // 自定义限流器(#{@BeanName})
        //filterParams.put("rate-limiter", "#{@redisRateLimiter}");
        filterDefinition.setArgs(filterParams);
        routeDefinition.setPredicates(Arrays.asList(predicateDefinition));
        routeDefinition.setFilters(Arrays.asList(filterDefinition));
        routeDefinition.setUri(uri);
        
        routeDefinitionWriter.save(Mono.just(routeDefinition)).subscribe();
        //this.publisher.publishEvent(new RefreshRoutesEvent(this));
        notifyChanged();
    }*/

    	
}


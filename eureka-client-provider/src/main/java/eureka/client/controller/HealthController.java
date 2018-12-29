package eureka.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@RestController
@RequestMapping("/health")
public class HealthController {
	
	@Autowired
	private EurekaClient eurekaClient;
	
	@Autowired
    private Registration registration;// 服务注册
	
    @Autowired
    private DiscoveryClient client;
	
	@GetMapping("/get")
	public String isHealth(){
		InstanceInfo instance = eurekaClient.getNextServerFromEureka("SONGMM", false);
		return instance.getHomePageUrl();
	}
	
	@GetMapping("/{pathId}")
	public String showPathId(@PathVariable("pathId") String pathId){
		return "7900---" + pathId;
	}
	
	@GetMapping(value = "/hello")
    public String index(){
        List<ServiceInstance> instances = client.getInstances(registration.getServiceId());
        if (instances != null && instances.size() > 0) {
        	System.out.println(instances.get(0).getHost() + "|||" + instances.get(0).getServiceId());
        }
        return "hello,provider";
    }
}

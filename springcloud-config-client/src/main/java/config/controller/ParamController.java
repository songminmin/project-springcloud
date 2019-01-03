package config.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ParamController {

	@Value("${springcloud.version}")
	private String version;

	@Autowired
	private Environment environment ;

	@RequestMapping("/version")
	public String getVersion(){
		return  this.version;
	}

	@RequestMapping("/version2")
	public String getVersion2() {
		return environment.getProperty("springcloud.version", "未定义");
	}

}

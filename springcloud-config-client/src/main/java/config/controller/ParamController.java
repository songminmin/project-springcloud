package config.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ParamController {

	@Value("${version}")
	private String version;

	/*@Autowired
	private Environment environment ;*/

	/*@RequestMapping("/version")
	public String getVersion(){
		return  this.version;
	}*/

	@RequestMapping("/version")
	public String getVersion2() {
		return this.version;
	}

}

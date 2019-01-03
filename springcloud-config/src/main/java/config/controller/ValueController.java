//package config.controller;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class ValueController {
//	
//	@Value("${springcloud.version}")
//    private String version;
//	
//	@GetMapping("/{value}")
//	public String getValue(@PathVariable(value="value") String value){
//		System.out.println(value);
//		return version;
//	}
//
//}

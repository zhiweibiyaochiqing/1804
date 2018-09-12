package cn.jt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import cn.jt.feign.EurekaServiceFeign;
import cn.jt.feign.SidecarServiceFeign;

@RestController
public class HelloController {
	@Autowired
	private EurekaServiceFeign eurekaServiceFeign;
	@Autowired
	private SidecarServiceFeign sidecarServiceFeign;
	@GetMapping("/hello/{name}")
	@ResponseBody
	@HystrixCommand(fallbackMethod="fallbackHello")
	public String hello(@PathVariable String name){
		return eurekaServiceFeign.hello(name);
	}
	
	//写一个方法业务访问失败时调用方法，要求：参数和返回值要调用微服务一致
	public String fallbackHello(String name){
		return "alex";//设置一个默认范围值
	}
	
	//实现sidecar和nodejs封装,返回欢迎页面
	@RequestMapping("/index")
	public String node(){
		return sidecarServiceFeign.node();
	}
}

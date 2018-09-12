package cn.jt;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.feign.EnableFeignClients;


@SpringCloudApplication
@EnableFeignClients 			//开启feign
@EnableCircuitBreaker			//开启hystrix

public class RunAppHistrix {
	public static void main(String[] args) {
        SpringApplication.run(RunAppHistrix.class, args);
    }

}

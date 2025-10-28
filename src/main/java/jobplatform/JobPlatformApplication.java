package jobplatform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("comptes.mapper") 

@SpringBootApplication
public class JobPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobPlatformApplication.class, args);
    }
}

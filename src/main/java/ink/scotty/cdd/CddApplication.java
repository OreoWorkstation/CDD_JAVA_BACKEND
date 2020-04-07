package ink.scotty.cdd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "ink.scotty.cdd.mapper")
public class CddApplication {

    public static void main(String[] args) {
        SpringApplication.run(CddApplication.class, args);
    }

}

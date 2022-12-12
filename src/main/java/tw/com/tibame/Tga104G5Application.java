package tw.com.tibame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
public class Tga104G5Application {

	public static void main(String[] args) {
		SpringApplication.run(Tga104G5Application.class, args);
	}

}

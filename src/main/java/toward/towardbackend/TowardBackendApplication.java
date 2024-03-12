package toward.towardbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing // 이게 있어야 audit 기능을 한다. (감시)
public class TowardBackendApplication {

	public static void main(String[] args) {



		SpringApplication.run(TowardBackendApplication.class, args);
	}

}

package s3_gps_ivanti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import s3_gps_ivanti.repository.*;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import java.util.List;

@EnableMongoRepositories
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })

public class s3_GPS_Ivanti implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;
	@Autowired
	ApplicationRepository applicationRepository;
	@Autowired
	ReviewRepository reviewRepository;

	public static void main(String[] args) {
		SpringApplication.run(s3_GPS_Ivanti.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}


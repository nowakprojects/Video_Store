package pl.nowakprojects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@SpringBootApplication
@Configuration
public class VideoStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideoStoreApplication.class, args);
	}
}

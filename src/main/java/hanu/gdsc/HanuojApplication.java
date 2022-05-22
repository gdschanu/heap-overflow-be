package hanu.gdsc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.net.ServerSocket;

@SpringBootApplication
@EnableScheduling
public class HanuojApplication {
	public static void main(String[] args) throws IOException {
		SpringApplication.run(HanuojApplication.class, args);
	}

}

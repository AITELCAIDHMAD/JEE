package org.sid;

import java.util.Date;
import java.util.stream.Stream;

import org.sid.dao.RessourceRepository;
import org.sid.entities.Ressource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceRessourceApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(ServiceRessourceApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start(RessourceRepository ressourceRepository){
		return args->{
			Stream.of("A","B","C").forEach(r->{
				ressourceRepository.save(new Ressource(null, r, new Date(), 100+Math.random()*900, Math.random()*20));
			});
			ressourceRepository.findAll().forEach(System.out::println);			
		};
	}
}

package org.sid;

import java.util.stream.Stream;

import org.sid.dao.CompanyRepository;
import org.sid.entities.Company;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceCompanyApplication {

	/*
	 * 
	 *   aaaaaaaaaaaaaa
	 * zzzzzzzzzzzz
	 * ana smyti hamad wnta
	 * Ana arajolo lbariy hahaha
	 * 8888888888
	 * 
	 */
	public static void main(String[] args) {
		SpringApplication.run(ServiceCompanyApplication.class, args);
	}
	
	//no autowired 
	// implement CommandLine Runner
	
	@Bean
	CommandLineRunner start(CompanyRepository companyRepository){
		return args->{
			Stream.of("A","B","C").forEach(cn->{
				
					companyRepository.save(new Company(null,cn,100+Math.random()*900));
			});
				
			
				companyRepository.findAll().forEach(System.out::println);
			
			
		};
	}
}

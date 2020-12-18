package com.tuliohsa87.hotels;

import java.util.stream.LongStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.tuliohsa87.hotels.entities.Hotel;
import com.tuliohsa87.hotels.repositories.HotelRepository;

@SpringBootApplication
public class HotelsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelsApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(HotelRepository repository) {
		return args -> {
			// opcional
			repository.deleteAll();
			LongStream.range(0,  5)
			.mapToObj(i -> {
				Hotel h = new Hotel();
				h.setName_hotel("Hotel - " + i);
				h.setCapacity(50 + (i*5));
				h.setPlace("Place - " + i);
				return h;
			})
			.map(m -> repository.save(m))
			.forEach(System.out::println);
		};
	}

}

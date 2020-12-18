package com.tuliohsa87.hotels.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tuliohsa87.hotels.entities.Hotel;
import com.tuliohsa87.hotels.repositories.HotelRepository;

@RestController
@RequestMapping({"/hotels"})
public class HotelController {
	
	@Autowired
	private HotelRepository repository;
	
	/**
	 * Method used to list all registered hotels
	 * @return List
	 */
	@GetMapping
	public List findAll() {
		return repository.findAll();
	}
	
	/**
	 * Method used to search hotel by id
	 * @param id
	 * @return Hotel
	 */
	@GetMapping(value = "{id}")
	public ResponseEntity findById(@PathVariable long id) {
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	/**
	 * Method used to register hotel
	 * @param hotel
	 * @return Hotel
	 */
	@PostMapping
	public Hotel create(@RequestBody Hotel hotel) {
		return repository.save(hotel);
	}
	
	/**
	 * Method used to update hotel information
	 * @param id
	 * @param hotel
	 * @return Hotel
	 */
	@PutMapping(value = "{id}")
	public ResponseEntity update(@PathVariable long id, @RequestBody Hotel hotel) {
		return repository.findById(id)
				.map(record -> {
					record.setName_hotel(hotel.getName_hotel());
					record.setCapacity(hotel.getCapacity());
					record.setPlace(hotel.getPlace());
					Hotel update = repository.save(record);
					return ResponseEntity.ok().body(update);
				}).orElse(ResponseEntity.notFound().build());
	}
	
	/**
	 * Method used to delete a hotel
	 * @param id
	 * @return
	 */
	@DeleteMapping(path = {"/{id}"})
	public ResponseEntity<?> delete(@PathVariable long id){
		return repository.findById(id)
				.map(record ->{
					repository.deleteById(id);
					return ResponseEntity.ok().build();
				}).orElse(ResponseEntity.notFound().build());
	}

}

package com.tuliohsa87.hotels.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tuliohsa87.hotels.entities.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

}

package com.nnk.springboot.serviceTest;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.service.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RatingServiceTests {

	@Autowired
	private RatingRepository ratingRepository;

	@Autowired
	private RatingService ratingService;


	@Test
	public void ratingTest() {
		Rating rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);

		// Save
		ratingRepository.save(rating);

		assertTrue(ratingService.findById(rating.getId()).isPresent());

		// Find
		List<Rating> listResult = ratingRepository.findAll();
		assertEquals(ratingService.findAll().size(), listResult.size());

		// Delete
		Integer id = rating.getId();
		ratingRepository.delete(rating);
		Optional<Rating> ratingList = ratingRepository.findById(id);
		assertTrue(ratingList.isEmpty());
	}
}

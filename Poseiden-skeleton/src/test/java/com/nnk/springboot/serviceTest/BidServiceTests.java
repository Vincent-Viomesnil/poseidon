package com.nnk.springboot.serviceTest;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.BidListService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class BidServiceTests {

	@Autowired
	private BidListRepository bidListRepository;

	@Autowired
	private BidListService bidListService;

	@Test
	public void bidListTest() {
		BidList bid = new BidList("Account Test", "Type Test", 10d);

		// Save
		bidListRepository.save(bid);

		assertTrue(bidListService.findById(bid.getId()).isPresent());
		assertThat(bidListService.save(bid)).hasFieldOrProperty("account");

		// Find
		List<BidList> listResult = bidListRepository.findAll();
		assertEquals(bidListService.findAll().size(),  listResult.size());

		// Delete
		Integer id = bid.getId();
		bidListService.delete(bid);
		Optional<BidList> bidList = bidListRepository.findById(id);
		assertTrue(bidList.isEmpty());
	}
}

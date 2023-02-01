package com.nnk.springboot.dalTest;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class BidTests {

	@Autowired
	private BidListRepository bidListRepository;

	@Test
	public void bidListTest() {
		Timestamp bidListDate = Timestamp.from(Instant.now());
		Timestamp creationDate = Timestamp.from(Instant.now().minusMillis(1000));
		Timestamp revisionDate = Timestamp.from(Instant.now().minusMillis(8000));
		BidList bid = new BidList(1, "Account Test", "Type Test", 10d,12d,5d,2d,
				"benchmark",bidListDate, "commentary","security",
				"status", "trader", "book", "creationName", creationDate,
				"revisionName", revisionDate, "dealName", "dealType", "sourceListId",
				"side"
				);

		// Save
		bid = bidListRepository.save(bid);
		assertNotNull(bid.getBid());
		assertNotNull(bid.getBidListDate());
		assertNotNull(bid.getAsk());
		assertNotNull(bid.getSide());
		assertNotNull(bid.getAccount());
		assertNotNull(bid.getBook());
		assertNotNull(bid.getCommentary());
		assertNotNull(bid.getId());
		assertNotNull(bid.getBenchmark());
		assertNotNull(bid.getType());
		assertNotNull(bid.getStatus());
		assertNotNull(bid.getType());
		assertNotNull(bid.getDealName());

		assertEquals(bid.getBidQuantity(), 10d, 10d);

		// Update
		bid.setBidQuantity(20d);
		bid = bidListRepository.save(bid);
		assertEquals(bid.getBidQuantity(), 20d, 20d);

		// Find
		List<BidList> listResult = bidListRepository.findAll();
		assertTrue(listResult.size() > 0);

		// Delete
		Integer id = bid.getId();
		bidListRepository.delete(bid);
		Optional<BidList> bidList = bidListRepository.findById(id);
		assertFalse(bidList.isPresent());
	}
}

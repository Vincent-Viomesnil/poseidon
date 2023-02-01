package com.nnk.springboot.dalTest;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TradeTests {

	@Autowired
	private TradeRepository tradeRepository;

	@Test
	public void tradeTest() {
		Timestamp tradeDate = Timestamp.from(Instant.now());
		Timestamp creationDate = Timestamp.from(Instant.now().minusMillis(1000));
		Timestamp revisionDate = Timestamp.from(Instant.now().minusMillis(8000));
		Trade trade = new Trade(1,"Trade Account", "Type", 10d, 8d, 12d, 8d, tradeDate, "security",
				"statuts", "trader", "benchmark", "book", "creationName", creationDate,
				"revisionName", revisionDate, "dealName", "dealType", "sourceListId",
				"side");

		// Save
		tradeRepository.save(trade);
		assertNotNull(trade.getDealType());
		assertNotNull(trade.getCreationName());
		assertNotNull(trade.getCreationDate());
		assertNotNull(trade.getSide());
		assertNotNull(trade.getAccount());
		assertNotNull(trade.getBook());
		assertNotNull(trade.getTradeDate());
		assertNotNull(trade.getId());
		assertNotNull(trade.getBenchmark());
		assertNotNull(trade.getType());
		assertNotNull(trade.getStatus());
		assertNotNull(trade.getType());
		assertNotNull(trade.getDealName());
		assertTrue(trade.getAccount().equals("Trade Account"));

		// Update
		trade.setAccount("Trade Account Update");
		tradeRepository.save(trade);
		assertTrue(trade.getAccount().equals("Trade Account Update"));

		// Find
		List<Trade> listResult = tradeRepository.findAll();
		assertTrue(listResult.size() > 0);

		// Delete
		Integer id = trade.getId();
		tradeRepository.delete(trade);
		Optional<Trade> tradeList = tradeRepository.findById(id);
		assertFalse(tradeList.isPresent());
	}
}

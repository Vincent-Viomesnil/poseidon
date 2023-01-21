package com.nnk.springboot.serviceTest;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.service.TradeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TradeServiceTests {

	@Autowired
	private TradeRepository tradeRepository;

	@Autowired
	private TradeService tradeService;

	@Test
	public void tradeTest() {
		Trade trade = new Trade("Trade Account", "Type");

		// Save
		tradeRepository.save(trade);

		assertTrue(tradeService.findById(trade.getId()).isPresent());

		// Find
		List<Trade> listResult = tradeRepository.findAll();
		assertEquals(tradeService.findAll().size(), listResult.size());

		// Delete
		Integer id = trade.getId();
		tradeRepository.delete(trade);
		Optional<Trade> tradeList = tradeRepository.findById(id);
		assertTrue(tradeList.isEmpty());
	}
}

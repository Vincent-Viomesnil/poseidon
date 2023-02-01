package com.nnk.springboot.serviceTest;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.service.RuleNameService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class RuleServiceTests {

	@Autowired
	private RuleNameRepository ruleNameRepository;

	@Autowired
	private RuleNameService ruleNameService;

	@Test
	public void ruleTest() {
		RuleName rule = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");

		// Save
		ruleNameRepository.save(rule);

		assertTrue(ruleNameService.findById(rule.getId()).isPresent());
		assertThat(ruleNameService.save(rule)).hasFieldOrProperty("name");

		// Find
		List<RuleName> listResult = ruleNameRepository.findAll();
		assertEquals(ruleNameService.findAll().size(), listResult.size());

		// Delete
		Integer id = rule.getId();
		ruleNameService.delete(rule);
		Optional<RuleName> ruleList = ruleNameRepository.findById(id);
		assertTrue(ruleList.isEmpty());
	}
}

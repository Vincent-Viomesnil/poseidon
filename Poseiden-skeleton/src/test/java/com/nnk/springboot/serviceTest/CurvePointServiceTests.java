package com.nnk.springboot.serviceTest;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.service.CurveService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class CurvePointServiceTests {

	@Autowired
	private CurvePointRepository curvePointRepository;

	@Autowired
	private CurveService curveService;

	@Test
	public void curvePointTest() {
		CurvePoint curvePoint = new CurvePoint(17, 10d, 30d);
		// Save
		curvePointRepository.save(curvePoint);

		assertTrue(curveService.findById(curvePoint.getId()).isPresent());
		assertThat(curveService.save(curvePoint)).isInstanceOfAny(CurvePoint.class);
		// Find
		List<CurvePoint> listResult = curvePointRepository.findAll();
		assertEquals(curveService.findAll().size(), listResult.size());

		// Delete
		Integer id = curvePoint.getId();
		curveService.delete(curvePoint);
		Optional<CurvePoint> curvePointList = curvePointRepository.findById(id);
		assertTrue(curvePointList.isEmpty());
	}

}

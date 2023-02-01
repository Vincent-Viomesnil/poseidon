package com.nnk.springboot.dalTest;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("test")
public class CurvePointTests {

	@Autowired
	private CurvePointRepository curvePointRepository;

	@Test
	public void curvePointTest() {
		Timestamp asofDate = Timestamp.from(Instant.now());
		Timestamp creationDate = Timestamp.from(Instant.now().minusMillis(1000));
		CurvePoint curvePoint = new CurvePoint(17, asofDate, 10d, 30d, creationDate);

		// Save
		curvePointRepository.save(curvePoint);
		assertNotNull(curvePoint.getId());
		assertNotNull(curvePoint.getCurveId());
		assertNotNull(curvePoint.getValue());
		assertNotNull(curvePoint.getTerm());
		assertNotNull(curvePoint.getCreationDate());
		assertNotNull(curvePoint.getAsOfDate());
		assertTrue(curvePoint.getCurveId() == 17);

		// Update
		curvePoint.setCurveId(20);
		curvePointRepository.save(curvePoint);
		assertTrue(curvePoint.getCurveId() == 20);

		// Find
		List<CurvePoint> listResult = curvePointRepository.findAll();
		assertTrue(listResult.size() > 0);

		// Delete
		Integer id = curvePoint.getId();
		curvePointRepository.delete(curvePoint);
		Optional<CurvePoint> curvePointList = curvePointRepository.findById(id);
		assertFalse(curvePointList.isPresent());
	}

}

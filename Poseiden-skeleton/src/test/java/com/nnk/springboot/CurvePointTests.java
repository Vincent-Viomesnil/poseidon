package com.nnk.springboot;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurvePointTests {

	@Autowired
	private CurvePointRepository curvePointRepository;

	@Test
	public void curvePointTest() {
		String str="2018-09-01 09:01:15";
		Timestamp creationDate = Timestamp.valueOf(str);
		Timestamp asOfDate = Timestamp.from(Instant.now());
		Integer curveId = 111;
		CurvePoint curvePoint = new CurvePoint(10, curveId,asOfDate, 10d, 30d , creationDate);
		// Save
		curvePointRepository.save(curvePoint);

		Assert.assertNotNull(curvePoint.getId());
		Assert.assertNotNull(curvePoint.getTerm());
		Assert.assertNotNull(curvePoint.getValue());
		Assert.assertEquals(10, curvePoint.getId());
		Assert.assertEquals(111, (int) curvePoint.getCurveId());

		// Update
		curvePoint.setCurveId(20);
		curvePoint = curvePointRepository.save(curvePoint);
		Assert.assertTrue(curvePoint.getCurveId() == 20);

		// Find
		List<CurvePoint> listResult = curvePointRepository.findAll();
		Assert.assertTrue(listResult.size() > 0);

		// Delete
		Integer id = curvePoint.getId();
		curvePointRepository.delete(curvePoint);
		Optional<CurvePoint> curvePointList = curvePointRepository.findById(id);
		Assert.assertFalse(curvePointList.isPresent());
	}

}

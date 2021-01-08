package com.codechasers.aspiringminds.core.service;

import org.junit.Before;
import org.mockito.MockitoAnnotations;

public class BaseUnitTest {

	@Before
	public void baseSetUp(){
		MockitoAnnotations.initMocks(this);
	}
}

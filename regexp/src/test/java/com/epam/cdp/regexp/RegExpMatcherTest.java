package com.epam.cdp.regexp;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RegExpMatcherTest {
	private RegExpMatcher matcher;

	@Before
	public void init() {
		matcher = new RegExpMatcher();
	}

	@Test
	public void testCheckMobilePhone() {
		assertTrue(matcher.checkMobilePhone("063-745-78-98"));
	}

	@Test
	public void testCheckEmail() {
		assertTrue(matcher.checkEmail("iurii_parfeniuk@epam.com"));
	}

	@Test
	public void testCheckCreditCard() {
		assertTrue(matcher.checkCreditCard("1234-2355-7889-2536"));
	}

	@Test
	public void testChecCarNumber() {
		assertTrue(matcher.checkCarNumber("BC6734BC"));
	}

	@Test
	public void testCheckPassport() {
		assertTrue(matcher.checkPassport("AC456723"));
	}

}

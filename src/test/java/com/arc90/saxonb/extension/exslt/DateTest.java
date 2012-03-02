/**
 * 
 */
package com.arc90.saxonb.extension.exslt;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 *
 * @author Andrew Lewisohn
 */
public class DateTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link com.arc90.saxonb.extension.exslt.Date#add(java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testAdd() {
		assertEquals("2013-02-28T00:00:00", Date.add("2012-02-28T00:00:00.10-05:00", "P1Y"));
		assertEquals("2013-02-28T00:00:00", Date.add("2012-02-28T00:00:00-05:00", "P1Y"));
		assertEquals("2013-02-28", Date.add("2012-02-29", "P1Y"));
		assertEquals("2013-03-01T00:00:00", Date.add("2012-02-29T00:00:00-05:00", "P1Y1D"));
		assertEquals("2013-08-01T00:00:00", Date.add("2012-08-01T00:00:00", "P1Y"));
		assertEquals("2013-09-01", Date.add("2012-08-01", "P1Y1M"));
		assertEquals("2013-09-12", Date.add("2012-08-01", "P1Y1M11D"));
		assertEquals("2012-12", Date.add("2012-08", "P4M"));
		assertEquals("2016", Date.add("2012", "P4Y"));
	}
}

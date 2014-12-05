package util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import junit.framework.TestCase;
import util.CommandLine;

import org.junit.Test;

public class CommandLineTest_CMPT473 {
	CommandLine cl = new CommandLine("");

	@Test
	public void testGetOptionArgumentNonExisting() {
		
		assertEquals(cl.getOptionArgument("optionName", "argName"), null);
	}
	
	@Test
	public void testHasOptionNull() {
		
		assertEquals(cl.hasOption(""), false);
	}
	
	@Test
	public void testParseNullString() {
		Throwable exception = null;
		try {
			assertEquals(cl.parse(null), true);
		}
		catch (Exception e) {
			exception = e;
		}
		
		assertTrue(exception instanceof NullPointerException);
	}
}

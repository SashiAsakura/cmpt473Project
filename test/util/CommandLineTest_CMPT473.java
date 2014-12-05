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
	
	/*
	 * Test cases for parse()
	 */

	@Test
	public void testParseNullArgValToNullArgName() {
		Throwable exception = null;
		try {
			assertEquals(cl.parse(null), true);
		}
		catch (Exception e) {
			exception = e;
		}
		
		assertTrue(exception instanceof NullPointerException);
	}
	
	@Test
	public void testParseArgValToEmptyArgName() {
		String[] argVals = {"val0"};
		assertFalse(cl.parse(argVals));
	}
	
	@Test
	public void testParseArgValToNullArgName() {
		Throwable exception = null;
		try {
			cl = new CommandLine(null);
			String[] argVals = {"val0"};
			assertTrue(cl.parse(argVals));
		}
		catch (Exception e) {
			exception = e;
		}
		
		assertTrue(exception instanceof NullPointerException);
	}
	
	@Test
	public void testParseArgValToArgName() {
		cl = new CommandLine("arg0");
		String[] argVals = {"val0"};
		assertTrue(cl.parse(argVals));
	}
	
	@Test
	public void testParseMoreArgValsToArgName() {
		cl = new CommandLine("arg0");
		String[] argVals = {"val0", "val1"};
		assertFalse(cl.parse(argVals));
	}
	
	@Test
	public void testParseArgValsToMoreArgNames() {
		cl = new CommandLine("arg0 arg1");
		String[] argVals = {"val0"};
		assertFalse(cl.parse(argVals));
	}

	@Test
	public void testHasOptionNull() {
		cl = new CommandLine("[arg0]");
		String[] argVals = new Option().split("val0");
		cl.parse(argVals);
		
		assertFalse(cl.hasOption("arg0"));
	}
	
	@Test
	public void testHasOption() {
		cl = new CommandLine("[-opt1 arg0]");
		String[] argVals = new Option().split("-opt1 val0");
		cl.parse(argVals);
		
		assertTrue(cl.hasOption("opt1"));
	}
	
	/*
	 * Test cases for getOptionArgument()
	 */
	
	@Test
	public void testGetOptionArgument() {
		cl = new CommandLine("[-opt1 arg0]");
		String[] argVals = new Option().split("-opt1 val0");
		cl.parse(argVals);
		
		assertEquals(cl.getOptionArgument("opt1", "arg0"), "val0");
	}
	
	@Test
	public void testGetOptionArgumentNonExistingArg() {
		cl = new CommandLine("[-opt1 arg0]");
		String[] argVals = new Option().split("-opt1 val0");
		cl.parse(argVals);
		
		assertEquals(cl.getOptionArgument("opt1", "arg1"), null);
	}
	
}

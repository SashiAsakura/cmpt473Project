package fitnesse.util;


//Copyright (C) 2003-2009 by Object Mentor, Inc. All rights reserved.
//Released under the terms of the CPL Common Public License version 1.0.

/*
	 RFC 2045 - Multipurpose Internet Mail Extensions (MIME) Part One: Format of Internet Message Bodies
	 section 6.8.  Base64 Content-Transfer-Encoding
The encoding process represents 24-bit groups of input bits as output
strings of 4 encoded characters.  Proceeding from left to right, a
24-bit input group is formed by concatenating 3 8bit input groups.
These 24 bits are then treated as 4 concatenated 6-bit groups, each
of which is translated into a single digit in the base64 alphabet.
When encoding a bit stream via the base64 encoding, the bit stream
must be presumed to be ordered with the most-significant-bit first.
That is, the first bit in the stream will be the high-order bit in
the first 8bit byte, and the eighth bit will be the low-order bit in
the first 8bit byte, and so on.
*/

import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import fitnesse.util.Base64;
import junit.framework.*;

public class Base64Test_CMPT473 extends TestCase {
	@Test
	public void testGetValueForNormalValues() throws Exception {
		assertEquals(0, Base64.getValueFor((byte) 'A'));
		assertEquals(17, Base64.getValueFor((byte) 'R'));
		assertEquals(26, Base64.getValueFor((byte) 'a'));
		assertEquals(43, Base64.getValueFor((byte) 'r'));
		assertEquals(52, Base64.getValueFor((byte) '0'));
		assertEquals(61, Base64.getValueFor((byte) '9'));
		assertEquals(62, Base64.getValueFor((byte) '+'));
	}
	
	@Test
	public void testGetValueForAbnormalValues() throws Exception {
		Throwable exception = null;
		try {
			Base64.getValueFor((byte) '@');
		}
		catch (Exception e) {
			exception = e;
		}
		assertTrue(exception instanceof IllegalArgumentException);
		
		try {
			Base64.getValueFor((byte) '=');
		}
		catch (Exception e) {
			exception = e;
		}
		assertTrue(exception instanceof IllegalArgumentException);
		
		try {
			Base64.getValueFor((byte) '(');
		}
		catch (Exception e) {
			exception = e;
		}
		assertTrue(exception instanceof IllegalArgumentException);
	}
	
	/*
	 * Tests for encode()
	 */
	@Test
	public void testEncodeSingleChar() {
		assertEquals("TQ==", Base64.encode("M"));
	}
	
	@Test
	public void testEncodeTwoChars() {
		assertEquals("TWE=", Base64.encode("Ma"));
	}
	
	@Test
	public void testEncodeThreeChars() {
		assertEquals("TWEy", Base64.encode("Ma2"));
	}
	
	@Test
	public void testEncodeNoChar() {
		assertEquals("", Base64.encode(""));
	}
	
	@Test
	public void testEncodeWhitespace() {
		assertEquals("IA==", Base64.encode(" "));
	}
	
	@Test
	public void testEncodeTab() {
		assertEquals("L3Q=", Base64.encode("/t"));
	}
	
	@Test
	public void testEncodeLongChars() {
		assertEquals("SGVsbG8gV29ybGQgMjAxNCE=", Base64.encode("Hello World 2014!"));
	}
	
	/*
	 * Tests for decode()
	 */
	@Test
	public void testDecodeSingleChar() throws UnsupportedEncodingException {
		assertEquals("M", Base64.decode("TQ=="));
	}
	
	@Test
	public void testDecodeTwoChars() throws UnsupportedEncodingException {
		assertEquals("Ma", Base64.decode("TWE="));
	}
	
	@Test
	public void testDecodeThreeChars() throws UnsupportedEncodingException {
		assertEquals("Ma2", Base64.decode("TWEy"));
	}
	
	@Test
	public void testDecodeNoChar() throws UnsupportedEncodingException {
		assertEquals("", Base64.decode(""));
	}
	
	@Test
	public void testDecodeWhitespace() throws UnsupportedEncodingException {
		assertEquals(" ", Base64.decode("IA=="));
	}
	
	@Test
	public void testDecodeTab() throws UnsupportedEncodingException {
		assertEquals("/t", Base64.decode("L3Q="));
	}
	
	@Test
	public void testDecodeLongChars() throws UnsupportedEncodingException {
		assertEquals("Hello World 2014!", Base64.decode("SGVsbG8gV29ybGQgMjAxNCE="));
	}

/*
 * Tests below were portion of the original test cases that I didn't cover
 */

//	@Test
//	public void testEncodeNuls() throws Exception {
//	 assertEquals("AAAA", Base64.encode("\0\0\0"));
//	 assertEquals("AAA=", Base64.encode("\0\0"));
//	 assertEquals("AA==", Base64.encode("\0"));
//	}
//	
//	@Test
//	public void testEncodeBinary() throws Exception {
//	 assertEquals("////", new String(Base64.encode(new byte [] { -1,-1,-1 })));
//	 assertEquals("WqVapVql", new String(Base64.encode(new byte [] { 90,-91,90,-91,90,-91 })));
//	}
//	
//	@Test
//	public void testDecodeNuls() throws Exception {
//	 assertEquals(Base64.decode("AAAA"), "\0\0\0");
//	 assertEquals(Base64.decode("AAA="), "\0\0");
//	 assertEquals(Base64.decode("AA=="), "\0");
//	}
//	
//	@Test
//	public void testDecodeBinary() throws Exception {
//	 assertEquals(Base64.decode("////"), new String(new byte [] { -1,-1,-1 }));
//	 assertEquals(Base64.decode("WqVapVql"), new String(new byte [] { 90,-91,90,-91,90,-91 }));
//	}

}

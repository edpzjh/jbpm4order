package com.bulain.common.util;

import junit.framework.TestCase;

public class UtilTest extends TestCase {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(UtilTest.class);
	}

	public void testGetSessionKey(){
		String sessionKey = Util.getSessionKey(UtilTest.class, UtilTest.class);
		assertEquals("UtilTest_UtilTest", sessionKey);
	}
	
	public void testGetCacheKey(){
		String cacheKey = Util.getCacheKey(UtilTest.class);
		assertEquals("UtilTest", cacheKey);
	}
	
}

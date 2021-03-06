/*****************************************************************
 *   Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 ****************************************************************/
package org.apache.cayenne.exp.parser;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.testdo.testmap.Artist;
import org.apache.cayenne.testdo.testmap.Painting;
import org.junit.Test;

public class ASTEqualTest {

	@Test
	public void testEvaluate() {
		Expression equalTo = new ASTEqual(new ASTObjPath("artistName"), "abc");

		Artist match = new Artist();
		match.setArtistName("abc");
		assertTrue(equalTo.match(match));

		Artist noMatch = new Artist();
		noMatch.setArtistName("123");
		assertFalse("Failed: " + equalTo, equalTo.match(noMatch));
	}

	@Test
	public void testEvaluate_Null() {
		Expression equalToNull = new ASTEqual(new ASTObjPath("artistName"), null);
		Expression equalToNotNull = new ASTEqual(new ASTObjPath("artistName"), "abc");

		Artist match = new Artist();
		assertTrue(equalToNull.match(match));
		assertFalse(equalToNotNull.match(match));

		Artist noMatch = new Artist();
		noMatch.setArtistName("abc");
		assertFalse(equalToNull.match(noMatch));
	}

	@Test
	public void testEvaluate_BigDecimal() {
		BigDecimal bd1 = new BigDecimal("2.0");
		BigDecimal bd2 = new BigDecimal("2.0");
		BigDecimal bd3 = new BigDecimal("2.00");
		BigDecimal bd4 = new BigDecimal("2.01");

		Expression equalTo = new ASTEqual(new ASTObjPath(Painting.ESTIMATED_PRICE.getName()), bd1);

		Painting p = new Painting();
		p.setEstimatedPrice(bd2);
		assertTrue(equalTo.match(p));

		// BigDecimals must compare regardless of the number of trailing zeros
		// (see CAY-280)
		p.setEstimatedPrice(bd3);
		assertTrue(equalTo.match(p));

		p.setEstimatedPrice(bd4);
		assertFalse(equalTo.match(p));
	}
}

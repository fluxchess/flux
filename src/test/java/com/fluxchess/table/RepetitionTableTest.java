/*
** Copyright 2007-2012 Phokham Nonava
**
** This file is part of Flux Chess.
**
** Flux Chess is free software: you can redistribute it and/or modify
** it under the terms of the GNU General Public License as published by
** the Free Software Foundation, either version 3 of the License, or
** (at your option) any later version.
**
** Flux Chess is distributed in the hope that it will be useful,
** but WITHOUT ANY WARRANTY; without even the implied warranty of
** MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
** GNU General Public License for more details.
**
** You should have received a copy of the GNU General Public License
** along with Flux Chess.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.fluxchess.table;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.fluxchess.table.RepetitionTable;

/**
 * RepetitionTableTest
 *
 * @author Phokham Nonava
 */
public class RepetitionTableTest {

	@Test
	public void testTable() {
		RepetitionTable table = new RepetitionTable();

		// Put a first entry
		table.put(1);
		assertTrue(table.exists(1));
		
		// Put a secondy entry
		table.put(1);
		
		// Remove one entry
		table.remove(1);
		assertTrue(table.exists(1));
		
		// Remove the second entry
		table.remove(1);
		assertFalse(table.exists(1));
	}

}

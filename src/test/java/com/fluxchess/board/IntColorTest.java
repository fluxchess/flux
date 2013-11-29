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
package com.fluxchess.board;

import static org.junit.Assert.assertEquals;
import com.fluxchess.jcpi.models.GenericColor;

import org.junit.Test;

import com.fluxchess.board.IntColor;

/**
 * IntColorTest
 *
 * @author Phokham Nonava
 */
public class IntColorTest {

	@Test
	public void testIntColor() {
		assertEquals(IntColor.valueOfColor(GenericColor.WHITE), IntColor.WHITE);
		assertEquals(IntColor.valueOfIntColor(IntColor.WHITE), GenericColor.WHITE);
	}

}

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
package com.fluxchess.evaluation;

import static org.junit.Assert.assertEquals;
import jcpi.data.GenericChessman;

import org.junit.Test;

import com.fluxchess.board.Hex88Board;
import com.fluxchess.board.IntChessman;
import com.fluxchess.board.IntColor;
import com.fluxchess.board.IntGamePhase;
import com.fluxchess.evaluation.PositionValues;

/**
 * PositionValuesTest
 *
 * @author Phokham Nonava
 */
public class PositionValuesTest {

	@Test
	public void testGetPositionValue() {
		for (int phase : IntGamePhase.values) {
			for (GenericChessman chessman : GenericChessman.values()) {
				for (int position = 0; position < Hex88Board.BOARDSIZE; position++) {
					if ((position & 0x88) == 0) {
						assertEquals(PositionValues.getPositionValue(phase, IntChessman.valueOfChessman(chessman), IntColor.WHITE, position), PositionValues.getPositionValue(phase, IntChessman.valueOfChessman(chessman), IntColor.BLACK, 119 - position));
					} else {
						position += 7;
					}
				}
			}
		}
	}

}
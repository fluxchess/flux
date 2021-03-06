/*
 * Copyright 2007-2020 Phokham Nonava
 *
 * This file is part of Flux Chess.
 *
 * Flux Chess is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Flux Chess is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Flux Chess.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.fluxchess.flux;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HistoryTableTest {

	@Test
	void testHistoryTable() {
		HistoryTable table = new HistoryTable();

		int move1 = Move.createMove(MoveType.NORMAL, 16, 32, PieceType.PAWN, Piece.NOPIECE, Piece.NOPIECE);
		table.add(move1, 1);
		assertThat(1).isEqualTo(table.get(move1));

		int move2 = Move.createMove(MoveType.NORMAL, 16, 32, PieceType.PAWN, Piece.NOPIECE, Piece.NOPIECE);
		table.add(move2, 1);
		assertThat(2).isEqualTo(table.get(move2));
	}
}

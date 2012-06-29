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

import com.fluxchess.board.Hex88Board;
import com.fluxchess.board.IntChessman;
import com.fluxchess.board.IntColor;
import com.fluxchess.board.PositionList;
import com.fluxchess.move.MoveGenerator;

/**
 * KnightEvaluation
 *
 * @author Phokham Nonava
 */
public final class KnightEvaluation {

	private static final int EVAL_KNIGHT_MOBILITY_BASE = -4;
	private static final int EVAL_KNIGHT_MOBILITYFACTOR = 4;
	private static final int EVAL_KNIGHT_SAFETY = 10;

	private KnightEvaluation() {
	}
	
	public static int evaluateKnight(int myColor, int enemyColor, Hex88Board board) {
		assert myColor != IntColor.NOCOLOR;
		assert enemyColor != IntColor.NOCOLOR;
		assert board != null;

		// Initialize
		int total = 0;
		byte[] enemyAttackTable = AttackTableEvaluation.attackTable[enemyColor];
		PositionList myKnightList = Hex88Board.knightList[myColor];
		
		// Evaluate each knight
		for (int i = 0; i < myKnightList.size; i++) {
			int knightPosition = myKnightList.position[i];

			int allMobility = EVAL_KNIGHT_MOBILITY_BASE;

			// Evaluate mobility
			for (int delta : MoveGenerator.moveDeltaKnight) {
				int targetPosition = knightPosition + delta;
				if ((targetPosition & 0x88) == 0) {
					int target = Hex88Board.board[targetPosition];
					if (target == IntChessman.NOPIECE) {
						allMobility++;
					} else {
						if (IntChessman.getColor(target) == enemyColor) {
							allMobility++;
						}
					}
				}
			}
			
			// Evaluate mobility
			total += EVAL_KNIGHT_MOBILITYFACTOR * allMobility;

			// Evaluate safety
			if ((enemyAttackTable[knightPosition] & AttackTableEvaluation.BIT_PAWN) == 0) {
				total += EVAL_KNIGHT_SAFETY;
			}
		}

		return total;
	}
	
}
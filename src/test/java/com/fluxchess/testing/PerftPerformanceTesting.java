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
package com.fluxchess.testing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.fluxchess.jcpi.models.GenericBoard;
import com.fluxchess.jcpi.models.IllegalNotationException;

import com.fluxchess.board.Attack;
import com.fluxchess.board.Hex88Board;
import com.fluxchess.move.IntMove;
import com.fluxchess.move.MoveGenerator;
import com.fluxchess.move.MoveSee;
import com.fluxchess.table.HistoryTable;
import com.fluxchess.table.KillerTable;

/**
 * PerftPerformanceTesting
 *
 * @author Phokham Nonava
 */
public class PerftPerformanceTesting {

	private static long miniMax(Hex88Board board, MoveGenerator generator, int depth, int maxDepth) {
		if (depth == 0) {
			return 1;
		}

		Attack attack = board.getAttack(board.activeColor);
		MoveGenerator.initializeMain(attack, 0, IntMove.NOMOVE);

		long totalNodes = 0;
		int move = MoveGenerator.getNextMove();
		while (move != IntMove.NOMOVE) {
			board.makeMove(move);
			totalNodes += miniMax(board, generator, depth - 1, maxDepth);
			board.undoMove(move);

			move = MoveGenerator.getNextMove();
		}

		MoveGenerator.destroy();

		return totalNodes;
	}

	public static void main(String[] args) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			String token = reader.readLine();
			while (!token.equalsIgnoreCase("quit")) {
				try {
					Hex88Board testBoard = new Hex88Board(new GenericBoard(token));
					new MoveSee(testBoard);
					KillerTable killerTable = new KillerTable();
					HistoryTable historyTable = new HistoryTable();
					MoveGenerator generator = new MoveGenerator(testBoard, killerTable, historyTable);
					
					long startTime = System.currentTimeMillis();
					long result = miniMax(testBoard, generator, 6, 6);
					long endTime = System.currentTimeMillis();
					
					System.out.printf("Found %d nodes in %d.%d seconds.", result, (endTime - startTime) / 1000, (endTime - startTime) % 1000);
					System.out.println();
				} catch (IllegalNotationException e) {
					e.printStackTrace();
					break;
				}
				token = reader.readLine();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}

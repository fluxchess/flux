/*
** Copyright 2007-2012 Phokham Nonava
**
** This file is part of Flux Chess.
**
** Flux Chess is free software: you can redistribute it and/or modify
** it under the terms of the GNU Lesser General Public License as published by
** the Free Software Foundation, either version 3 of the License, or
** (at your option) any later version.
**
** Flux Chess is distributed in the hope that it will be useful,
** but WITHOUT ANY WARRANTY; without even the implied warranty of
** MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
** GNU Lesser General Public License for more details.
**
** You should have received a copy of the GNU Lesser General Public License
** along with Flux Chess.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.fluxchess.flux.evaluation;

import com.fluxchess.flux.board.Hex88Board;
import com.fluxchess.flux.board.IntChessman;
import com.fluxchess.flux.board.IntColor;
import com.fluxchess.flux.board.IntPosition;

/**
 * PatternEvaluation
 *
 * @author Phokham Nonava
 */
public final class PatternEvaluation {

    private PatternEvaluation() {
    }

    public static int evaluatePatterns(int myColor, Hex88Board board) {
        assert myColor != IntColor.NOCOLOR;
        assert board != null;

        // Initialize
        int total = 0;

        if (myColor == IntColor.WHITE) {
            // Trapped white bishop
            if (board.board[IntPosition.a7] == IntChessman.WHITE_BISHOP
                    && board.board[IntPosition.b6] == IntChessman.BLACK_PAWN) {
                total -= 100;
                if (board.board[IntPosition.c7] == IntChessman.BLACK_PAWN) {
                    total -= 50;
                }
            }
            if (board.board[IntPosition.b8] == IntChessman.WHITE_BISHOP
                    && board.board[IntPosition.c7] == IntChessman.BLACK_PAWN) {
                total -= 100;
            }
            if (board.board[IntPosition.h7] == IntChessman.WHITE_BISHOP
                    && board.board[IntPosition.g6] == IntChessman.BLACK_PAWN) {
                total -= 100;
                if (board.board[IntPosition.f7] == IntChessman.BLACK_PAWN) {
                    total -= 50;
                }
            }
            if (board.board[IntPosition.g8] == IntChessman.WHITE_BISHOP
                    && board.board[IntPosition.f7] == IntChessman.BLACK_PAWN) {
                total -= 100;
            }
            if (board.board[IntPosition.a6] == IntChessman.WHITE_BISHOP
                    && board.board[IntPosition.b5] == IntChessman.BLACK_PAWN) {
                total -= 50;
            }
            if (board.board[IntPosition.h6] == IntChessman.WHITE_BISHOP
                    && board.board[IntPosition.g5] == IntChessman.BLACK_PAWN) {
                total -= 50;
            }

            // Blocked center pawn
            if (board.board[IntPosition.d2] == IntChessman.WHITE_PAWN
                    && board.board[IntPosition.d3] != IntChessman.NOPIECE) {
                total -= 20;
                if (board.board[IntPosition.c1] == IntChessman.WHITE_BISHOP) {
                    total -= 30;
                }
            }
            if (board.board[IntPosition.e2] == IntChessman.WHITE_PAWN
                    && board.board[IntPosition.e3] != IntChessman.NOPIECE) {
                total -= 20;
                if (board.board[IntPosition.f1] == IntChessman.WHITE_BISHOP) {
                    total -= 30;
                }
            }

            // Blocked rook
            if ((board.board[IntPosition.c1] == IntChessman.WHITE_KING
                    || board.board[IntPosition.b1] == IntChessman.WHITE_KING)
                    && (board.board[IntPosition.a1] == IntChessman.WHITE_ROOK
                            || board.board[IntPosition.a2] == IntChessman.WHITE_ROOK
                            || board.board[IntPosition.b1] == IntChessman.WHITE_ROOK)) {
                total -= 50;
            }
            if ((board.board[IntPosition.f1] == IntChessman.WHITE_KING
                    || board.board[IntPosition.g1] == IntChessman.WHITE_KING)
                    && (board.board[IntPosition.h1] == IntChessman.WHITE_ROOK
                            || board.board[IntPosition.h2] == IntChessman.WHITE_ROOK
                            || board.board[IntPosition.g1] == IntChessman.WHITE_ROOK)) {
                total -= 50;
            }
        } else {
            assert myColor == IntColor.BLACK;

            // Trapped black bishop
            if (board.board[IntPosition.a2] == IntChessman.BLACK_BISHOP
                    && board.board[IntPosition.b3] == IntChessman.WHITE_PAWN) {
                total -= 100;
                if (board.board[IntPosition.c2] == IntChessman.WHITE_PAWN) {
                    total -= 50;
                }
            }
            if (board.board[IntPosition.b1] == IntChessman.BLACK_BISHOP
                    && board.board[IntPosition.c2] == IntChessman.WHITE_PAWN) {
                total -= 100;
            }
            if (board.board[IntPosition.h2] == IntChessman.BLACK_BISHOP
                    && board.board[IntPosition.g3] == IntChessman.WHITE_PAWN) {
                total -= 100;
                if (board.board[IntPosition.f2] == IntChessman.WHITE_PAWN) {
                    total -= 50;
                }
            }
            if (board.board[IntPosition.g1] == IntChessman.BLACK_BISHOP
                    && board.board[IntPosition.f2] == IntChessman.WHITE_PAWN) {
                total -= 100;
            }
            if (board.board[IntPosition.a3] == IntChessman.BLACK_BISHOP
                    && board.board[IntPosition.b4] == IntChessman.WHITE_PAWN) {
                total -= 50;
            }
            if (board.board[IntPosition.h3] == IntChessman.BLACK_BISHOP
                    && board.board[IntPosition.g4] == IntChessman.WHITE_PAWN) {
                total -= 50;
            }

            // Blocked center pawn
            if (board.board[IntPosition.d7] == IntChessman.BLACK_PAWN
                    && board.board[IntPosition.d6] != IntChessman.NOPIECE) {
                total -= 20;
                if (board.board[IntPosition.c8] == IntChessman.BLACK_BISHOP) {
                    total -= 30;
                }
            }
            if (board.board[IntPosition.e7] == IntChessman.BLACK_PAWN
                    && board.board[IntPosition.e6] != IntChessman.NOPIECE) {
                total -= 20;
                if (board.board[IntPosition.f8] == IntChessman.BLACK_BISHOP) {
                    total -= 30;
                }
            }

            // Blocked rook
            if ((board.board[IntPosition.c8] == IntChessman.BLACK_KING
                    || board.board[IntPosition.b8] == IntChessman.BLACK_KING)
                    && (board.board[IntPosition.a8] == IntChessman.BLACK_ROOK
                            || board.board[IntPosition.a7] == IntChessman.BLACK_ROOK
                            || board.board[IntPosition.b8] == IntChessman.BLACK_ROOK)) {
                total -= 50;
            }
            if ((board.board[IntPosition.f8] == IntChessman.BLACK_KING
                    || board.board[IntPosition.g8] == IntChessman.BLACK_KING)
                    && (board.board[IntPosition.h8] == IntChessman.BLACK_ROOK
                            || board.board[IntPosition.h7] == IntChessman.BLACK_ROOK
                            || board.board[IntPosition.g8] == IntChessman.BLACK_ROOK)) {
                total -= 50;
            }
        }

        return total;
    }

}

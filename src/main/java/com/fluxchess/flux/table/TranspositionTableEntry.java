/*
 * Copyright 2007-2013 the original author or authors.
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
package com.fluxchess.flux.table;

import com.fluxchess.flux.search.Search;
import com.fluxchess.flux.move.IntMove;
import com.fluxchess.flux.move.IntScore;

public final class TranspositionTableEntry {

  public long zobristCode = 0;
  public int age = -1;
  public int depth = -1;
  private int value = -Search.INFINITY;
  public int type = IntScore.NOSCORE;
  public int move = IntMove.NOMOVE;
  public boolean mateThreat = false;

  public TranspositionTableEntry() {
  }

  public void clear() {
    zobristCode = 0;
    age = -1;
    depth = -1;
    value = -Search.INFINITY;
    type = IntScore.NOSCORE;
    move = IntMove.NOMOVE;
    mateThreat = false;
  }

  public int getValue(int height) {
    int value = this.value;
    if (value < -Search.CHECKMATE_THRESHOLD) {
      value += height;
    } else if (value > Search.CHECKMATE_THRESHOLD) {
      value -= height;
    }

    return value;
  }

  public void setValue(int value, int height) {
    // Normalize mate values
    if (value < -Search.CHECKMATE_THRESHOLD) {
      value -= height;
    } else if (value > Search.CHECKMATE_THRESHOLD) {
      value += height;
    }
    assert value <= Search.CHECKMATE || value >= -Search.CHECKMATE;

    this.value = value;
  }

}

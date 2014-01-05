/*
 * Copyright 2007-2014 the original author or authors.
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
package com.fluxchess.flux.board;

import com.fluxchess.flux.search.Search;

/**
 * This table is used to store the Killer Moves. We use two slots to
 * differentiate primary and secondary killers.
 */
public final class KillerTable {

  private static final int MAXSIZE = Search.MAX_HEIGHT + 1;

  private final int[] primaryKiller = new int[MAXSIZE];
  private final int[] secondaryKiller = new int[MAXSIZE];

  public KillerTable() {
    for (int i = 0; i < MAXSIZE; i++) {
      primaryKiller[i] = Move.NOMOVE;
      secondaryKiller[i] = Move.NOMOVE;
    }
  }

  /**
   * Increment the killer count if the move is good.
   *
   * @param killer the new killer move.
   * @param height the height.
   */
  public void add(int killer, int height) {
    assert killer != Move.NOMOVE;
    assert height >= 0;

    // Update killers
    if (primaryKiller[height] != killer) {
      secondaryKiller[height] = primaryKiller[height];
      primaryKiller[height] = killer;
    }

    assert primaryKiller[height] == killer;
    assert secondaryKiller[height] != killer;
  }

  /**
   * Returns the primary killer move.
   *
   * @param height the height.
   * @return the primary killer move.
   */
  public int getPrimaryKiller(int height) {
    assert height >= 0;

    return primaryKiller[height];
  }

  /**
   * Returns the secondary killer move.
   *
   * @param height the depth.
   * @return the secondary killer move.
   */
  public int getSecondaryKiller(int height) {
    assert height >= 0;

    return secondaryKiller[height];
  }

}
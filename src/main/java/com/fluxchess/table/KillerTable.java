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
package com.fluxchess.table;

import com.fluxchess.Search;
import com.fluxchess.move.IntMove;

/**
 * This table is used to store the Killer Moves. We use two slots to
 * differentiate primary and secondary killers.
 * 
 * @author Phokham Nonava
 */
public final class KillerTable {

	private static final int MAXSIZE = Search.MAX_HEIGHT + 1;

	private final int[] primaryKiller = new int[MAXSIZE];
	private final int[] secondaryKiller = new int[MAXSIZE];

	/**
	 * Creates a new KillerTable.
	 */
	public KillerTable() {
		for (int i = 0; i < MAXSIZE; i++) {
			this.primaryKiller[i] = IntMove.NOMOVE;
			this.secondaryKiller[i] = IntMove.NOMOVE;
		}
	}
	
	/**
	 * Increment the killer count if the move is good.
	 * 
	 * @param killer the new killer move.
	 * @param height the height.
	 */
	public void add(int killer, int height) {
		assert killer != IntMove.NOMOVE;
		assert height >= 0;
		
		// Update killers
		if (this.primaryKiller[height] != killer) {
			this.secondaryKiller[height] = this.primaryKiller[height];
			this.primaryKiller[height] = killer;
		}
		
		assert this.primaryKiller[height] == killer;
		assert this.secondaryKiller[height] != killer;
	}
	
	/**
	 * Returns the primary killer move.
	 * 
	 * @param height the height.
	 * @return the primary killer move.
	 */
	public int getPrimaryKiller(int height) {
		assert height >= 0;

		return this.primaryKiller[height];
	}

	/**
	 * Returns the secondary killer move.
	 * @param height the depth.
	 * @return the secondary killer move.
	 */
	public int getSecondaryKiller(int height) {
		assert height >= 0;
		
		return this.secondaryKiller[height];
	}

}

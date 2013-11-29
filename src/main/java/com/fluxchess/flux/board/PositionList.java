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
package com.fluxchess.flux.board;

/**
 * PositionList
 *
 * @author Phokham Nonava
 */
public final class PositionList {

	public final int MAXSIZE;

	public final int[] position;
	public int size = 0;

	public PositionList() {
		this.MAXSIZE = 64;
		this.position = new int[MAXSIZE];
	}
	
	public PositionList(int MAXSIZE) {
		this.MAXSIZE = MAXSIZE;
		this.position = new int[MAXSIZE];
	}

}
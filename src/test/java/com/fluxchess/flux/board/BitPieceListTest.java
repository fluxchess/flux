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

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class BitPieceListTest {

  private Random random = null;
  private LinkedList<Integer> pool = null;

  @Before
  public void setUp() {
    random = new Random();
    pool = new LinkedList<Integer>();

    while (pool.size() < Long.SIZE) {
      int value = random.nextInt(Long.SIZE);
      if (!pool.contains(IntPosition.values[value])) {
        pool.add(IntPosition.values[value]);
      }
    }
  }

  @Test
  public void testX88Positions() {
    int bitposition = 0;
    for (int x88position : IntPosition.values) {
      assertEquals(bitposition, BitPieceList.toBitPosition(x88position));
      assertEquals(x88position, BitPieceList.toX88Position(bitposition));
      ++bitposition;
    }
  }

  @Test
  public void testAdd() {
    BitPieceList list = new BitPieceList();

    for (int x88position : pool) {
      list.add(x88position);
    }

    assertEquals(-1, list.list);
  }

  @Test
  public void testRemove() {
    BitPieceList list = new BitPieceList();
    list.list = -1;

    for (int x88position : pool) {
      list.remove(x88position);
    }

    assertEquals(0, list.list);
  }

}
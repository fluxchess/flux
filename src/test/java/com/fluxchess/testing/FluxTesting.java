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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.fluxchess.jcpi.protocols.IProtocolHandler;
import com.fluxchess.jcpi.AbstractEngine;
import com.fluxchess.jcpi.commands.EngineAnalyzeCommand;
import com.fluxchess.jcpi.commands.EngineDebugCommand;
import com.fluxchess.jcpi.commands.EngineInitializeRequestCommand;
import com.fluxchess.jcpi.commands.EngineNewGameCommand;
import com.fluxchess.jcpi.commands.EngineQuitCommand;
import com.fluxchess.jcpi.commands.EngineReadyRequestCommand;
import com.fluxchess.jcpi.commands.EngineStartCalculatingCommand;
import com.fluxchess.jcpi.commands.EngineStopCalculatingCommand;
import com.fluxchess.jcpi.commands.ProtocolBestMoveCommand;
import com.fluxchess.jcpi.commands.ProtocolInformationCommand;
import com.fluxchess.jcpi.commands.ProtocolInitializeAnswerCommand;
import com.fluxchess.jcpi.commands.ProtocolReadyAnswerCommand;
import com.fluxchess.jcpi.commands.IEngineCommand;
import com.fluxchess.jcpi.commands.IProtocolCommand;
import com.fluxchess.jcpi.models.GenericBoard;
import com.fluxchess.jcpi.models.GenericMove;

import com.fluxchess.Flux;

/**
 * FluxTesting
 *
 * @author Phokham Nonava
 */
public class FluxTesting implements IProtocolHandler {

	BlockingQueue<IEngineCommand> commandQueue = new LinkedBlockingQueue<IEngineCommand>();
	List<GenericMove> moveList = new ArrayList<GenericMove>();
	
	public FluxTesting() {
		this.commandQueue.add(new EngineInitializeRequestCommand());
		this.commandQueue.add(new EngineDebugCommand(false, true));
		this.commandQueue.add(new EngineReadyRequestCommand("test"));
		this.commandQueue.add(new EngineNewGameCommand());
		this.commandQueue.add(new EngineAnalyzeCommand(new GenericBoard(GenericBoard.STANDARDSETUP), this.moveList));
		EngineStartCalculatingCommand startCommand = new EngineStartCalculatingCommand();
		startCommand.setMoveTime(5000L);
		this.commandQueue.add(startCommand);
	}

	public static void main(String[] args) {
		FluxTesting testing = new FluxTesting();
		AbstractEngine engine = new Flux(testing);
		engine.run();
	}

	public IEngineCommand receive() {
		IEngineCommand command = null;
		try {
			command = this.commandQueue.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assert command != null;
		
		System.out.println(command);

		return command;
	}

	public void send(ProtocolInitializeAnswerCommand command) {
		System.out.println(command);
	}

	public void send(ProtocolReadyAnswerCommand command) {
		System.out.println(command);
	}

	public void send(ProtocolBestMoveCommand command) {
		System.out.println(command);
		if (command.bestMove != null) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.moveList.add(command.bestMove);
			this.commandQueue.add(new EngineStopCalculatingCommand());
			this.commandQueue.add(new EngineAnalyzeCommand(new GenericBoard(GenericBoard.STANDARDSETUP), this.moveList));
			EngineStartCalculatingCommand startCommand = new EngineStartCalculatingCommand();
			startCommand.setMoveTime(5000L);
			this.commandQueue.add(startCommand);
		} else {
			this.commandQueue.add(new EngineQuitCommand());
		}
	}

	public void send(ProtocolInformationCommand command) {
		System.out.println(command);
	}

	public String toString() {
		return "FluxTesting Protocol";
	}

}

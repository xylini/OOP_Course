package pl.edu.agh.iisg.to.javafx.cw3.command;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CommandRegistry {

	private ObservableList<Command> commandStack = FXCollections.observableArrayList();
	private ObservableList<Command> undoCommandStack = FXCollections.observableArrayList();

	public void executeCommand(Command command) {
		command.execute();
		commandStack.add(command);
		undoCommandStack.removeAll();
	}

	public void redo() {
		if(!undoCommandStack.isEmpty()){
			Command command = undoCommandStack.get(undoCommandStack.size()-1);
			undoCommandStack.remove(command);
			command.redo();
			commandStack.add(command);
		}
	}

	public void undo() {
		if(!commandStack.isEmpty()){
			Command command = commandStack.get(commandStack.size()-1);
			commandStack.remove(command);
			command.undo();
			undoCommandStack.add(command);
		}
	}

	public ObservableList<Command> getCommandStack() {
		return commandStack;
	}
}

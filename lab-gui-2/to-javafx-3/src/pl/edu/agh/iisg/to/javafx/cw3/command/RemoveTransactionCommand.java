package pl.edu.agh.iisg.to.javafx.cw3.command;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import pl.edu.agh.iisg.to.javafx.cw3.model.Account;
import pl.edu.agh.iisg.to.javafx.cw3.model.Transaction;

import java.util.stream.Collectors;

public class RemoveTransactionCommand implements Command {

    private ObservableList<Transaction> transactionsList;
    private Account account;
    private int rowsDeleted;

    public RemoveTransactionCommand(ObservableList<Transaction> transactionsList, Account account){
        this.transactionsList = transactionsList;
        this.account = account;
        this.rowsDeleted = transactionsList.size();
    }

    @Override
    public void execute() {
        account.getTransactions().removeAll(transactionsList);
    }

    @Override
    public void undo() {
        for(Transaction transaction : transactionsList){
            account.addTransaction(transaction);
        }
    }

    @Override
    public void redo() {
        account.getTransactions().removeAll(transactionsList);
    }

    @Override
    public String getName() {
        //return "Deleted: " + rowsDeleted + " rows.";
        return rowsDeleted + " transactions removed: \n    " +
                this.transactionsList.stream().map(transaction -> transaction.toString() + "\n    ")
                        .collect(Collectors.joining());
    }
}

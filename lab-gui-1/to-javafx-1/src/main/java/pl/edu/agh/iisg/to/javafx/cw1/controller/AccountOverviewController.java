package pl.edu.agh.iisg.to.javafx.cw1.controller;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pl.edu.agh.iisg.to.javafx.cw1.model.Account;
import pl.edu.agh.iisg.to.javafx.cw1.model.Category;
import pl.edu.agh.iisg.to.javafx.cw1.model.Transaction;

import java.math.BigInteger;
import java.time.LocalDate;

public class AccountOverviewController {

    private Account data;

    @FXML
    private TableView<Transaction> transactionsTable;

    @FXML
    private TableColumn<Transaction, LocalDate> dateColumn;

    @FXML
    private TableColumn<Transaction, String> payeeColumn;

    @FXML
    private TableColumn<Transaction, Category> categoryColumn;

    @FXML
    private TableColumn<Transaction, BigInteger> inflowColumn;

    @FXML
    private void initialize(){
        dateColumn.setCellValueFactory(dataValue -> dataValue.getValue().getDateProperty());
        payeeColumn.setCellValueFactory(dataValue -> dataValue.getValue().getPayeeProperty());
        categoryColumn.setCellValueFactory(dataValue -> dataValue.getValue().getCategoryProperty());
        inflowColumn.setCellValueFactory(dataValue -> dataValue.getValue().getInflowProperty());
    }

    @FXML
    private Button deleteButton;


    @FXML
    private void handleDeleteAction(ActionEvent event) {
        data.getTransactions().removeAll(transactionsTable.getSelectionModel().getSelectedItems());
        deleteButton.disableProperty().bind(Bindings.isEmpty(transactionsTable.getSelectionModel().getSelectedItems()));
    }

    public void setData(Account acccount) {
        this.data = acccount;
        transactionsTable.setItems(acccount.getTransactions());
    }



}

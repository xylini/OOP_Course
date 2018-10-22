package pl.edu.agh.iisg.to.javafx.cw2.presenter;

import com.sun.javafx.tools.packager.Log;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;
import pl.edu.agh.iisg.to.javafx.cw2.model.Category;
import pl.edu.agh.iisg.to.javafx.cw2.model.Transaction;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

public class TransactionEditDialogPresenter {

	private Transaction transaction;

	@FXML
	private TextField dateTextField;

	@FXML
	private TextField payeeTextField;

	@FXML
	private TextField categoryTextField;

	@FXML
	private TextField inflowTextField;
	
	private Stage dialogStage;
	
	private boolean approved;
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public void setData(Transaction transaction) {
		this.transaction = transaction;
		updateControls();
	}
	
	public boolean isApproved() {
		return approved;
	}
	
	@FXML
	private void handleOkAction(ActionEvent event) {
		if((this.approved = isInputValid())){
			updateModel();
			dialogStage.close();
		} else {
			Stage stage = new Stage();
			stage.setTitle("Value error");
			stage.show();
		}

	}
	
	@FXML
	private void handleCancelAction(ActionEvent event) {
		dialogStage.close();
	}
	
	private boolean isInputValid() {
		// TODO: implement
		return true;
	}
	
	private void updateModel() {
		String pattern = "yyyy-MM-dd";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		LocalDateStringConverter converter = new LocalDateStringConverter(formatter, formatter);
		LocalDate localDate = converter.fromString(dateTextField.getText());

		DecimalFormat decimalFormatter = new DecimalFormat();
		decimalFormatter.setParseBigDecimal(true);
		BigDecimal intToBigDecimal = BigDecimal.ZERO;
		try {
			intToBigDecimal = (BigDecimal) decimalFormatter.parse(inflowTextField.getText());
		} catch (ParseException e) {
			e.printStackTrace();
		}


		this.transaction.setDate(localDate);
		this.transaction.setPayee(this.payeeTextField.getText());
		this.transaction.setInflow(intToBigDecimal);
		this.transaction.setCategory(new Category(this.categoryTextField.getText()));
	}
	
	private void updateControls() {

		String pattern = "yyyy-MM-dd";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		LocalDateStringConverter converter = new LocalDateStringConverter(formatter, formatter);
		String date = converter.toString(transaction.getDate());

		this.dateTextField.setText(date);
		this.payeeTextField.setText(this.transaction.getPayee());
		this.categoryTextField.setText(this.transaction.getCategory().toString());
		this.inflowTextField.setText(this.transaction.getInflow().toString());
	}
}

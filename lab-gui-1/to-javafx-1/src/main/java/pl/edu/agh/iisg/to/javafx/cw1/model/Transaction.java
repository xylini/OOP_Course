package pl.edu.agh.iisg.to.javafx.cw1.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.math.BigInteger;
import java.time.LocalDate;

public class Transaction {
	
	private static final String EMPTY = "";
		
	private ObjectProperty<LocalDate> date = new SimpleObjectProperty<LocalDate>();
	private StringProperty payee = new SimpleStringProperty();
	private ObjectProperty<Category> category = new SimpleObjectProperty<Category>();
	private ObjectProperty<BigInteger> inflow = new SimpleObjectProperty<BigInteger>();

	
	private Transaction() {
		this(LocalDate.now(), EMPTY, new Category(EMPTY), BigInteger.ZERO);
	}
	
	public Transaction(LocalDate date, String payee, Category category, BigInteger inflow) {
		this.date.set(date);
		this.payee.set(payee);
		this.category.set(category);
		this.inflow.set(inflow);
	}

	public final LocalDate getDate() {
		return date.get();
	}

	public final void setDate(LocalDate date) {
		this.date.set(date);
	}
	

	public final String getPayee() {
		return payee.get();
	}
	
	public final void setPayee(String payee) {
		this.payee.set(payee);
	}

	
	public final Category getCategory() {
		return category.get();
	}
	
	
	public final void setCategory(Category category) {
		this.category.set(category);
	}


	public final BigInteger getInflow() {
		return inflow.get();
	}

	public final void setInflow(BigInteger inflow) {
		this.inflow.set(inflow);
	}
	
	public static final Transaction newTransaction() {
		return new Transaction();
	}

	public final ObjectProperty<LocalDate> getDateProperty() { return this.date; }

	public final StringProperty getPayeeProperty() {return this.payee; }

	public final ObjectProperty<Category> getCategoryProperty() { return this.category; }

	public final ObjectProperty<BigInteger> getInflowProperty() { return this.inflow; }

}

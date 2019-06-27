package snapcheck;
import java.text.*;
import java.util.Date;
/*
 * A Class for PaymentObject
 */
public class PaymentObject {
	/*
	 * paymentNumber: Number to keep track of payment
	 * amount: amount of payment
	 * date: date the payment was made
	 * 
	 */
	private int paymentNumber;
	private int amount;
	private Long date;
	
	public PaymentObject(int paymentNumber,int amounts, Long date2) {
		this.paymentNumber = paymentNumber;
		this.amount = amount;
		this.date = date2;
	}
	/*
	 * Getter for getting paymentNumber or ID
	 */
	public int getPaymentNumber() {
		return(this.paymentNumber);
	}
	
	/*
	 * Getter for getting amount
	 */
	public int getAmount() {
		return(this.amount);
	}
	/*
	 * Getter for getting Date
	 */
	public Long getDate() {
		return(this.date);
	}
}

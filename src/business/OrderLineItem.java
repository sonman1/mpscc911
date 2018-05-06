package business;

import java.io.Serializable;
import java.text.NumberFormat;

public class OrderLineItem implements Serializable {

	private String item;
	private double cost;
	private int quantity;
	
	private Product product;

	public OrderLineItem() {
		// TODO Auto-generated constructor stub
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Gets total of this line item
	 * 
	 * @return
	 */
	public double getTotal() {
		double total = cost * quantity;
		return total;
	} // end getTotal

	/**
	 * Gets total of this line item in currency format
	 * 
	 * @return
	 */
	public String getTotalCurrencyFormat() {
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		return currency.format(this.getTotal());
	} // end getTotalCurrencyFormat
}

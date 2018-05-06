package business;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * OrderCart class.
 *
 * @version 1.0 2018-02-17
 * @author Sonny Saxton
 */
public class OrderCart implements Serializable {

	private String item;
	private int quantity;
	private double cost;

	private Product product;
	
	private ArrayList<OrderLineItem> items;

	public OrderCart() {
		items = new ArrayList<OrderLineItem>();
	}

	public ArrayList<OrderLineItem> getItems() {
		return items;
	}

	public void setItems(ArrayList<OrderLineItem> items) {
		this.items = items;
	}

	public String getItem() {
		return item;
	}

	public int getCount() {
		return items.size();
	}

	public void setItem(String item) {
		this.item = item;
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

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	/**
	 * Gets the total of all cart items
	 * 
	 * @return
	 */
	public double getTotal() {
		double total = 0;
		for (int i = 0; i < items.size(); i++) {
			OrderLineItem OrderLineItem = items.get(i);
			total = total + OrderLineItem.getTotal();
		}
		return total;
	} // end getTotal

	/**
	 * Gets the total of all cart items in currency format
	 * 
	 * @return
	 */
	public String getTotalCurrencyFormat() {
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		return currency.format(this.getTotal());
	} // end getTotalCurrencyFormat

	/**
	 * Adds a lineItem to the cart
	 * 
	 * @param item
	 */
	public void addItem(OrderLineItem item) {
		int quantity = item.getQuantity();
		for (int i = 0; i < items.size(); i++) {
			OrderLineItem OrderLineItem = items.get(i);
			OrderLineItem.setQuantity(quantity);

			System.out.println("Adding lineItem to cart:");
			System.out.println("\tlineItem.getItem(): " + OrderLineItem.getItem());
			System.out.println("\tlineItem.getQuantity(): " + OrderLineItem.getQuantity());
			System.out.println("\tlineItem.getCost(): " + OrderLineItem.getCost());
			System.out.println("\tlineItem.getTotal(): " + OrderLineItem.getTotal());
			System.out.println("\tlineItem.getTotalCurrencyFormat(): " + OrderLineItem.getTotalCurrencyFormat());
		}

		items.add(item);
	} // end addItem

	/**
	 * Removes an item from the cart
	 * 
	 * @param item
	 */
	public void removeItem(String item) {
		for (int i = 0; i < items.size(); i++) {
			OrderLineItem OrderLineItem = items.get(i);

			if (OrderLineItem.getItem().equals(item)) {
				System.out.println("\tRemoving lineItem from cart:");
				System.out.println("\tlineItem.getItem(): " + OrderLineItem.getItem());
				items.remove(i);
			}
		}
	} // end removeItem

	/**
	 * Checks if the cart contains an item.
	 * 
	 * @param item
	 * @return true if it contains the item
	 */
	public boolean containsItem(String item) {
		for (int i = 0; i < items.size(); i++) {
			OrderLineItem OrderLineItem = items.get(i);

			if (OrderLineItem.getItem().equals(item)) {
				System.out.println("Searched for and found lineItem in cart:" + item);
				System.out.println("\tlineItem.getItem(): " + OrderLineItem.getItem());
				return true;
			}
		}

		System.out.println("Returning: False");
		return false;
	} // end containsItem

} // end of OrderCart
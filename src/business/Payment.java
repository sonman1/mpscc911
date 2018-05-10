package business;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "payments")
public class Payment implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
	private User user;
        
	private double paymentTotal;
	private Date paymentDatetime;
	private String paymentStatus;
	
	//  no arg constructor
	public Payment() {
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public double getPaymentTotal() {
		return paymentTotal;
	}
	public void setPaymentTotal(double paymentTotal) {
		this.paymentTotal = paymentTotal;
	}
	public Date getPaymentDatetime() {
		return paymentDatetime;
	}
	public void setPaymentDatetime(Date paymentDatetime) {
		this.paymentDatetime = paymentDatetime;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	
	
}

package data;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import business.Payment;
import business.User;;

public class PaymentDB {

	/**
	 * Inserts a payment into the DB
	 * 
	 * @param payment
	 */
	public static void insert(Payment payment) {
		Session session = DBUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try {
			trans = session.beginTransaction();
			session.save(payment);
			System.out.println("Object saved to DB.");
			trans.commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			if (trans != null) {
				trans.rollback();
			}
		} finally {
			session.close();
		}
	}

	/**
	 * Updates a payment into the DB
	 * 
	 * @param payment
	 */
	public static void update(Payment payment) {
		Session session = DBUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try {
			trans = session.beginTransaction();
			session.merge(payment);
			System.out.println("Object updated to DB.");
			trans.commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			if (trans != null) {
				trans.rollback();
			}
		} finally {
			session.close();
		}
	}

	/**
	 * Deletes a payment from the DB
	 * 
	 * @param payment
	 */
	public static void delete(Payment payment) {
		Session session = DBUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try {
			trans = session.beginTransaction();
			session.remove(session.merge(payment));
			System.out.println("Object deleted from DB.");
			trans.commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			if (trans != null) {
				trans.rollback();
			}
		} finally {
			session.close();
		}

	}

	/**
	 * List of payment for a user
	 * 
	 * @param user
	 * @return
	 */
	public static List<Payment> selectAllPaymentsByUsername(User user) {
		Session session = DBUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try {
			trans = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Payment> query = builder.createQuery(Payment.class);
			Root<Payment> root = query.from(Payment.class);
			System.out.println("Query payments for user: " + user);
			query.select(root).where(builder.equal(root.get("user"), user));
			Query<Payment> q = session.createQuery(query);
			List<Payment> payments = q.getResultList();

			System.out.println("Checking if payment exist for user: " + user);
			if (payments != null) {
				for (Payment payment : payments) {
					System.out.println("\tPayment: " + payment);
				}
				return payments;
			} else {
				System.out.println("No payments found for user: " + user);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
			if (trans != null) {
				trans.rollback();
				return null;
			}
		} finally {
			session.close();
		}

		return null;
	}

	// List of all line items in DB
	public static List<Payment> selectAllPayments() {
		Session session = DBUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try {
			trans = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Payment> query = builder.createQuery(Payment.class);
			Root<Payment> root = query.from(Payment.class);
			query.select(root);
			Query<Payment> q = session.createQuery(query);
			List<Payment> payments = q.getResultList();
			if (payments != null) {
				for (Payment payment : payments) {
					System.out.println("\tPayment: " + payment);
				}
			} else {
				System.out.println("No payments found");
			}
			return payments;
		} catch (HibernateException he) {
			he.printStackTrace();
			if (trans != null) {
				trans.rollback();
				return null;
			}
		} finally {
			session.close();
		}

		return null;
	}

	/**
	 * Checks if payments exists yet for a user
	 * 
	 * @param user
	 * @return
	 */
	public static boolean paymentsExistforUsername(User user) {
		Session session = DBUtil.getSessionFactory().openSession();
		Transaction trans = null;
		List<Payment> payment = null;

		try {
			trans = session.beginTransaction();
			System.out.println("Checking payments for matching users in DB.");
			payment = selectAllPaymentsByUsername(user);
			System.out.println("Payments for matching users: " + payment);
		} catch (HibernateException he) {
			he.printStackTrace();
			if (trans != null) {
				trans.rollback();
				return false;
			}
		} finally {
			session.close();
		}

		return payment.isEmpty() != true;
	}
}

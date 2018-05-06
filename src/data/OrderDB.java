package data;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import business.Order;
import business.User;;

public class OrderDB {

	/**
	 * Inserts a single line item into the DB
	 * 
	 * @param orderLineItem
	 */
	public static void insert(Order orderLineItem) {
		Session session = DBUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try {
			trans = session.beginTransaction();
			session.save(orderLineItem);
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
	 * Updates a single line item into the DB
	 * 
	 * @param orderLineItem
	 */
	public static void update(Order orderLineItem) {
		Session session = DBUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try {
			trans = session.beginTransaction();
			session.merge(orderLineItem);
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
	 * Deletes a single line item into the DB
	 * 
	 * @param orderLineItem
	 */
	public static void delete(Order orderLineItem) {
		Session session = DBUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try {
			trans = session.beginTransaction();
			session.remove(session.merge(orderLineItem));
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
	 * Deletes all line items for a user
	 * 
	 * @param user
	 */
	public static void deleteByUser(User user) {
		Session session = DBUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try {
			if (lineItemsExistforUsername(user) == true) {
				trans = session.beginTransaction();
				CriteriaBuilder builder = session.getCriteriaBuilder();
				CriteriaDelete<Order> query = builder.createCriteriaDelete(Order.class);
				Root<Order> root = query.from(Order.class);
				query.where(builder.equal(root.get("user"), user));
				int result = session.createQuery(query).executeUpdate();
				System.out.println("Multiple line items matching user deleted from DB.");
				trans.commit();
			} else {
				System.out.println("No line items matching user were found in DB, nothing to delete.");
			}
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
	 * List of all line items for a user
	 * 
	 * @param user
	 * @return
	 */
	public static List<Order> selectAllLineItemsByUsername(User user) {
		Session session = DBUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try {
			trans = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Order> query = builder.createQuery(Order.class);
			Root<Order> root = query.from(Order.class);
			System.out.println("Query line items for user: " + user);
			query.select(root).where(builder.equal(root.get("user"), user));
			Query<Order> q = session.createQuery(query);
			List<Order> orders = q.getResultList();

			System.out.println("Checking if line items exist for user: " + user);
			if (orders != null) {
				for (Order order : orders) {
					System.out.println("\tLine item: " + order.getProduct() + ", quantity: "
							+ order.getQuantity() + ", cost_each: " + order.getCostEach());
				}
				return orders;
			} else {
				System.out.println("No line items found for user: " + user);
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
	public static List<Order> selectAllLineItems() {
		Session session = DBUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try {
			trans = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Order> query = builder.createQuery(Order.class);
			Root<Order> root = query.from(Order.class);
			query.select(root);
			Query<Order> q = session.createQuery(query);
			List<Order> orders = q.getResultList();
			if (orders != null) {
				for (Order order : orders) {
					System.out.println("\tLine item: " + order.getProduct() + ", quantity: "
							+ order.getQuantity() + ", cost_each: " + order.getCostEach());
				}
			} else {
				System.out.println("No line items found");
			}
			return orders;
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
	 * Checks if line items exists yet for a user
	 * 
	 * @param user
	 * @return
	 */
	public static boolean lineItemsExistforUsername(User user) {
		Session session = DBUtil.getSessionFactory().openSession();
		Transaction trans = null;
		List<Order> order = null;

		try {
			trans = session.beginTransaction();
			System.out.println("Checking orders for matching users in DB.");
			order = selectAllLineItemsByUsername(user);
			System.out.println("Orders for matching users: " + order);
		} catch (HibernateException he) {
			he.printStackTrace();
			if (trans != null) {
				trans.rollback();
				return false;
			}
		} finally {
			session.close();
		}

		return order.isEmpty() != true;
	}
}

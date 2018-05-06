package data;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import business.Product;

public class ProductDB {

	/**
	 * Inserts a product into DB
	 * 
	 * @param product
	 */
	public static void insert(Product product) {
		Session session = DBUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try {
			trans = session.beginTransaction();
			session.save(product);
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
	 * Updates a product into DB
	 * 
	 * @param product
	 */
	public static void update(Product product) {
		Session session = DBUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try {
			trans = session.beginTransaction();
			session.merge(product);
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
	 * Deletes a product from DB
	 * 
	 * @param product
	 */
	public static void delete(Product product) {
		Session session = DBUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try {
			trans = session.beginTransaction();
			session.remove(session.merge(product));
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
	 * Selects a product from DB using product name
	 * 
	 * @param productName
	 * @return
	 */
	public static Product selectProduct(String productName) {
		Session session = DBUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try {
			trans = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Product> query = builder.createQuery(Product.class);
			Root<Product> root = query.from(Product.class);
			query.select(root).where(builder.equal(root.get("product"), productName));
			;
			Query<Product> q = session.createQuery(query);
			Product product = q.getSingleResult();
			System.out.println("Object found in DB, returning object.");
			trans.commit();
			return product;
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
	 * List all products from DB
	 * 
	 * @return
	 */
	public static List<Product> selectAllProducts() {
		Session session = DBUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try {
			trans = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Product> query = builder.createQuery(Product.class);
			Root<Product> root = query.from(Product.class);
			query.select(root);
			Query<Product> q = session.createQuery(query);
			List<Product> products = q.getResultList();
			for (Product product : products) {
				System.out.println(product.getName());
			}
			return products;
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
	 * Checks if product name already exists
	 * 
	 * @param productName
	 * @return
	 */
	public static boolean productExists(String productName) {
		Product u = selectProduct(productName);
		return u != null;
	}
}

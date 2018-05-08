package data;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import business.User;

public class UserDB {

	/**
	 * Insert user into DB
	 * 
	 * @param user
	 */
	public static void insert(User user) {
		Session session = DBUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try {
			trans = session.beginTransaction();
			System.out.println("Saving object to DB.");
			session.save(user);
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
	 * Update user in DB
	 * 
	 * @param user
	 */
	public static void update(User user) {
		Session session = DBUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try {
			trans = session.beginTransaction();
			System.out.println("Merging object to DB.");
			session.merge(user);
			System.out.println("Object merged to DB.");
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
	 * Delete user from DB
	 * 
	 * @param user
	 */
	public static void delete(User user) {
		Session session = DBUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try {
			trans = session.beginTransaction();
			System.out.println("Deleting object from DB.");
			session.remove(session.merge(user));
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
	 * Select user by username in DB
	 * 
	 * @param username
	 * @return user
	 */
	public static User selectUsername(String username) {
		Session session = DBUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try {
			trans = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<User> query = builder.createQuery(User.class);
			Root<User> root = query.from(User.class);
			query.select(root).where(builder.equal(root.get("username"), username));
			;
			Query<User> q = session.createQuery(query);
			System.out.println("Searching for object in DB.");
			User user = q.setMaxResults(1).uniqueResult();
			System.out.println("Object found in DB, returning object.");
			trans.commit();
			return user;
		} catch (NoResultException nre) {
			System.out.println("Object NOT found in DB");
			return null;
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
	 * List of all users in DB
	 * 
	 * @return
	 */
	public static List<User> selectAllUsers() {
		Session session = DBUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try {
			trans = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<User> query = builder.createQuery(User.class);
			Root<User> root = query.from(User.class);
			query.select(root);
			Query<User> q = session.createQuery(query);
			List<User> users = q.getResultList();
			for (User user : users) {
				System.out.println(user.getUsername());
			}
			return users;
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
	 * checks if username exists in DB
	 * 
	 * @param username
	 * @return
	 */
	public static boolean usernameExists(String username) {
		System.out.println("Checking db for username: " + username);
		User u = selectUsername(username);
		return u != null;
	}

	/**
	 * Makes a call to the DB to select a specific user by username and password.
	 * Prints out the result set and returns the user object
	 * 
	 * @param username
	 * @param password
	 */
	public static User selectUserByUseridandPW(String username, String password) {

		User user = new User();

		Session session = DBUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try {
			trans = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<User> query = builder.createQuery(User.class);
			Root<User> root = query.from(User.class);
			query.select(root).where(builder.equal(root.get("username"), username))
					.where(builder.equal(root.get("password"), password));
			;
			Query<User> q = session.createQuery(query);
			System.out.println("Searching for object in DB.");
			
			User user1 = null;
			user1 = q.setMaxResults(1).uniqueResult();

			System.out.println("Checking if object found in DB");

			if (user1 != null) {
				// printing out values for testing purposes
				System.out.println();
				System.out.print("USERNAME \t");
				System.out.print("FIRSTNAME \t");
				System.out.print("LASTNAME \t");
				System.out.print("EMAIL \t\t\t");
				System.out.print("USER_TYPE \t\t");
				System.out.print("PHONE \t\t");
				System.out.print("ADDRESS_BILLING \t\t");
				System.out.print("ADDRESS_SHIPPING \t\t");
				System.out.println("\n------------------------------------------------------"
						+ "------------------------------------------------------"
						+ "------------------------------------------------------");

				System.out.println(user1.getUsername() + "\t\t" + user1.getFirstName() + "\t\t" + user1.getLastName()
						+ "\t" + user1.getEmail() + "\t" + user1.getUserType() + "\t" + user1.getPhone() + "\t"
						+ user1.getAddressBilling() + "\t" + user1.getAddressShipping());

				return user1;
			} else {
				System.out.println("User not found. Invalid user/password combination for: " + username);
				return null;
			}

		} catch (NoResultException nre) {
			System.out.println("User not found. Invalid user/password combination for: " + username);
			return null;
		} catch (HibernateException he) {
			he.printStackTrace();
			if (trans != null) {
				trans.rollback();
				return null;
			}
		} finally {
			session.close();
		}

		return user;
	} // end selectUserByUseridandPW
}

package com.luv2code.springdemo.dao;

import com.luv2code.springdemo.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Locale;

@Repository // Just like @Controller but for
            // DAO implementations, extends @Component (DAO - Data Access Object)
public class CustomerDAOImpl implements CustomerDAO {

    // need to inject hibernate session factory
    private final SessionFactory sessionFactory;

    @Autowired
    public CustomerDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    // @Transactional // This let method handle the transaction
    public List<Customer> getCustomers() {

        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // create a query ... sort by first name
        Query<Customer> theQuery =
                currentSession.createQuery("from Customer order by firstName", Customer.class);

        // return the results
        return theQuery.getResultList();
    }

    @Override
    public void saveCustomer(Customer theCustomer) {
        // get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // save the customer
        currentSession.saveOrUpdate(theCustomer);
    }

    @Override
    public Customer getCustomer(int theId) {
        Session currSession = sessionFactory.getCurrentSession();
        return currSession.get(Customer.class, theId);
    }

    @Override
    public void deleteCustomer(int theId) {
        Session currSession = sessionFactory.getCurrentSession();
        Query theQuery =
                currSession.createQuery("delete from Customer where id=:customerId");
        theQuery.setParameter("customerId", theId);

        theQuery.executeUpdate();
    }

    @Override
    public List<Customer> searchCustomers(String theName) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<Customer> query = null;

        if(theName != null && theName.trim().length() > 0) {
            query = currentSession.createQuery(
                    "from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
            query.setParameter("theName", "%" + theName.toLowerCase(Locale.ROOT) + "%");
        }
        else {
            // when search is empty get all customers
            query = currentSession.createQuery("from Customer", Customer.class);
        }

        return query.getResultList();
    }

    @Override
    public List<Customer> sortByFirstName() {

        Session currentSession = sessionFactory.getCurrentSession();

        Query<Customer> query = currentSession.createQuery("from Customer order by firstName", Customer.class);

        return query.getResultList();
    }

    @Override
    public List<Customer> sortByLastName() {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<Customer> query = currentSession.createQuery("from Customer order by lastName", Customer.class);

        return query.getResultList();
    }

    @Override
    public List<Customer> sortByEmail() {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<Customer> query = currentSession.createQuery("from Customer order by email", Customer.class);

        return query.getResultList();
    }
}

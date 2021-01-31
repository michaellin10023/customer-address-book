package com.example.mycrudapp.dao;

import com.example.mycrudapp.entity.Customer;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO{

    private EntityManager entityManager;

    @Autowired
    public CustomerDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Customer> findAll() {

        Session session = entityManager.unwrap(Session.class);
        Query<Customer> query = session.createQuery("from Customer", Customer.class);
        List<Customer> customers = query.getResultList();

        return customers;
    }

    @Override
    public Customer findById(int id) {

        Session session = entityManager.unwrap(Session.class);
        Customer customer = session.get(Customer.class, id);

        return customer;
    }

    @Override
    public void save(Customer customer) {

        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(customer);

        return;
    }

    @Override
    public void deleteById(int id) {

        Session session = entityManager.unwrap(Session.class);
        Query<Customer> query = session.createQuery("delete from Customer where id=:customerId");
        query.setParameter("customerId", id);
        query.executeUpdate();
        return;
    }

    @Override
    public List<Customer> findByKeyword(String keyword) {

        Session session = entityManager.unwrap(Session.class);
        Query<Customer> query = session.createQuery("from Customer where concat(name,address,email) like concat('%', :keyword, '%')");
        query.setParameter("keyword", keyword);
        List<Customer> customers = query.getResultList();

        return customers;
    }
}

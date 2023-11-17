package com.onex.element;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class Main {
  public static void main(String[] args) {
    SessionFactory sessionFactory =
        new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    Session session = sessionFactory.openSession();

    Account account = session.get(Account.class, 1);

    System.out.println("Найденный аккаунт с ID 1: " + account);

    Account newAccount = new Account();
    newAccount.setBalance(1000);
    newAccount.setFullName("Jack Green");

    session.beginTransaction();
    session.save(newAccount);
    session.getTransaction().commit();

    // Запрос с Criteria
    CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
    CriteriaQuery<Account> criteriaQuery = criteriaBuilder.createQuery(Account.class);
    Root<Account> root = criteriaQuery.from(Account.class);
    criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("balance"), 600));
    List<Account> accounts = session.createQuery(criteriaQuery).getResultList();
    for (Account acc : accounts) {
      System.out.println("Найденный аккаунт c балансом 600: " + account);
    }

    // Запрос с HQL
    Query<Account> query =
        session.createQuery("FROM Account WHERE balance = :balanceValue", Account.class);
    query.setParameter("balanceValue", 100);
    accounts = query.getResultList();
    for (Account acc : accounts) {
      System.out.println("Найденный аккаунт с балансом 100: " + account);
    }
  }
}

package com.onex.element;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
  public static void main(String[] args) {
    SessionFactory sessionFactory =
        new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    Session session = sessionFactory.openSession();

    Account account = session.get(Account.class, 1);

    System.out.println("Найденный аккаунт: " + account);
  }
}

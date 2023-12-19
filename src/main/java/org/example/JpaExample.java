package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Пример использования JPA (Hibernate) для работы с базой данных H2.
 */
public class JpaExample {

  /**
   * Точка входа в программу
   *
   * @param args
   */
  public static void main(String[] args) {
    // Создаем EntityManagerFactory
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");

    // Создаем EntityManager
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    // Начинаем транзакцию
    entityManager.getTransaction().begin();

    // 2.1 Описываем сущность Book
    Book book1 = new Book("Book1", "Author1");
    Book book2 = new Book("Book2", "Author2");
    Book book3 = new Book("Book3", "Author1");
    Book book4 = new Book("Book4", "Author2");
    Book book5 = new Book("Book5", "Author1");
    Book book6 = new Book("Book6", "Author2");
    Book book7 = new Book("Book7", "Author1");
    Book book8 = new Book("Book8", "Author2");
    Book book9 = new Book("Book9", "Author1");
    Book book10 = new Book("Book10", "Author2");

    // 2.2 Создаем Session и сохраняем в таблицу 10 книг
    entityManager.persist(book1);
    entityManager.persist(book2);
    entityManager.persist(book3);
    entityManager.persist(book4);
    entityManager.persist(book5);
    entityManager.persist(book6);
    entityManager.persist(book7);
    entityManager.persist(book8);
    entityManager.persist(book9);
    entityManager.persist(book10);

    // Фиксируем транзакцию
    entityManager.getTransaction().commit();

    // 2.3 Выгружаем список книг какого-то автора
    List<Book> books = entityManager.createQuery("SELECT b FROM Book b WHERE b.author = :author", Book.class)
        .setParameter("author", "Author1")
        .getResultList();

    // Выводим результат
    for (Book book : books) {
      System.out.println("Book: " + book);
    }

    // Закрываем EntityManager и EntityManagerFactory
    entityManager.close();
    entityManagerFactory.close();
  }
}

package org.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Класс, представляющий сущность "Книга" для взаимодействия с базой данных.
 */
@Entity
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;   // Название книги
  private String author; // Автор книги

  /**
   * Конструктор по умолчанию, необходим для работы Hibernate.
   */
  public Book() {
  }

  /**
   * Конструктор для создания экземпляра книги с указанным названием и автором.
   *
   * @param name   Название книги.
   * @param author Автор книги.
   */
  public Book(String name, String author) {
    this.name = name;
    this.author = author;
  }

  // Геттеры и сеттеры

  /**
   * Получить идентификатор книги.
   *
   * @return Идентификатор книги.
   */
  public Long getId() {
    return id;
  }

  /**
   * Получить название книги.
   *
   * @return Название книги.
   */
  public String getName() {
    return name;
  }

  /**
   * Установить название книги.
   *
   * @param name Название книги.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Получить автора книги.
   *
   * @return Автор книги.
   */
  public String getAuthor() {
    return author;
  }

  /**
   * Установить автора книги.
   *
   * @param author Автор книги
   */
  public void setAuthor(String author) {
    this.author = author;
  }

  /**
   * Переопределенный метод toString для удобного вывода информации о книге.
   *
   * @return Строковое представление объекта Book.
   */
  @Override
  public String toString() {
    return "Book{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", author='" + author + '\'' +
        '}';
  }
}

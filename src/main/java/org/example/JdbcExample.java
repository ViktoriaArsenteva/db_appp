package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Пример использования JDBC для работы с базой данных H2.
 */
public class JdbcExample {

  /**
   * Точка входа в программу
   *
   * @param args
   */
  public static void main(String[] args) {
    // URL-адрес JDBC, имя пользователя и пароль для базы данных H2 в памяти
    String url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    String user = "sa";
    String password = "";

    try {
      // Установка соединения с базой данных
      Connection connection = DriverManager.getConnection(url, user, password);

      // Создание таблицы book
      String createTableSQL = "CREATE TABLE book (id bigint PRIMARY KEY, name varchar(255), author varchar(255))";
      try (PreparedStatement preparedStatement = connection.prepareStatement(createTableSQL)) {
        preparedStatement.executeUpdate();
      }

      // Добавление 10 книг
      String insertSQL = "INSERT INTO book (id, name, author) VALUES (?, ?, ?)";
      try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
        for (int i = 1; i <= 10; i++) {
          preparedStatement.setLong(1, i);
          preparedStatement.setString(2, "Book " + i);
          preparedStatement.setString(3, "Author " + i);
          preparedStatement.executeUpdate();
        }
      }

      // Выполнение запроса на выборку
      String selectSQL = "SELECT * FROM book WHERE author = ?";
      try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
        preparedStatement.setString(1, "Author 5");

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
          while (resultSet.next()) {
            System.out.println("Book: id=" + resultSet.getLong("id") +
                ", name=" + resultSet.getString("name") +
                ", author=" + resultSet.getString("author"));
          }
        }
      }

      // Закрытие соединения
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}

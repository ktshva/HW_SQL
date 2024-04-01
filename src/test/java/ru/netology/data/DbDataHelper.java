package ru.netology.data;

import java.sql.DriverManager;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.dbutils.QueryRunner;

public class DbDataHelper {
    @SneakyThrows
    public static String getCode() {
        var runner = new QueryRunner();
        var codeSQL = "SELECT code FROM auth_codes ORDER BY created DESC LIMIT 1";

        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );

        ) {
            // обычная вставка
            var code = runner.query(conn, codeSQL, new ScalarHandler<>());
            System.out.println(code);
            return (String) code;
        }
    }

    @SneakyThrows
    public static void clearTables() {
        var runner = new QueryRunner();
        var deleteUsers = "DELETE FROM users;";
        var deleteCards = "DELETE FROM cards;";
        var deleteCodes = "DELETE FROM auth_codes;";

        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
        ) {
            runner.update(conn, deleteCards);
            runner.update(conn, deleteCodes);
            runner.update(conn, deleteUsers);
        }

    }
}
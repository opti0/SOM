package som.server.database.student;

import som.server.database.Dao;
import som.server.database.DatabaseData;
import som.server.database.ObjectGenerator;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentDao implements Dao<Student, String> {
    private final StudentParser studentParser = new StudentParser();

    @Override
    public Optional<Student> get(String key) {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            var result = statement.executeQuery(studentParser.createGetQuery(key));
            var generator = new ObjectGenerator<>(result, studentParser);
            var student = generator.getNext();

            statement.close();
            connection.close();

            return student;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<Student> getAll() {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            var result = statement.executeQuery(studentParser.createGetAllQuery());
            var generator = new ObjectGenerator<>(result, studentParser);

            List<Student> students = new ArrayList<>();

            while (generator.hasNext()) {
               generator.getNext().ifPresent(students::add);
            }

            statement.close();
            connection.close();

            return students;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    @Override
    public void save(Student student) {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            statement.executeUpdate(studentParser.createInsertQuery(student));

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Student student, String[] params) {

    }

    @Override
    public void delete(Student student) {

    }
}

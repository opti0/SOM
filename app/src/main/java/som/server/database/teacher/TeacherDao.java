package som.server.database.teacher;

import som.server.database.Dao;
import som.server.database.DatabaseData;
import som.server.database.ObjectGenerator;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeacherDao implements Dao<Teacher, String> {
    private final TeacherParser teacherParser = new TeacherParser();

    @Override
    public Optional<Teacher> get(String key) {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            var result = statement.executeQuery(teacherParser.createGetQuery(key));
            var generator = new ObjectGenerator<>(result, teacherParser);
            var teacher = generator.getNext();

            statement.close();
            connection.close();

            return teacher;
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Teacher> getAll() {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            var result = statement.executeQuery(teacherParser.createGetAllQuery());
            var generator = new ObjectGenerator<>(result, teacherParser);

            List<Teacher> teachers = new ArrayList<>();

            while (generator.hasNext()) {
                generator.getNext().ifPresent(teachers::add);
            }

            statement.close();
            connection.close();

            return teachers;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public void save(Teacher teacher) {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            statement.executeUpdate(teacherParser.createInsertQuery(teacher));

            statement.close();
            connection.close();
        } catch (SQLException ignored) {

        }
    }

    @Override
    public void update(Teacher teacher, String[] params) {

    }

    @Override
    public void delete(Teacher teacher) {

    }
}

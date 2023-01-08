package som.server.database.school_subject;

import som.server.database.Dao;
import som.server.database.DatabaseData;
import som.server.database.ObjectGenerator;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SchoolSubjectDao implements Dao<SchoolSubject, String> {
    private final SchoolSubjectParser schoolSubjectParser = new SchoolSubjectParser();

    @Override
    public Optional<SchoolSubject> get(String key) {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            var result = statement.executeQuery(schoolSubjectParser.createGetQuery(key));
            var generator = new ObjectGenerator<>(result, schoolSubjectParser);
            var schoolSubject = generator.getNext();

            statement.close();
            connection.close();

            return schoolSubject;
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<SchoolSubject> getAll() {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            var result = statement.executeQuery(schoolSubjectParser.createGetAllQuery());
            var generator = new ObjectGenerator<>(result, schoolSubjectParser);

            List<SchoolSubject> schoolSubjects = new ArrayList<>();

            while (generator.hasNext()) {
                generator.getNext().ifPresent(schoolSubjects::add);
            }

            statement.close();
            connection.close();

            return schoolSubjects;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public void save(SchoolSubject subject) {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            statement.executeUpdate(schoolSubjectParser.createInsertQuery(subject));

            statement.close();
            connection.close();
        } catch (SQLException ignored) {

        }
    }

    @Override
    public void update(SchoolSubject subject, String[] params) {

    }

    @Override
    public void delete(SchoolSubject subject) {

    }
}

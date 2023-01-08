package som.server.database.students_exam;

import som.server.database.Dao;
import som.server.database.DatabaseData;
import som.server.database.ObjectGenerator;
import som.server.database.student.Student;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentsExamDao implements Dao<StudentsExam, String> {
    private final StudentsExamParser studentsExamParserser = new StudentsExamParser();

    @Override
    public Optional<StudentsExam> get(String key) {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            var result = statement.executeQuery(studentsExamParserser.createGetQuery(key));
            var generator = new ObjectGenerator<>(result, studentsExamParserser);
            var studentsExam = generator.getNext();

            statement.close();
            connection.close();

            return studentsExam;
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<StudentsExam> getAll() {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            var result = statement.executeQuery(studentsExamParserser.createGetAllQuery());
            var generator = new ObjectGenerator<>(result, studentsExamParserser);

            List<StudentsExam> studentsExams = new ArrayList<>();

            while (generator.hasNext()) {
                generator.getNext().ifPresent(studentsExams::add);
            }

            statement.close();
            connection.close();

            return studentsExams;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public void save(StudentsExam studentsExam) {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            statement.executeUpdate(studentsExamParserser.createInsertQuery(studentsExam));

            statement.close();
            connection.close();
        } catch (SQLException ignored) {

        }
    }

    @Override
    public void update(StudentsExam studentsExam, String[] params) {

    }

    @Override
    public void delete(StudentsExam studentsExam) {

    }
}

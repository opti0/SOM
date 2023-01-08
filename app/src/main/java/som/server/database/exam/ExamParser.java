package som.server.database.exam;

import som.server.database.SqlParser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ExamParser implements SqlParser<Exam, String> {
    public String createInsertQuery(Exam exam) {
        return "INSERT INTO Egzamin VALUES ('" +
                "null, " +
                exam.subject() + "', '" +
                exam.level() +"');";
    }

    @Override
    public String createGetQuery(String key) {
        return String.format("SELECT * FROM Egzamin WHERE ID_Egzaminu=\"%s\";", key);
    }

    @Override
    public String createGetAllQuery() {
        return "SELECT * FROM Egzamin;";
    }

    @Override
    public String createSaveQuery(Exam exam) {
        return "INSERT INTO Egzamin VALUES ('" +
                "null, " +
                exam.subject() + "', '" +
                exam.level() +"');";
    }

    @Override
    public String createUpdateQuery(Exam exam, String[] params) {
        return null;
    }

    @Override
    public String createDeleteQuery(Exam exam) {
        return null;
    }

    @Override
    public Optional<Exam> makeFrom(ResultSet result) {
        try {
            return Optional.of(new Exam(
                    result.getInt("ID_Egzaminu"),
                    result.getInt("Przedmiot"),
                    result.getInt("Poziom")
            ));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }
}

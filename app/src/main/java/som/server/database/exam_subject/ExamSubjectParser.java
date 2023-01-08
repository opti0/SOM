package som.server.database.exam_subject;

import som.server.database.SqlParser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ExamSubjectParser implements SqlParser<ExamSubject, String> {
    public String createInsertQuery(ExamSubject subject) {
        return "INSERT INTO Przedmiot_Maturalny VALUES ('" +
                "null" + "', '" +
                subject.subject() +"');";
    }

    @Override
    public String createGetQuery(String key) {
        return String.format("SELECT * FROM Przedmiot_Maturalny WHERE ID_PM=\"%s\";", key);
    }

    @Override
    public String createGetAllQuery() {
        return "SELECT * FROM Przedmiot_Maturalny;";
    }

    @Override
    public String createSaveQuery(ExamSubject subject) {
        return "INSERT INTO Przedmiot_Maturalny VALUES ('" +
                "null" + "', '" +
                subject.subject() +"');";
    }

    @Override
    public String createUpdateQuery(ExamSubject subject, String[] params) {
        return null;
    }

    @Override
    public String createDeleteQuery(ExamSubject subject) {
        return null;
    }

    @Override
    public Optional<ExamSubject> makeFrom(ResultSet result) {
        try {
            return Optional.of(new ExamSubject(
                    result.getInt("ID_PM"),
                    result.getString("Przedmiot")
            ));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }
}

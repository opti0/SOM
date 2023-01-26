package som.server.database.examSubject;

import som.server.database.SqlParser;
import som.server.database.utils.Utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ExamSubjectParser implements SqlParser<ExamSubject, Integer> {
    @Override
    public String createGetQuery(Integer key) {
        return String.format("SELECT * FROM Przedmiot_Maturalny WHERE ID_PM=\"%s\";", key);
    }

    @Override
    public String createGetAllQuery() {
        return "SELECT * FROM Przedmiot_Maturalny;";
    }

    @Override
    public String createSaveQuery(ExamSubject subject) {
        return "INSERT INTO Przedmiot_Maturalny VALUES (null, " +
                Utils.toSqlQuery(subject.subject()) +");";
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
    public String createDeleteAllQuery() {
        return "truncate Przedmiot_Maturalny;";
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

package som.server.database.school_subject;

import som.server.database.SqlParser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class SchoolSubjectParser implements SqlParser<SchoolSubject, String> {
    public String createInsertQuery(SchoolSubject subject) {
        return "INSERT INTO Przedmiot_Szkolny VALUES ('" +
                "null" + "', '" +
                subject.subject() +"');";
    }

    @Override
    public String createGetQuery(String key) {
        return String.format("SELECT * FROM Przedmiot_Szkolny WHERE ID_PS=\"%s\";", key);
    }

    @Override
    public String createGetAllQuery() {
        return "SELECT * FROM Przedmiot_Szkolny;";
    }

    @Override
    public String createSaveQuery(SchoolSubject subject) {
        return "INSERT INTO Przedmiot_Szkolny VALUES ('" +
                "null" + "', '" +
                subject.subject() +"');";
    }

    @Override
    public String createUpdateQuery(SchoolSubject subject, String[] params) {
        return null;
    }

    @Override
    public String createDeleteQuery(SchoolSubject subject) {
        return null;
    }

    @Override
    public Optional<SchoolSubject> makeFrom(ResultSet result) {
        try {
            return Optional.of(new SchoolSubject(
                    result.getInt("ID_PS"),
                    result.getString("Przedmiot")
            ));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }
}

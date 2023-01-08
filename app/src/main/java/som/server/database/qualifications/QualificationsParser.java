package som.server.database.qualifications;

import som.server.database.SqlParser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class QualificationsParser implements SqlParser<Qualifications, String> {
    public String createInsertQuery(Qualifications qualifications) {
        return "INSERT INTO Kwalifikacje VALUES ('" +
                "null" + "', '" +
                qualifications.qualificationsId() + "', '" +
                qualifications.teacher() + "', '" +
                qualifications.subject() +"');";
    }

    @Override
    public String createGetQuery(String key) {
        return String.format("SELECT * FROM Kwalifikacje WHERE ID_KD=\"%s\";", key);
    }

    @Override
    public String createGetAllQuery() {
        return "SELECT * FROM Kwalifikacje;";
    }

    @Override
    public String createSaveQuery(Qualifications qualifications) {
        return "INSERT INTO Kwalifikacje VALUES ('" +
                "null" + "', '" +
                qualifications.qualificationsId() + "', '" +
                qualifications.teacher() + "', '" +
                qualifications.subject() +"');";
    }

    @Override
    public String createUpdateQuery(Qualifications qualifications, String[] params) {
        return null;
    }

    @Override
    public String createDeleteQuery(Qualifications qualifications) {
        return null;
    }

    @Override
    public Optional<Qualifications> makeFrom(ResultSet result) {
        try {
            return Optional.of(new Qualifications(
                    result.getInt("ID_KD"),
                    result.getInt("Nauczyciel"),
                    result.getInt("Przedmiot")
            ));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }
}

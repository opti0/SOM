package som.server.database.jury;

import som.server.database.SqlParser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class JuryParser implements SqlParser<Jury, String> {
    public String createInsertQuery(Jury jury) {
        return "INSERT INTO Komisja_Egzaminacyjna VALUES ('" +
                "null" + "', '" +
                jury.juryPresident() +"');";
    }

    @Override
    public String createGetQuery(String key) {
        return String.format("SELECT * FROM Komisja_Egzaminacyjna WHERE ID_KE=\"%s\";", key);
    }

    @Override
    public String createGetAllQuery() {
        return "SELECT * FROM Komisja_Egzaminacyjna;";
    }

    @Override
    public String createSaveQuery(Jury jury) {
        return "INSERT INTO Komisja_Egzaminacyjna VALUES ('" +
                "null" + "', '" +
                jury.juryPresident() +"');";
    }

    @Override
    public String createUpdateQuery(Jury jury, String[] params) {
        return null;
    }

    @Override
    public String createDeleteQuery(Jury jury) {
        return null;
    }

    @Override
    public Optional<Jury> makeFrom(ResultSet result) {
        try {
            return Optional.of(new Jury(
                    result.getInt("ID_KE"),
                    result.getInt("Przewodniczacy")
            ));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }
}

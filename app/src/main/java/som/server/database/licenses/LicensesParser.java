package som.server.database.licenses;

import som.server.database.SqlParser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class LicensesParser implements SqlParser<Licenses, Integer> {

    @Override
    public String createGetQuery(Integer key) {
        return String.format("SELECT * FROM Uprawnienia WHERE ID_Uprawnienia=\"%s\";", key);
    }

    @Override
    public String createGetAllQuery() {
        return "SELECT * FROM Uprawnienia;";
    }

    @Override
    public String createSaveQuery(Licenses licenses) {
        return "INSERT INTO Uprawnienia VALUES (null, "+
                licenses.teacher() + ", " +
                licenses.licenses() +");";
    }

    @Override
    public String createUpdateQuery(Licenses licenses, String[] params) {
        return null;
    }

    @Override
    public String createDeleteQuery(Licenses licenses) {
        return null;
    }

    @Override
    public Optional<Licenses> makeFrom(ResultSet result) {
        try {
            return Optional.of(new Licenses(
                    result.getInt("ID_Uprawnienia"),
                    result.getInt("Nauczyciel"),
                    result.getInt("Uprawnienia")
            ));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }
}

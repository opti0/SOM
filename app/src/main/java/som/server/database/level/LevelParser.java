package som.server.database.level;

import som.server.database.SqlParser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class LevelParser implements SqlParser<Level, Integer> {
    @Override
    public String createGetQuery(Integer key) {
        return String.format("SELECT * FROM Poziom WHERE ID_Poziomu=\"%s\";", key);
    }

    @Override
    public String createGetAllQuery() {
        return "SELECT * FROM Poziom;";
    }

    @Override
    public String createSaveQuery(Level level) {
        return "INSERT INTO Poziom VALUES (null, " +
                level.name() +");";
    }

    @Override
    public String createUpdateQuery(Level level, String[] params) {
        return null;
    }

    @Override
    public String createDeleteQuery(Level level) {
        return null;
    }

    @Override
    public String createDeleteAllQuery() {
        return "truncate Poziom;";
    }

    @Override
    public Optional<Level> makeFrom(ResultSet result) {
        try {
            return Optional.of(new Level(
                    result.getInt("ID_Poziomu"),
                    result.getString("Nazwa")
            ));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }
}

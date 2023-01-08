package som.server.database.jury_members;

import som.server.database.SqlParser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class JuryMembersParser implements SqlParser<JuryMembers, String> {
    public String createInsertQuery(JuryMembers juryMembers) {
        return "INSERT INTO Czlonkowie_Komisji VALUES ('" +
                "null" + "', '" +
                juryMembers.teacher() + "', '" +
                juryMembers.jury() +"');";
    }

    @Override
    public String createGetQuery(String key) {
        return String.format("SELECT * FROM Czlonkowie_Komisji WHERE ID_CK=\"%s\";", key);
    }

    @Override
    public String createGetAllQuery() {
        return "SELECT * FROM Czlonkowie_Komisji;";
    }

    @Override
    public String createSaveQuery(JuryMembers juryMembers) {
        return "INSERT INTO Czlonkowie_Komisji VALUES ('" +
                "null" + "', '" +
                juryMembers.teacher() + "', '" +
                juryMembers.jury() +"');";
    }

    @Override
    public String createUpdateQuery(JuryMembers juryMembers, String[] params) {
        return null;
    }

    @Override
    public String createDeleteQuery(JuryMembers juryMembers) {
        return null;
    }

    @Override
    public Optional<JuryMembers> makeFrom(ResultSet result) {
        try {
            return Optional.of(new JuryMembers(
                    result.getInt("ID_CK"),
                    result.getInt("Nauczyciel"),
                    result.getInt("Komisja")
            ));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }
}

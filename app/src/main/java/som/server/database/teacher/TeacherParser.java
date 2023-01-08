package som.server.database.teacher;

import som.server.database.SqlParser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class TeacherParser implements SqlParser<Teacher, String> {
    public String createInsertQuery(Teacher teacher) {
        return "INSERT INTO Nauczyciel VALUES ('" +
                "null" + "', '" +
                teacher.name() + "', '" +
                teacher.surname() + "', '" +
                teacher.fromOtherSchool() + "', '" +
                teacher.phoneNumber() +"');";
    }

    @Override
    public String createGetQuery(String key) {
        return String.format("SELECT * FROM Nauczyciel WHERE ID_Nauczyciela=\"%s\";", key);
    }

    @Override
    public String createGetAllQuery() {
        return "SELECT * FROM Nauczyciel;";
    }

    @Override
    public String createSaveQuery(Teacher teacher) {
        return "INSERT INTO Nauczyciel VALUES ('" +
                "null" + "', '" +
                teacher.name() + "', '" +
                teacher.surname() + "', '" +
                teacher.fromOtherSchool() + "', '" +
                teacher.phoneNumber() +"');";
    }

    @Override
    public String createUpdateQuery(Teacher teacher, String[] params) {
        return null;
    }

    @Override
    public String createDeleteQuery(Teacher teacher) {
        return null;
    }

    @Override
    public Optional<Teacher> makeFrom(ResultSet result) {
        try {
            return Optional.of(new Teacher(
                    result.getInt("ID_Nauczyciela"),
                    result.getString("Imie"),
                    result.getString("Nazwisko"),
                    result.getByte("Czy_Z_Zewnatrz"),
                    result.getString("Nr_Telefonu")
            ));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }
}

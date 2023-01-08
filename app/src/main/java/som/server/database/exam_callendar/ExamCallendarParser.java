package som.server.database.exam_callendar;

import som.server.database.SqlParser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ExamCallendarParser implements SqlParser<ExamCallendar, String> {
    public String createInsertQuery(ExamCallendar examCallendar) {
        return "INSERT INTO Terminarz_Egzaminow VALUES ('" +
                "null" + "', '" +
                examCallendar.room() + "', '" +
                examCallendar.exam() + "', '" +
                examCallendar.jury() + "', '" +
                examCallendar.date() +"');";
    }

    @Override
    public String createGetQuery(String key) {
        return String.format("SELECT * FROM Terminarz_Egzaminow WHERE ID_Terminu=\"%s\";", key);
    }

    @Override
    public String createGetAllQuery() {
        return "SELECT * FROM Terminarz_Egzaminow;";
    }

    @Override
    public String createSaveQuery(ExamCallendar examCallendar) {
        return "INSERT INTO Terminarz_Egzaminow VALUES ('" +
                "null" + "', '" +
                examCallendar.room() + "', '" +
                examCallendar.exam() + "', '" +
                examCallendar.jury() + "', '" +
                examCallendar.date() +"');";
    }

    @Override
    public String createUpdateQuery(ExamCallendar examCallendar, String[] params) {
        return null;
    }

    @Override
    public String createDeleteQuery(ExamCallendar examCallendar) {
        return null;
    }

    @Override
    public Optional<ExamCallendar> makeFrom(ResultSet result) {
        try {
            return Optional.of(new ExamCallendar(
                    result.getInt("ID_Terminu"),
                    result.getInt("Sala"),
                    result.getInt("Egzamin"),
                    result.getInt("Komisja"),
                    result.getDate("Termin")
            ));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }
}

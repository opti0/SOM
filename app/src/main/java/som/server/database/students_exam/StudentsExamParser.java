package som.server.database.students_exam;

import som.server.database.SqlParser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class StudentsExamParser implements SqlParser<StudentsExam, String> {
    public String createInsertQuery(StudentsExam studentsExam) {
        return "INSERT INTO Ucznio_Egzamin VALUES ('" +
                "null" + "', '" +
                studentsExam.dateId() + "', '" +
                studentsExam.pesel() +"');";
    }

    @Override
    public String createGetQuery(String key) {
        return String.format("SELECT * FROM Uczen WHERE ID_UE=\"%s\";", key);
    }

    @Override
    public String createGetAllQuery() {
        return "SELECT * FROM Uczen;";
    }

    @Override
    public String createSaveQuery(StudentsExam studentsExam) {
        return "INSERT INTO Ucznio_Egzamin VALUES ('" +
                "null" + "', '" +
                studentsExam.dateId() + "', '" +
                studentsExam.pesel() +"');";
    }

    @Override
    public String createUpdateQuery(StudentsExam studentsExam, String[] params) {
        return null;
    }

    @Override
    public String createDeleteQuery(StudentsExam studentsExam) {
        return null;
    }

    @Override
    public Optional<StudentsExam> makeFrom(ResultSet result) {
        try {
            return Optional.of(new StudentsExam(
                    result.getInt("ID_UE"),
                    result.getInt("ID_Terminu"),
                    result.getString("PESEL")
            ));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }
}

package som.server.database.subjectKnowledge;

import som.server.database.SqlParser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class SubjectKnowledgeParser implements SqlParser<SubjectKnowledge, Integer> {
    @Override
    public String createGetQuery(Integer key) {
        return String.format("SELECT * FROM Znajomosc_Przedmiotu_Maturalnego WHERE ID_ZPM=\"%s\";", key);
    }

    @Override
    public String createGetAllQuery() {
        return "SELECT * FROM Znajomosc_Przedmiotu_Maturalnego;";
    }

    @Override
    public String createSaveQuery(SubjectKnowledge subjectKnowledge) {
        return "INSERT INTO Znajomosc_Przedmiotu_Maturalnego VALUES (null, " +
                subjectKnowledge.schoolSubject() + ", " +
                subjectKnowledge.examSubject() +");";
    }

    @Override
    public String createUpdateQuery(SubjectKnowledge subjectKnowledge, String[] params) {
        return null;
    }

    @Override
    public String createDeleteQuery(SubjectKnowledge subjectKnowledge) {
        return null;
    }

    @Override
    public Optional<SubjectKnowledge> makeFrom(ResultSet result) {
        try {
            return Optional.of(new SubjectKnowledge(
                    result.getInt("ID_ZPM"),
                    result.getInt("Przedmiot_Szkolny"),
                    result.getInt("Przedmiot_Maturalny")
            ));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }
}

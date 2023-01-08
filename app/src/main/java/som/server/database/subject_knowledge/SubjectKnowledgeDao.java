package som.server.database.subject_knowledge;

import som.server.database.Dao;
import som.server.database.DatabaseData;
import som.server.database.ObjectGenerator;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubjectKnowledgeDao implements Dao<SubjectKnowledge, String> {
    private final SubjectKnowledgeParser subjectKnowledgeParser = new SubjectKnowledgeParser();

    @Override
    public Optional<SubjectKnowledge> get(String key) {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            var result = statement.executeQuery(subjectKnowledgeParser.createGetQuery(key));
            var generator = new ObjectGenerator<>(result, subjectKnowledgeParser);
            var subjectKnowledge = generator.getNext();

            statement.close();
            connection.close();

            return subjectKnowledge;
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<SubjectKnowledge> getAll() {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            var result = statement.executeQuery(subjectKnowledgeParser.createGetAllQuery());
            var generator = new ObjectGenerator<>(result, subjectKnowledgeParser);

            List<SubjectKnowledge> subjectKnowledges = new ArrayList<>();

            while (generator.hasNext()) {
                generator.getNext().ifPresent(subjectKnowledges::add);
            }

            statement.close();
            connection.close();

            return subjectKnowledges;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public void save(SubjectKnowledge subjectKnowledge) {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            statement.executeUpdate(subjectKnowledgeParser.createInsertQuery(subjectKnowledge));

            statement.close();
            connection.close();
        } catch (SQLException ignored) {

        }
    }

    @Override
    public void update(SubjectKnowledge subjectKnowledge, String[] params) {

    }

    @Override
    public void delete(SubjectKnowledge subjectKnowledge) {

    }
}

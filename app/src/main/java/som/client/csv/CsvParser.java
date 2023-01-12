package som.client.csv;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import static java.util.stream.Collectors.groupingBy;

public class CsvParser {
    public List<StudentData> parseFromFile(File file) {
        try {
            var reader = new CSVReaderBuilder(new FileReader(file))
                    .withCSVParser(
                            new CSVParserBuilder()
                                    .withSeparator(';')
                                    .build()
                    ).build();
            return parseFromLines(reader.readAll());
        } catch (IOException | CsvException e) {
            return new ArrayList<>();
        }
    }

    public List<StudentData> parseFromLines(List<String[]> lines) {
        return squashStudents(getStudents(lines));
    }

    private List<StudentData> getStudents(List<String[]> lines) {
        var students = new ArrayList<StudentData>();
        for (var line : lines) {
            getStudentFromLine(line).ifPresent(students::add);
        }
        return students;
    }

    private Optional<StudentData> getStudentFromLine(String[] line) {
        var examData = getExamData(line);
        if (examData.isEmpty()) {
            return Optional.empty();
        }
        return getStudentData(line, examData.get());
    }

    private Optional<ExamData> getExamData(String[] line) {
        if (line.length <= Configuration.ADJUSTMENTS_OFFSET) {
            return Optional.empty();
        }
        var exam = line[Configuration.EXAM_OFFSET].split("poziom");
        if (exam.length != 2) {
            return Optional.empty();
        }
        return Optional.of(new ExamData(
                toSqlStr(exam[0].trim()),
                toSqlStr(exam[1].trim()),
                !line[Configuration.ADJUSTMENTS_OFFSET].isEmpty()
        ));
    }

    private Optional<StudentData> getStudentData(String[] line, ExamData examData) {
        if (line.length <= Configuration.ADJUSTMENTS_OFFSET) {
            return Optional.empty();
        }

        return Optional.of(new StudentData(
                toSqlStr(line[Configuration.DIVISION_OFFSET]),
                toSqlStr(line[Configuration.REGISTER_NUMBER_OFFSET]),
                toSqlStr(line[Configuration.SURNAME_OFFSET]),
                toSqlStr(line[Configuration.NAMES_OFFSET]),
                toSqlStr(line[Configuration.PESEL_OFFSET]),
                new ArrayList<>(Collections.singleton(examData)),
                toSqlStr(line[Configuration.PHONE_NUMBER_OFFSET]),
                toSqlStr(line[Configuration.EMAIL_OFFSET])
        ));
    }

    private List<StudentData> squashStudents(List<StudentData> students) {
        var squashed = new ArrayList<StudentData>();
        students.stream()
                .sorted(Comparator.comparing(StudentData::pesel))
                .collect(groupingBy(StudentData::pesel))
                .values()
                .forEach(sameStudents -> squashed.add(mergeStudents(sameStudents)));

        return squashed;
    }

    private StudentData mergeStudents(List<StudentData> sameStudents) {
        var it = sameStudents.iterator();
        var student = it.next();

        while (it.hasNext()) {
            student.exams().addAll(it.next().exams());
        }

        return student;
    }

    private String toSqlStr(String str) {
        return str.isEmpty() ? "" : str;
    }

    private static class Configuration {
        public static final int DIVISION_OFFSET = 0;
        public static final int REGISTER_NUMBER_OFFSET = 1;
        public static final int SURNAME_OFFSET = 2;
        public static final int NAMES_OFFSET = 3;
        public static final int PESEL_OFFSET = 4;
        public static final int EXAM_OFFSET = 5;
        public static final int PHONE_NUMBER_OFFSET = 10;
        public static final int EMAIL_OFFSET = 11;
        public static final int ADJUSTMENTS_OFFSET = 17;
    }
}

package som.client.csv;

import som.server.database.student.Student;

import java.util.List;

public record StudentData(String division,
                          String registerNumber,
                          String surname,
                          String names,
                          String pesel,
                          List<ExamData> exams,
                          String phoneNumber,
                          String email) {
    public Student toStudent() {
        return new Student(
                pesel,
                names,
                surname,
                requireAdjustments(),
                makeStudentCode(),
                phoneNumber,
                email);
    }

    private String makeStudentCode() {
        return division + (registerNumber.length() < 2 ? "0" + registerNumber : registerNumber);
    }

    private byte requireAdjustments() {
        return (byte) (exams.stream().anyMatch(ExamData::requireAdjustments) ? 1 : 0);
    }
}

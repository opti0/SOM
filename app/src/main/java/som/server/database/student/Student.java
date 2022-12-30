package som.server.database.student;

public record Student(String pesel,
                      String name,
                      String surname,
                      byte requireAdjustments,
                      String studentCode,
                      String phoneNumber,
                      String email) {
}

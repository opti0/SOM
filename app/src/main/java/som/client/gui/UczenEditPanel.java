package som.client.gui;

import javax.swing.*;
import java.awt.*;

public class UczenEditPanel extends JPanel {

    JLabel peselL = new JLabel("PESEL");
    JLabel nameL = new JLabel("Imie");
    JLabel surnameL = new JLabel("Nazwisko");
    JLabel requireAdjustmentsL = new JLabel("Dostosowania    ");
    JLabel studentCodeL = new JLabel("Kod ucznia");
    JLabel phoneNumberL = new JLabel("Nr telefonu");
    JLabel emailL = new JLabel("Email");

    JTextField pesel = new JTextField("");
    JTextField name = new JTextField("");
    JTextField surname = new JTextField("");
    JCheckBox requireAdjustments = new JCheckBox();
    JTextField studentCode = new JTextField("");
    JTextField phoneNumber = new JTextField("");
    JTextField email = new JTextField("");

    public UczenEditPanel(){
        setLayout(new GridLayout(7, 2));
        addComponents();
    }

    public void addComponents(){
        add(peselL);
        add(pesel);
        add(nameL);
        add(name);
        add(surnameL);
        add(surname);
        add(requireAdjustmentsL);
        add(requireAdjustments);
        add(studentCodeL);
        add(studentCode);
        add(phoneNumberL);
        add(phoneNumber);
        add(emailL);
        add(email);
    }

    public String getPeselVal(){
        return pesel.getText();
    }

    public String getNameVal(){
        return name.getText();
    }

    public String getSurnameVal(){
        return surname.getText();
    }

    public byte getAdjustmentsVal(){
        return (byte) (requireAdjustments.isSelected() ? 1 : 0 );
    }

    public String getStudentCodeVal(){
        return studentCode.getText();
    }

    public String getPhoneNumberVal(){
        return phoneNumber.getText();
    }

    public String getEmailVal(){
        return email.getText();
    }
}

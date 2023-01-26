package som.client.gui;

import javax.swing.*;
import java.awt.*;

public class NauczycielEditPanel extends JPanel {
    JLabel nameL = new JLabel("Imie");
    JLabel surnameL = new JLabel("Nazwisko");
    JLabel FOSL = new JLabel("Czy z innej szkoly    ");
    JLabel phoneNumberL = new JLabel("Nr telefonu");
    JTextField name = new JTextField();
    JTextField surname = new JTextField();
    JCheckBox FOS = new JCheckBox();
    JTextField phoneNumber = new JTextField();

    public NauczycielEditPanel(){
        setLayout(new GridLayout(5, 2));
        addComponents();
    }

    public void addComponents(){
        add(nameL);
        add(name);
        add(surnameL);
        add(surname);
        add(FOSL);
        add(FOS);
        add(phoneNumberL);
        add(phoneNumber);
    }

    public String getNameVal(){
        return name.getText();
    }

    public String getSurnameVal(){
        return surname.getText();
    }

    public byte getFOSVal(){
        return (byte) (FOS.isSelected() ? 1 : 0 );
    }

    public String getPhoneNumberVal(){
        return phoneNumber.getText();
    }
}

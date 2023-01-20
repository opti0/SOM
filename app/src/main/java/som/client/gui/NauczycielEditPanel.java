package som.client.gui;

import javax.swing.*;
import java.awt.*;

public class NauczycielEditPanel extends JPanel {

    JLabel IDL = new JLabel("ID");
    JLabel nameL = new JLabel("Imie");
    JLabel surnameL = new JLabel("Nazwisko");
    JLabel FOSL = new JLabel("Czy z innej szkoly    ");
    JLabel phoneNumberL = new JLabel("Nr telefonu");

    JTextField ID = new JTextField();
    JTextField name = new JTextField();
    JTextField surname = new JTextField();
    JCheckBox FOS = new JCheckBox();
    JTextField phoneNumber = new JTextField();

    public NauczycielEditPanel(){
        setLayout(new GridLayout(5, 2));
        addComponents();
    }

    public void addComponents(){
        add(IDL);
        add(ID);
        add(nameL);
        add(name);
        add(surnameL);
        add(surname);
        add(FOSL);
        add(FOS);
        add(phoneNumberL);
        add(phoneNumber);
    }

    public int getIDVal(){
        return Integer.parseInt(ID.getText());
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

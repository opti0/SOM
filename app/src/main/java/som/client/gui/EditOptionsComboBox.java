package som.client.gui;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class EditOptionsComboBox extends JComboBox<String> {

    public List<String> editOptions = new LinkedList<>();

    public EditOptionsComboBox(){
        editOptions.add("Uczen");
        editOptions.add("Nauczyciel");
        editOptions.add("Egzamin");

        for(String option : editOptions){
            addItem(option);
        }
    }
}

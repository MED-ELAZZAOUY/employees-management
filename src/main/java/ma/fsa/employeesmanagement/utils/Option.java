package ma.fsa.employeesmanagement.utils;

public class Option {
    private String id;
    private String displayText;

    public Option(String id, String displayText) {
        this.id = id;
        this.displayText = displayText;
    }

    public String getId() {
        return id;
    }
    @Override
    public String toString() {
        return displayText; // This will be used to display the text in the ComboBox
    }
}
package Heroes3_GUI.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Fractions {
    public enum FractionNames {
        Inferno,
        Haven,
        Necropolis
    }

    private FractionNames fractionName;

    public Fractions(FractionNames fractionName) {
        this.fractionName = fractionName;
    }

    public FractionNames getName() {
        return this.fractionName;
    }

    public void setName(FractionNames fractionName) {
        this.fractionName = fractionName;
    }

    @Override
    public String toString() {
        return fractionName.name();
    }
}

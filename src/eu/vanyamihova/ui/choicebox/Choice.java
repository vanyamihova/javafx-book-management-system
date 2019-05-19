package eu.vanyamihova.ui.choicebox;

import eu.vanyamihova.model.BaseModel;

import java.util.Objects;

/**
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-18
 */
final class Choice {

    private BaseModel model;
    private String displayString;

    Choice(BaseModel model, String displayString) {
        this.model = model;
        this.displayString = displayString;
    }

    public BaseModel getModel() {
        return model;
    }

    @Override
    public String toString() {
        return displayString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Choice choice = (Choice) o;
        return Objects.equals(model, choice.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model);
    }
}

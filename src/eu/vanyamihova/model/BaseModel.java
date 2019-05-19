package eu.vanyamihova.model;

/**
 * Parent for all domain models with provided {@code index}
 * which is the ID in the database.
 *
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-17
 */
public abstract class BaseModel {

    private Integer index;

    @Deprecated
    public BaseModel(Integer index) {
        this.index = index;
    }

    protected BaseModel(BaseBuilder builder) {
        this.index = builder.index;
    }

    public void reset() {
        index = null;
    }

    public boolean hasId() {
        return index != null && index != 0;
    }

    public Integer getIndex() {
        return index;
    }

    public static class BaseBuilder {

        private Integer index;

        protected BaseBuilder(Integer index) {
            this.index = index;
        }

    }
}

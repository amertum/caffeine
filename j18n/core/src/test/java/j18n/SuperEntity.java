package j18n;

public class SuperEntity {

    public String getSuperValueNotLocalized() {
        return superValueNotLocalized;
    }

    public void setSuperValueNotLocalized(String superValueNotLocalized) {
        this.superValueNotLocalized = superValueNotLocalized;
    }

    public String getSuperValue() {
        return superValue;
    }

    public void setSuperValue(String superValue) {
        this.superValue = superValue;
    }

    private String superValueNotLocalized;

    @Localized("superEntity.value")
    private String superValue;

}

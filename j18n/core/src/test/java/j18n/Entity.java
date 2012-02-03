package j18n;

public class Entity extends SuperEntity {

    public String getValueNotLocalized() {
        return valueNotLocalized;
    }

    public void setValueNotLocalized(String valueNotLocalized) {
        this.valueNotLocalized = valueNotLocalized;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValueWithArgs() {
        return valueWithArgs;
    }

    public void setValueWithArgs(String valueWithArgs) {
        this.valueWithArgs = valueWithArgs;
    }

    public String getValueWithDefaultValue() {
        return valueWithDefaultValue;
    }

    public void setValueWithDefaultValue(String valueWithDefaultValue) {
        this.valueWithDefaultValue = valueWithDefaultValue;
    }

    public String getValueWithArgsAndDefaultValue() {
        return valueWithArgsAndDefaultValue;
    }

    public void setValueWithArgsAndDefaultValue(String valueWithArgsAndDefaultValue) {
        this.valueWithArgsAndDefaultValue = valueWithArgsAndDefaultValue;
    }

    private String valueNotLocalized;

    @Localized("entity.value")
    private String value;

    @Localized(value = "entity.valueWithArgs", args = {"this.value", "this.valueNotLocalized"})
    private String valueWithArgs;

    @Localized(value = "entity.valueWithDefaultValue", defaultMessage = "defaultValue")
    private String valueWithDefaultValue;

    @Localized(value = "entity.valueWithArgsAndDefaultValue", defaultMessage = "defaultValue {} {}", args = {"this.value", "this.valueNotLocalized"})
    private String valueWithArgsAndDefaultValue;

}

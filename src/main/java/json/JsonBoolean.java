package json;

/**
 * Created by Andrii_Rodionov on 1/4/2017.
 */
public class JsonBoolean extends Json {
    private final Boolean booleanValue;


    public JsonBoolean(Boolean bool) {
        this.booleanValue = bool;
    }

    @Override
    public String toJson() {
        return this.booleanValue.toString();
    }
}

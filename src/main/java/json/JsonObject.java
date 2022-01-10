package json;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private Map<String, Json> information = new LinkedHashMap<>();

    public JsonObject(JsonPair... jsonPairs) {
        for (JsonPair jsp: jsonPairs) {
            information.put(jsp.getKey(), jsp.getValue());
        }
    }

    @Override
    public String toJson() {
        if (information.size() == 0) {
            return "{}";
        }
        String json = "{";
        for (Map.Entry<String, Json> entry : information.entrySet()) {
            json.concat("'")
                    .concat(entry.getKey())
                    .concat("'").concat(": ")
                    .concat(entry.getValue().toJson())
                    .concat(", ");
        }
        return json.substring(0, json.length()-2) + "}";
    }

    public void add(JsonPair jsonPair) {
        information.put(jsonPair.getKey(), jsonPair.getValue());
    }

    public Json find(String name) {
        return information.get(name);
    }

    public JsonObject projection(String... names) {
        JsonObject jsonObject = new JsonObject();
        for (String name: names) {
            if (information.containsKey(name)) {
                jsonObject.add(new JsonPair(name, information.get(name)));
            }
        }
        return jsonObject;
    }
}

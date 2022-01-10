package app;

import domain.BasicStudent;
import domain.Student;
import json.Json;
import json.JsonArray;
import json.JsonNumber;
import json.JsonPair;
import json.JsonObject;
import json.JsonString;
import json.Tuple;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JSONApp {
    public static void main(String[] args) {
        Json jYear = new JsonNumber(2);
        print(jYear); // 2

        final int THREE = 3;
        final int FOUR = 4;
        Json jMarks = new JsonArray(new JsonNumber(THREE),
                new JsonNumber(FOUR));
        print(jMarks); // [3, 4]

        JsonPair name = new JsonPair("name", new JsonString("Andrii"));
        JsonPair surname = new JsonPair("surname", new JsonString("Rodionov"));
        JsonPair marks = new JsonPair("marks", jMarks);
        JsonPair year = new JsonPair("year", jYear);
        JsonObject jsonObj = new JsonObject(name, surname, year, marks);
        print(jsonObj);
        // {'name': 'Andrii', 'surname': 'Rodionov', 'year': 2, 'marks': [3, 4]}

        print(jsonObj.projection("surname", "age", "year", "marks"));
        // {'surname': 'Rodionov', 'year': 2, 'marks': [3, 4]}

        BasicStudent basicStudent = new BasicStudent("Andrii", "Rodionov", 2);
        print(basicStudent.toJsonObject());
        // {'name': 'Andrii', 'surname': 'Rodionov', 'year': 2}
        print(sessionResult());
    }

    private static void print(Json json) {
        System.out.println(json.toJson());
    }

    public static JsonObject sessionResult() {
        final int OOP = 3;
        final int ENGLISH = 5;
        final int MATH = 2;
        return new Student("Andrii", "Rodionov", 2,
                new Tuple<>("OOP", OOP),
                new Tuple<>("English", ENGLISH),
                new Tuple<>("Math", MATH)).toJsonObject();
    }
}

package domain;

import json.Json;
import json.JsonArray;
import json.JsonNumber;
import json.JsonPair;
import json.JsonObject;
import json.JsonString;
import json.Tuple;
import json.JsonBoolean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    private String name;
    private String surname;
    private Integer year;
    private List<Json> examsList = new ArrayList<>();
    private JsonObject jsonObject;

    public Student(String name,
                   String surname,
                   Integer year,
                   Tuple<String, Integer>... exams) {
        this.name = name;
        this.surname = surname;
        this.year = year;
        int MINMARK = 3;
        for (Tuple<String, Integer> exam : exams) {
            Boolean bool = true;
            if (exam.getValue() < MINMARK) {
                bool = false;
            }
            this.examsList.add(new JsonObject(
                    new JsonPair("course", new JsonString(exam.getKey())),
                    new JsonPair("mark", new JsonNumber(exam.getValue())),
                    new JsonPair("passed", new JsonBoolean(bool))));
        }
    }

    @Override
    public JsonObject toJsonObject() {
        this.jsonObject = new BasicStudent(name, surname, year).toJsonObject();
        Json[] array = new Json[this.examsList.size()];
        this.examsList.toArray(array);
        this.jsonObject.add(new JsonPair("exams", new JsonArray(array)));
        return jsonObject;
    }
}
package domain;

import json.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    protected String name;
    protected String surname;
    protected Integer year;
    protected List<Json> examsList = new ArrayList<>();
    protected JsonObject jsonObject;

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        this.name = name;
        this.surname = surname;
        this.year = year;
        for (Tuple<String, Integer> exam : exams) {
            Boolean bool = true;
            if (exam.value < 3)
                bool = false;
            this.examsList.add(new JsonObject(
                    new JsonPair("course", new JsonString(exam.key)),
                    new JsonPair("mark", new JsonNumber(exam.value)),
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
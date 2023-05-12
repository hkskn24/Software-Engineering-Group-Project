package main.java.Data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Data {

    protected String readFileToString(String filePath) {
        String file = null;

        try {
            file = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    protected  <T> T readJsonToList(String filePath, Type type) {
        String jsonStr = readFileToString(filePath);
        return new Gson().fromJson(jsonStr, type);
    }

    protected void saveJsonToFile(String filePath, Object obj) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(obj, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

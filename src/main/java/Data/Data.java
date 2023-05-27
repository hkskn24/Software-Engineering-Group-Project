package main.java.Data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

public class Data {

    protected String readFileToString(InputStream inputStream) {
        String fileContent = null;

        try {
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;

            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }

            fileContent = result.toString(StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return fileContent;
    }


    protected <T> T readJsonToList(InputStream inputStream, Type type) {
        String jsonStr = readFileToString(inputStream);
        return new Gson().fromJson(jsonStr, type);
    }


    protected void saveJsonToFile(InputStream inputStream, Object obj) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileOutputStream outputStream = new FileOutputStream(String.valueOf(inputStream))) {
            OutputStreamWriter writer = new OutputStreamWriter(outputStream);
            gson.toJson(obj, writer);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

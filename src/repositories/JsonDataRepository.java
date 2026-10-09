/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.AppData;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author nmart
 */

public class JsonDataRepository {

    private static final String FILE_PATH =
            "data/buscaminas.json";

    private final Gson gson;

    public JsonDataRepository() {

        gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    public AppData load() {

        try (FileReader reader = new FileReader(FILE_PATH)) {

            return gson.fromJson(reader, AppData.class);

        } catch (IOException e) {

            return new AppData();
        }
    }

    public void save(AppData data) {

        try (FileWriter writer = new FileWriter(FILE_PATH)) {

            gson.toJson(data, writer);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}

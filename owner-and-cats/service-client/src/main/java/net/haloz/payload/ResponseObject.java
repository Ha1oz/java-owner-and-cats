package net.haloz.payload;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Data;
import net.haloz.adapter.LocalDateTypeAdapter;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class ResponseObject {
    private String globalId;
    private String data;
    public ResponseObject(String jsonStr) {
        JsonElement jsonElement = extractField(jsonStr, "globalId");
        this.globalId = jsonElement.getAsString();

        this.data = jsonStr;
    }
    private JsonElement extractField(String from, String fieldName) {

        JsonObject jsonObject = new Gson().fromJson(from, JsonObject.class);
        return jsonObject.get(fieldName);
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();

        return gson.toJson(this);
    }
}

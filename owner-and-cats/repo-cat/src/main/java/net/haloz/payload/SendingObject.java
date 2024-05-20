package net.haloz.payload;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import net.haloz.adapter.LocalDateTypeAdapter;
import net.haloz.dto.CatDto;
import net.haloz.entity.Cat;

import java.time.LocalDate;
import java.util.UUID;
@Data
public class SendingObject {
    private final UUID globalId;
    private final CatDto data;
    public SendingObject(CatDto data) {
        this.data = data;
        this.globalId = UUID.randomUUID();
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();

        return gson.toJson(this);
    }
}

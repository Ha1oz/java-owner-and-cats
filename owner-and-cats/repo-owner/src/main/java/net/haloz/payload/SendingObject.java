package net.haloz.payload;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import net.haloz.adapter.LocalDateTypeAdapter;
import net.haloz.dto.OwnerDto;
import net.haloz.entity.Cat;
import net.haloz.entity.Owner;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;
@Data
public class SendingObject {
    private final UUID globalId;
    private OwnerDto data;
    public SendingObject(OwnerDto data) {
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

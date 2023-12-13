package redis;

import jakarta.json.bind.annotation.JsonbCreator;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.json.bind.annotation.JsonbProperty;


public abstract class AbstractEntityJson {
    @JsonbProperty("_id")
    private int id;

    @JsonbCreator
    public AbstractEntityJson(
            @JsonbProperty("_id") int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

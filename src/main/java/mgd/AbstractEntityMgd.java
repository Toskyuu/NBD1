package mgd;

import org.bson.codecs.pojo.annotations.BsonProperty;

import java.io.Serializable;
import java.util.UUID;


public class AbstractEntityMgd implements Serializable {
    @BsonProperty("_id")
    protected UUID entityId;

    public AbstractEntityMgd(UUID entityId) {
        this.entityId = entityId;
    }

    public AbstractEntityMgd() {
        this.entityId = null;
    }

    public UUID getEntityId() {
        return entityId;
    }

    public void setEntityId(UUID entityId) {
        this.entityId = entityId;
    }
}

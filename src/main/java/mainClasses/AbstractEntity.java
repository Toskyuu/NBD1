package mainClasses;

import java.io.Serializable;
import java.util.UUID;


public class AbstractEntity implements Serializable {
    protected UUID entityID;

    public AbstractEntity() {
    }

    public AbstractEntity(UUID entityID) {
        this.entityID = entityID;
    }

    public UUID getEntityID() {
        return entityID;
    }

    public void setEntityID(UUID entityID) {
        this.entityID = entityID;
    }
}

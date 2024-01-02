package codec;

import com.datastax.oss.driver.api.core.ProtocolVersion;
import com.datastax.oss.driver.api.core.type.DataType;
import com.datastax.oss.driver.api.core.type.DataTypes;
import com.datastax.oss.driver.api.core.type.codec.TypeCodec;
import com.datastax.oss.driver.api.core.type.codec.TypeCodecs;
import com.datastax.oss.driver.api.core.type.reflect.GenericType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import model.Rent;

import java.nio.ByteBuffer;

public class RentCodec implements TypeCodec<Rent> {
    private static final ObjectMapper mapper = new ObjectMapper();
    @NonNull
    @Override
    public GenericType<Rent> getJavaType() {
        return GenericType.of(Rent.class);
    }

    @NonNull
    @Override
    public DataType getCqlType() {
        return DataTypes.TEXT;
    }

    @Nullable
    @Override
    public ByteBuffer encode(@Nullable Rent rent, @NonNull ProtocolVersion protocolVersion) {
        String str = null;
        try {
            str = mapper.writeValueAsString(rent);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return TypeCodecs.TEXT.encode(str, ProtocolVersion.DEFAULT);
    }

    @Nullable
    @Override
    public Rent decode(@Nullable ByteBuffer byteBuffer, @NonNull ProtocolVersion protocolVersion) {
        String str = TypeCodecs.TEXT.decode(byteBuffer, ProtocolVersion.DEFAULT);
        try {
            return mapper.readValue(str, Rent.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    @NonNull
    @Override
    public String format(@Nullable Rent rent) {
        try {
            return mapper.writeValueAsString(rent);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    @Override
    public Rent parse(@Nullable String s) {
        try {
            return mapper.readValue(s, Rent.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

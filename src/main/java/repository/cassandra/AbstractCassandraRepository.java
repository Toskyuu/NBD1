package repository.cassandra;

import codec.RentCodec;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.type.codec.registry.MutableCodecRegistry;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;

import java.net.InetSocketAddress;
import static ids.RentIds.RENT_ITEMS_NAMESPACE;

public abstract class AbstractCassandraRepository {
    protected static CqlSession session;
    protected MutableCodecRegistry registry;
    public void initSession() {
        session = CqlSession.builder()
                .addContactPoint(new InetSocketAddress("localhost", 9042 ))
                .addContactPoint(new InetSocketAddress( "localhost" , 9043 ))
                .withLocalDatacenter("dc1")
                .withAuthCredentials("cassandra", "cassandra" )
                .withKeyspace(RENT_ITEMS_NAMESPACE)
        .build();

        session.execute(
                SchemaBuilder.createKeyspace(RENT_ITEMS_NAMESPACE)
                .ifNotExists()
                .withSimpleStrategy(2)
                .withDurableWrites(true)
                .build());

        registry = (MutableCodecRegistry) session.getContext().getCodecRegistry();
        registry.register(new RentCodec());
    }

}

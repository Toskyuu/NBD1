package ids;

import com.datastax.oss.driver.api.core.CqlIdentifier;

public class RentIds {
    public static final CqlIdentifier RENT_ITEMS_NAMESPACE = CqlIdentifier.fromCql("rent_items");
    public static final CqlIdentifier ID = CqlIdentifier.fromCql("id");
    public static final CqlIdentifier CLIENT_ID = CqlIdentifier.fromCql("client");
//    public static final CqlIdentifier CLIENT_ID = CqlIdentifier.fromCql("client");

}

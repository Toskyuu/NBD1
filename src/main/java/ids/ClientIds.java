package ids;

import com.datastax.oss.driver.api.core.CqlIdentifier;

public class ClientIds {
    public static final CqlIdentifier RENT_ITEMS_NAMESPACE = CqlIdentifier.fromCql("rent_items");
    public static final CqlIdentifier TABLE_NAME = CqlIdentifier.fromCql("clients");
    public static final CqlIdentifier ID = CqlIdentifier.fromCql("id");
    public static final CqlIdentifier FIRST_NAME = CqlIdentifier.fromCql("first_name");
    public static final CqlIdentifier SECOND_NAME = CqlIdentifier.fromCql("second_name");
    public static final CqlIdentifier PHONE_NUMBER = CqlIdentifier.fromCql("phone_number");
}

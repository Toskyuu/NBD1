package ids;

import com.datastax.oss.driver.api.core.CqlIdentifier;

public class ItemIds {
    public static final CqlIdentifier RENT_ITEMS_NAMESPACE = CqlIdentifier.fromCql("rent_items");
    public static final CqlIdentifier TABLE_NAME = CqlIdentifier.fromCql("items");
    public static final CqlIdentifier ID = CqlIdentifier.fromCql("id");
    public static final CqlIdentifier YEAR_OF_PREMIERE = CqlIdentifier.fromCql("year_of_premiere");
    public static final CqlIdentifier IS_RENTED = CqlIdentifier.fromCql("is_rented");
    public static final CqlIdentifier NAME = CqlIdentifier.fromCql("name");
    public static final CqlIdentifier STYLE = CqlIdentifier.fromCql("style");
    public static final CqlIdentifier AUTHOR = CqlIdentifier.fromCql("author");
    public static final CqlIdentifier BASE_PRICE = CqlIdentifier.fromCql("base_price");
    public static final CqlIdentifier DISCRIMINATOR = CqlIdentifier.fromCql("discriminator");
    public static final CqlIdentifier TOTAL_TIME = CqlIdentifier.fromCql("total_time");
    public static final CqlIdentifier NUMBER_OF_SONGS = CqlIdentifier.fromCql("number_of_songs");
}

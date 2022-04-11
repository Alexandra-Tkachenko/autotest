package elements;

public enum Criteria {
    CHEAP,
    EXPENSIVE,
    RELEVANCE;

    public String get() {
        return switch (this) {
            case CHEAP -> "PRICE_ASC";
            case EXPENSIVE -> "PRICE_DSC";
            case RELEVANCE -> "RELEVANCE";
        };
    }
}

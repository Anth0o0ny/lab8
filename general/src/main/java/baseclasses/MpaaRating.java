package baseclasses;



public enum MpaaRating {

    G("Нет возрастных ограничений"),
    PG("Рекомендуется присутствие родителей"),
    PG_13("Детям до 13 лет просмотр не желателен"),
    R("Лицам до 17 лет обязательно присутствие взрослого"),
    NC_17("Лицам до 18 лет просмотр запрещен");

    private final String title;

    MpaaRating(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        return title;
    }

    public static MpaaRating fromString(String mpaaStr) {
        for (MpaaRating mpaaRating : MpaaRating.values()) {
            if (mpaaRating.toString().equalsIgnoreCase(mpaaStr))
                return mpaaRating;
        }
        return null;
    }
}


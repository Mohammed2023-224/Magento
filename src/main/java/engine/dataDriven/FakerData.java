package engine.dataDriven;

import com.github.javafaker.Faker;

public class FakerData {

    static Faker faker = new Faker();

    public static String getFakeAddress() {
        return faker.address().fullAddress();
    }

    public static String getFakeCity() {
        return faker.address().city();
    }

    public static String getFakeAnimalName() {
        return faker.animal().name();
    }

    public static String getFakeDigit() {
        return faker.number().digit();
    }

    public static String getFakeBackToTheFutureCharacter() {
        return faker.backToTheFuture().character();
    }

    public static String getFakeFirstName() {
        return faker.address().firstName();
    }

    public static String getFakeEmail() {
        return faker.internet().emailAddress();
    }

    public static String getFakePassword(int min, int max, boolean upper, boolean special, boolean digits) {
        return faker.internet().password(min, max, upper, special, digits);
    }


}

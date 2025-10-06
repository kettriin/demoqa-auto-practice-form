import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class FillFormTest {
    @BeforeAll
    static void beforeMyTest() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @AfterEach
    void checkResultTable() {
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".modal-body").shouldHave(
                text("Marry By"),
                text("mbemail@mail.ru"),
                text("Female"),
                text("9384109813"),
                text("01 November,1991"),
                text("Biology"),
                text("Sports, Reading, Music"),
                text("png.png"),
                text("Stretty Street 128"),
                text("NCR Noida")
        );
    }

    @Test
    void positiveFormFilling() {
        open("/automation-practice-form");
        $("#firstName").setValue("Marry");
        $("#lastName").setValue("By");
        $("#userEmail").setValue("mbemail@mail.ru");

        $("#genterWrapper").$(byText("Female")).click();

        $("#userNumber").setValue("9384109813");

        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("1991");
        $(".react-datepicker__month-select").selectOption("October");
        $(".react-datepicker__day--001.react-datepicker__day--outside-month").click();

        $("#subjectsInput").setValue("b").pressEnter();

        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#hobbiesWrapper").$(byText("Reading")).click();
        $("#hobbiesWrapper").$(byText("Music")).click();

        $("#uploadPicture").uploadFromClasspath("png.png");

        $("#state").scrollTo().click();
        $("#state").$(byText("NCR")).click();

        $("#city").scrollTo().click();
        $("#city").$(byText("Noida")).click();

        $("#currentAddress").setValue("Stretty Street 128");

        $("#submit").click();

    }
}


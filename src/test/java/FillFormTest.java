import com.codeborne.selenide.Configuration;
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

    @Test
    void positiveFormFilling() {
        open("/automation-practice-form");

        executeJavaScript("$('footer').remove();");
        executeJavaScript("$('#fixedban').remove();");

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

        // проверка на овпадение введенных и сохраненных данных в модалке
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        // через проверку содержимого нужных нам ячеек в таблице
        $(".table-responsive").$("tbody tr", 0).$("td", 1).shouldHave(text("Marry By"));
        $(".table-responsive").$("tbody tr", 1).$("td", 1).shouldHave(text("mbemail@mail.ru"));
        $(".table-responsive").$("tbody tr", 2).$("td", 1).shouldHave(text("Female"));
        $(".table-responsive").$("tbody tr", 3).$("td", 1).shouldHave(text("9384109813"));
        $(".table-responsive").$("tbody tr", 4).$("td", 1).shouldHave(text("01 November,1991"));
        $(".table-responsive").$("tbody tr", 5).$("td", 1).shouldHave(text("Biology"));
        $(".table-responsive").$("tbody tr", 6).$("td", 1).shouldHave(text("Sports, Reading, Music"));
        $(".table-responsive").$("tbody tr", 7).$("td", 1).shouldHave(text("png.png"));
        $(".table-responsive").$("tbody tr", 8).$("td", 1).shouldHave(text("Stretty Street 128"));
        $(".table-responsive").$("tbody tr", 9).$("td", 1).shouldHave(text("NCR Noida"));

    }
}


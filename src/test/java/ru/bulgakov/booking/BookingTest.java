package ru.bulgakov.booking;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.bulgakov.booking.dto.AuthResponse;
import ru.bulgakov.booking.dto.AuthRequest;
import ru.bulgakov.booking.dto.CreateBookingDTO;
import ru.bulgakov.booking.dto.CreateBookingDTO.BookingDates;
import ru.bulgakov.booking.dto.CreateBookingResponse;

import java.util.stream.Stream;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;

public class BookingTest {

    private static final String BOOKING_URL = "https://restful-booker.herokuapp.com";

    @BeforeAll
    static void setUp() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter(), new AllureRestAssured());
    }

    @Test
    void authTest() {
        String user = "admin";
        String password = "password123";

        AuthResponse response = given()
                .contentType(ContentType.JSON)
                .body(new AuthRequest(user, password))
                .post(BOOKING_URL + "/auth")
                .then()
                .statusCode(200)
                .extract().as(AuthResponse.class);

        assertThat(response.getToken()).isNotNull();
    }

    @Test
    void createBookingTest() {
        CreateBookingResponse resp = given()
                .contentType(ContentType.JSON)
                .body(buildBookingRequest())
                .post(BOOKING_URL + "/booking")
                .then()
                .statusCode(200)
                .extract().as(CreateBookingResponse.class);
        assertThat(resp.getBookingid()).isNotNull();
        assertThat(resp.getBooking().getTotalprice()).isEqualTo(1000);
        assertThat(resp.getBooking().getBookingdates().getCheckin()).isEqualTo("2026-01-01");
        assertThat(resp.getBooking().getDepositpaid()).isFalse();
    }

    private static CreateBookingDTO bookingRequest(String name) {
        CreateBookingDTO booking = new CreateBookingDTO();
        booking.setFirstname("Barack");
        booking.setLastname("Obama");
        booking.setTotalprice(1000);
        booking.setDepositpaid(false);
        booking.setBookingdates(new BookingDates("2026-01-01", "2027-01-01"));
        booking.setAdditionalneeds("newspaper");

        return booking;
    }

    private static CreateBookingDTO buildBookingRequest() {
        return CreateBookingDTO.builder()
                .firstname("Barack")
                .lastname("Obama")
                .totalprice(1000)
                .depositpaid(false)
                .bookingdates(BookingDates.builder()
                        .checkin("2026-01-01")
                        .checkout("2027-01-01")
                        .build())
                .additionalneeds("newspaper")
                .build();
    }

    @ParameterizedTest
    @MethodSource("invalidAuthRequests")
    void invalidAuthTest(String testName, AuthRequest request, int expectedStatus) {

        String response = given()
                .contentType(ContentType.JSON)
                .body(request)
                .post(BOOKING_URL + "/auth")
                .then()
                .statusCode(expectedStatus)
                .extract().asString();

        assertThat(response).doesNotContain("\"token\"");
    }

    static Stream<Arguments> invalidAuthRequests() {
        return Stream.of(
                // Неверный пароль — 401
                Arguments.of("Неверный пароль",
                        AuthRequest.builder()
                                .username("admin")
                                .password("wrong")
                                .build(), 200),

                // Неверный логин — 401
                Arguments.of("Неверный логин",
                        AuthRequest.builder()
                                .username("wrong")
                                .password("password123")
                                .build(), 200),

                // Пустой пароль — 400
                Arguments.of("Пустой пароль",
                        AuthRequest.builder()
                                .username("admin")
                                .password("")
                                .build(), 200),

                // Пустой логин — 400
                Arguments.of("Пустой логин",
                        AuthRequest.builder()
                                .username("")
                                .password("password123")
                                .build(), 200),

                // Пустое body — 400
                Arguments.of("Пустое body",
                        AuthRequest.builder().build(), 200)
        );
    }

    @Test
    void authWithoutBodyTest() {

        // Без body — 400
        String response = given()
                .contentType(ContentType.JSON)
                .post(BOOKING_URL + "/auth")
                .then()
                .statusCode(200)
                .extract().asString();

        assertThat(response).doesNotContain("\"token\"");
    }

    @ParameterizedTest
    @MethodSource("invalidBookingRequests")
    void invalidCreateBookingTest(String testName, CreateBookingDTO request, int expectedStatus) {

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .post(BOOKING_URL + "/booking")
                .then()
                .statusCode(expectedStatus);
    }

    static Stream<Arguments> invalidBookingRequests() {
        return Stream.of(

                // Без firstname — 400
                Arguments.of("Без firstname",
                        CreateBookingDTO.builder()
                                .lastname("Obama")
                                .totalprice(1000)
                                .depositpaid(false)
                                .bookingdates(BookingDates.builder()
                                        .checkin("2026-01-01")
                                        .checkout("2027-01-01")
                                        .build())
                                .additionalneeds("newspaper")
                                .build(), 500),

                // Без lastname — 400
                Arguments.of("Без lastname",
                        CreateBookingDTO.builder()
                                .firstname("Barack")
                                .totalprice(1000)
                                .depositpaid(false)
                                .bookingdates(BookingDates.builder()
                                        .checkin("2026-01-01")
                                        .checkout("2027-01-01")
                                        .build())
                                .additionalneeds("newspaper")
                                .build(), 500),

                // Отрицательная цена — 422
                Arguments.of("Отрицательная цена",
                        CreateBookingDTO.builder()
                                .firstname("Barack")
                                .lastname("Obama")
                                .totalprice(-500)
                                .depositpaid(false)
                                .bookingdates(BookingDates.builder()
                                        .checkin("2026-01-01")
                                        .checkout("2027-01-01")
                                        .build())
                                .additionalneeds("newspaper")
                                .build(), 200),

                // Неверный формат даты — 422
                Arguments.of("Неверный формат даты",
                        CreateBookingDTO.builder()
                                .firstname("Barack")
                                .lastname("Obama")
                                .totalprice(1000)
                                .depositpaid(false)
                                .bookingdates(BookingDates.builder()
                                        .checkin("не-дата")
                                        .checkout("2027-01-01")
                                        .build())
                                .additionalneeds("newspaper")
                                .build(), 200),

                // Дата выезда раньше даты заезда — 422
                Arguments.of("Дата выезда раньше даты заезда",
                        CreateBookingDTO.builder()
                                .firstname("Barack")
                                .lastname("Obama")
                                .totalprice(1000)
                                .depositpaid(false)
                                .bookingdates(BookingDates.builder()
                                        .checkin("2027-01-02")
                                        .checkout("2027-01-01")
                                        .build())
                                .additionalneeds("newspaper")
                                .build(), 200),

                // Пустое body — 400
                Arguments.of("Пустое body",
                        CreateBookingDTO.builder().build(), 500)
        );
    }
}
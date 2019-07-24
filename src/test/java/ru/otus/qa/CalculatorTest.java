package ru.otus.qa;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.get;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CalculatorTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    void testPlus() {
        int result = get("/calc?operation=plus&a=5&b=3").then().statusCode(200)
                .assertThat().log().all()
                .extract().body().as(Integer.class);

        assertEquals(8, result);
    }
    @Test
    void testMinus() {
        int result = get("/calc?operation=minus&a=5&b=3").then().statusCode(200)
                .assertThat().log().all()
                .extract().body().as(Integer.class);

        assertEquals(2, result);
    }
    @Test
    void testMultiply() {
        int result = get("/calc?operation=multiply&a=5&b=3").then().statusCode(200)
                .assertThat().log().all()
                .extract().body().as(Integer.class);

        assertEquals(15, result);
    }
    @Test
    void testDivide() {
        int result = get("/calc?operation=divide&a=5&b=3").then().statusCode(200)
                .assertThat().log().all()
                .extract().body().as(Integer.class);

        assertEquals(1, result);
    }
    @Test
    void testUnknown() {

            String result = get("/calc?operation=Unknown&a=5&b=3").then().statusCode(500)
                    .assertThat().log().all()
                    .extract().body().asString();
    }
    @Test
    void testDivideZero() {
        String result = get("/calc?operation=divide&a=5&b=0").then().statusCode(500)
                .assertThat().log().all()
                .extract().body().asString();
    }
    @Test
    void testMultiplyA() {
        String result = get("/calc?operation=multiply&a=5000000000&b=3000000000").then().statusCode(400)
                .assertThat().log().all()
                .extract().body().asString();
    }
}

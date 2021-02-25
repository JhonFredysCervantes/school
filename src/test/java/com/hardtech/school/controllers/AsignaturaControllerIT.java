package com.hardteach.school.controllers;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.equalTo;

import com.hardteach.school.controllers.asignatura.AsignaturaController;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:aplicationTest.properties")
public class AsignaturaControllerIT {

    @Autowired
    private AsignaturaController asignaturaController;

    @Test
    public void Guardar_Asignatura_Controller_Exitosamente(){

        String asignatura = "{\n" +
                "  \"nombreAsignatura\": \"Programacion\",\n" +
                "  \"numeroCreditos\": 5\n" +
                "}";

        given().
                standaloneSetup(this.asignaturaController).
                contentType(ContentType.JSON).
                body(asignatura).
            when().
                post("/api-v1/subject").
            then().
                statusCode(HttpStatus.CREATED.value()).
                contentType(ContentType.JSON).
                body("nombreAsignatura",equalTo("Programacion"));


    }
}

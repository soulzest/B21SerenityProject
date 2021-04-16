package b21.spartan.editor;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import spartan_util.SpartanUtil;

import java.util.*;

import static net.serenitybdd.rest.SerenityRest.*;
import static org.hamcrest.Matchers.*;

@SerenityTest
public class SpartanEditorRolePostTest {


    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://18.235.32.166:7000" ;
        RestAssured.basePath = "/api" ;

    }
    @AfterAll
    public static void cleanup(){
        reset();
    }

    @DisplayName("Editor Should be able Post Valid Data")
    @Test
    public void testEditorPostData(){

        Map<String,Object> bodyMap = SpartanUtil.getRandomSpartanMap() ;

        SerenityRest.given()
                            .auth().basic("editor","editor")
                            .log().body()
                            .contentType(ContentType.JSON)
                            .body(bodyMap).
                    when()
                           .post("/spartans").prettyPeek() ;
        // Do all assertions here
        Ensure.that("It ran successfully", thenSection-> thenSection.statusCode( equalTo(201)  ) );
        Ensure.that("Response format is correct" , thenSection -> thenSection.contentType(ContentType.JSON) );

        Ensure.that("success message is correct" , v -> v.body("success", is("A Spartan is Born!")   )       ) ;
        Ensure.that("ID is generated and not null" , v-> v.body("data.id" , notNullValue()      ) ) ;
        // checking actual data
        Ensure.that("name is correct" ,
                            v-> v.body("data.name" ,  is(bodyMap.get("name")) )
                    ) ;
        Ensure.that("gender is correct" ,
                v-> v.body("data.gender" ,  is(bodyMap.get("gender")) )
        ) ;
        Ensure.that("phone is correct" ,
                v-> v.body("data.phone" ,  is(bodyMap.get("phone")) )
        ) ;
        // check Location header end with newly generated id
        String newId = lastResponse().path("data.id").toString() ;
        System.out.println(   lastResponse().header("Location")   );

        Ensure.that("location header end with "+ newId ,
                      v-> v.header("Location" , endsWith(newId)  )
        )  ;


    }

    // TODO : Add Parameterized Test for Positive Valid Data
    // We will need to provide name , gender , phone for each iteration
    @ParameterizedTest
    @CsvSource({
            "Ercan Civi , Male , 7133306302",
            "Muhammad , Male , 9293215645",
            "Inci, Female, 7038142311"
    })
    public void testPostValidDateWithCSVSource(String nameArg, String genderArg, long phone){

        System.out.println("nameArg = " + nameArg);
        System.out.println("genderArg = " + genderArg);
        System.out.println("phone = " + phone);

        Map<String, Object> bodyMap = new LinkedHashMap<>();
        bodyMap.put("name" , nameArg) ;
        bodyMap.put("gender" , genderArg) ;
        bodyMap.put("phone" , phone) ;

        System.out.println("bodyMap = " + bodyMap);

        SerenityRest.given()
                .auth().basic("editor","editor")
                .log().body()
                .contentType(ContentType.JSON)
                .body(bodyMap).
                when()
                .post("/spartans").prettyPeek() ;
        // Do all assertions here
        Ensure.that("It ran successfully", thenSection-> thenSection.statusCode( equalTo(201)  ) );
        Ensure.that("Response format is correct" , thenSection -> thenSection.contentType(ContentType.JSON) );

        Ensure.that("success message is correct" , v -> v.body("success", is("A Spartan is Born!")   )       ) ;
        Ensure.that("ID is generated and not null" , v-> v.body("data.id" , notNullValue()      ) ) ;
        // checking actual data
        Ensure.that("name is correct" ,
                v-> v.body("data.name" ,  is(bodyMap.get("name")) )
        ) ;
        Ensure.that("gender is correct" ,
                v-> v.body("data.gender" ,  is(bodyMap.get("gender")) )
        ) ;
        Ensure.that("phone is correct" ,
                v-> v.body("data.phone" ,  is(bodyMap.get("phone")) )
        ) ;
        // check Location header end with newly generated id
        String newId = lastResponse().path("data.id").toString() ;
        System.out.println(   lastResponse().header("Location")   );

        Ensure.that("location header end with "+ newId ,
                v-> v.header("Location" , endsWith(newId)  )
        )  ;


    }




    // TODO : Add Parameterized Test for negative invalid Data


}

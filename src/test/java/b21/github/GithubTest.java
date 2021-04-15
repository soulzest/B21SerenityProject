package b21.github;

import io.restassured.RestAssured;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.SerenityRest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

//import static io.restassured.RestAssured.*;
import static net.serenitybdd.rest.SerenityRest.* ;

@SerenityTest
public class GithubTest {

 // SEND GET https://api.github.com/users/CybertekSchool request

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "https://api.github.com";
    }

    @Test
    public void testGitHubUser(){

        given()
                .pathParam("user_id" , "CybertekSchool")
                .log().all().
        when()
                .get("/users/{user_id}").
        then()
                .log().all()
                .statusCode(200) ;

    }

    @Test
    public void testGitHubUser2(){

        SerenityRest.given()
                .pathParam("user_id" , "CybertekSchool").
                //.log().all().
        when()
                .get("/users/{user_id}") ;
        // If you send request using SerenityRest , the response object
        // can be obtained from the method called lastResponse() without being saved separately

        System.out.println(    SerenityRest.lastResponse().statusCode()  );
        System.out.println(  lastResponse().header("Date")     );
        // print the response field login and id
        String loginFieldValue = lastResponse().path("login") ;
        System.out.println("loginFieldValue = " + loginFieldValue);

        System.out.println(lastResponse().jsonPath().getInt("id")  ) ;


    }



    @AfterAll
    public static void cleanup(){
        RestAssured.reset();
    }



}

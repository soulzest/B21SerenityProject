package b21.spartan.editor;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;
import org.junit.jupiter.api.*;
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




    }


}

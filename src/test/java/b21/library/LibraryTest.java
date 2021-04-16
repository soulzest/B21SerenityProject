package b21.library;

import config_util.ConfigReader;
import net.serenitybdd.junit5.SerenityTest;
import org.junit.jupiter.api.Test;

@SerenityTest
public class LibraryTest {


    @Test
    public void testReadingConfProperties(){

        System.out.println( ConfigReader.getProperty("base.url")  );

    }

}

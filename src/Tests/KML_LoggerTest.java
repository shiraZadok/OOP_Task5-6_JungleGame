package Tests;

import gameClient.KML_Logger;
import org.junit.Test;
import java.text.ParseException;
import static org.junit.Assert.*;

public class KML_LoggerTest {

    @Test
    public void objKML() {
        KML_Logger k = new KML_Logger();
        boolean b = true;
        try{
            k.objKML();
        }
        catch (ParseException | InterruptedException e){
            b=false;
        }
        assertEquals(true,b);
    }
}
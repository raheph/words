package Scrabble;

import com.tuesdays.scrabble.ScrabbleController;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.TestProperties;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.*;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Rahel Ephrem on 10/16/2017.
 */
public class ScrabbleControllerTest extends JerseyTest{

    @Override
    public Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(ScrabbleController.class);
    }

//    @Test
//    public void testGetWords(){
//        Response output = target("/hat").request().get();
//        assertEquals("should return status 200", 200, output.getStatus());
//    }
//
//    @Test
//    public void testJsonNotNull(){
//        Response output = target("/tah").request().get();
//        assertNotNull("Should not be null",  output.getEntity());
//    }

//    @Test
//    public void testJsonNul(){
//        Response output = target("/zzz").request().get();
//        List<String> list = output.readEntity(new GenericType<List<String>>() {});
//        assertEquals(0, list.size());
//    }
}

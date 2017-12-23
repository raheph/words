package com.tuesdays.scrabble;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * The root service. That is http://localhost:8080/words
 */
@Path("/word")
public class ScrabbleController {
    private static final Logger LOGGER = Logger.getLogger(ScrabbleController.class.getName());

    @Autowired
    private scrabble scrabbleService;

    public ScrabbleController(){}

    /**
     * Takes letters and responds all posible words from
     * the dictionary
     * @param letters
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{letters}")
    public List<String> getWords(@PathParam("letters") String letters) {
        List<String> words = scrabbleService.getWords(letters.toLowerCase());
        LOGGER.info("Generated words size : " + words.size());
        return words;
    }
}

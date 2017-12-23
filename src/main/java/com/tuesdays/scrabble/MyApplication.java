package com.tuesdays.scrabble;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;


/**
 * Register JAX-RS application components.
 */
public class MyApplication  extends ResourceConfig {
    /**
     * Register JAX-RS application components.
     */
    public MyApplication () {
        register(ScrabbleController.class);
    }
}

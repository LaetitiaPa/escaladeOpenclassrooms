package com.springboot.demo.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.openclassrooms.escaladefun.EscladeFunApplication;

@RunWith( SpringRunner.class )
@SpringBootTest( classes = EscladeFunApplication.class )
/**
 * 
 * By default, @SpringBootTest does not start the server.
 * 
 * @SpringBootTest(classes =
 *                         DemoApplication.class) @SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT),
 *                         an available port is picked at random each time your
 *                         test runs.
 **/
public class DemoApplicationTests {

    @Test
    public void contextLoads() {

    }

}

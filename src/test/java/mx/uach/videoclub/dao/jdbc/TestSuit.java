/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uach.videoclub.dao.jdbc;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author erikzubia
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({mx.uach.videoclub.dao.jdbc.DaoJdbcJUnitTest.class})
public class TestSuit {

    public TestSuit() {
    }
    
    

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Tutut");
    }
    
}

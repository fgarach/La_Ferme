
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author admin
 */
public class Test {

    @org.junit.Test
    public void alea() {

        Random rand = new Random();
        int nombre = rand.nextInt(3 - 2 + 1) + 2;

        System.out.println("************* " + nombre);
    }
}

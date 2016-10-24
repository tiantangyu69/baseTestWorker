package jlinetest;

import jline.ConsoleReader;
import jline.Terminal;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by bjlizhitao on 2016/8/19.
 */
public class JlineTest {
    @Test
    public void baseTest() throws IOException {
        /*ConsoleReader reader = new ConsoleReader();
        String line = reader.readLine(">");*/

        Terminal terminal = Terminal.getTerminal();
        int i = terminal.readCharacter(System.in);
        System.out.println(i);
    }
}

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class tGameUI {
    GameUI ui = new GameUI();

    @Test
    public void testUserNameNumbersChar(){
        boolean result = ui.validateUserName("34657");
        Assertions.assertFalse(result);
    }

    @Test
    public void testUserNameSpecialChar(){
        boolean result = ui.validateUserName("#$^@");
        Assertions.assertFalse(result);
    }
    @Test
    public void testUserNameAlphaChar(){
        boolean result = ui.validateUserName("abc");
        Assertions.assertTrue(result);
    }

    @Test
    public void testStartGameNumbers(){
        boolean result = ui.validateStartGame("2341");
        Assertions.assertFalse(result);
    }
    @Test
    public void testStartGameSpecialCharWithNumbers(){
        boolean result = ui.validateStartGame("$2$#45");
        Assertions.assertFalse(result);
    }
    @Test
    public void testStartGameAnyAlphaChars(){
        boolean result = ui.validateStartGame("asdfsdf");
        Assertions.assertFalse(result);
    }
    @Test
    public void testStartGameAnyLowerS(){
        boolean result = ui.validateStartGame("s");
        Assertions.assertTrue(result);
    }
    @Test
    public void testStartGameAnyUpperS(){
        boolean result = ui.validateStartGame("S");
        Assertions.assertTrue(result);
    }
    @Test
    public void testStartGameAnyLowerQ(){
        boolean result = ui.validateStartGame("q");
        Assertions.assertTrue(result);
    }
    @Test
    public void testStartGameAnyUpperQ(){
        boolean result = ui.validateStartGame("Q");
        Assertions.assertTrue(result);
    }

}

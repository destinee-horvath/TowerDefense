
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import processing.event.MouseEvent;


import processing.core.PApplet;
import processing.event.*;
import WizardTD.App;

public class testApp {
    PApplet app;

    @Test 
    public void test() {
        PApplet app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        app.delay(1000);
    }

    @Test
    public void testMouseReleased() {
        PApplet app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        app.delay(1000);

        app.mouseReleased();

        //MouseEvent(java.lang.Object nativeObject, long millis, int action, int modifiers, int x, int y, int button, int count) 
        MouseEvent e = new MouseEvent(app, 1, 2, 100, 100, 1, false);
        app.mouseReleased(e);
    }
}

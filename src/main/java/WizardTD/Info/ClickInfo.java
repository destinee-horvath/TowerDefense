package WizardTD.Info;

import java.util.*;

public class ClickInfo {
    public int lastMouseX;
    public int lastMouseY;

    public ClickInfo(int lastMouseX, int lastMouseY) {
        this.lastMouseX = lastMouseX;
        this.lastMouseY = lastMouseY;
    }

    public int getLastMouseX() {
        return lastMouseX;
    }

    public int getLastMouseY() {
        return lastMouseY;
    }


    public List<Integer> getLastClick() {
        List<Integer> lastClick = new ArrayList<>();
        lastClick.add(lastMouseX);
        lastClick.add(lastMouseY);
        return lastClick;
    }
}

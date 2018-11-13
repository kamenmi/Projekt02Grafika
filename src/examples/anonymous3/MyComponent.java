package examples.anonymous3;

import java.util.ArrayList;
import java.util.List;

public class MyComponent {

    private List<MyMouseListener> listeners = new ArrayList<>();

    public void addMouseListener(MyMouseListener listener) {
        listeners.add(listener);
    }

    public void fireClick() {
        int i = 0;
        for (MyMouseListener listener : listeners) {
            listener.mouseClicked(i++);
        }
    }

}

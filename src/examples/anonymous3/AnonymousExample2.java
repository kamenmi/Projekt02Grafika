package examples.anonymous3;

public class AnonymousExample2 {

    // anonymous class
    private static MyMouseListener listener = new MyMouseListener() {
        @Override
        public void mouseClicked(int param) {
            System.out.println("clicked - saved into variable");
        }
    };

    public static void main(String[] args) {
        MyComponent component = new MyComponent();
        // 1. - inner anonymous class, MyMouseListener is interface
        component.addMouseListener(new MyMouseListener() {
            @Override
            public void mouseClicked(int param) {
                System.out.println("clicked - " + param);
            }
        });
        // 2. anonymous using lambda
        component.addMouseListener(e -> System.out.println("clicked - second"));
        // 3. MyMouseListenerImpl implementation
        component.addMouseListener(new MyMouseListenerImpl());
        // 4. using global anonymous class
        component.addMouseListener(listener);

        // run
        component.fireClick();
    }
}

class MyMouseListenerImpl implements MyMouseListener {
    @Override
    public void mouseClicked(int param) {
        System.out.println("clicked - " + param);
    }
}

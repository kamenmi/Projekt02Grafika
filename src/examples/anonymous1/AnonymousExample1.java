package examples.anonymous1;

public class AnonymousExample1 {

    public static void main(String[] args) {

        // anonymous class inside method
        MyListener listener = new MyListener() {
            @Override
            public void handleMouseClick() {
                System.out.println("click called");
            }
        };
        // the same using lambda
        // MyListener listener2 = () -> System.out.println("click called");

        listener.handleMouseClick();
    }

}

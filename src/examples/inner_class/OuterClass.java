package examples.inner_class;

public class OuterClass {

    private int a = 10;

    public static void main(String[] args) {
        OuterClass outerClass = new OuterClass();
        InnerClass innerClass = outerClass.new InnerClass();
        innerClass.sayHello();
    }

    private class InnerClass {

        private void sayHello() {
            System.out.println("hello from inner class " + a);
        }

    }

}

package examples.abstract_class;

public class AbstractClassExample {

    public static void main(String[] args) {

        AbstractDevice abstractDevice1 = new Thermometer(1);
        abstractDevice1.setValue(15);

        AbstractDevice abstractDevice2 = new Lamp(2);
        abstractDevice2.setValue(0);

        AbstractDevice abstractDevice3 = new AbstractDevice("test device", 3) {
            @Override
            void showValue() {
                System.out.println("exiting with value " + getValue());
                System.exit(getValue());
            }
        };
        abstractDevice3.setValue(1);
    }

}

package examples.abstract_class;

public class Thermometer extends AbstractDevice {

    public Thermometer(long id) {
        super("Termostat", id);
    }

    @Override
    void showValue() {
        System.out.println("Temperature is " + getValue() + "°C");
    }

}

package examples.abstract_class;

public class Lamp extends AbstractDevice {

    public Lamp(long id) {
        super("lampa", id);
    }

    @Override
    void showValue() {
        System.out.println("Lamp \"" + getName() + "\" is " + (getValue() == 0 ? "off." : "on."));
    }

    @Override
    public void setValue(int value) {
        super.setValue(value);
        System.out.println(getName());
    }

}

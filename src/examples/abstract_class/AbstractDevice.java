package examples.abstract_class;

public abstract class AbstractDevice {

    private String name;
    private long id;
    private int value;

    public AbstractDevice(String name, long id) {
        this.name = name;
        this.id = id;
    }

    abstract void showValue();

    public /*final*/ void setValue(int value) {
        this.value = value;
        showValue();
    }

    ////// getters

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public int getValue() {
        return value;
    }

}

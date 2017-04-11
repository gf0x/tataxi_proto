package app.pojo;

/**
 * Created by Alex_Frankiv on 11.04.2017.
 */
public class CarStatsPojo {

    private String brand;
    private String model;
    private int amount;

    @Override
    public String toString() {
        return "CarStatsPojo{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", amount=" + amount +
                '}';
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public CarStatsPojo() {

    }

    public CarStatsPojo(String brand, String model, int amount) {

        this.brand = brand;
        this.model = model;
        this.amount = amount;
    }
}

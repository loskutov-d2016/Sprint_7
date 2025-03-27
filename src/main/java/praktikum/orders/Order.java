package praktikum.orders;

import java.util.List;

public class Order {
    private String name;
    private String surname;
    private String address;
    private int paymentType;
    private String phone;
    private int deliveryPointId;
    private String deliveryDate;
    private String comment;
    private List<String> colors;

    public Order(String name, String surname, String address, int paymentType, String phone,
                 int deliveryPointId, String deliveryDate, String comment, List<String> colors) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.paymentType = paymentType;
        this.phone = phone;
        this.deliveryPointId = deliveryPointId;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.colors = colors;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public String getPhone() {
        return phone;
    }

    public int getDeliveryPointId() {
        return deliveryPointId;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public String getComment() {
        return comment;
    }

    public List<String> getColors() {
        return colors;
    }
}
package model;

public class Order {
    private long id;
    private long customerId; // кому продали игрушку
    private long employeeId; // кто продал
    private long[] toys;  // список номеров игрушек, которые продали

    public Order(long id, long customerId, long employeeId, long[] toys) {
        this.id = id;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.toys = toys;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public long[] getToys() {
        return toys;
    }

    public void setToys(long[] toys) {
        this.toys = toys;
    }
}

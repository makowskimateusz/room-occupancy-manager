package pl.makowski.roomoccupancymanager.booking.domain;

public class Booking implements Comparable<Booking> {

    private final Double price;
    private final BookingType type;

    public Booking(double price, BookingType type) {
        this.price = price;
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public BookingType getType() {
        return type;
    }

    @Override
    public int compareTo(Booking booking) {
        return booking.price.compareTo(this.price);
    }
}

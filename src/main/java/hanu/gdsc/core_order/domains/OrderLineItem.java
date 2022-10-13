package hanu.gdsc.core_order.domains;

import hanu.gdsc.core_order.exceptions.InsufficientQuantityException;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.exceptions.InvalidInputException;

import java.util.Collections;
import java.util.List;

public class OrderLineItem {
    private Id itemId;
    private String name;
    private String color;
    private Gram weight;
    private Centimeter height;
    private Centimeter width;
    private int price;
    private int quantity;
    private List<String> images;

    private OrderLineItem(Id itemId,
                          String name,
                          String color,
                          Gram weight,
                          Centimeter height,
                          Centimeter width,
                          int price,
                          int quantity,
                          List<String> images) {
        this.itemId = itemId;
        this.name = name;
        this.color = color;
        this.weight = weight;
        this.height = height;
        this.width = width;
        this.price = price;
        this.quantity = quantity;
        this.images = images;
    }

    public static OrderLineItem takeFromItem(Item item, int quantity) throws InvalidInputException,
            InsufficientQuantityException {
        // validate input
        if (item == null) {
            throw new InvalidInputException("item must be not null.");
        }
        // logic
        item.decreaseQuantity(quantity);
        return new OrderLineItem(
                item.getId(),
                item.getName(),
                item.getColor(),
                item.getWeight(),
                item.getHeight(),
                item.getWidth(),
                item.getPrice(),
                quantity,
                item.getImages()
        );
    }

    public Id getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public Gram getWeight() {
        return weight;
    }

    public Centimeter getHeight() {
        return height;
    }

    public Centimeter getWidth() {
        return width;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public List<String> getImages() {
        return Collections.unmodifiableList(images);
    }
}

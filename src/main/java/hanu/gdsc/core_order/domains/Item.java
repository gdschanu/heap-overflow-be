package hanu.gdsc.core_order.domains;

import hanu.gdsc.core_order.exceptions.InsufficientQuantityException;
import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentitifedVersioningDomainObject;
import hanu.gdsc.share.exceptions.InvalidInputException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Item extends IdentitifedVersioningDomainObject {
    private String name;
    private String color;
    private Gram weight;
    private Centimeter height;
    private Centimeter width;
    private int price;
    private int quantity;
    private List<String> images;
    private DateTime createdAt;

    private Item(Id id,
                 long version,
                 String name,
                 String color,
                 Gram weight,
                 Centimeter height,
                 Centimeter width,
                 int price,
                 int quantity,
                 List<String> images,
                 DateTime createdAt) {
        super(id, version);
        this.name = name;
        this.color = color;
        this.weight = weight;
        this.height = height;
        this.width = width;
        this.price = price;
        this.quantity = quantity;
        this.images = images;
        this.createdAt = createdAt;
    }

    public static Item create(String name,
                              String color,
                              Gram weight,
                              Centimeter height,
                              Centimeter width,
                              int price,
                              int quantity,
                              List<String> image) throws InvalidInputException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidInputException("name must be not empty.");
        }
        if (color == null || color.trim().isEmpty()) {
            throw new InvalidInputException("color must be not empty.");
        }
        if (weight == null) {
            throw new InvalidInputException("weight must be not null.");
        }
        if (height == null) {
            throw new InvalidInputException("height must be not null.");
        }
        if (width == null) {
            throw new InvalidInputException("width must be not null.");
        }
        if (price <= 0) {
            throw new InvalidInputException("price must be greater than 0.");
        }
        if (quantity < 0) {
            throw new InvalidInputException("quantity must be greater than 0.");
        }
        if (image == null) {
            image = new ArrayList<>();
        }
        return new Item(
                Id.generateRandom(),
                0,
                name,
                color,
                weight,
                height,
                width,
                price,
                quantity,
                image,
                DateTime.now()
        );
    }

    public void decreaseQuantity(int value) throws InsufficientQuantityException, InvalidInputException {
        if (quantity <= 0) {
            throw new InvalidInputException("Cannot decrease '" + value + "' from quantity.");
        }
        if (value > quantity) {
            throw new InsufficientQuantityException("Insufficient quantity, you are trying to buy "
                    + value + ", but item '" + name + "' has only " + quantity + " left on stock.");
        }
        quantity -= value;
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

    public DateTime getCreatedAt() {
        return createdAt;
    }
}

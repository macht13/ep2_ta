package Java;

public class Junction {
    // doppelt verkettete Liste
    private Junction next, prev;

    private String name;
    private double xPos, yPos;
    private JunctionType type;

    public Junction(String name, double xPos, double yPos, String type) {
        this.name = name;
        this.xPos = xPos;
        this.yPos = yPos;
        // do checking of input
        if (type.equals("AIRPORT")) {
            this.type = JunctionType.AIRPORT;
        }
        else if (type.equals("TRAINSTATION")) {
            this.type = JunctionType.TRAINSTATION;
        }
        else {
            // wrong input
            throw new IllegalArgumentException();
        }
    }

    // check if Junction is in range of (x, y)
    public boolean checkInRange(double x, double y, double radius) {
        return Math.sqrt(Math.pow(x - this.getxPos(), 2) + Math.pow(y - this.getyPos(), 2)) <= radius;
    }

    public Junction() {
    }

    public JunctionType getType() {
        return type;
    }

    public double getxPos() {
        return xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public String getName() {
        return name;
    }

    public void setType(JunctionType type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    public Junction getNext() {
        return next;
    }

    public Junction getPrev() {
        return prev;
    }

    public void setNext(Junction next) {
        this.next = next;
    }

    public void setPrev(Junction prev) {
        this.prev = prev;
    }
}

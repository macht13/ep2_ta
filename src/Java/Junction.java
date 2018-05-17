package Java;

public class Junction {
    // doppelt verkettete Liste
    private Junction next, prev;

    private String name;
    private double xPos, yPos;
    private JunctionType type;

    public Junction(String name, double xPos, double yPos, String isAirport) {
        this.name = name;
        this.xPos = xPos;
        this.yPos = yPos;
        // do checking of input
        if (isAirport.equals("AIRPORT")) {
            type = JunctionType.AIRPORT;
        }
        else if (isAirport.equals("TRAINSTATION")) {
            type = JunctionType.TRAINSTATION;
        }
        else {
            // wrong input
            throw new IllegalArgumentException();
        }
    }

    public Junction() {
    }

    public JunctionType isAirport() {
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

    public void setIsAirport(JunctionType type) {
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

package Java;

public class Junction {
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

}

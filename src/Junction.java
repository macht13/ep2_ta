public class Junction {
    private String name;
    private double xPos, yPos;
    private boolean isAirport;

    public Junction(String name, double xPos, double yPos, boolean isAirport) {
        this.name = name;
        this.xPos = xPos;
        this.yPos = yPos;
        this.isAirport = isAirport;
    }

    public boolean isAirport() {
        return isAirport;
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

    public void setIsAirport(boolean airport) {
        isAirport = airport;
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
}

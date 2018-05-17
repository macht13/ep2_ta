package Java;

public class QuadTree implements JunctionStructure {
    // recursive objects
    // maybe redo with dynamic binding
    QuadTree topLeft = null;
    QuadTree topRight = null;
    QuadTree bottomLeft = null;
    QuadTree bottomRight = null;

    public QuadTree() {

    }

    // add adds a new element to the structure
    // the new element is added to the correct quadrant recursively
    @Override
    public void add(Junction j) {

    }

    // getCntInRange returns a Pair of longs
    // these store the # of trainstations/airports
    // goes through the necessary quads to find them all
    @Override
    public Pair getCntInRange(double x, double y, double radius) {
        long cntAirport = 0, cntTrainstation = 0;


        return new Pair(cntAirport, cntTrainstation);
    }

    // printInRange prints the cnt obtained from getCntInRange
    @Override
    public void printInRange(double x, double y, double radius) {
        Pair pair = getCntInRange(x, y, radius);
        System.out.println("Airports: " + pair.first + " Trainstations: " + pair.second);
    }
}

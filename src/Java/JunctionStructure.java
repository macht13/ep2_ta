package Java;

/*
 * A interface to enable a simple switch between the naive and more complex data structure
 */
public interface JunctionStructure {
    // add adds a new element to the structure
    boolean add(Junction j);

    // getCntInRange returns a Pair of longs
    // these store the # of trainstations/airports
    Pair getCntInRange(double x, double y, double radius);

    // printInRange prints the cnt obtained from getCntInRange
    void printInRange(double x, double y, double radius);

    // printAirports prints the number of airports
    // with atleast numTS amount of trainstations
    void printAirports(long numTS, double radius);
}

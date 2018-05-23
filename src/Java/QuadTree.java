package Java;

public class QuadTree implements JunctionStructure {
    // recursive objects
    // either loop through the list twice --> min/max for boundaries
    // or
    // set boundaries to just include all elements
    private QuadTree topLeft;
    private QuadTree topRight;
    private QuadTree bottomLeft;
    private QuadTree bottomRight;
    private Junction junction;
    private double topBoundary;
    private double leftBoundary;
    private double bottomBoundary;
    private double rightBoundary;

    public QuadTree() {
        this.junction = null;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public QuadTree(double top, double left, double bot, double right) {
        this.junction = null;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
        this.topBoundary = top;
        this.leftBoundary = left;
        this.bottomBoundary = bot;
        this.rightBoundary = right;
    }

    // add adds a new element to the structure
    // the new element is added to the correct quadrant recursively
    @Override
    public void add(Junction j) {
        if (j == null) {
            return;
        }
        // outside of Quad Boundary
        else if (!inBoundary(j.getxPos(), j.getyPos())) {
            return;
        }
        // cannot subdivide quad further
        if (Math.abs(leftBoundary - rightBoundary) <= 1 && Math.abs(topBoundary - bottomBoundary) <= 1) {
            if (this.junction == null) this.junction = j;
            else System.out.println("Cannot add new Junction: " + j.getName() + ", duplicate coordinates");
        }
        // add
        if ((this.leftBoundary + this.rightBoundary) / 2 >= this.junction.getxPos()) {
            // Top Left QuadTree
            if ((this.topBoundary + this.bottomBoundary) / 2 >= this.junction.getyPos()) {
                if (this.topLeft == null) {
                    topLeft = new QuadTree(
                            this.topBoundary,
                            this.leftBoundary,
                            (this.topBoundary + this.bottomBoundary) / 2,
                            (this.leftBoundary + this.rightBoundary) / 2);
                }
                topLeft.add(j);
            }
            // Bottom Left QuadTree
            else {
                if (this.bottomLeft == null) {
                    bottomLeft = new QuadTree(
                            (this.topBoundary + this.bottomBoundary) / 2,
                            this.leftBoundary,
                            this.bottomBoundary,
                            (this.leftBoundary + this.rightBoundary) / 2);
                }
                bottomLeft.add(j);
            }
        } else {
            // Top Right QuadTree
            if ((this.topBoundary + this.bottomBoundary) / 2 >= this.junction.getyPos()) {
                if (this.topRight == null) {
                    topRight = new QuadTree(
                            this.topBoundary,
                            (this.leftBoundary + this.rightBoundary) / 2,
                            (this.topBoundary + this.bottomBoundary) / 2,
                            this.rightBoundary);
                }
                topRight.add(j);
            }
            // Bottom Right QuadTree
            else {
                if (this.bottomRight == null) {
                    bottomRight = new QuadTree(
                            (this.topBoundary + this.bottomBoundary) / 2,
                            (this.leftBoundary + this.rightBoundary) / 2,
                            this.bottomBoundary,
                            this.rightBoundary);
                }
                bottomLeft.add(j);
            }
        }
    }

    public boolean inBoundary(double x, double y) {
        return (x >= this.leftBoundary &&
                x <= this.rightBoundary &&
                y <= this.topBoundary &&
                y >= this.bottomBoundary);
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

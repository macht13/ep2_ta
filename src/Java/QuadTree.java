package Java;

public class QuadTree implements JunctionStructure {
    // recursive objects
    // either loop through the list twice --> min/max for boundaries
    // or
    // set boundaries to just include all elements
    private QuadTree[][] trees = new QuadTree[2][2];

    private Junction junction;

    private double topBoundary;
    private double leftBoundary;
    private double bottomBoundary;
    private double rightBoundary;

    public QuadTree() {
        this.junction = null;
        // topLeft
        this.trees[0][1] = null;
        // topRight
        this.trees[1][1] = null;
        // bottomLeft
        this.trees[0][0] = null;
        // bottomRight
        this.trees[1][0] = null;
    }

    public QuadTree(double top, double left, double bot, double right) {
        this.junction = null;

        // topLeft
        this.trees[0][1] = null;
        // topRight
        this.trees[1][1] = null;
        // bottomLeft
        this.trees[0][0] = null;
        // bottomRight
        this.trees[1][0] = null;

        this.topBoundary = top;
        this.leftBoundary = left;
        this.bottomBoundary = bot;
        this.rightBoundary = right;
    }

    // add adds a new element to the structure
    // the new element is added to the correct quadrant recursively
    @Override
    public boolean add(Junction j) {
        if (j == null) {
            return false;
        }
        if (!inBoundary(j.getxPos(), j.getyPos())) {
            return false;
        }

        // add
        if (this.isLeaf()) {
            // tree is empty
            if (this.junction == null) {
                this.junction = j;
                return true;
            }
            // tree has value
            else {
                this.subdivide(j);
                return true;
            }
        }

        // find correct QuadTree to insert into
        return trees[findX(j)][findY(j)].add(j);
    }

    // findX decides whether the Junction gets added at the left or right
    private int findX(Junction j) {
        if ((this.leftBoundary + this.rightBoundary) / 2 >= j.getxPos()) {
            // Left
            return  0;
        }
        else {
            // Right
            return  1;
        }
    }

    // findY decides whether the Junction gets added at the top or bottom
    private int findY(Junction j) {
        if ((this.topBoundary + this.bottomBoundary) / 2 >= j.getyPos()) {
            // Bottom
            return 0;
        }
        else {
            // Top
            return 1;
        }
    }

    private boolean isLeaf() {
        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {
                if (trees[x][y] != null) {
                    return false;
                }
            }
        }
        return true;
    }

    // subdivide takes a QuadTree with a value
    // and creates the correctly divided Trees
    // it adds the old/new value into the tree
    private void subdivide(Junction j) {
        // get old value
        Junction oldJunction = this.junction;
        this.junction = null;

        // create new QuadTree
        double hMid = (this.bottomBoundary + this.topBoundary) / 2;
        double vMid = (this.leftBoundary + this.rightBoundary) / 2;
        trees[0][1] = new QuadTree(this.topBoundary, this.leftBoundary, hMid, vMid);
        trees[1][1] = new QuadTree(this.topBoundary, vMid, hMid, this.rightBoundary);
        trees[0][0] = new QuadTree(hMid, this.leftBoundary, this.bottomBoundary, vMid);
        trees[1][0] = new QuadTree(hMid, vMid, this.bottomBoundary, this.rightBoundary);

        // add both Junctions
        this.add(oldJunction);
        this.add(j);
    }

    // returns true if point within QuadTrees region
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

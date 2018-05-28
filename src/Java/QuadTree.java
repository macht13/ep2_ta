package Java;

public class QuadTree implements JunctionStructure {
    // recursive objects
    // either loop through the list twice --> min/max for boundaries
    // or
    // set boundaries to just include all elements
    private QuadTree[][] trees = new QuadTree[2][2];
    private JunctionListe airports;

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

        this.airports = new JunctionListe();
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

    public void addAirport(Junction airport) {
        this.airports.add(airport);
    }

    public JunctionListe getAirports() {
        return this.airports;
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
        if (this.isLeaf()) {
            if (this.junction != null && distance(this.junction.getxPos(), this.junction.getyPos(), x, y) <= radius) {
                if (this.junction.getType() == JunctionType.TRAINSTATION) {
                    cntTrainstation++;
                } else {
                    cntAirport++;
                }
            }
            return new Pair(cntAirport, cntTrainstation);
        }

        Pair tmp;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (this.trees[i][j] != null && this.trees[i][j].intersectCircle(x, y, radius)) {
                    tmp = this.trees[i][j].getCntInRange(x, y, radius);
                    cntAirport += tmp.first;
                    cntTrainstation += tmp.second;
                }
            }
        }
        return new Pair(cntAirport, cntTrainstation);
    }

    // printAirports prints the number of airports
    // with atleast numTS amount of trainstations
    // iterates over array of airports
    @Override
    public void printAirports(long numTS, double radius) {
        long cntAirports = 0;
        for (JunctionNode node = this.airports.getNil().getNext(); node != this.airports.getNil(); node = node.getNext()) {
            Junction j = node.getValue();
            Pair tmp = getCntInRange(j.getxPos(), j.getyPos(), radius);
            if (tmp.second >= numTS) {
                cntAirports++;
            }
        }
        System.out.println(cntAirports);
    }

    // returns true if the circle (x,y) with the radius 'radius'
    // intersects the QuadTrees boundaries
    private boolean intersectCircle(double x, double y, double radius) {
        // check if a corner is in the circle
                // top-right
        if (distance(x, y, rightBoundary, topBoundary) <= radius ||
                // top-left
                distance(x, y, leftBoundary, topBoundary) <= radius ||
                // bottom-right
                distance(x, y, rightBoundary, bottomBoundary) <= radius ||
                // bottom-left
                distance(x, y, leftBoundary, bottomBoundary) <= radius) {
            return true;
        }

        // check if circle is inside the rectangle
        if (x <= rightBoundary && x >= leftBoundary &&
                y <= topBoundary && y >= bottomBoundary) {
            return true;
        }

        // check if circle intersects edge
        // horizontal edges
        if (x <= rightBoundary && x >= leftBoundary &&
                (y + radius >= bottomBoundary || y - radius <= topBoundary)) {
            return true;
        }
        // vertical edges
        if (y <= topBoundary && y >= bottomBoundary &&
                (x + radius >= leftBoundary || x - radius <= rightBoundary)) {
            return true;
        }

        return false;
    }

    private double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y1 - y2, 2));
    }

    // printInRange prints the cnt obtained from getCntInRange
    @Override
    public void printInRange(double x, double y, double radius) {
        Pair pair = getCntInRange(x, y, radius);
        System.out.println("Airports: " + pair.first + " Trainstations: " + pair.second);
    }
}

package Java;

public class JunctionListe implements JunctionStructure {
    private JunctionNode nil;
    private JunctionListe airports;
    private boolean empty = true;

    // add adds a new element to the structure
    // the new element is added to the end of the list
    @Override
    public boolean add(Junction junction){
        if (junction == null) {
            return false;
        }
        if (this.nil.getPrev() == this.nil) {
            this.nil.setNext(new JunctionNode());
            this.nil.getNext().setValue(junction);
        }

        JunctionNode last = this.nil.getPrev();
        this.nil.setPrev(new JunctionNode());
        this.nil.getPrev().setValue(junction);
        this.nil.getPrev().setNext(this.nil);
        this.nil.getPrev().setPrev(last);
        last.setNext(this.nil.getPrev());

        return true;
    }

    // getCntInRange returns a Pair of longs
    // these store the # of trainstations/airports
    // iterates over all elements to check which are in range
    @Override
    public Pair getCntInRange(double x, double y, double radius) {
        long cntAirport = 0, cntTrainstation = 0;
        for (JunctionNode elem = this.nil.getNext(), nil = this.nil; elem != nil; elem = elem.getNext()) {
            Junction value = elem.getValue();
            if (value.checkInRange(x, y, radius)) {
                switch (value.getType()) {
                    case AIRPORT:
                        cntAirport++;
                        break;
                    case TRAINSTATION:
                        cntTrainstation++;
                }
            }
        }

        return new Pair(cntAirport, cntTrainstation);
    }

    // printInRange prints the cnt obtained from getCntInRange
    @Override
    public void printInRange(double x, double y, double radius) {
        Pair pair = getCntInRange(x, y, radius);
        System.out.println("Airports: " + pair.first + " Trainstations: " + pair.second);
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

    public JunctionListe(){
        this.nil = new JunctionNode();
        this.nil.setNext(this.nil);
        this.nil.setPrev(this.nil);
    }

    public JunctionNode getNil() {
        return nil;
    }

    public JunctionNode getRoot() {
        return nil.getNext();
    }

    public void addAirport(Junction airport) {
        this.airports.add(airport);
    }

    public JunctionListe getAirports() {
        return this.airports;
    }
}

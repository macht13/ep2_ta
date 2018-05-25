package Java;

public class JunctionListe implements JunctionStructure {
    private JunctionNode nil;
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
}

package Java;

public class SimpleList implements JunctionStructure {
    private JunctionListe list;
    private boolean empty = true;

    public SimpleList() {
        list = new JunctionListe();
    }

    // add adds a new element to the structure
    // the new element is added to the end of the list
    @Override
    public void add(Junction j) {
        list.add(j);
    }

    // getCntInRange returns a Pair of longs
    // these store the # of trainstations/airports
    // iterates over all elements to check which are in range
    @Override
    public Pair getCntInRange(double x, double y, double radius) {
        long cntAirport = 0, cntTrainstation = 0;
        for (JunctionNode elem = list.getNil().getNext(), nil = list.getNil(); elem != nil; elem = elem.getNext()) {
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
}

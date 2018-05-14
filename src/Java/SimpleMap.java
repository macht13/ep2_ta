package Java;

public class SimpleMap {
    private JunctionListe list;

    public SimpleMap() {
        list = new JunctionListe();
    }

    private Pair getCntInRange(double x, double y, double radius) {
        long cntAirport = 0, cntTrainstation = 0;
        for (Junction elem = list.getNil().getNext(), nil = list.getNil(); elem != nil; elem = elem.getNext()) {
            if (checkInRange(elem, x, y, radius)) {
                if (elem.isAirport()) {
                    cntAirport++;
                }
                else {
                    cntTrainstation++;
                }
            }
        }

        return new Pair(cntAirport, cntTrainstation);
    }

    public void printInRange(double x, double y, double radius) {
        Pair pair = getCntInRange(x, y, radius);
        System.out.println("Airports: " + pair.first + " Trainstations: " + pair.second);
    }

    private boolean checkInRange(Junction junction, double x, double y, double radius) {
        double junctionX = junction.getxPos();
        double junctionY = junction.getyPos();

        return Math.sqrt(Math.pow(x - junctionX, 2) + Math.pow(y - junctionY, 2)) <= radius;
    }
}

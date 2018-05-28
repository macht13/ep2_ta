package Java;

import static Java.Importer.importJunctions;

public class main {
    public static void main(String[] args) {
        JunctionListe structure = importJunctions(new JunctionListe());
        //QuadTree q = importJunctions();
        //structure.printInRange(1818.54657, 5813.29982, 100);
        //structure.printInRange(0, 0, 100000);
        structure.printAirports(5, 1);
    }
}

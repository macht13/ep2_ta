package Java;

import static Java.Importer.importJunctions;

public class main {
    public static void main(String[] args) {
        JunctionListe structure = importJunctions(new JunctionListe());
        QuadTree q = importJunctions();
        structure.printInRange(0, 0, 1000);
        q.printInRange(0, 0, 1000);
    }
}

package Java;

import static Java.Importer.importJunctions;

public class main {
    public static void main(String[] args) {
        SimpleList structure = importJunctions(new SimpleList());
        QuadTree q = importJunctions();
        //structure.printInRange(0, 0, 1000);
    }
}

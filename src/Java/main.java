package Java;

import static Java.Importer.*;

public class main {
    public static void main(String[] args) {
        JunctionListe structure = importToList(new JunctionListe());
        QuadTree q = importToList(new QuadTree());
        System.out.println("-------------------");
        long start = System.nanoTime();
        structure.printInRange(1818.54657, 5813.29982, 100);
        System.out.println((System.nanoTime() - start) / 1000000);
        System.out.println("-------------------");
        start = System.nanoTime();
        q.printAirports(5, 1);
        System.out.println((System.nanoTime() - start) / 1000000);
        System.out.println("-------------------");
        start = System.nanoTime();
        q.printInRange(1818.54657, 5813.29982, 100);
        System.out.println((System.nanoTime() - start) / 1000000);
    }
}

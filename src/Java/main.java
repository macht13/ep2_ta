package Java;

public class main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        // vielleicht durch interface auf allgemeine Lösung ändern
        Importer liste1 = new Importer();
        JunctionStructure structure = liste1.importJunctions(new SimpleList());
    }
}

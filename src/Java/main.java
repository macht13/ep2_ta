package Java;

public class main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        // vielleicht durch interface auf allgemeine Lösung ändern
        DoubleLinkedListImporter liste1 = new DoubleLinkedListImporter();
        JunctionStructure structure = liste1.importJunctions();
    }
}

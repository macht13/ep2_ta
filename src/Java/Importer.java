package Java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static Java.JunctionType.AIRPORT;

public class Importer {

    // imports data from csv and adds data to JunctionListe parameter
    public static JunctionListe importToList(JunctionListe structure){
        try (Scanner s = new Scanner(new File(System.getProperty("user.dir") + "/data/junctions.csv"), "UTF-8")){
                while (s.hasNextLine()){
                    String[] line = s.nextLine().split(";");
                    structure.add(new Junction(line[0], Double.parseDouble(line[1]), Double.parseDouble(line[2]), line[3]));
                }

        } catch (FileNotFoundException e){
            System.exit(1);
        }
        return structure;
    }

    // adds all entries using the above function to a QuadTree
    public static QuadTree importToList(QuadTree q){
        JunctionListe structure = importToList(new JunctionListe());
        double x, y;
        double hMax = 0;
        double hMin = 0;
        double vMax = 0;
        double vMin = 0;

        // get boundaries by iterating through each element of
        // the temporary JunctionListe structure list
        for (JunctionNode j = structure.getRoot(); j != structure.getNil(); j = j.getNext()) {
            x = j.getValue().getxPos();
            y = j.getValue().getyPos();
            if (x > hMax) {
                hMax = x;
            }
            else if (x < hMin) {
                hMin = x;
            }

            if (y > vMax) {
                vMax = y;
            }
            else if (y < vMin) {
                vMin = y;
            }
        }

        q = new QuadTree(vMax, hMin, vMin, hMax);

        // inserts all the elements to the QuadTree via add
        for (JunctionNode j = structure.getRoot(); j != structure.getNil(); j = j.getNext()) {
            q.add(j.getValue());
            if (j.getValue().getType() == AIRPORT) {
                q.addAirport(j.getValue());
            }
        }
        return q;
    }
}

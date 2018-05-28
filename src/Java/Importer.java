package Java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static Java.JunctionType.AIRPORT;

public class Importer {

    public static JunctionListe importJunctions(JunctionListe structure){
        try (Scanner s = new Scanner(new File(System.getProperty("user.dir") + "/data/junctions.csv"), "UTF-8")){
                while (s.hasNextLine()){
                    String[] line = s.nextLine().split(";");
                    structure.add(new Junction(line[0], Double.parseDouble(line[1]), Double.parseDouble(line[2]), line[3]));
                    if (structure.getNil().getPrev().getValue().getType() == AIRPORT) {
                        structure.addAirport(structure.getNil().getPrev().getValue());
                    }
                }

        } catch (FileNotFoundException e){
            System.exit(1);
        }
        return structure;
    }

    public static QuadTree importJunctions() {
        JunctionListe structure = importJunctions(new JunctionListe());
        double x, y;
        double hMax = 0;
        double hMin = 0;
        double vMax = 0;
        double vMin = 0;
        // get boundaries
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

        QuadTree q = new QuadTree(vMax, hMin, vMin, hMax);

        // insert
        for (JunctionNode j = structure.getRoot(); j != structure.getNil(); j = j.getNext()) {
            q.add(j.getValue());
            if (j.getValue().getType() == AIRPORT) {
                q.addAirport(j.getValue());
            }
        }
        return q;
    }
}

package Java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Importer {

    public JunctionStructure importJunctions(JunctionStructure structure){
        SimpleList junctionList = new SimpleList();
        boolean firstEntry = true;
        try (Scanner s = new Scanner(new File(System.getProperty("user.dir") + "/data/junctions.csv"), "UTF-8")){
                while (s.hasNextLine()){
                    //Junction junction = new Junction();
                    String[] line = s.nextLine().split(";");/*
                    junction.setName(line[0]);
                    junction.setxPos(Double.parseDouble(line[1]));
                    junction.setyPos(Double.parseDouble(line[2]));
                    junction.setType(line[3].equals("AIRPORT") ? JunctionType.AIRPORT : JunctionType.TRAINSTATION);*/
                    // wäre so einfacher
                    junctionList.add(new Junction(line[0], Double.parseDouble(line[1]), Double.parseDouble(line[2]), line[3]));
                }

        } catch (FileNotFoundException e){
            System.exit(1);
        }
        return junctionList;
    }
}

import java.util.HashMap;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.File;
import java.util.Scanner;

public class DNAtool {

    private static HashMap<String, String> readGenome(String filename) throws FileNotFoundException {
        Scanner in;
        HashMap<String, String> genome = new HashMap<>();
        in = new Scanner(new File(filename));
        in.useDelimiter("\n");

        while(in.hasNext()) {
            String line = in.next();
            if (!line.startsWith("#")) {
                String[] lineArray = line.split("\t");
                genome.put(lineArray[0], lineArray[3]);
            }
        }
        return genome;
    }
    private static HashMap<String, HashMap<String, String>> readData(String filename) throws FileNotFoundException {
        Scanner in;
        HashMap<String, HashMap<String, String>> data = new HashMap<>();
        in = new Scanner(new File(filename));
        in.useDelimiter("\n");

        while(in.hasNext()) {
            String line = in.next();
            String[] lineArray = line.split("\t");
            HashMap<String, String> map;
            
            // System.out.printf("%s %s %s\n", lineArray[0], lineArray[1], lineArray[2]);
            if (data.containsKey(lineArray[0])) {
                map = data.get(lineArray[0]);
            }  else {
                map = new HashMap<>();
            }

            // System.out.printf("RSID: %s\nGenotype: %s\nInfo: %s\n\n", lineArray[0], lineArray[1], lineArray[2]);

            map.put(lineArray[1], lineArray[2]);
            data.put(lineArray[0], map);
        }

        return data;
    }

    private static void analyzeData(HashMap<String, HashMap<String, String>> data, HashMap<String, String> genome, int i)
            throws FileNotFoundException {
        String filename = "report" + i + ".txt";
        PrintWriter writer = new PrintWriter(filename);

        for (String rsid : data.keySet()) {
            String genotype = genome.get(rsid);
            HashMap<String, String> map = data.get(rsid);
            String info = map.get(genotype.trim());
            if (info == null) {
                writer.printf("Error, Information on genotype not found.");
                writer.printf("RSID: %s\nGenotype: %s\n\n", rsid, genotype);
                // System.out.print("\n\nToString:\n");
                // System.out.println(data.get(rsid).toString());
            } else {
                writer.printf("RSID: %s\nGenotype: %s\nInfo: %s\n\n", rsid, genotype, info);
            }
        }
        writer.close();
    }
    public static void main(String[] args) {
        HashMap<String, HashMap<String, String>> data = new HashMap<>();

        for (int i = 0; i < args.length; i++) {
            try {
                if (i == 0) {
                    data = readData(args[0]);
                } else {
                    HashMap<String, String> genome = readGenome(args[i]);
                    analyzeData(data, genome, i);
                }
            } catch (FileNotFoundException e) {
                System.err.println("Error: File Not Found.");
                System.exit(1);
            }
        }
    }
}
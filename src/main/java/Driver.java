import org.apache.commons.cli.*;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Driver {

    static String fileData = "";
    static ArrayList<Integer> parsedData = new ArrayList<>();
    static Random random = randomSeed(1);
    public static void main(String[] args) {
        int cacheSize = 3;
        Options options = new Options();

        String addressValue = "-1";
        String addressfileValue = "";
        int numaddressValue = 10;
        String policyValue = "FIFO";
        int clockbitsValue = 2;
        int cachesizeValue =3;
        int maxpageValue = 10;
        long seedValue = 0;
        Boolean notraceValue = false;
        Boolean computeValue = false;

        Option address = new Option("a", "addresses", true, "a set of comma-separated pages to access; \n" +
                "                -1 means randomly generate");
        //address.setRequired(true);
        options.addOption(address);

        Option addressfile = new Option("f", "addressfile", true, "a file with a bunch of addresses in it");
        //addressfile.setRequired(true);
        options.addOption(addressfile);

        Option numaddrs = new Option("n", "numaddrs", true, "if -a (--addresses) is -1, this is the number of addrs to generate");
        options.addOption(numaddrs);

        Option policy = new Option("p", "policy", true, "replacement policy: FIFO, LRU, OPT, UNOPT, RAND, CLOCK");
        options.addOption(policy);

        Option clockbits = new Option("b", "clockbits", true, "for CLOCK policy, how many clock bits to use");
        options.addOption(clockbits);

        Option cachesize = new Option("C", "cachesize", true, "size of the page cache, in pages");
        options.addOption(cachesize);

        Option maxpage = new Option("m", "maxpage", true, "if randomly generating page accesses, this is the max page number");
        options.addOption(maxpage);

        Option seed = new Option("s", "seed", true, "random number seed");
        options.addOption(seed);

        Option notrace = new Option("N", "notrace", true, "do not print out a detailed trace");
        options.addOption(notrace);

        Option compute = new Option("c", "compute", true, "compute answers for me");
        options.addOption(compute);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;


        try{
            cmd = parser.parse(options,args);
        } catch (ParseException e) {
            e.printStackTrace();
            System.exit(1);
        }

        if(cmd.hasOption("addresses")){
            addressValue = cmd.getOptionValue("addresses");
        }
        if(cmd.hasOption("addressfile")){
            addressfileValue = cmd.getOptionValue("addressfile");
        }
        if(cmd.hasOption("numaddrs")){
            numaddressValue = Integer.parseInt(cmd.getOptionValue("numaddrs"));
        }
        if(cmd.hasOption("policy")){
            policyValue = cmd.getOptionValue("policy");
        }
        if(cmd.hasOption("clockbits")){
            clockbitsValue = Integer.parseInt(cmd.getOptionValue("clockbits"));
        }
        if(cmd.hasOption("cachesize")){
            cachesizeValue = Integer.parseInt(cmd.getOptionValue("cachesize"));
        }
        if(cmd.hasOption("maxpage")){
            maxpageValue = Integer.parseInt(cmd.getOptionValue("maxpage"));
        }
        if(cmd.hasOption("seed")){
            seedValue = Long.parseLong(cmd.getOptionValue("seed"));
            random = randomSeed(seedValue);
        }
        if(cmd.hasOption("notrace")){
            notraceValue = Boolean.valueOf(cmd.getOptionValue("notrace"));
        }
        if(cmd.hasOption("compute")){
            computeValue = Boolean.valueOf(cmd.getOptionValue("compute"));
        }

        /*
        for(String arg : args) {
            if(arg.startsWith("--addresses")) {
                String addresses = arg.split("=")[1];
                String splitUpValues[] = addresses.split(",");
                for (String value : splitUpValues) {
                    list.add(Integer.valueOf(value));
                }
            }
            else if(arg.startsWith("--policy")) {
                policy = arg.split("=")[1];
            }
            else if(arg.startsWith("--cachesize")) {
                cacheSize = Integer.valueOf(arg.split("=")[1]);
            }
        }
         */
        //print all values
        System.out.println("ARG addresses " + addressValue);
        System.out.println("ARG addressfile " + addressfileValue);
        System.out.println("ARG numaddresses " + numaddressValue);
        System.out.println("ARG policy " + policyValue);
        System.out.println("ARG cachesize " + cachesizeValue);
        System.out.println("ARG maxpage " + maxpageValue);
        System.out.println("ARG seed " + seedValue);
        System.out.println("ARG notrace " + notraceValue);
        System.out.println("ARG compute " + computeValue);
        System.out.println("ARG clockbitsValue " + clockbitsValue);
        System.out.println("\nSolving...\n");

        if(!addressValue.equals("-1")){
            fileData = addressValue;
            getData(addressfileValue, addressValue, maxpageValue);
            System.out.println(parsedData);
        }
        if(!addressfileValue.isBlank()){
            getData(addressfileValue, addressValue, maxpageValue);
            System.out.println(parsedData);
        }

        LRUCache lruCache = new LRUCache(cacheSize);
        lruCache.compute(parsedData);
        //for(int l : list) {
        //   lruCache.refer(l);
        //}
    }

    public static void getFileData(String file){
        File f = null;
        try{
            f = new File(file);
        }catch (Exception e){
            System.out.println("Could not open/get File");
            return;
        }

        Scanner scan = null;
        try {
            scan = new Scanner(f);
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            System.out.println("File not found");
            return;
        }
        String data = "";
        while(scan.hasNext()){
            data += scan.nextLine();
        }
        fileData = data;
        getParsedData();
    }

    public static void getParsedData(){
        if(fileData.isEmpty()){
            System.out.println("Data empty");
            return;
        }
        String[] temp = fileData.split(",");
        ArrayList<Integer> list = new ArrayList<>();
        for (String s : temp) {
            list.add(Integer.valueOf(s));
        }
        parsedData = list;
    }

    public static void getData(String addressfileValue, String addressesValue, int max) {
        if(!addressfileValue.isBlank()){
            getFileData(addressfileValue);
        }else{
            if(addressesValue.equals("-1")){
                // not max but num address arg
                for (int i = 0; i < max; i++) {
                    int x = 0 + random.nextInt(max - 0 + 1);
                    parsedData.add(x);
                }
            }else{
                fileData = addressesValue;
                getParsedData();
            }
        }
    }

    static Random randomSeed(long seed) {
        return new Random(seed);
    }

}

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class JavaDriverLevels {

    public static void main(String[] args) throws FileNotFoundException {
        PrintStream fileStream = new PrintStream("output.txt");
        System.setOut(fileStream);

        System.out.println("CPU Max is: 1");
        System.out.println("RAM Max is: 2");
        System.out.println("HDD Max is: 5");
        LevelNode cpu = new LevelNode(null, null, 1, "CPU");
        LevelNode ram = new LevelNode(null, null, 2, "Ram");
        LevelNode hdd = new LevelNode(null, null, 5, "HDD");

        cpu.setLower(ram);

        ram.setUpper(cpu);
        ram.setLower(hdd);

        hdd.setUpper(ram);

        System.out.println();
        System.out.println("============================");
        System.out.println("Add values 1-9, max cap 8 in sys");
        System.out.println("============================");
        cpu.add(1);
        cpu.add(2);
        cpu.add(3);
        cpu.add(4);
        cpu.add(5);
        cpu.add(6);
        cpu.add(7);
        cpu.add(8);
        cpu.add(9);

        System.out.println(cpu);
        System.out.println(ram);
        System.out.println(hdd);

        System.out.println();
        System.out.println("============================");
        System.out.println("find data(1) from CPU: is null? " + (cpu.findData(1,0) == null));
        System.out.println("============================");

        System.out.println(cpu);
        System.out.println(ram);
        System.out.println(hdd);

        System.out.println();
        System.out.println("============================");
        System.out.println("Find Data 3 at cpu");
        System.out.println("============================");


        cpu.findData(3,0);

        System.out.println(cpu);
        System.out.println(ram);
        System.out.println(hdd);

        System.out.println();
        System.out.println("============================");
        System.out.println("Find Data 2 at cpu");
        System.out.println("============================");


        cpu.findData(2,0);

        System.out.println(cpu);
        System.out.println(ram);
        System.out.println(hdd);

        System.out.println();
        System.out.println("============================");
        System.out.println("Find Data 4 at ram");
        System.out.println("============================");


        ram.findData(4,0);

        System.out.println(cpu);
        System.out.println(ram);
        System.out.println(hdd);
    }
}

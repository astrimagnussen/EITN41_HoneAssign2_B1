
public class Main {


    public static void main(String[] args) {
        DiningCrypt firstExample = new DiningCrypt("0C73", "80C1", "A2A9", "92F5", "9B57", 0);
        System.out.println("First: "+ firstExample.send());

        DiningCrypt secondExample = new DiningCrypt("27C2", "0879", "35F6", "1A4D", "27BC", 1);
        System.out.println("Second: "+secondExample.send());
    }
}

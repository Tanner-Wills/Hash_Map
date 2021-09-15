import java.util.Arrays;

public class HashMapTesting {

    public static void main(String[] args) {
    ExternalChainingHashMap tester = new ExternalChainingHashMap();

    ExternalChainingMapEntry<String , Integer> apple = new ExternalChainingMapEntry<>("Apple", 1);
    ExternalChainingMapEntry<String , Integer> banana = new ExternalChainingMapEntry<>("Banana", 2);
    ExternalChainingMapEntry<String , Integer> orange = new ExternalChainingMapEntry<>("Orange", 3);
    ExternalChainingMapEntry<String , Integer> pear = new ExternalChainingMapEntry<>("Pear", 1);
    ExternalChainingMapEntry<String , Integer> apple1 = new ExternalChainingMapEntry<>("Apple", 33);
    ExternalChainingMapEntry<String , Integer> apple3 = new ExternalChainingMapEntry<>("Apple", 44);

    tester.put(apple.getKey(), apple.getValue());
    tester.put(apple1.getKey(), apple1.getValue());
    tester.put(apple3.getKey(), apple3.getValue());

    tester.put(banana.getKey(), banana.getValue());
    tester.put(orange.getKey(), orange.getValue());
    tester.put(pear.getKey(), pear.getValue());


    System.out.println(Arrays.toString(tester.getTable()));

    tester.remove(apple.getKey());
    System.out.println(Arrays.toString(tester.getTable()));

    }
}

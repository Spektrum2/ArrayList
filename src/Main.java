public class Main {
    public static void main(String[] args) {
        StringList stringList = new ArrayStringList();
        stringList.add("Hello");
        stringList.add("HelloTwo");
        StringList stringList2 = new ArrayStringList();
        stringList2.add("Hello");
        stringList2.add("HelloTwo");
        System.out.println(stringList.equals(stringList2));
        }
    }

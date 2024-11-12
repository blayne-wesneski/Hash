public class HashDemo {
    
    public static void main(String[] args) {
        Hash<String, String> map = new Hash<>(43);

        map.add("Alphie", "555-1234");
        map.add("Scolincolnlot", "555-4321");
        map.add("Skohottentot", "555-9876");

        System.out.println(map.getValue("Alphie"));
        map.remove("Alphie");
        System.out.println(map.getValue("Alphie"));

        Hash<String, String> map2 = new Hash<>(10);
        map2.add("Alphie", "555-1234");
        map2.add("Scolincolnlot", "555-4321");
        map2.add("Skohottentot", "555-9876");
        map2.add("Jenny", "867-5309");
        map2.add("Mike", "555-1212");
        map2.add("Alphys", "555-1212");
        map2.add("Alfredo", "555-1212");

        System.out.println(map2.getValue("Alphys"));
    }
}

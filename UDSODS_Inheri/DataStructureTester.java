public class DataStructureTester {

    private static SortedList testSortedList(SortedList sortedList) {
        System.out.println("Adding 7..");
        sortedList.add(7);
        System.out.println("Adding 9..");
        sortedList.add(9);
        System.out.println("Adding 2..");
        sortedList.add(2);
        System.out.println("Adding 6..");
        sortedList.add(6);
        System.out.println("Adding 3..");
        sortedList.add(3);
        System.out.println("Adding 5..");
        sortedList.add(5);
        System.out.println("Deleting an element..");
        int deleted = sortedList.delete();
        System.out.println("Deleted: " + deleted);
        System.out.println("Deleting an element..");
        deleted = sortedList.delete();
        System.out.println("Deleted: " + deleted);

        System.out.println("Top: " + sortedList.peek());
        System.out.println(sortedList.toString());
        System.out.println("Size: " + sortedList.size());
        return sortedList;
    }

    public static void main(String[] args) {
        SortedList sortedList = new SortedList(5);
        System.out.println("Testing SortedList");
        sortedList = testSortedList(sortedList);
        System.out.println("*********************************************************************");

    }
}

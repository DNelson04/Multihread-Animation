import java.util.Arrays;

public class SortThread extends Thread{
    public int sortType;
    private final int defaultElements = 40;
    public int[] array;

    public SortThread(String sortMethod){
        switch (sortMethod){
            case "Selection" -> this.sortType = 0;
            case "Insertion" -> this.sortType = 1;
            case "Bubble" -> this.sortType = 2;
            case "Merge" -> this.sortType = 3;
            case "Quick" -> this.sortType = 4;
        }
        //So if they dont pass in an array, we can make a default one.
        //we'll allow people to input their own stupid numbers, but
        // we'll find a way to scale the values themselves to fit the
        // gui, but for now the default will be like 40 elements
        //and the range will be from 0 to 70
        //so
        array = new int[defaultElements];
        for(int i = 0; i < defaultElements; i++){
            array[i] = (int)(Math.random()*70);
        }
    }
    public SortThread(String sortMethod, int[] unsortedArr){
        switch (sortMethod){
            case "Selection" -> this.sortType = 0;
            case "Insertion" -> this.sortType = 1;
            case "Bubble" -> this.sortType = 2;
            case "Merge" -> this.sortType = 3;
            case "Quick" -> this.sortType = 4;
        }
        array = new int[unsortedArr.length];
        System.arraycopy(unsortedArr, 0, array, 0, unsortedArr.length);
    }
    public void beginSort(){
        beginSort0();
    }
    private void beginSort0(){
        int sortedIndex, element, sortMethod;
    }

    public String toString(){
        return Arrays.toString(array);
    }
}

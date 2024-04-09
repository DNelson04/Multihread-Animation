import java.util.Arrays;

public class SortThread extends Thread{
    public int sortType;

    int sortedIndex, element;
    //for use with insertion sort, need global variables
    // to maintain over multiple run calls
    private final int defaultElements = 6;
    public int[] array;

    public SortThread(String sortMethod){
        switch (sortMethod){
            case "s" -> this.sortType = 0;
            case "i" -> this.sortType = 1;
            case "b" -> this.sortType = 2;
            case "m" -> this.sortType = 3;
            case "q" -> this.sortType = 4;
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
        System.out.println(Arrays.toString(array));
        beginSort(this.sortType);
    }
    public SortThread(String sortMethod, int[] unsortedArr){

        switch (sortMethod){
            case "s" -> this.sortType = 0;
            case "i" -> this.sortType = 1;
            case "b" -> this.sortType = 2;
            case "m" -> this.sortType = 3;
            case "q" -> this.sortType = 4;
        }
        array = new int[unsortedArr.length];
        System.arraycopy(unsortedArr, 0, array, 0, unsortedArr.length);
    }
    public void beginSort(int sortType){
        start();
    }

    @Override
    public void run(){
        switch (sortType){
            case 0 -> sSort();
            case 1 -> {
                iSort();
                System.out.println(Arrays.toString(array));
            }
            case 2 -> bSort();
            case 3 -> mSort();
            case 4 -> qSort();

        }
    }

    private void qSort() {

    }

    private void sSort() {

    }

    private void iSort() {
        int element;
        for(int i = 0; i < array.length; i++){
            element = array[i];
            for(int j = i-1; j >= 0; j--){
                int compareTo = array[j];
                System.out.println("is " + element + " less than " + compareTo);
                if(element < compareTo){
                    array[j+1] = compareTo;
                    array[j] = element;
                }
                else{
                    j=0;
                }
            }
        }
    }

    private void bSort() {

    }

    private void mSort() {

    }

    public String toString(){
        return Arrays.toString(array);
    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }
}

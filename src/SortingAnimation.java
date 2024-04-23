import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class SortingAnimation extends JFrame{

    public int sortType;
    private final SortThread animationThread;
    private int windowHeight;
    private int windowWidth;
    private int estFinishTime;
    private int numElements;

    public SortingAnimation(String sortMethod){
        this(sortMethod, 8000, 60);
    }
    public SortingAnimation(String sortMethod, int estFinishTime, int numElements){
        windowHeight = 1000;
        windowWidth = 1000;
        switch (sortMethod){
            case "s" -> this.sortType = 0;
            case "i" -> this.sortType = 1;
            case "b" -> this.sortType = 2;
            case "m" -> this.sortType = 3;
            case "q" -> this.sortType = 4;
            default -> throw new RuntimeException("Unrecognized Sort Method");
        }
        this.estFinishTime = estFinishTime;
        this.numElements = numElements;
        animationThread = new SortThread();


        setTitle("Sorting Animation");
        setSize(windowWidth, windowHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel arrayPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                //TODO: figure out how to fit elements flush, and resize elements based on window size
                super.paintComponent(g);
                setBackground(Color.BLACK);
                int offset = 0;
                int[] array = animationThread.getArray();
                int x = windowWidth / array.length;
                double remainder = (double)(x) - x;
                for(int i = 0; i < animationThread.getArray().length; i++){
                    x = windowWidth / array.length;

                    /*
                    remainder*=2;
                    if(remainder > 1){
                        offset++;
                        remainder--;
                    }
                    This doesn't work :(
                     */

                    int y = array[i] * windowHeight / animationThread.getMaxRange();
                    if(i != animationThread.elementIndex){
                        g.setColor(Color.MAGENTA);
                    }else{
                        g.setColor(Color.RED);
                    }
                    g.fillRect(i * x + offset, windowHeight - y, x, y);
                }

            }
        };
        add(arrayPanel);
        setVisible(true);
        startAnimation();
    }

    public class SortThread extends Thread{

        //region Declarations
        //array, default value declaration
        public int[] array;
        private int maxRange = 350;
        private int speedIncrement = 8000;

        //for use with insertion sort, need global variables
        // to maintain over multiple run calls
        int element, elementIndex;

        //globals for selection sort
        int currentMin, minIndex;
        //endregion

        //primary constructor
        public SortThread(){
            array = new int[numElements];
            for(int i = 0; i < numElements; i++){
                array[i] = (int)(Math.random()*maxRange);
            }
            System.out.println(Arrays.toString(array));
        }

        @Override
        public void run(){
            switch (sortType){
                case 0 -> {
                    try {
                        speedIncrement = estFinishTime/(numElements*numElements);
                        sSort();
                        System.out.println(Arrays.toString(array));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 1 -> {
                    try {
                        speedIncrement = estFinishTime/(numElements*numElements);
                        iSort();
                        System.out.println(Arrays.toString(array));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 2 -> {
                    try {
                        speedIncrement = estFinishTime/(numElements*numElements);
                        bSort();
                        System.out.println(Arrays.toString(array));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 3 -> {
                    try {
                        speedIncrement = estFinishTime/(numElements*numElements);
                        mSort();
                        System.out.println(Arrays.toString(array));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 4 -> {
                    try {
                        speedIncrement = estFinishTime/(numElements*numElements);
                        qSort();
                        System.out.println(Arrays.toString(array));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }



        private void sSort() throws InterruptedException {
            for(int sortedIndex = 0; sortedIndex < array.length; sortedIndex++){
                currentMin = array[sortedIndex];
                minIndex = sortedIndex;
                for(int j = sortedIndex; j < array.length; j++){
                    if(array[j] < currentMin){
                        minIndex = j;
                        currentMin = array[j];
                    }
                    SwingUtilities.invokeLater(SortingAnimation.this::repaint);
                    Thread.sleep(speedIncrement);
                }
                array[minIndex] = array[sortedIndex];
                array[sortedIndex] = currentMin;
            }
        }

        private void iSort() throws InterruptedException {
            for (int sortedIndex = 0; sortedIndex < array.length-1; sortedIndex++){
                element = array[sortedIndex + 1];
                for (int j = sortedIndex; j >= 0; j--) {
                    elementIndex = j;
                    int compareTo = array[j];
                    if (element < compareTo) {
                        array[j + 1] = compareTo;
                        array[j] = element;
                    } else {
                        j = -1;
                    }
                    SwingUtilities.invokeLater(SortingAnimation.this::repaint);
                    Thread.sleep(speedIncrement);
                }
            }
        }

        private void bSort() throws InterruptedException {
            //TODO: update highlighted element in GUI
            boolean sorting = true;
            int toCompare;
            while (sorting){
                sorting = false;
                for(int index = 0; index < array.length-1; index++) {
                    toCompare = array[index];
                    if(toCompare > array[index+1]){
                        array[index] = array[index+1];
                        array[index+1] = toCompare;
                        sorting = true;
                    }
                    SwingUtilities.invokeLater(SortingAnimation.this::repaint);
                    Thread.sleep(speedIncrement);
                }
            }
        }

        private void mSort() throws InterruptedException {

        }

        private void qSort() throws InterruptedException {

        }

        public String toString(){
            return Arrays.toString(array);
        }

        private int[] getArray() {
            return array;
        }

        public int getMaxRange() {
            return maxRange;
        }

        public int getNumElements() {
            return numElements;
        }

        private int getElementAt(int index){
            return array[index];
        }

        private int getElementIndex(int value){
            int arrIndex;
            for(int i = 0; i < array.length; i ++){
                if(array[i] == value){
                    arrIndex = i;
                    return arrIndex;
                }
            }
            return -1;
        }

        private void setArray(int[] array) {
            this.array = array;
        }
    }

    public void startAnimation(){
        animationThread.start();
    }

    public String toString(){
        return animationThread.toString();
    }
}
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class SortingAnimation extends JFrame{

    private final String sortMethod;
    private SortThread animationThread;
    private int windowHeight, windowWidth;

    public SortingAnimation(String sortMethod, int speedIncrement){
        windowHeight = 1000;
        windowWidth = 1000;
        if(sortMethod.equals("s")|| sortMethod.equals("i") ||
                sortMethod.equals("b") || sortMethod.equals("m") ||
                sortMethod.equals("q")){
            this.sortMethod = sortMethod;
        } else {
            throw new RuntimeException("Unrecognized Sort Method");
        }
        animationThread = new SortThread(this.sortMethod);

        startAnimation();
        setTitle("Sorting Animation");
        setSize(windowWidth, windowHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel arrayPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(Color.BLACK);
                for(int i = 0; i < animationThread.getArray().length; i++){
                    int x = animationThread.getArray().length;
                    int y = animationThread.getElementAt(i);
                    g.setColor(Color.CYAN);
                    System.out.println("hello");
                    //this isn't showing up rn bc the window vars are wrong somehow
                    g.fillRect(windowWidth, windowHeight, x, y);
                }

            }
        };
        add(arrayPanel);
        setVisible(true);
    }

    public class SortThread extends Thread{
        public int sortType;

        int sortedIndex, element;
        //for use with insertion sort, need global variables
        // to maintain over multiple run calls

        public int[] array;

        //primary constructor
        public SortThread(String sortMethod){
            switch (sortMethod){
                case "s" -> this.sortType = 0;
                case "i" -> this.sortType = 1;
                case "b" -> this.sortType = 2;
                case "m" -> this.sortType = 3;
                case "q" -> this.sortType = 4;
            }
            //So if they don't pass in an array, we can make a default one.
            //we'll allow people to input their own stupid numbers, but
            // we'll find a way to scale the values themselves to fit the
            // gui, but for now the default will be like 40 elements
            //and the range will be from 0 to 70
            //so
            int defaultElements = 40;
            array = new int[defaultElements];
            for(int i = 0; i < defaultElements; i++){
                array[i] = (int)(Math.random()*350);
            }
        }

        //overloaded constructor for manual array entry
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
        public void beginSort(){
            start();
        }

        @Override
        public void run(){
            switch (sortType){
                case 0 -> {
                    try {
                        Thread.sleep(200);
                        sSort();
                        repaint();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 1 -> {
                    try {
                        Thread.sleep(200);
                        iSort();
                        repaint();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 2 -> {
                    try {
                        Thread.sleep(200);
                        bSort();
                        repaint();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 3 -> {
                    try {
                        Thread.sleep(200);
                        mSort();
                        repaint();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 4 -> {
                    try {
                        Thread.sleep(200);
                        qSort();
                        repaint();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        }

        private void qSort() {

        }

        private void sSort() {

        }

        private void iSort() {
                element = array[sortedIndex + 1];
                for (int j = sortedIndex; j >= 0; j--) {
                    int compareTo = array[j];
                    if (element < compareTo) {
                        array[j + 1] = compareTo;
                        array[j] = element;
                    } else {
                        j = 0;
                    }
                }
                sortedIndex++;
        }

        private void bSort() {

        }

        private void mSort() {

        }

        public String toString(){
            return Arrays.toString(array);
        }

        private int[] getArray() {
            return array;
        }

        private void setArray(int[] array) {
            this.array = array;
        }

        private int getElementAt(int index){
            return array[index];
        }
    }

    public void startAnimation(){
        animationThread.start();
    }
    public String toString(){
        return animationThread.toString();
    }
}
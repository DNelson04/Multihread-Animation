import javax.swing.*;

public class SortingAnimation extends JFrame {

    private String sortMethod;
    private int numElements;
    Thread animationThread;

    public SortingAnimation(String sortMethod, int speedIncrement){
        if(sortMethod.equals("s")|| sortMethod.equals("i") ||
                sortMethod.equals("bubble") || sortMethod.equals("merge") || sortMethod.equals("quick")){
            this.sortMethod = sortMethod;
        }
        else {
            throw new RuntimeException("Unrecognized Sort Method");
        }
        animationThread = new SortThread(this.sortMethod);

    }

    public void startAnimation(){
        animationThread.start();
    }
    public String toString(){
        return animationThread.toString();
    }
}
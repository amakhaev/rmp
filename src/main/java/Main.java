import com.rmp.mediator.RMPApplication;

public class Main {

    public static void main(String[] args) {
        RMPApplication mediator = new RMPApplication();
        mediator.initialize();
        mediator.showUI();
    }

}

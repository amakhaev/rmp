import com.rmp.mediator.UIMediator;

public class Main {

    public static void main(String[] args) {
        UIMediator mediator = new UIMediator();
        mediator.initialize();
        mediator.showUI();
    }

}

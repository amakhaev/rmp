import com.google.inject.Guice;
import com.google.inject.Injector;
import com.rmp.dao.DaoBeanModule;
import com.rmp.mediator.RMPApplication;

public class Main {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AppBeanModule(), new DaoBeanModule());
        RMPApplication mediator = injector.getInstance(RMPApplication.class);
        mediator.initialize();
        mediator.showUI();
    }
}

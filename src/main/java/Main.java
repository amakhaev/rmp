import com.google.inject.Guice;
import com.google.inject.Injector;
import com.rmp.mediator.RMPApplication;
import com.rmp.mediator.listeners.ControlPanelEventListener;
import com.rmp.mediator.listeners.PlaylistEventListener;
import com.rmp.mediator.mediaPlayer.PlayerMediator;

public class Main {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AppBeanModule());
        createAppBeans(injector);

        RMPApplication mediator = injector.getInstance(RMPApplication.class);
        mediator.initialize();
        mediator.showUI();
    }

    private static void createAppBeans(Injector injector) {
        PlayerMediator playerMediator = injector.getInstance(PlayerMediator.class);

        PlaylistEventListener playlistHandler = new PlaylistEventListener(playerMediator);
        ControlPanelEventListener controlPanelEventHandler = new ControlPanelEventListener(playerMediator);

        injector.injectMembers(playlistHandler);
        injector.injectMembers(controlPanelEventHandler);
    }
}

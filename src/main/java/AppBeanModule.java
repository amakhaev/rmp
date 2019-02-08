import com.google.inject.AbstractModule;
import com.rmp.mediator.RMPApplication;
import com.rmp.mediator.RMPApplicationImpl;
import com.rmp.mediator.watchers.ControlPanelWatcher;
import com.rmp.mediator.watchers.MediaDetailWatcher;
import com.rmp.mediator.watchers.PlaylistWatcher;
import com.rmp.widget.dataWatcher.ControlPanelDataWatcher;
import com.rmp.widget.dataWatcher.MediaDetailDataWatcher;
import com.rmp.widget.dataWatcher.PlaylistDataWatcher;

public class AppBeanModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(RMPApplication.class).to(RMPApplicationImpl.class);
        bind(ControlPanelDataWatcher.class).to(ControlPanelWatcher.class);
        bind(PlaylistDataWatcher.class).to(PlaylistWatcher.class);
        bind(MediaDetailDataWatcher.class).to(MediaDetailWatcher.class);
    }

}

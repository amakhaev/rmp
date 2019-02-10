import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.rmp.dao.domain.mediaFile.MediaFileDao;
import com.rmp.dao.domain.mediaFile.MediaFileDaoImpl;
import com.rmp.dao.domain.playlist.PlaylistDao;
import com.rmp.dao.domain.playlist.PlaylistDaoImpl;
import com.rmp.dao.domain.state.StateDao;
import com.rmp.dao.domain.state.StateDaoImpl;
import com.rmp.mediator.RMPApplication;
import com.rmp.mediator.RMPApplicationImpl;
import com.rmp.mediator.listeners.ControlPanelEventListener;
import com.rmp.mediator.listeners.PlaylistEventListener;
import com.rmp.mediator.mediaPlayer.PlayerMediator;
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

    @Provides
    PlaylistEventListener createPlaylistEventListener(PlayerMediator playerMediator) {
        return new PlaylistEventListener(playerMediator);
    }

    @Provides
    ControlPanelEventListener createControlPanelEventListener(PlayerMediator playerMediator) {
        return new ControlPanelEventListener(playerMediator);
    }

}

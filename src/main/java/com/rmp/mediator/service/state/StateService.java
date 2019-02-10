package com.rmp.mediator.service.state;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.rmp.dao.domain.state.StateDao;
import com.rmp.dao.domain.state.StateModel;
import com.rmp.dao.domain.state.TimeLabelOrder;

/**
 * Provides service to work with state of player
 */
@Singleton
public class StateService {

    private final StateDao stateDao;

    /**
     * Initialize new instance of {@link StateDao}
     */
    @Inject
    public StateService(StateDao stateDao) {
        this.stateDao = stateDao;
    }

    /**
     * Gets the current state of player
     */
    public StateModel getCurrentState() {
        return this.stateDao.findFirst();
    }

    /**
     * Updates the playlist file in the state
     *
     * @param currentPlaylistFile - the file value in state
     * @return updated {@link StateModel} instance
     */
    public StateModel updatePlaylistFile(Integer currentPlaylistFile) {
        StateModel current = this.getCurrentState();
        current.setPlaylistFileId(currentPlaylistFile);
        return this.updateState(current);
    }

    /**
     * Updates the playlist file in the state
     *
     * @param currentPlaylistId - the playlist id value in state
     * @return updated {@link StateModel} instance
     */
    public StateModel updatePlaylistId(int currentPlaylistId) {
        StateModel current = this.getCurrentState();
        current.setPlaylistId(currentPlaylistId);
        return this.updateState(current);
    }

    /**
     * Updates the time label order in the state
     *
     * @param timeLabelOrder - the value
     * @return updated {@link StateModel} instance
     */
    public StateModel updateTimeLabelOrder(TimeLabelOrder timeLabelOrder) {
        StateModel current = this.getCurrentState();
        current.setTimeLabelOrder(timeLabelOrder);
        return this.updateState(current);
    }

    /**
     * Sets the mute to app state
     *
     * @param isMute - the mute state of player
     * @return updated {@link StateModel} instance
     */
    public StateModel updateMuteState(boolean isMute) {
        StateModel current = this.getCurrentState();
        current.setMute(isMute);
        return this.updateState(current);
    }

    /**
     * Updates the volume value of state
     *
     * @param value - the volume value
     * @return updated {@link StateModel} instance
     */
    public StateModel updateVolumeValue(Integer value) {
        StateModel current = this.getCurrentState();
        current.setVolumeValue(value);
        return this.updateState(current);
    }

    /**
     * Updates the current state
     *
     * @param state - the state to update
     * @return the updated {@link StateModel} instance
     */
    private StateModel updateState(StateModel state) {
        return this.stateDao.update(state);
    }
}

package com.rmp.mediator.service.state;

import com.rmp.dao.domain.state.StateDao;
import com.rmp.dao.domain.state.StateModel;

/**
 * Provides service to work with state of player
 */
public class StateService {

    private final StateDao stateDao;

    /**
     * Initialize new instance of {@link StateDao}
     */
    public StateService() {
        this.stateDao = StateDao.INSTANCE;
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
     * Updates the current state
     *
     * @param state - the state to update
     * @return the updated {@link StateModel} instance
     */
    private StateModel updateState(StateModel state) {
        return this.stateDao.update(state);
    }
}

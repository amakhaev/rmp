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
     * Updates the current state
     *
     * @param state - the state to update
     * @return the updated {@link StateModel} instance
     */
    public StateModel updateState(StateModel state) {
        return this.stateDao.update(state);
    }
}

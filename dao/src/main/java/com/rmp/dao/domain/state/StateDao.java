package com.rmp.dao.domain.state;

/**
 * Provides the state of player
 */
public interface StateDao {

    StateDao INSTANCE = new StateDaoImpl();

    /**
     * Finds the first record.
     *
     * @return the {@link StateModel} instance.
     */
    StateModel findFirst();

    /**
     * Updates the player state
     *
     * @param state - the state to be updated
     * @return updated {@link StateModel} instance.
     */
    StateModel update(StateModel state);

}

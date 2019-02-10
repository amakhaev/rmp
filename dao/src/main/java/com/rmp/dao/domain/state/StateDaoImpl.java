package com.rmp.dao.domain.state;

import com.google.inject.Inject;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.rmp.dao.configuration.DatabaseConfiguration;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;

/**
 * Provides the implementation of state DAO.
 */
@Slf4j
public class StateDaoImpl implements StateDao {

    private Dao<StateEntity, Integer> dao;
    private StateMapper mapper;

    /**
     * Initialize new instance of {@link StateDaoImpl}
     */
    @Inject
    public StateDaoImpl(ConnectionSource connectionSource) {
        this.mapper = StateMapper.INSTANCE;
        try {
            this.dao = DaoManager.createDao(connectionSource, StateEntity.class);
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * Finds the first record.
     *
     * @return the {@link StateModel} instance.
     */
    @Override
    public StateModel findFirst() {
        try {
            return this.mapper.entityToModel(
                    this.dao.queryBuilder().limit(1L).queryForFirst()
            );
        } catch (SQLException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * Updates the player state
     *
     * @param state - the state to be updated
     * @return updated {@link StateModel} instance.
     */
    @Override
    public StateModel update(StateModel state) {
        try {
            this.dao.update(this.mapper.modelToEntity(state));
            return this.mapper.entityToModel(
                    this.dao.queryBuilder().limit(1L).where().eq(StateEntity.ID_FIELD, state.getId()).queryForFirst()
            );
        } catch (SQLException e) {
            log.error(e.getMessage());
            return null;
        }
    }
}

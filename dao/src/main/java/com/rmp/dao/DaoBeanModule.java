package com.rmp.dao;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.j256.ormlite.support.ConnectionSource;
import com.rmp.dao.configuration.DatabaseConfiguration;
import com.rmp.dao.domain.mediaFile.MediaFileDao;
import com.rmp.dao.domain.mediaFile.MediaFileDaoImpl;
import com.rmp.dao.domain.playlist.PlaylistDao;
import com.rmp.dao.domain.playlist.PlaylistDaoImpl;
import com.rmp.dao.domain.state.StateDao;
import com.rmp.dao.domain.state.StateDaoImpl;

public class DaoBeanModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(MediaFileDao.class).to(MediaFileDaoImpl.class);
        bind(PlaylistDao.class).to(PlaylistDaoImpl.class);
        bind(StateDao.class).to(StateDaoImpl.class);
    }

    @Provides
    public ConnectionSource createConnectionSource(DatabaseConfiguration dbConfiguration) {
        return dbConfiguration.getSqliteConnection();
    }

}

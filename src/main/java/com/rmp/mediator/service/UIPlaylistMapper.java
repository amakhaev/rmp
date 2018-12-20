package com.rmp.mediator.service;

import com.rmp.dao.domain.playlist.PlaylistModel;
import com.rmp.widget.readModels.UIPlaylistModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Provides the mapper from data model to ui model
 */
@Mapper
public interface UIPlaylistMapper {

    UIPlaylistMapper INSTANCE = Mappers.getMapper(UIPlaylistMapper.class);

    /**
     * Converts the entity to model.
     *
     * @param dataModel - the data model to convert.
     * @return the UI Model instance.
     */
    UIPlaylistModel dataModelToUIModel(PlaylistModel dataModel);

    /**
     * Converts the models to entities.
     *
     * @param dataModels - the data models to convert
     * @return the List<UIModel> instance
     */
    List<UIPlaylistModel> dataModelsToUIModels(List<PlaylistModel> dataModels);

}
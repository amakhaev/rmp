package com.rmp.dao.domain.playlist;

import com.rmp.dao.domain.DataMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Provides the mapper to work with playlists
 */
@Mapper
public interface PlaylistMapper extends DataMapper {

    PlaylistMapper INSTANCE = Mappers.getMapper(PlaylistMapper.class);

    /**
     * Converts the entity to model.
     *
     * @param entity - the entity to convert.
     * @return the Model instance.
     */
    @Mapping(source = "createdAtAsString", target = "createdAt", qualifiedByName = "timestampConverter")
    PlaylistModel entityToModel(PlaylistEntity entity);

    /**
     * Converts the model to entity.
     *
     * @param model - the model to convert.
     * @return the Entity instance.
     */
    PlaylistEntity modelToEntity(PlaylistModel model);

    /**
     * Converts the entities to models.
     *
     * @param entities - the entities to convert
     * @return the List<Model> instance
     */
    List<PlaylistModel> entitiesToModels(List<PlaylistEntity> entities);
}


package com.rmp.dao.domain.state;

import com.rmp.dao.domain.DataMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Provides the mapper to work with playlists
 */
@Mapper
interface StateMapper extends DataMapper {

    StateMapper INSTANCE = Mappers.getMapper(StateMapper.class);

    /**
     * Converts the entity to model.
     *
     * @param entity - the entity to convert.
     * @return the Model instance.
     */
    StateModel entityToModel(StateEntity entity);

    /**
     * Converts the model to entity.
     *
     * @param model - the model to convert.
     * @return the Entity instance.
     */
    StateEntity modelToEntity(StateModel model);

}

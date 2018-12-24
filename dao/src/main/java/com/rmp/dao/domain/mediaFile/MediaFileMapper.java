package com.rmp.dao.domain.mediaFile;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Provides the mapper to use media files
 */
@Mapper
interface MediaFileMapper {

    MediaFileMapper INSTANCE = Mappers.getMapper(MediaFileMapper.class);

    /**
     * Converts the entity to model.
     *
     * @param entity - the entity to convert.
     * @return the Model instance.
     */
    MediaFileModel entityToModel(MediaFileEntity entity);

    /**
     * Converts the model to entity.
     *
     * @param model - the model to convert.
     * @return the Entity instance.
     */
    MediaFileEntity modelToEntity(MediaFileModel model);

    /**
     * Converts the entities to models.
     *
     * @param entities - the entities to convert
     * @return the List<Model> instance
     */
    List<MediaFileModel> entitiesToModels(List<MediaFileEntity> entities);

    /**
     * Converts the models to entities.
     *
     * @param models - the models to convert
     * @return the List<Entity> instance
     */
    List<MediaFileEntity> modelsToEntities(List<MediaFileModel> models);
}

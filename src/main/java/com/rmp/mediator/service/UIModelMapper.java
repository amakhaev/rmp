package com.rmp.mediator.service;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Provides base mapper from database model to UI model
 */
public abstract class UIModelMapper<Model, UIModel> {

    private ModelMapper modelMapper;
    private final Class<Model> dataModelClass;
    private final Class<UIModel> uiModelClass;

    /**
     * Initialize new instance of {@link UIModelMapper}
     */
    public UIModelMapper(Class<Model> dataModelClass, Class<UIModel> uiModelClass) {
        this.modelMapper = new ModelMapper();
        this.dataModelClass = dataModelClass;
        this.uiModelClass = uiModelClass;
    }

    /**
     * Maps the data model to watchers model
     *
     * @param dataModel - the model for mapping
     * @return watchers representation of model
     */
    public UIModel dataModelToUIModel(Model dataModel) {
        return this.modelMapper.map(dataModel, this.uiModelClass);
    }

    /**
     * Maps the watchers model to data model
     *
     * @param dataModel - the model for mapping
     * @return watchers representation of model
     */
    public Model UiModelToDataModel(UIModel dataModel) {
        return this.modelMapper.map(dataModel, this.dataModelClass);
    }

    /**
     * Maps the data models to watchers models
     *
     * @param dataModels - the models for mapping
     * @return watchers representation of model
     */
    public List<UIModel> dataModelsToUIModels(List<Model> dataModels) {
        if (dataModels == null) {
            return new ArrayList<>();
        }

        return dataModels.stream()
                .map(this::dataModelToUIModel)
                .collect(Collectors.toList());
    }
}

package com.rmp.widget.dataWatcher;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides the observer that indicates when data will be changed
 */
public class DataObserver<T> {

    private List<DataChangeHandler<T>> dataChangeHandlers;

    /**
     * Initialize new instance of {@link DataObserver}
     */
    public DataObserver() {
        this.dataChangeHandlers = new ArrayList<>();
    }

    /**
     * Emits the data changing
     *
     * @param data - the new value of data
     */
    public void emit(T data) {
        if (!this.dataChangeHandlers.isEmpty()) {
            this.dataChangeHandlers.forEach(handler -> handler.onChange(data));
        }
    }

    /**
     * Subscribes the handlers on data change
     *
     * @param changeHandler - the handler of data changing
     */
    public void subscribe(DataChangeHandler<T> changeHandler) {
        this.dataChangeHandlers.add(changeHandler);
    }
}

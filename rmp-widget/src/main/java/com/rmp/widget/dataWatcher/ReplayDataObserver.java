package com.rmp.widget.dataWatcher;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides the observer that indicates when data will be changed
 */
public class ReplayDataObserver<T> {

    private List<DataChangeHandler<T>> dataChangeHandlers;
    private List<T> dataHistory;

    /**
     * Initialize new instance of {@link ReplayDataObserver}
     */
    public ReplayDataObserver() {
        this.dataChangeHandlers = new ArrayList<>();
        this.dataHistory = new ArrayList<>();
    }

    /**
     * Emits the data changing
     *
     * @param data - the new value of data
     */
    public void emit(T data) {
        this.dataHistory.add(data);

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
        this.dataHistory.forEach(changeHandler::onChange);
    }
}

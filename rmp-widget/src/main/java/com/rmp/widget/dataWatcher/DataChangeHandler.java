package com.rmp.widget.dataWatcher;

/**
 * Provides the interface that handles changing of data
 */
@FunctionalInterface
public interface DataChangeHandler<T> {

    /**
     * Handles the data changing
     *
     * @param data - the changed data
     */
    void onChange(T data);

}

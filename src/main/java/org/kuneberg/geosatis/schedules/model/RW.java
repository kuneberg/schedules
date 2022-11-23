package org.kuneberg.geosatis.schedules.model;

public class RW<T> {
    private boolean success;
    private String message;
    private T data;

    public boolean isSuccess() {
        return success;
    }

    public RW<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public RW<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public RW<T> setData(T data) {
        this.data = data;
        return this;
    }

    public static <T> RW<T> ok(T data) {
        return new RW<T>().setSuccess(true).setData(data);
    }

    public static <T> RW<T> fail(String message) {
        return new RW<T>().setSuccess(false).setMessage(message);
    }
}

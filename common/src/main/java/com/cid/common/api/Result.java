package com.cid.common.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * JSend
 * https://github.com/omniti-labs/jsend
 * <p>
 * 1. Success
 * - Description : All went well, and (usually) some data was returned.
 * - Required Keys : status, data
 * <p>
 * 2. Fail
 * - Description : There was a problem with the data submitted,
 * or some pre-condition of the API call wasn't satisfied
 * - Required Keys : status, data
 * <p>
 * 3. Error
 * - Description : An error occurred in processing the request, i.e. an exception was thrown
 * - Required Keys : status, message
 * - Optional Keys : code, data
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // ignore null fields
public class Result<T> implements Serializable {

    /**
     * 상태(success, fail, error)
     */
    @NotNull
    private String status;

    /**
     * 메세지
     */
    private String message;

    /**
     * 에러 코드
     */
    private String code;

    /**
     * 데이터
     */
    private T data;

    /************************
     * success
     ************************/
    public static Result<Object> ok() {
        Result<Object> r = new Result<>();
        r.setStatus(Status.SUCCESS.value());
        return r;
    }

    public static Result<Object> ok(String message) {
        Result<Object> r = new Result<>();
        r.setStatus(Status.SUCCESS.value());
        r.setMessage(message);
        return r;
    }

    public static <T> Result<T> ok(T data) {
        Result<T> r = new Result<>();
        r.setStatus(Status.SUCCESS.value());
        r.setData(data);
        return r;
    }

    /************************
     * fail
     ************************/
    public static Result<Object> fail() {
        Result<Object> r = new Result<>();
        r.setStatus(Status.FAIL.value());
        return r;
    }

    public static <T> Result<T> fail(T data) {
        Result<T> r = new Result<>();
        r.setStatus(Status.FAIL.value());
        r.setData(data);
        return r;
    }

    /************************
     * error
     ************************/
    public static Result<Object> error(String message) {
        Result<Object> r = new Result<>();
        r.setStatus(Status.ERROR.value());
        r.setMessage(message);
        return r;
    }

    public static Result<Object> error(String message, String code) {
        Result<Object> r = new Result<>();
        r.setStatus(Status.ERROR.value());
        r.setMessage(message);
        r.setCode(code);
        return r;
    }

    public static <T> Result<T> error(String message, String code, T data) {
        Result<T> r = new Result<>();
        r.setStatus(Status.ERROR.value());
        r.setMessage(message);
        r.setCode(code);
        r.setData(data);
        return r;
    }

    private Result() {
        setStatus(Status.SUCCESS.value());
    }

    public enum Status {
        SUCCESS("success"),
        FAIL("fail"),
        ERROR("error");

        private final String s;

        Status(String s) {
            this.s = s;
        }

        String value() {
            return s;
        }
    }
}

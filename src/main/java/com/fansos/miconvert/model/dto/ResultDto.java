package com.fansos.miconvert.model.dto;

import com.fansos.miconvert.constans.ResultCodeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author lzq
 * @date 2022-10-10 11:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {
        "successResult",
        "errorResult"
})
public class ResultDto<T> implements Cloneable {

    /**
     * 自定义状态码
     */
    private Integer code;

    /**
     * 返回消息提示
     */
    private String message;

    /**
     * 泛型，返回消息实体类对象
     */
    private T data;

    /**
     * 建造者模式
     * @param <T>
     */
    public static class Builder<T> {

        private final ResultDto<T> resultDto;

        public Builder(Integer code) {
            resultDto = new ResultDto<T>();
            resultDto.setCode(code);
        }

        public Builder<T> message(String message) {
            resultDto.setMessage(message);
            return this;
        }

        public Builder<T> data(T data) {
            resultDto.setData(data);
            return this;
        }

        public ResultDto<T> build() {
            return resultDto;
        }
    }

}

package com.fansos.miconvert.constant;

/**
 * @author fzw
 * 转换相关枚举类型
 * @date 2022-10-13 17:37
 */
public class ConvertEnum {


    public enum ConvertUtilEnum {
        //文件转换工具
        LIBRE_OFFICE(1, "libreoffice"),
        PDF2DOCX(2, "pdf2docx");

        private Integer code;
        private String message;

        ConvertUtilEnum(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;

        }
    }
}

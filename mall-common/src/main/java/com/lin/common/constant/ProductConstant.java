package com.lin.common.constant;

/**
 * @projectName: mall
 * @package: com.lin.common.constant
 * @className: AttrEnum
 * @author: LinYi
 * @description: TODO
 * @date: 2023/9/8 21:26
 * @version: 1.0
 */
public class ProductConstant {
    public enum AttrEnum{
        AttrTypeSale(0,"销售属性"),
        AttrTypeBase(1,"基本属性");

        AttrEnum(Integer code,String msg) {
            this.msg = msg;
            this.code = code;
        }

        String msg;
        Integer code;

        public String getMsg() {
            return msg;
        }

        public Integer getCode() {
            return code;
        }
    }

}

package com.cqnu.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AjaxResult {
    private boolean flag;
    private Object data;
    private String message;

    public AjaxResult(boolean flag) {
        this.flag = flag;
    }

    public AjaxResult(boolean flag, Object data, String message) {
        this.flag = flag;
        this.data = data;
        this.message = message;
    }

    public AjaxResult(boolean flag, Object data) {
        this.flag = flag;
        this.data = data;
    }
}

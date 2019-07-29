package cc.mrbird.febs.common.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReturnInfo implements Serializable {

    private Integer ret;

    private String msg;

    private Object data;
}

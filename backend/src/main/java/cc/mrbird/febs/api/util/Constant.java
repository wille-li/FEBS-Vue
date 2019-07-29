package cc.mrbird.febs.api.util;

import java.util.HashMap;
import java.util.Map;

public class Constant {

    public static final Integer INIT = 0;
    public static final Integer ASK = 1;


    public enum Mode {

        NORMAL(0, "普通模式"),

        CONSULT(2, "咨询模式"),

        COMPETE(3, "竞答模式");

        private Integer value;

        private String text;

        Mode(Integer value, String text) {
            this.value = value;
            this.text = text;
        }

        public Integer getValue() {
            return value;
        }

        public String getText() {
            return text;
        }

        public static Map<Integer, String> toMap(){
            Map<Integer, String> map = new HashMap<>();
            for(Mode mode : Mode.values()){
                map.put(mode.value, mode.text);
            }
            return map;
        }
    }
}

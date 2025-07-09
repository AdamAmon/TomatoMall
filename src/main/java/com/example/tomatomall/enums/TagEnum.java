package com.example.tomatomall.enums;

/**
 * 商品标签
 */
public enum TagEnum {
    NOVEL("小说"),LITERATURE("文学"),ART("艺术"),
    HISTORY("历史"),PHILOSOPHY("哲学"),PSYCHOLOGY("心理"),
    CULTURE("文化"),TEXTBOOK("教辅"),
    OTHER("其他");
    private final String chinese;
    TagEnum(String chinese){
        this.chinese=chinese;
    }
    public String getChinese(){
        return chinese;
    }
}

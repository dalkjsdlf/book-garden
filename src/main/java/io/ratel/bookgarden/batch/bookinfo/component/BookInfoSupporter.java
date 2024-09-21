package io.ratel.bookgarden.batch.bookinfo.component;

import org.springframework.stereotype.Component;

@Component
public class BookInfoSupporter {

    public String getUrl(){
        return "http://www.aladin.co.kr/ttb/api/";
    }
}

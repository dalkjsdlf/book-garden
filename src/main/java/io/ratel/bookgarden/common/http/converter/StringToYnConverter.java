package io.ratel.bookgarden.common.http.converter;

import io.ratel.bookgarden.domain.userbook.entity.Yn;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

public class StringToYnConverter implements Converter<String, Yn> {
    @Override
    public Yn convert(String source) {
        if(StringUtils.isEmpty(source)){
            return null;
        }
        if(source.trim().equals(Yn.Y)){
            return Yn.Y;
        }else if(source.trim().equals(Yn.N)){
            return Yn.N;
        }else{
            return null;
        }
    }
}

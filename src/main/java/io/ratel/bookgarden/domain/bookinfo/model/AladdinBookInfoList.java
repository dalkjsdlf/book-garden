package io.ratel.bookgarden.domain.bookinfo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@JacksonXmlRootElement(localName = "object")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AladdinBookInfoList {
    @JacksonXmlElementWrapper(localName = "item", useWrapping = false)
    @JacksonXmlProperty(localName = "item")
    private List<AladdinBookInfo> items;
}

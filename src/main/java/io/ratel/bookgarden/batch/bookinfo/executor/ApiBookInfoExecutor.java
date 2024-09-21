package io.ratel.bookgarden.batch.bookinfo.executor;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.ratel.bookgarden.common.exception.BusinessErrorResult;
import io.ratel.bookgarden.common.exception.BusinessException;
import io.ratel.bookgarden.domain.bookinfo.model.AladdinBookInfo;
import io.ratel.bookgarden.domain.bookinfo.model.AladdinBookInfoList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApiBookInfoExecutor {

    private final ApiService apiService;

    public List<AladdinBookInfo> getBooksFromAladdinBySearch(String searchText){
        //http://www.aladin.co.kr/ttb/api/ItemSearch.aspx?ttbkey=TTBKey&Query=aladdin&QueryType=Title&MaxResults=10&start=1&SearchTarget=Book&output=xml&Version=20131101

        Mono<String> responseMono = apiService.getDynamicData("Search","title",searchText,"10");
        log.debug(responseMono.block());

        // XML 데이터를 파싱하여 BookInfo 리스트로 변환
        return responseMono.map(xml -> {
            try {
                XmlMapper xmlMapper = new XmlMapper();
                xmlMapper.setDefaultUseWrapper(false);  // 기본 래퍼 사용을 비활성화
                AladdinBookInfoList bookInfoList = xmlMapper.readValue(xml, AladdinBookInfoList.class);
                return bookInfoList.getItems();
            } catch (Exception e) {
                throw new BusinessException(BusinessErrorResult.FAILED_TO_RETRIEVE_BOOK_INFO);
            }
        }).block();
    }

    public List<AladdinBookInfo> getBooksFromAladdin(){

        Mono<String> responseMono = apiService.getDynamicData("List","ItemNewAll","","10");
        log.debug("response : {}",responseMono.block());

        // XML 데이터를 파싱하여 BookInfo 리스트로 변환
        return responseMono.map(xml -> {
            try {
                XmlMapper xmlMapper = new XmlMapper();
                xmlMapper.setDefaultUseWrapper(false);  // 기본 래퍼 사용을 비활성화
                AladdinBookInfoList bookInfoList = xmlMapper.readValue(xml, AladdinBookInfoList.class);
                return bookInfoList.getItems();
            } catch (Exception e) {
                throw new BusinessException(BusinessErrorResult.FAILED_TO_RETRIEVE_BOOK_INFO);
            }
        }).block();
    }
}

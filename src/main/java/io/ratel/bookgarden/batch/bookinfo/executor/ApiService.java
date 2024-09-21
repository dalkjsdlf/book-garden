package io.ratel.bookgarden.batch.bookinfo.executor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ApiService {

    private final WebClient webClient;

    @Value("${aladdin.api.key}")
    private String aladinApiKey;

    @Value("${aladdin.url}")
    private String aladinUrl;

    public ApiService(WebClient.Builder webClientBuilder) {
        aladinUrl = "https://www.aladin.co.kr/ttb/api";
        log.debug("aladinUrl = {}", aladinUrl);
        this.webClient = webClientBuilder.baseUrl(aladinUrl).build();
    }

    public Mono<String> getDynamicData(String searchType,
                                       String queryType,
                                       String query,
                                       String maxResult) {

        String searchTypeUrl = "/ItemSearch.aspx";
        boolean isQuery = true;
        if("List".equals(searchType)) {
            searchTypeUrl = "/ItemList.aspx";
            isQuery = false;
        }

        // 동적으로 URL 빌드
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(searchTypeUrl);

        if(isQuery)
            uriComponentsBuilder.queryParam("Query",query);

        String uri = uriComponentsBuilder.queryParam("ttbkey",aladinApiKey).
                            queryParam("QueryType",queryType).
                            queryParam("MaxResults",maxResult).
                            queryParam("start",1).
                            queryParam("SearchTarget","Book").
                            queryParam("output","xml").
                            queryParam("Version","20131101").
                            toUriString();
        log.debug("uri = {}", uri);
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(uri)  // 동적으로 빌드된 URL 사용
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .doOnNext(response -> {
                    if (response == null) {
                        throw new RuntimeException("API returned null response");
                    }
                });
    }
}

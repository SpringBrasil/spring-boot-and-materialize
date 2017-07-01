package com.crud.financeiro.controller.page;

import org.springframework.data.domain.Page;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class PageWrapper<T> {

    private Page<T> page;
    private UriComponentsBuilder uriBuilder;

    public PageWrapper(Page<T> page, HttpServletRequest httpServletRequest) {
        this.page = page;
        //this.uriBuilder = ServletUriComponentsBuilder.fromRequest(httpServletRequest);

        String httpUrl = httpServletRequest.getRequestURL().append(
                httpServletRequest.getQueryString() != null ? "?" + httpServletRequest.getQueryString() : "")
                .toString().replaceAll("\\+", "%20");
        this.uriBuilder = UriComponentsBuilder.fromHttpUrl(httpUrl);
    }

    public List<T> getConteudo(){
        return page.getContent();
    }

    public boolean isVazia(){
        return page.getContent().isEmpty();
    }

    public int getAtual(){
        return page.getNumber();
    }

    public boolean isPrimeira(){
        return page.isFirst();
    }

    public boolean isUltima(){
        return page.isLast();
    }

    public int getTotal(){
        return page.getTotalPages();
    }

    /**
     *
     * Se tiver "page" na url, então troca pelo numero da página
     * Se não tiver, adiciona
     */
    public String urlParaPagina(int pagina) {
        return uriBuilder.replaceQueryParam("page", pagina).build(true).encode().toUriString();
    }

}
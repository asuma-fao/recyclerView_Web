package es.faouzi.ua.procweb.data.web;

import es.faouzi.ua.procweb.util.RestAdapterUtil;

/**
 * Created by Faouzi Asmaa on 01/05/2021
 */
public class ServiceRetrofit<T> {
    private final T t;
    final String baseURL = "https://vertice.cpd.ua.es";


    public ServiceRetrofit(T t) {
        this.t = t;
    }


    public void getServiceRetrofit(final Class<T> api){
        RestAdapterUtil.createXmlAdapterFor(api,baseURL);

    }
}

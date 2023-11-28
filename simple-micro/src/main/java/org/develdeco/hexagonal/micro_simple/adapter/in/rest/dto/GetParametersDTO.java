package org.develdeco.hexagonal.micro_simple.adapter.in.rest.dto;

import org.develdeco.hexagonal.micro_simple.domain.model.entity.QueryParameters;

public class GetParametersDTO {

    Integer page;

    public QueryParameters toQueryParameters() {
        QueryParameters queryParameters = new QueryParameters();
        queryParameters.setPage(page);
        return queryParameters;
    }
}

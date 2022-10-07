package com.uninter.laridosos.dto.filter;

import lombok.Getter;
import lombok.Setter;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

@Getter
@Setter
public class PatientGetAllFilter {
    @QueryParam("page")
    @DefaultValue("0")
    private int page;

    @QueryParam("itemsPerPage")
    @DefaultValue("10")
    private int itemsPerPage;

    @QueryParam("name")
    private String name;

}

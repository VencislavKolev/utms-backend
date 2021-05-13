package com.vmware.utms.cli.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vmware.utms.cli.models.jsonExport.ReportDto;

import java.io.IOException;

public interface JsonService {
    ReportDto processInput(String... args) throws IOException;

    void printJsonString(Object obj) throws JsonProcessingException;

    String getJsonString(Object obj) throws JsonProcessingException;
}

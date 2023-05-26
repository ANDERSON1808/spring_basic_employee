package spring_basic_employee.service.config;


import lombok.Data;

import java.io.Serializable;

@Data
public class ConfigExternal implements Serializable {
    private String all;
    private String byId;

    public ConfigExternal(String routeGetAllEmploys, String routeGetByIdEmploys) {
        this.all = routeGetAllEmploys;
        this.byId = routeGetByIdEmploys;
    }

    public String getAll() {
        return all;
    }

    public String getById() {
        return byId;
    }
}

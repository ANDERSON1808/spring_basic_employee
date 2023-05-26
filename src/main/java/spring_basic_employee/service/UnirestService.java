package spring_basic_employee.service;

import spring_basic_employee.config.ExternalApiConfiguration;
import spring_basic_employee.service.config.ConfigExternal;
import spring_basic_employee.service.vm.EmployeesVm;
import spring_basic_employee.service.vm.ResponseEmployVm;
import spring_basic_employee.service.vm.ResponseEmploysVm;
import com.google.gson.Gson;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.mashape.unirest.http.HttpResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;


@Service
public class UnirestService {
    private static final Logger log = LoggerFactory.getLogger(UnirestService.class);
    private final ExternalApiConfiguration externalApiConfiguration;
    private final Gson gson;

    public UnirestService(ExternalApiConfiguration externalApiConfiguration, Gson gson) {
        this.externalApiConfiguration = externalApiConfiguration;
        this.gson = gson;
    }

    public Flux<EmployeesVm> sendGetAll(Optional<Long> id) throws UnirestException {
        log.debug("Start debug send get all: {}", id);
        ConfigExternal url = externalApiConfiguration.startConfigExternalEmploys();
        Unirest.setTimeouts(0, 0);

        String apiUrl = url.getAll();
        if (id.isPresent()) {
            return sendGetById(id.get()).flux();
        }

        HttpResponse<String> response = Unirest.get(apiUrl).asString();
        if (response.getStatus() == 200) {
            try {
                ResponseEmploysVm responseEmploysVm = gson.fromJson(response.getBody(), ResponseEmploysVm.class);
                List<EmployeesVm> employeesList = responseEmploysVm.getData();
                return Flux.fromIterable(employeesList);
            } catch (Exception e) {
                log.debug("error send get all :{}", e.getMessage());
                return Flux.error(new Throwable(e.getMessage()));
            }
        }

        return Flux.error(new Throwable(response.getStatusText()));
    }


    public Mono<EmployeesVm> sendGetById(Long id) throws UnirestException {
        log.debug("Start debug send get by id: {}", id);

        Unirest.setTimeouts(0, 0);

        String apiUrl = externalApiConfiguration.getRouteGetByIdEmploys() + "/" + id;

        HttpResponse<String> response = Unirest.get(apiUrl).asString();
        if (response.getStatus() == 200) {
            try {
                ResponseEmployVm responseEmploysVm = gson.fromJson(response.getBody(), ResponseEmployVm.class);
                return Mono.just(responseEmploysVm.getData());
            } catch (Exception e) {
                log.debug("error send get all :{}", e.getMessage());
                return Mono.error(new Throwable(e.getMessage()));
            }
        }
        return Mono.error(new Throwable(response.getStatusText()));
    }
}

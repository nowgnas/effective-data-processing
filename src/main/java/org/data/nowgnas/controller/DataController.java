package org.data.nowgnas.controller;

import lombok.extern.slf4j.Slf4j;
import org.data.nowgnas.dto.ApiResponse;
import org.data.nowgnas.dto.ApiResponse;
import org.data.nowgnas.dto.CachedData;
import org.data.nowgnas.service.DataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/data")
public class DataController {

    private final DataService dataService;

    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @PostMapping("/sync")
    public ResponseEntity<ApiResponse> syncData(@RequestBody String data) {
        // Process the data here
        log.info("Received data for sync: {}", data);
        ApiResponse response = new ApiResponse(HttpStatus.OK.value(), "SUCCESS",
                "Data synced successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CachedData> getCachedData(@PathVariable String id) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        CachedData cachedData = dataService.getCachedData(id);

        stopWatch.stop();
        log.info("Controller method execution time: {} ms", stopWatch.getTotalTimeMillis());

        return ResponseEntity.ok(cachedData);
    }
}

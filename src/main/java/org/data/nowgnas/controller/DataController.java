package org.data.nowgnas.controller;

import lombok.extern.slf4j.Slf4j;
import org.data.nowgnas.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/data")
public class DataController {

    @PostMapping("/sync")
    public ResponseEntity<ApiResponse> syncData(@RequestBody String data) {
        // Process the data here
        log.info("Received data for sync: {}", data);
        ApiResponse response = new ApiResponse(HttpStatus.OK.value(), "SUCCESS",
                "Data synced successfully");
        return ResponseEntity.ok(response);
    }
}

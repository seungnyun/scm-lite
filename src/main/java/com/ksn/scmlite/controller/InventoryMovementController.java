package com.ksn.scmlite.controller;

import com.ksn.scmlite.dto.InventoryMovementRequest;
import com.ksn.scmlite.service.InventoryMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movements")
@RequiredArgsConstructor
public class InventoryMovementController {

    private final InventoryMovementService inventoryMovementService;

    @PostMapping("/inbound")
    public void inbound(@RequestBody InventoryMovementRequest request){
        inventoryMovementService.inbound(request);
    }

    @PostMapping("/outbound")
    public void outbound(@RequestBody InventoryMovementRequest request){
        inventoryMovementService.outbound(request);
    }
}

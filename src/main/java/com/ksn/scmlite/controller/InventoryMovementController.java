package com.ksn.scmlite.controller;

import com.ksn.scmlite.dto.InventoryMovementRequest;
import com.ksn.scmlite.dto.InventoryMovementResponse;
import com.ksn.scmlite.dto.InventoryMovementSearchRequest;
import com.ksn.scmlite.service.InventoryMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/search")
    public List<InventoryMovementResponse> search(InventoryMovementSearchRequest request){
        return inventoryMovementService.search(request);
    }
}

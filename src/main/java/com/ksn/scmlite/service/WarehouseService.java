package com.ksn.scmlite.service;

import com.ksn.scmlite.entity.Warehouse;
import com.ksn.scmlite.exception.BusinessException;
import com.ksn.scmlite.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public List<Warehouse> findAll(){
        return warehouseRepository.findAll();
    }

    public Warehouse findById(Long id){
        return warehouseRepository.findById(id).orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "창고를 찾을 수 없습니다."));
    }

    public Warehouse save(Warehouse warehouse){
        return warehouseRepository.save(warehouse);
    }

    public void delete(Long id){
        warehouseRepository.deleteById(id);
    }

    @Transactional
    public Warehouse update(Long id, Warehouse request){
        Warehouse warehouse = warehouseRepository.findById(id).orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "창고를 찾을 수 없습니다."));

        warehouse.update(request.getWarehouseName());

        return warehouse;
    }
}

package com.ksn.scmlite.service;

import com.ksn.scmlite.entity.Item;
import com.ksn.scmlite.exception.BusinessException;
import com.ksn.scmlite.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<Item> findAll(){
        return itemRepository.findAll();
    }

    public Item findbyid(Long id){
        return  itemRepository.findById(id).orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "품목을 찾을 수 없습니다."));
    }

    public Item save(Item item) {
        return itemRepository.save(item);
    }

    public void delete(Long id){
        itemRepository.deleteById(id);
    }

    @Transactional
    public Item update(Long id, Item request){
        Item item = itemRepository.findById(id).orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "품목을 찾을 수 없습니다."));

        item.update(request.getItemName(), request.getSafetyStock());

        return item;
    }
}

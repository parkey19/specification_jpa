package com.attacomsian.jpa.repositories;

import com.attacomsian.jpa.domains.Item;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long>,
        JpaSpecificationExecutor<Item> {
}

package org.codenova.moneylog.repository;

import org.apache.ibatis.annotations.Mapper;
import org.codenova.moneylog.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CategoryRepository {
    public List<Category> findAll();
}

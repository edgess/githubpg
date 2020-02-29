package com.edge.daotkmapper;

import com.edge.entity.Product;
import tk.mybatis.mapper.common.Mapper;
import java.util.List;

public interface ProductDao extends Mapper<Product> {
    List<Product> getProducts();
}
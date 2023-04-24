package com.hexagonal.bakery.infrastructure.repository.cassandra;

import com.hexagonal.bakery.domain.models.Order;
import com.hexagonal.bakery.domain.models.OrderLine;
import com.hexagonal.bakery.domain.models.OrderLineType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = CassandraRepository.class)
public interface EntityMapper {

    @Mapping(source = "lines", target = "lineEntities", qualifiedByName = "toLineEntities")
    @Mapping(source = "priceBeforeMargin", target = "price")
    OrderEntity toOrderEntity(Order order);

    @Mapping(source = "lineEntities", target = "lines", qualifiedByName = "toLines")
    @Mapping(source = "price", target = "priceBeforeMargin")
    Order toOrder(OrderEntity orderEntity);
    @Mapping(source = "label", target = "reference")
    @Mapping(source = "type", target = "category", qualifiedByName = "toCategory")
    OrderLineEntity toOrderLineEntity(OrderLine orderline);

    @Mapping(source = "reference", target = "label")
    @Mapping(source = "category", target = "type", qualifiedByName = "toType")
    OrderLine toOrderLine(OrderLineEntity orderLineEntity);

    @Named("toLineEntities")
    default List<OrderLineEntity> toLineEntities(List<OrderLine> orderLines){
        return orderLines.stream().map(this::toOrderLineEntity).toList();
    }

    @Named("toLines")
    default List<OrderLine> toLines(List<OrderLineEntity> orderLineEntities){
        return orderLineEntities.stream().map(this::toOrderLine).toList();
    }

    @Named("toCategory")
    default OrderLineEntityCategory toCategory(OrderLineType orderLineType){
        if(orderLineType == OrderLineType.CAKE){
            return OrderLineEntityCategory.FOOD;
        }
        return OrderLineEntityCategory.SERVICE;
    }

    @Named("toType")
    default OrderLineType toType(OrderLineEntityCategory orderLineEntityCategory){
        if(orderLineEntityCategory == OrderLineEntityCategory.FOOD){
            return OrderLineType.CAKE;
        }
        return OrderLineType.EXTRA;
    }
}

package com.library.online_library.repository.order;

import com.library.online_library.model.entity.order.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long>, JpaSpecificationExecutor<OrderEntity> {

    Optional<OrderEntity> findByIdAndUserId(Long orderId, Long id);

    boolean existsByUserIdAndStatusIn(Long userId, List<Object> objects);

    void deleteAllByUserId(Long userId);

    Page<OrderEntity> findAllByStatusAndUser_Id(boolean status, Long id, Pageable pageable);
}

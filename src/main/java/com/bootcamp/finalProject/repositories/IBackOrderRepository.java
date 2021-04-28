package com.bootcamp.finalProject.repositories;

import com.bootcamp.finalProject.model.BackOrder;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBackOrderRepository extends JpaRepository<BackOrder,Long> {

    @Query("FROM BackOrder bo WHERE bo.backOrderStatus = 'P' AND bo.backOrderPriority = :priority AND bo.backOrderDetail.partBackOrder.idPart = :idPart ORDER BY backOrderDate ASC")
    List<BackOrder> findPendingBackOrderByPriorityOrderByBackOrderDateASC(@Param("priority") String priority, @Param("idPart") Long idPart);
}

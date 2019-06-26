package com.example.medico.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.medico.custom.dao.OrderCustomDao;
import com.example.medico.model.Orders;
import com.example.medico.model.User;

public interface OrderDao extends CrudRepository<Orders,Long>,PagingAndSortingRepository<Orders,Long>,OrderCustomDao{

	@Query("Select Max(o.id) from Orders o")
	public Long getMaxId();
	public List<Orders> findByPlacedUserOrderByPlacedDateDesc(User user);
	public List<Orders> findByStatusOrderByPlacedDateDesc(String status);
	public List<Orders> findByStatusAndDeliveredUser(String status,User user,Pageable pageable);
	public Orders findByOrderId(String orderId);
}

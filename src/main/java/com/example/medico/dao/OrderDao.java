package com.example.medico.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.medico.custom.dao.OrderCustomDao;
import com.example.medico.model.Orders;
import com.example.medico.model.User;

public interface OrderDao extends CrudRepository<Orders,Long>,OrderCustomDao{

	@Query("Select Max(o.id) from Orders o")
	public Long getMaxId();
	public List<Orders> findByPlacedUserOrderByPlacedDateDesc(User user);
}

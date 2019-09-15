package com.restapi.task.service.houseFinance;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restapi.task.domain.HouseFinance;

public interface HouseFinanceRepository extends JpaRepository<HouseFinance, Long>, HouseFinanceRepositoryCustom {

}

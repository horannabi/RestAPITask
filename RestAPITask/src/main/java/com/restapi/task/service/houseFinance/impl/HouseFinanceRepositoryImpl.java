package com.restapi.task.service.houseFinance.impl;

import java.util.List;

import org.hibernate.sql.Insert;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.restapi.task.domain.HouseFinance;
import com.restapi.task.domain.Institute;
import com.restapi.task.domain.QHouseFinance;
import com.restapi.task.domain.QInstitute;
import com.restapi.task.dto.HouseFinanceDTO;
import com.querydsl.core.Tuple;
import com.querydsl.core.dml.InsertClause;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.restapi.task.service.houseFinance.HouseFinanceRepositoryCustom;

public class HouseFinanceRepositoryImpl implements HouseFinanceRepositoryCustom {

	private final JPAQueryFactory queryFactory;
	public HouseFinanceRepositoryImpl(JPAQueryFactory queryFactory) {
		super();
	    this.queryFactory = queryFactory;
	}
	QHouseFinance houseFinance = QHouseFinance.houseFinance;
	QInstitute institute = QInstitute.institute;
	
	
	@Override
	public List<HouseFinanceDTO> listYearlySum() {
		return queryFactory
                .select(Projections.fields(HouseFinanceDTO.class, houseFinance.year.as("year"), institute.instituteName.as("instituteName"), ExpressionUtils.as(houseFinance.amount.sum(), "amount")))
                .from(houseFinance).innerJoin(houseFinance.institute, institute)
                .groupBy(houseFinance.year, institute.instituteName)
                .orderBy(houseFinance.year.asc(), institute.instituteName.asc())
                .fetch();
	}
	
	public HouseFinanceDTO listYearlySumMax(int year){
		return queryFactory
				//.select(institute.instituteName, houseFinance.year, houseFinance.amount.sum())
				.select(Projections.fields(HouseFinanceDTO.class, institute.instituteName.as("instituteName"), houseFinance.year.as("year"), ExpressionUtils.as(houseFinance.amount.sum(), "amount")))
				.from(houseFinance).innerJoin(houseFinance.institute, institute)
				.where(houseFinance.year.eq(year))
		        .groupBy(houseFinance.year, institute.instituteName).limit(1)
		        .orderBy(houseFinance.amount.sum().desc())
		        .fetch().get(0);
	}

	@Override
	public List<HouseFinanceDTO> listYearlyAvgMinMax(String instituteName) {
		return queryFactory
				.select(Projections.fields(HouseFinanceDTO.class, institute.instituteName.as("instituteName"), houseFinance.year.as("year"), ExpressionUtils.as((houseFinance.amount.avg().round()).castToNum(Integer.class), "amount")))
				.from(houseFinance).innerJoin(houseFinance.institute, institute)
				.where(institute.instituteName.contains(instituteName))
				.groupBy(houseFinance.year, institute.instituteName)
				.orderBy(houseFinance.amount.avg().asc())
				.fetch();
	}

}

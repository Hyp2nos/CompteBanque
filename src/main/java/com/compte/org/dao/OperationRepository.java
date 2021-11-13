package com.compte.org.dao;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.data.repository.query.Param;

import com.compte.org.enteties.Operation;

public interface OperationRepository extends JpaRepository<Operation, Long> {
 
//	@Query("select o from Operation o where o.compte.codeCompte=:x order by o.dateOperation desc")
//	public Pageable listOperation(@Param("x") String codecpte , QPageRequest qPageRequest);
}


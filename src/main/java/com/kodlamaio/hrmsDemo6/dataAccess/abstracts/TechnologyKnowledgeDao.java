package com.kodlamaio.hrmsDemo6.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kodlamaio.hrmsDemo6.entities.concretes.TechnologyKnowledge;

@Repository
public interface TechnologyKnowledgeDao extends JpaRepository<TechnologyKnowledge, Integer> {

}

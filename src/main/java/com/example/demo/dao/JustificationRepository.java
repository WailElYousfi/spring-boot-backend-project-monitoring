package com.example.demo.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Justification;
import com.example.demo.models.Task;


@Repository
public interface JustificationRepository extends JpaRepository<Justification, Integer> {

/*	@Query("select j from Justification j where j.project.projectId = ?1 and j.justificator.role.name != 'ROLE_TEAMLEADER'")
	Optional< List<Justification> > findDevelopersJustificationsByProjectId(Integer projectId);
	
	@Query("select j from Justification j where j.project.projectId = ?1 and j.justificator.role.name = 'ROLE_TEAMLEADER'")
	Optional< List<Justification> > findTeamLeadersJustificationsByProjectId(Integer projectId);	*/
	
	@Query("select j from Justification j where j.project.projectId = ?1 and j.justificator.role.roleId = ?2 ")
	Optional< List<Justification> > findJustificationsByProjectIdAndRoleId(Integer projectId, Integer roleId);
	
	@Query("select j from Justification j where j.justificator.userId = ?1 and j.isArchived = false")
	Optional< List<Justification> > findJustificationsByJustificatorId(Long justificatorId);
	
	
}
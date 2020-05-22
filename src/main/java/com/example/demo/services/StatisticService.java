package com.example.demo.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.example.demo.models.Incidence;
import com.example.demo.models.Project;
import com.example.demo.models.Task;
import com.example.demo.models.User;
import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.dao.IncidenceRepository;
import com.example.demo.dao.ProjectRepository;
import com.example.demo.dao.TaskRepository;
import com.example.demo.dao.UserRepository;

@Service
public class StatisticService {
     
    @Autowired
    UserService userService;
    
    @Autowired
    TaskService taskService;
    
    @Autowired
    IncidenceService incidenceService;

	public Float getEfficiencyByUserIdOrProjectId(@Nullable Long userId, @Nullable Integer projectId, int month) throws Exception {	// return float number or null
		List<Task> tasks = taskService.getTasksByUserIdAndProjectIdAndStatusAndDates(userId, projectId, "Resolved", month);
		if(tasks == null)
			return null;
		Float originalEstimateSum = 0.0f;
		Float timeSpentSum = 0.0f;
		for (Task task : tasks) {
			String originalEstimate= task.getOriginalEstimate();
			if(task.getOriginalEstimate() == null || task.getTimeSpent() == null)
				continue;
			String timeSpent= task.getTimeSpent();
			Float oe = 0.0f;
			Float ts = 0.0f;
			oe = Float.parseFloat(originalEstimate.replace(",", "."));
			ts = Float.parseFloat(timeSpent.replace(",", "."));
			originalEstimateSum += oe;
			timeSpentSum += ts;
		}
		if(timeSpentSum !=0)
			return (originalEstimateSum/timeSpentSum)*100;
		else
			return null;
	}
	
	public Float getRetrabajoByUserIdOrProjectId(Long userId, Integer projectId, int month, String typeName) throws Exception {
		List<Incidence> incidences = incidenceService.getIncidencesByUserIdOrProjectIdAndStatusAndDatesAndType(userId, projectId, "Resolved", month, typeName);
		List<Task> tasks = taskService.getTasksByUserIdAndProjectIdAndStatusAndDates(userId, projectId, "Resolved", month);
		if(tasks == null)
			return null;
		Float originalEstimateSum = 0.0f;
		Float timeSpentSum = 0.0f;
		
		for (Task task : tasks) {
			String originalEstimate= task.getOriginalEstimate();
			if(task.getOriginalEstimate() == null)
				continue;
			Float oe = 0.0f;
			oe = Float.parseFloat(originalEstimate.replace(",", "."));
			originalEstimateSum += oe;
		}
		if(incidences != null)
			for (Incidence incidence : incidences) {
				if(incidence.getTimeSpent() == null)
					continue; //go to the next iteration
				String timeSpent= incidence.getTimeSpent();
				Float ts = 0.0f;
				ts = Float.parseFloat(timeSpent.replace(",", "."));
				timeSpentSum += ts;
			}
		if(originalEstimateSum != 0)
			return (timeSpentSum/originalEstimateSum)*100;
		else
			return null;
	}
	
	public Float getRatioIncidenciasByUserIdOrPrjectId(Long userId, Integer projectId, int month, String typeName) throws Exception {
		Integer countOfIncidences = incidenceService.getCountOfIncidencesByUserIdOrProjectIdAndStatusAndDatesAndType(userId, projectId, "Resolved", month, typeName);
		List<Task> tasks = taskService.getTasksByUserIdAndProjectIdAndStatusAndDates(userId, projectId, "Resolved", month);
		if(countOfIncidences == 0 || tasks == null)
			return null;
		Float originalEstimateSum = 0.0f;
		
		for (Task task : tasks) {
			String originalEstimate= task.getOriginalEstimate();
			if(task.getOriginalEstimate() == null)
				continue; // go to the next iteration
			Float oe = 0.0f;
			oe = Float.parseFloat(originalEstimate.replace(",", "."));
			originalEstimateSum += oe;
		}
		
		return (originalEstimateSum/countOfIncidences)*100;
	}
	
	
	public Float getProductividadByUserIdOrProjectId(Long userId, Integer projectId, int month) throws Exception {
		List<Incidence> incidencesInternes = incidenceService.getIncidencesByUserIdOrProjectIdAndStatusAndDatesAndType(userId, projectId, "Resolved", month, "Incidencia interna");
		List<Incidence> incidencesExternes = incidenceService.getIncidencesByUserIdOrProjectIdAndStatusAndDatesAndType(userId, projectId, "Resolved", month, "Incidencia externa");
		List<Task> tasks = taskService.getTasksByUserIdAndProjectIdAndStatusAndDates(userId, projectId, "Resolved", month);
		if(tasks == null)
			return null;
		Float originalEstimateTasksSum = 0.0f;
		Float timeSpentTasksSum = 0.0f;
		Float timeSpentIncidencesSum = 0.0f;
		
		for (Task task : tasks) {
			String originalEstimate= task.getOriginalEstimate();
			String timeSpent= task.getTimeSpent();
			if(task.getOriginalEstimate() == null || task.getTimeSpent() == null)
				continue;
			Float oe = 0.0f;
			Float ts = 0.0f;
			oe = Float.parseFloat(originalEstimate.replace(",", "."));
			ts = Float.parseFloat(timeSpent.replace(",", "."));
			originalEstimateTasksSum += oe;
			timeSpentTasksSum += ts;
		}
		if(incidencesExternes != null)
			for (Incidence incidence : incidencesExternes) {
				if(incidence.getTimeSpent() == null)
					continue; //go to the next iteration
				String timeSpent= incidence.getTimeSpent();
				Float ts = 0.0f;
				ts = Float.parseFloat(timeSpent.replace(",", "."));
				timeSpentIncidencesSum += ts;
			}
		if(incidencesInternes != null)
			for (Incidence incidence : incidencesInternes) {
				if(incidence.getTimeSpent() == null)
					continue; //go to the next iteration
				String timeSpent= incidence.getTimeSpent();
				Float ts = 0.0f;
				ts = Float.parseFloat(timeSpent.replace(",", "."));
				timeSpentIncidencesSum += ts;
			}
		if((timeSpentTasksSum + timeSpentIncidencesSum) != 0)
			return (originalEstimateTasksSum*100) / (timeSpentIncidencesSum + timeSpentTasksSum);
		else
			return null;
	}
     
   
}
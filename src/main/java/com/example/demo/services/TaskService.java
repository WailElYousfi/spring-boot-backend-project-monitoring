package com.example.demo.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.example.demo.models.Task;
import com.example.demo.models.Type;
import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.dao.TaskRepository;

@Service
public class TaskService {
     
    @Autowired
    TaskRepository repository;
    
    @Autowired
    TypeService typeRepository;
    
    public Task createTask(Task task) {
        return repository.save(task);
    }

    public Task updateTask(Task task) {
        Optional <Task> taskDb = this.repository.findByKeyAndTitle(task.getKey(), task.getSummary());

        if (taskDb.isPresent()) {
            Task taskUpdate = taskDb.get();
            taskUpdate.setProject(task.getProject());
            taskUpdate.setKey(task.getKey());
            taskUpdate.setSummary(task.getSummary());
            taskUpdate.setCreated(task.getCreated());
            taskUpdate.setUpdated(task.getUpdated());
            taskUpdate.setResolved(task.getResolved());
            taskUpdate.setOriginalEstimate(task.getOriginalEstimate());
            taskUpdate.setRemainingEstimate(task.getRemainingEstimate());
			taskUpdate.setStatus(task.getStatus());
			taskUpdate.setTimeSpent(task.getTimeSpent());
			taskUpdate.setTaskType(task.getTaskType());
		//	taskUpdate.setDate(task.getDate());
			taskUpdate.setAssignedUser(task.getAssignedUser());
			taskUpdate.setComment(task.getComment());
			taskUpdate.setFileType(task.getFileType());
			taskUpdate.setIdOt(task.getIdOt());
            repository.save(taskUpdate);            
            return taskUpdate;
        } else {
        	return repository.save(task);
        }
    }

    
    public List <Task> getAllTask() {
        return this.repository.findAll();
    }
    

    public Task getTaskById(Integer taskId) {

        Optional <Task> taskDb = this.repository.findById(taskId);

        if (taskDb.isPresent()) {
            return taskDb.get();
        } else {
            throw new ResourceNotFoundException("Record not found");
        }
    }
    
    public Task getTaskByKey(String key) {

        Optional <Task> taskDb = this.repository.findByKey(key);

        if (taskDb.isPresent()) {
            return taskDb.get();
        } else {
            throw new ResourceNotFoundException("Record not found");
        }
    }
    
    public List<Task> getTasksByUserId(Long userId) {

        Optional <List<Task>> taskDb = this.repository.findByUserId(userId);

        if (taskDb.isPresent()) {
            return taskDb.get();
        } else {
            return null;
        }
    }
    
    public List<Task> getTasksByUserIdAndStatus(Long userId, String status) {
    	Optional < List<Task> > taskDb = this.repository.findByUserIdAndStatus(userId, status);

        if (taskDb.isPresent()) {
            return taskDb.get();
        } else {
            return null;
        }
    }
    
    public List<Task> getTasksByProjectIdAndStatus(Integer projectId, String status) {
    	Optional < List<Task> > taskDb = this.repository.findByProjectIdAndStatus(projectId, status);

        if (taskDb.isPresent()) {
            return taskDb.get();
        } else {
            return null;
        }
    }
    
    public List<Task> getTasksByUserIdAndProjectIdAndStatusAndDates(Long userId, Integer projectId, String status, int month) {
    	Optional < List<Task> > taskDb;
    	if(userId == null)
    		taskDb = this.repository.findByProjectIdAndStatusAndDates(projectId, status, month);
    	else
    		taskDb = this.repository.findByUserIdAndStatusAndDates(userId, status, month);

        if (taskDb.isPresent()) {
            return taskDb.get();
        } else {
            return null;
        }
    }
    
  /*  public List<Task> getTasksByUserIdAndStatusAndDates(Long userId, String status, Date startDate, Date endDate) {
    	Optional < List<Task> > taskDb = this.repository.findByUserIdAndStatusAndDates(userId, status, startDate, endDate);

        if (taskDb.isPresent()) {
            return taskDb.get();
        } else {
            return null;
        }
    }*/
    
    public List<Task> getTaskByDates(Date startDate, Date endDate) {
        Optional < List<Task> > taskDb = this.repository.findByDates(startDate, endDate);

        if (taskDb.isPresent()) {
            return taskDb.get();
        } else {
            throw new ResourceNotFoundException("Record not found");
        }
    }
    
    public List<Task> getTaskByDatesAndIdOt(Date startDate, Date endDate, Integer idOt) {
        Optional < List<Task> > taskDb = this.repository.findByDatesAndIdOt(startDate, endDate, idOt);

        if (taskDb.isPresent()) {
            return taskDb.get();
        } else {
            throw new ResourceNotFoundException("Record not found");
        }
    }
    
    public List<Task> getTaskByDatesAndSummary(Date startDate, Date endDate, String summary, String type) {
    	Type fileType = typeRepository.getTypeByName(type);
    	Integer typeId = fileType.getTypeId();
        Optional < List<Task> > taskDb = this.repository.findByDatesAndType(startDate, endDate, typeId); 
        List <Task> taskList = new ArrayList<Task>();
        if (taskDb.isPresent()) {
        	for (Task task : taskDb.get()) {
    			if(task.getSummary().contains(summary))
    				taskList.add(task);
    		}
        	if(taskList.isEmpty())
                throw new ResourceNotFoundException("There is no task contains this keyword in summary");
        	else
        		return taskList;
        } else {
            throw new ResourceNotFoundException("There is no task in these dates");
        }
    }

    public void deleteTask(Integer taskId) {
        Optional <Task> taskDb = this.repository.findById(taskId);

        if (taskDb.isPresent()) {
            this.repository.delete(taskDb.get());
        } else {
            throw new ResourceNotFoundException("Record not found");
        }

    }
}
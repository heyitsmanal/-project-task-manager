package ma.emsi.ptm.task;

import ma.emsi.ptm.common.exception.NotFoundException;
import ma.emsi.ptm.project.Project;
import ma.emsi.ptm.task.dto.CreateTaskRequest;
import ma.emsi.ptm.task.dto.TaskResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskResponse create(Project project, CreateTaskRequest req) {
        Task t = new Task();
        t.setTitle(req.title());
        t.setDescription(req.description());
        t.setDueDate(req.dueDate());
        t.setCompleted(false);
        t.setProjectId(project.getId());

        return toResponse(taskRepository.save(t));
    }

    public List<TaskResponse> list(Project project) {
        return taskRepository.findByProjectId(project.getId())
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public TaskResponse markCompleted(Project project, Long taskId) {
        Task t = taskRepository.findByIdAndProjectId(taskId, project.getId())
                .orElseThrow(() -> new NotFoundException("Task not found"));
        t.setCompleted(true);
        return toResponse(taskRepository.save(t));
    }

    public void delete(Project project, Long taskId) {
        Task t = taskRepository.findByIdAndProjectId(taskId, project.getId())
                .orElseThrow(() -> new NotFoundException("Task not found"));
        taskRepository.delete(t);
    }

    private TaskResponse toResponse(Task t) {
        return new TaskResponse(
                t.getId(),
                t.getTitle(),
                t.getDescription(),
                t.getDueDate(),
                t.isCompleted()
        );
    }
}

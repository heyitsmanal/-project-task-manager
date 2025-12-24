package ma.emsi.ptm.task;

import jakarta.validation.Valid;
import ma.emsi.ptm.common.security.CurrentUser;
import ma.emsi.ptm.project.Project;
import ma.emsi.ptm.project.ProjectService;
import ma.emsi.ptm.task.dto.CreateTaskRequest;
import ma.emsi.ptm.task.dto.TaskResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects/{projectId}/tasks")
public class TaskController {

    private final ProjectService projectService;
    private final TaskService taskService;

    public TaskController(ProjectService projectService, TaskService taskService) {
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @PostMapping
    public TaskResponse create(
            @PathVariable("projectId") Long projectId,
            @Valid @RequestBody CreateTaskRequest req
    ) {
        Project project = projectService.getOwnedEntity(CurrentUser.id(), projectId);
        return taskService.create(project, req);
    }

    @GetMapping
    public List<TaskResponse> list(@PathVariable("projectId") Long projectId) {
        Project project = projectService.getOwnedEntity(CurrentUser.id(), projectId);
        return taskService.list(project);
    }

    @PatchMapping("/{taskId}/complete")
    public TaskResponse complete(
            @PathVariable("projectId") Long projectId,
            @PathVariable("taskId") Long taskId
    ) {
        Project project = projectService.getOwnedEntity(CurrentUser.id(), projectId);
        return taskService.markCompleted(project, taskId);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> delete(
            @PathVariable("projectId") Long projectId,
            @PathVariable("taskId") Long taskId
    ) {
        Project project = projectService.getOwnedEntity(CurrentUser.id(), projectId);
        taskService.delete(project, taskId);
        return ResponseEntity.noContent().build();
    }
}

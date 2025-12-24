package ma.emsi.ptm.project;

import jakarta.validation.Valid;
import ma.emsi.ptm.common.security.CurrentUser;
import ma.emsi.ptm.project.dto.CreateProjectRequest;
import ma.emsi.ptm.project.dto.ProjectResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ProjectResponse create(@Valid @RequestBody CreateProjectRequest req) {
        return projectService.create(CurrentUser.id(), req);
    }

    @GetMapping
    public List<ProjectResponse> list() {
        return projectService.list(CurrentUser.id());
    }

    @GetMapping("/{projectId}")
    public ProjectResponse get(@PathVariable("projectId") Long projectId) {
        return projectService.get(CurrentUser.id(), projectId);
    }
}

package ma.emsi.ptm.progress;

import ma.emsi.ptm.common.security.CurrentUser;
import ma.emsi.ptm.progress.dto.ProgressResponse;
import ma.emsi.ptm.project.Project;
import ma.emsi.ptm.project.ProjectService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects/{projectId}/progress")
public class ProgressController {

    private final ProjectService projectService;
    private final ProgressService progressService;

    public ProgressController(ProjectService projectService, ProgressService progressService) {
        this.projectService = projectService;
        this.progressService = progressService;
    }

    @GetMapping
    public ProgressResponse progress(@PathVariable("projectId") Long projectId) {
        Project project = projectService.getOwnedEntity(CurrentUser.id(), projectId);
        return progressService.get(project);
    }
}

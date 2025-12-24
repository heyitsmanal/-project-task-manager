package ma.emsi.ptm.project;

import ma.emsi.ptm.common.exception.NotFoundException;
import ma.emsi.ptm.project.dto.CreateProjectRequest;
import ma.emsi.ptm.project.dto.ProjectResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public ProjectResponse create(Long userId, CreateProjectRequest req) {
        Project p = new Project();
        p.setTitle(req.title());
        p.setDescription(req.description());
        p.setUserId(userId);

        Project saved = projectRepository.save(p);
        return toResponse(saved);
    }

    public List<ProjectResponse> list(Long userId) {
        return projectRepository.findByUserId(userId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ProjectResponse get(Long userId, Long projectId) {
        Project p = projectRepository.findByIdAndUserId(projectId, userId)
                .orElseThrow(() -> new NotFoundException("Project not found"));
        return toResponse(p);
    }

    /** Used by tasks/progress to ensure ownership */
    public Project getOwnedEntity(Long userId, Long projectId) {
        return projectRepository.findByIdAndUserId(projectId, userId)
                .orElseThrow(() -> new NotFoundException("Project not found"));
    }

    private ProjectResponse toResponse(Project p) {
        return new ProjectResponse(p.getId(), p.getTitle(), p.getDescription());
    }
}

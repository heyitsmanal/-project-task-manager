package ma.emsi.ptm.progress;

import ma.emsi.ptm.progress.dto.ProgressResponse;
import ma.emsi.ptm.project.Project;
import ma.emsi.ptm.task.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class ProgressService {

    private final TaskRepository taskRepository;

    public ProgressService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public ProgressResponse get(Project project) {
        long total = taskRepository.countByProjectId(project.getId());
        long completed = taskRepository.countByProjectIdAndCompletedTrue(project.getId());
        int percentage = total == 0 ? 0 : (int) Math.round((completed * 100.0) / total);
        return new ProgressResponse(total, completed, percentage);
    }
}

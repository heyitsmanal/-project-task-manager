package ma.emsi.ptm.progress.dto;

public record ProgressResponse(
        long totalTasks,
        long completedTasks,
        int percentage
) {}

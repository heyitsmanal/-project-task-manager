export default function TaskList({ tasks, onComplete, onDelete }) {
    return (
        <ul className="list-group">
            {tasks.map(t => (
                <li key={t.id}
                    className="list-group-item d-flex justify-content-between">
          <span style={{ textDecoration: t.completed ? 'line-through' : '' }}>
            {t.title}
          </span>
                    <div>
                        {!t.completed && (
                            <button className="btn btn-sm btn-success me-2"
                                    onClick={() => onComplete(t.id)}>
                                ✓
                            </button>
                        )}
                        <button className="btn btn-sm btn-danger"
                                onClick={() => onDelete(t.id)}>
                            ✕
                        </button>
                    </div>
                </li>
            ))}
        </ul>
    )
}

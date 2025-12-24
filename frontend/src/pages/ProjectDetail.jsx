import { useEffect, useState } from 'react'
import { useParams, useNavigate } from 'react-router-dom'
import { getProject, getProgress } from '../api/projects.api.js'
import { getTasks, createTask, completeTask, deleteTask } from '../api/tasks.api.js'
import TaskForm from '../components/TaskForm.jsx'
import TaskList from '../components/TaskList.jsx'
import ProgressBar from '../components/ProgressBar.jsx'
import Loader from '../components/Loader.jsx'

export default function ProjectDetail() {
    const { id } = useParams()
    const nav = useNavigate()

    const [project, setProject] = useState(null)
    const [tasks, setTasks] = useState([])
    const [progress, setProgress] = useState({ totalTasks: 0, completedTasks: 0, percentage: 0 })
    const [loading, setLoading] = useState(true)

    const load = async () => {
        setLoading(true)
        try {
            const [proj, tasksRes, prog] = await Promise.all([
                getProject(id),
                getTasks(id),
                getProgress(id)
            ])
            setProject(proj)
            setTasks(tasksRes)
            setProgress(prog)
        } finally {
            setLoading(false)
        }
    }

    useEffect(() => {
        load()
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [id])

    if (loading) return <Loader />

    if (!project) {
        return (
            <div className="alert alert-danger">
                Project not found. <button className="btn btn-link p-0" onClick={() => nav('/projects')}>Back</button>
            </div>
        )
    }

    return (
        <>
            <div className="d-flex justify-content-between align-items-start mb-3">
                <div>
                    <h3 className="mb-1">{project.title}</h3>
                    {project.description && <p className="text-muted mb-0">{project.description}</p>}
                </div>
                <button className="btn btn-outline-secondary" onClick={() => nav('/projects')}>
                    Back
                </button>
            </div>

            <div className="mb-2">
                <div className="small text-muted">
                    {progress.completedTasks} / {progress.totalTasks} completed
                </div>
                <ProgressBar value={progress.percentage} />
            </div>

            <div className="card mb-3">
                <div className="card-body">
                    <TaskForm
                        onSubmit={async (data) => {
                            await createTask(id, data)
                            await load()
                        }}
                    />
                </div>
            </div>

            <TaskList
                tasks={tasks}
                onComplete={async (taskId) => {
                    await completeTask(id, taskId)
                    await load()
                }}
                onDelete={async (taskId) => {
                    await deleteTask(id, taskId)
                    await load()
                }}
            />
        </>
    )
}

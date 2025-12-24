import { useEffect, useState } from 'react'
import { getProjects, createProject } from '../api/projects.api'
import ProjectForm from '../components/ProjectForm'
import { useNavigate } from 'react-router-dom'

export default function Projects() {
    const [projects, setProjects] = useState([])
    const nav = useNavigate()

    useEffect(() => {
        getProjects().then(setProjects)
    }, [])

    const add = async (data) => {
        const p = await createProject(data)
        setProjects([...projects, p])
    }

    return (
        <>
            <h2>Projects</h2>
            <ProjectForm onSubmit={add} />
            <ul className="list-group mt-3">
                {projects.map(p => (
                    <li key={p.id}
                        className="list-group-item"
                        onClick={() => nav(`/projects/${p.id}`)}
                        style={{ cursor: 'pointer' }}>
                        {p.title}
                    </li>
                ))}
            </ul>
        </>
    )
}

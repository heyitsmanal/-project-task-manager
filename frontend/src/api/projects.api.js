import { http } from './http'

export const getProjects = () => http('/projects')

export const createProject = (data) =>
    http('/projects', {
        method: 'POST',
        body: JSON.stringify(data)
    })

export const getProject = (id) => http(`/projects/${id}`)

export const getProgress = (id) => http(`/projects/${id}/progress`)

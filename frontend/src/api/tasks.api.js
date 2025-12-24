import { http } from './http'

export const getTasks = (projectId) =>
    http(`/projects/${projectId}/tasks`)

export const createTask = (projectId, data) =>
    http(`/projects/${projectId}/tasks`, {
        method: 'POST',
        body: JSON.stringify(data)
    })

export const completeTask = (projectId, taskId) =>
    http(`/projects/${projectId}/tasks/${taskId}/complete`, {
        method: 'PATCH'
    })

export const deleteTask = (projectId, taskId) =>
    http(`/projects/${projectId}/tasks/${taskId}`, {
        method: 'DELETE'
    })

import { useState } from 'react'

export default function TaskForm({ onSubmit }) {
    const [title, setTitle] = useState('')
    const [description, setDescription] = useState('')
    const [dueDate, setDueDate] = useState('')

    return (
        <form
            onSubmit={(e) => {
                e.preventDefault()
                onSubmit({
                    title,
                    description: description.trim() === '' ? null : description,
                    dueDate: dueDate === '' ? null : dueDate
                })
                setTitle('')
                setDescription('')
                setDueDate('')
            }}
        >
            <input
                className="form-control mb-2"
                placeholder="Title"
                value={title}
                onChange={(e) => setTitle(e.target.value)}
                required
            />

            <textarea
                className="form-control mb-2"
                placeholder="Description (optional)"
                value={description}
                onChange={(e) => setDescription(e.target.value)}
            />

            <input
                type="date"
                className="form-control mb-2"
                value={dueDate}
                onChange={(e) => setDueDate(e.target.value)}
            />

            <button className="btn btn-success">Add Task</button>
        </form>
    )
}

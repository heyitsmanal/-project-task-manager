import { useState } from 'react'

export default function ProjectForm({ onSubmit }) {
    const [title, setTitle] = useState('')
    const [description, setDescription] = useState('')

    return (
        <form onSubmit={(e) => {
            e.preventDefault()
            onSubmit({ title, description })
            setTitle('')
            setDescription('')
        }}>
            <input className="form-control mb-2" placeholder="Title"
                   value={title} onChange={e => setTitle(e.target.value)} />
            <textarea className="form-control mb-2" placeholder="Description"
                      value={description} onChange={e => setDescription(e.target.value)} />
            <button className="btn btn-primary">Create</button>
        </form>
    )
}

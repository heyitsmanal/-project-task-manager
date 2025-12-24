import { useState } from 'react'
import { login } from '../api/auth.api'
import { useAuth } from '../auth/AuthProvider'
import { useNavigate } from 'react-router-dom'

export default function Login() {
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const { login: doLogin } = useAuth()
    const nav = useNavigate()

    const submit = async (e) => {
        e.preventDefault()
        const res = await login(email, password)
        doLogin(res.token)
        nav('/projects')
    }

    return (
        <div className="container mt-5" style={{ maxWidth: 400 }}>
            <h3>Login</h3>
            <form onSubmit={submit}>
                <input className="form-control mb-2" placeholder="Email"
                       value={email} onChange={e => setEmail(e.target.value)} />
                <input className="form-control mb-2" type="password"
                       placeholder="Password"
                       value={password} onChange={e => setPassword(e.target.value)} />
                <button className="btn btn-primary w-100">Login</button>
            </form>
        </div>
    )
}

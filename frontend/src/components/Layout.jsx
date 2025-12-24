import { Outlet, useNavigate } from 'react-router-dom'
import { useAuth } from '../auth/AuthProvider'

export default function Layout() {
    const { logout } = useAuth()
    const nav = useNavigate()

    return (
        <>
            <nav className="navbar navbar-dark bg-dark px-3">
                <span className="navbar-brand">Task Manager</span>
                <button className="btn btn-outline-light" onClick={() => {
                    logout()
                    nav('/login')
                }}>
                    Logout
                </button>
            </nav>

            <div className="container mt-4">
                <Outlet />
            </div>
        </>
    )
}

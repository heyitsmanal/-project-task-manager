import { Routes, Route, Navigate } from 'react-router-dom'
import Login from '../pages/Login.jsx'
import Projects from '../pages/Projects.jsx'
import ProjectDetail from '../pages/ProjectDetail.jsx'
import RequireAuth from '../auth/RequireAuth.jsx'
import Layout from '../components/Layout.jsx'

export default function AppRouter() {
    return (
        <Routes>
            <Route path="/login" element={<Login />} />

            <Route
                path="/"
                element={
                    <RequireAuth>
                        <Layout />
                    </RequireAuth>
                }
            >
                <Route index element={<Navigate to="/projects" replace />} />
                <Route path="projects" element={<Projects />} />
                <Route path="projects/:id" element={<ProjectDetail />} />
            </Route>

            <Route path="*" element={<Navigate to="/projects" replace />} />
        </Routes>
    )
}

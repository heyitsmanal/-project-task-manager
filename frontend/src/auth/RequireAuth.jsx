import { Navigate } from 'react-router-dom'
import { useAuth } from './AuthProvider'

export default function RequireAuth({ children }) {
    const { token } = useAuth()
    return token ? children : <Navigate to="/login" />
}

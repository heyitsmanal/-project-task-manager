import { http } from './http'

export function login(email, password) {
    return http('/auth/login', {
        method: 'POST',
        body: JSON.stringify({ email, password })
    })
}

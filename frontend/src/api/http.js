const API_URL = import.meta.env.VITE_API_URL

export async function http(url, options = {}) {
    const token = localStorage.getItem('token')

    const res = await fetch(`${API_URL}${url}`, {
        ...options,
        headers: {
            'Content-Type': 'application/json',
            ...(token && { Authorization: `Bearer ${token}` }),
            ...options.headers
        }
    })

    if (res.status === 401) {
        localStorage.removeItem('token')
        window.location.href = '/login'
    }

    if (!res.ok) {
        const err = await res.json()
        throw new Error(err.message || 'Request failed')
    }

    return res.status === 204 ? null : res.json()
}

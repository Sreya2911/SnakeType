const API_URL = "http://localhost:8080/api";

export async function registerUser(data) {
    const response = await fetch(`${API_URL}/auth/register`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    });

    return response.json();
}

export async function loginUser(data) {
    const response = await fetch(`${API_URL}/auth/login`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    });

    return response.json();
}

export async function getLanguages() {
    const response = await fetch(`${API_URL}/languages`);
    return response.json();
}

export async function getSnippet(language, complexity = 1) {
    const response = await fetch(
        `${API_URL}/practice/snippet?language=${language}&complexity=${complexity}`
    );

    return response.json();
}

export async function getAttempts(userId) {
    const response = await fetch(
        `${API_URL}/dashboard/${userId}/attempts`
    );

    return response.json();
}

export async function saveAttempt(data) {
    const response = await fetch(`${API_URL}/practice/attempt`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    });

    return response.json();
}

export async function getDashboard(userId) {
    const response = await fetch(
        `${API_URL}/dashboard/${userId}`
    );

    return response.json();
}

export async function getHistory(userId) {
    const response = await fetch(
        `${API_URL}/history/${userId}`
    );

    return response.json();
}


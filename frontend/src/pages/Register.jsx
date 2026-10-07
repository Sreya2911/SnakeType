import { useState } from "react";
import { registerUser } from "../api";

function Register({ onLogin, onBack }) {
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");

    const handleSubmit = async (event) => {
        event.preventDefault();
        setError("");

        try {
            const data = await registerUser({
                name,
                email,
                password
            });

            if (data.id) {
                localStorage.setItem(
                    "snaketypeUser",
                    JSON.stringify(data)
                );

                onLogin(data);
            } else {
                setError(data.message || "Registration failed");
            }
        } catch {
            setError("Unable to connect to server");
        }
    };

    return (
        <div className="auth-page">
            <div className="auth-card">
                <h1>SnakeType</h1>
                <p>Create your account</p>

                <form onSubmit={handleSubmit}>
                    <input
                        type="text"
                        placeholder="Name"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        required
                    />

                    <input
                        type="email"
                        placeholder="Email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />

                    <input
                        type="password"
                        placeholder="Password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />

                    {error && <div className="auth-error">{error}</div>}

                    <button type="submit">Create Account</button>
                </form>

                <p>
                    Already have an account?
                    <button
                        className="link-button"
                        onClick={onBack}
                    >
                        Login
                    </button>
                </p>
            </div>
        </div>
    );
}

export default Register;
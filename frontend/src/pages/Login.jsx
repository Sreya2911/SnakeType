import { useState } from "react";
import { loginUser } from "../api";

function Login({ onLogin, onRegister }) {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");

    const handleSubmit = async (event) => {
        event.preventDefault();
        setError("");

        try {
            const data = await loginUser({ email, password });

            if (data.id) {
                localStorage.setItem("snaketypeUser", JSON.stringify(data));
                onLogin(data);
            } else {
                setError(data.message || "Login failed");
            }
        } catch {
            setError("Unable to connect to server");
        }
    };

    return (
        <div className="auth-page">
            <div className="auth-card">
                <h1>SnakeType</h1>
                <p>Welcome back</p>

                <form onSubmit={handleSubmit}>
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

                    <button type="submit">Login</button>
                </form>

                <p>
                    Don't have an account?
                    <button
                        className="link-button"
                        onClick={onRegister}
                    >
                        Register
                    </button>
                </p>
            </div>
        </div>
    );
}

export default Login;
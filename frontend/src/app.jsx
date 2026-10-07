import { useEffect, useState } from "react";
import { getLanguages } from "./api";
import TypingArea from "./components/TypingArea";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Dashboard from "./pages/Dashboard";
import Practice from "./pages/Practice";
import History from "./pages/History";

function App() {
    const [languages, setLanguages] = useState([]);
    const [user, setUser] = useState(null);
    const [showRegister, setShowRegister] = useState(false);
    const [page, setPage] = useState("dashboard");
    const [selectedLanguage, setSelectedLanguage] = useState(null);

    useEffect(() => {
        const savedUser = localStorage.getItem("snaketypeUser");

        if (savedUser) {
            setUser(JSON.parse(savedUser));
        }
    }, []);

    useEffect(() => {
        if (user) {
            getLanguages().then(setLanguages);
        }
    }, [user]);

    if (!user) {
        if (showRegister) {
            return (
                <Register
                    onLogin={setUser}
                    onBack={() => setShowRegister(false)}
                />
            );
        }

        return (
            <Login
                onLogin={setUser}
                onRegister={() => setShowRegister(true)}
            />
        );
    }

    const logout = () => {
        localStorage.removeItem("snaketypeUser");
        setUser(null);
        setSelectedLanguage(null);
        setPage("dashboard");
    };

    const startPractice = (language) => {
        setSelectedLanguage(language);
        setPage("typing");
    };

    if (page === "typing" && selectedLanguage) {
        return (
            <TypingArea
                language={selectedLanguage}
                onBack={() => {
                    setSelectedLanguage(null);
                    setPage("practice");
                }}
            />
        );
    }

    if (page === "practice") {
        return (
            <Practice
                languages={languages}
                onSelect={startPractice}
                onBack={() => setPage("dashboard")}
            />
        );
    }

    if (page === "history") {
        return (
            <History
                user={user}
                onBack={() => setPage("dashboard")}
            />
        );
    }

    return (
        <Dashboard
            user={user}
            onLogout={logout}
            onPractice={() => setPage("practice")}
            onHistory={() => setPage("history")}
        />
    );
}

export default App;
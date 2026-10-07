import { useEffect, useState } from "react";
import { getDashboard } from "../api";

function Dashboard({ user, onPractice, onHistory, onLogout }) {
    const [dashboard, setDashboard] = useState(null);

    useEffect(() => {
        getDashboard(user.id).then(setDashboard);
    }, [user.id]);

    if (!dashboard) {
        return <div className="app">Loading dashboard...</div>;
    }

    return (
        <div className="dashboard-page">
            <div className="dashboard-header">
                <div>
                    <h1>SnakeType</h1>
                    <p>Welcome back, {user.name}</p>
                </div>

                <button
                    className="logout-button"
                    onClick={onLogout}
                >
                    Logout
                </button>
            </div>

            <div className="dashboard-stats">
                <div className="dashboard-card">
                    <span>Average WPM</span>
                    <strong>{dashboard.averageWpm}</strong>
                </div>

                <div className="dashboard-card">
                    <span>Best WPM</span>
                    <strong>{dashboard.bestWpm}</strong>
                </div>

                <div className="dashboard-card">
                    <span>Accuracy</span>
                    <strong>{dashboard.averageAccuracy}%</strong>
                </div>

                <div className="dashboard-card">
                    <span>Tests</span>
                    <strong>{dashboard.totalTests}</strong>
                </div>
            </div>

            <section className="language-performance">
                <h2>Language Performance</h2>

                {Object.entries(dashboard.languageStats).map(
                    ([language, stats]) => (
                        <div
                            className="language-row"
                            key={language}
                        >
                            <strong>{language}</strong>

                            <span>
                                {stats.averageWpm} WPM
                            </span>

                            <span>
                                {stats.averageAccuracy}% accuracy
                            </span>

                            <span>
                                {stats.tests} test
                                {stats.tests !== 1 ? "s" : ""}
                            </span>
                        </div>
                    )
                )}
            </section>

            <div className="dashboard-actions">
    <button
        className="practice-button"
        onClick={onPractice}
    >
        Start Practice
    </button>

    <button
        className="history-button"
        onClick={onHistory}
    >
        View History
    </button>
</div>
        </div>
    );
}

export default Dashboard;
import { useEffect, useState } from "react";
import { getHistory } from "../api";

function History({ user, onBack }) {
    const [history, setHistory] = useState([]);

    useEffect(() => {
        getHistory(user.id).then(setHistory);
    }, [user.id]);

    return (
        <div className="history-page">
            <button className="back-button" onClick={onBack}>
                ← Dashboard
            </button>

            <div className="history-heading">
                <h1>Practice History</h1>
                <p>Your completed SnakeType sessions</p>
            </div>

            {history.length === 0 ? (
                <div className="empty-history">
                    <h2>No attempts yet</h2>
                    <p>Complete a typing test and it will appear here.</p>
                </div>
            ) : (
                <div className="history-table-wrapper">
                    <table className="history-table">
                        <thead>
                            <tr>
                                <th>Date</th>
                                <th>Language</th>
                                <th>WPM</th>
                                <th>Accuracy</th>
                                <th>Errors</th>
                                <th>Duration</th>
                            </tr>
                        </thead>

                        <tbody>
                            {history.map((attempt) => (
                                <tr key={attempt.id}>
                                    <td>
                                        {new Date(
                                            attempt.completedAt
                                        ).toLocaleString()}
                                    </td>

                                    <td>
                                        {attempt.language.name}
                                    </td>

                                    <td className="history-wpm">
                                        {Math.round(attempt.wpm)}
                                    </td>

                                    <td>
                                        {Math.round(
                                            attempt.accuracy
                                        )}
                                        %
                                    </td>

                                    <td>
                                        {attempt.errorCount}
                                    </td>

                                    <td>
                                        {attempt.duration}s
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            )}
        </div>
    );
}

export default History;
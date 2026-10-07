import MetricCard from "../components/MetricCard";

function Results({ metrics, onRestart, onBack }) {
    return (
        <div className="results-page">
            <button className="back-button" onClick={onBack}>
                ← Languages
            </button>

            <h1>Test Complete</h1>

            <div className="metrics-grid">
                <MetricCard label="WPM" value={metrics.wpm} />
                <MetricCard label="CPM" value={metrics.cpm} />
                <MetricCard label="Raw WPM" value={metrics.rawWpm} />
                <MetricCard
                    label="Accuracy"
                    value={`${metrics.accuracy}%`}
                />

                <MetricCard
                    label="Correct"
                    value={metrics.correctCharacters}
                />

                <MetricCard
                    label="Incorrect"
                    value={metrics.incorrectCharacters}
                />

                <MetricCard
                    label="Errors"
                    value={metrics.errors}
                />

                <MetricCard
                    label="Consistency"
                    value={`${metrics.consistency}%`}
                />
            </div>

            <button className="restart-button" onClick={onRestart}>
                Try Again
            </button>
        </div>
    );
}

export default Results;
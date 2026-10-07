function Practice({ languages, onSelect, onBack }) {
    return (
        <div className="practice-page">
            <button
                className="back-button"
                onClick={onBack}
            >
                ← Dashboard
            </button>

            <div className="practice-heading">
                <h1>Choose a Language</h1>
                <p>
                    Select what you want to practice today.
                </p>
            </div>

            <div className="practice-grid">
                {languages.map((language) => (
                    <button
                        className="practice-language"
                        key={language.id}
                        onClick={() => onSelect(language.name)}
                    >
                        <strong>{language.name}</strong>
                        <span>
                            Start Practice →
                        </span>
                    </button>
                ))}
            </div>
        </div>
    );
}

export default Practice;
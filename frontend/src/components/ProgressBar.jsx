export default function ProgressBar({ value }) {
    return (
        <div className="progress mb-3">
            <div
                className="progress-bar"
                style={{ width: `${value}%` }}
            >
                {value}%
            </div>
        </div>
    )
}

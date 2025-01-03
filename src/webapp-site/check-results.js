window.onload = function () {
    // Retrieve the student's exam marks and answers from LocalStorage
    const examSubmissions = JSON.parse(localStorage.getItem('examSubmissions')) || [];
    const studentAnswers = JSON.parse(localStorage.getItem('studentAnswers')) || [];
    const marks = JSON.parse(localStorage.getItem('studentMarks')) || [];

    let resultMessage = 'You have not received any marks yet.';

    // Check if the student has submitted answers and if the submission matches
    examSubmissions.forEach((submission) => {
        if (JSON.stringify(submission.answers) === JSON.stringify(studentAnswers.answers)) {
            resultMessage = `Your marks: ${submission.marks}`;
        }
    });

    // If no marks are found in the exam submission, display the marks from the studentMarks
    if (marks.length > 0) {
        resultMessage = `Your marks: ${marks.join(', ')}`; // Display all marks
    }

    // Display the result message to the student
    document.getElementById('result').textContent = resultMessage;
};

// Function to redirect the student back to the homepage
function goToHome() {
    window.location.href = 'home.html';
}
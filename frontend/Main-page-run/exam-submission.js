// This function will populate the answers from LocalStorage
function loadStudentAnswers() {
    const studentAnswers = JSON.parse(localStorage.getItem('studentAnswers')) || [];
    const answersContainer = document.getElementById('student-answers');
    answersContainer.innerHTML = '';

    studentAnswers.forEach((answer, index) => {
        const questionDiv = document.createElement('div');
        questionDiv.classList.add('answer');
        questionDiv.innerHTML = `
            <p><strong>Question ${index + 1}: </strong>${answer.question}</p>
            <textarea disabled>${answer.answer}</textarea>
            <input type="number" class="marks-input" id="marks${index}" placeholder="Marks" min="0" max="10">
        `;
        answersContainer.appendChild(questionDiv);
    });
}

// This function will be triggered when admin submits the marks
function submitMarks() {
    const studentAnswers = JSON.parse(localStorage.getItem('studentAnswers')) || [];
    const marks = [];

    studentAnswers.forEach((_, index) => {
        const mark = document.getElementById(`marks${index}`).value;
        marks.push(mark);
    });

    // Store the marks in LocalStorage (or send to the backend)
    localStorage.setItem('studentMarks', JSON.stringify(marks));

    alert('Marks have been submitted!');
    window.location.href = 'admin-dashboard.html'; // Redirect back to admin dashboard after submission
}

// Admin login check (default admin credentials)
function checkLogin(event) {
    event.preventDefault();
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    // Default admin credentials (can be replaced with database authentication)
    const adminUsername = "admin";
    const adminPassword = "admin123";

    if (username === adminUsername && password === adminPassword) {
        // Redirect to check submissions page if login is successful
        location.href = 'exam-submission-page.html'; // Redirect to the page where admin reviews the exam answers
    } else {
        alert('Invalid login credentials.');
    }
}

// Load student answers when the page loads
window.onload = loadStudentAnswers;

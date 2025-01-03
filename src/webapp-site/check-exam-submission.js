// Admin login check (default admin credentials)
function checkLogin(event) {
    event.preventDefault();
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    // Default admin credentials (can be replaced with database authentication)
    const adminUsername = "admin";
    const adminPassword = "admin123";

    if (username === adminUsername && password === adminPassword) {
        // Hide login container and show the exam submission container
        document.getElementById('login-container').style.display = 'none';
        document.getElementById('exam-container').style.display = 'block';

        // Load student answers on successful login
        loadStudentAnswers();
    } else {
        alert('Invalid login credentials.');
    }
}

// Function to load student answers from LocalStorage
function loadStudentAnswers() {
    const storedData = JSON.parse(localStorage.getItem('studentAnswers')) || [];
    const answersContainer = document.getElementById('student-answers');
    answersContainer.innerHTML = ''; // Clear any existing content

    if (storedData.length === 0 || !Array.isArray(storedData.answers)) {
        answersContainer.innerHTML = '<p>No submissions available.</p>';
        return;
    }

    const studentAnswers = storedData.answers; // Extract answers array
    const timestamp = new Date(storedData.timestamp).toLocaleString(); // Format timestamp

    // Display timestamp
    const timestampDiv = document.createElement('div');
    timestampDiv.classList.add('timestamp');
    timestampDiv.innerHTML = `<p><strong>Submission Time:</strong> ${timestamp}</p>`;
    answersContainer.appendChild(timestampDiv);

    // Display each answer with a marks input field
    studentAnswers.forEach((answer, index) => {
        const answerDiv = document.createElement('div');
        answerDiv.classList.add('answer');
        answerDiv.innerHTML = `
            <p><strong>Question ${index + 1}:</strong></p>
            <textarea disabled>${answer}</textarea>
            <input type="number" class="marks-input" id="marks${index}" placeholder="Marks" min="0" max="10">
        `;
        answersContainer.appendChild(answerDiv);
    });
}

// Function to submit marks for the student
function submitMarks() {
    const studentAnswers = JSON.parse(localStorage.getItem('studentAnswers')) || {};
    const marks = [];

    if (!Array.isArray(studentAnswers.answers)) {
        alert('No answers to grade.');
        return;
    }

    studentAnswers.answers.forEach((_, index) => {
        const mark = document.getElementById(`marks${index}`).value;
        marks.push(mark);
    });

    // Save marks to LocalStorage
    localStorage.setItem('studentMarks', JSON.stringify(marks));

    alert('Marks have been submitted!');
    window.location.href = 'admin-dashboard.html'; // Redirect to admin dashboard
}

// Load student answers when the page loads
window.onload = function () {
    // Check if we are on the exam submission page directly
    if (document.getElementById('login-container')) {
        // If there's a login container, wait for admin login
        return;
    }

    // Load student answers otherwise
    loadStudentAnswers();
};
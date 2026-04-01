document.getElementById('current-date').innerText = new Date().toDateString();

function submitAttendance() {
    const id = document.getElementById('studentIdInput').value;
    const status = document.getElementById('statusInput').value;
    const msgDiv = document.getElementById('response-message');

    if(!id) {
        alert("Please enter a Student ID");
        return;
    }

    // Replace with your actual IP if testing on mobile, 
    // but localhost works for browser testing.
    const url = `http://localhost:8080/mark/${id}/${status}`;

    fetch(url, { method: 'POST' })
    .then(response => {
        if (!response.ok) throw new Error('Student not found or Server error');
        return response.json();
    })
    .then(data => {
        msgDiv.className = "message success";
        msgDiv.innerText = `Success: ${data.student.name} marked ${data.status}`;
    })
    .catch(error => {
        msgDiv.className = "message error";
        msgDiv.innerText = error.message;
    });
}

// 1. Fetch all students and display them in the table
function getAllStudents() {
    fetch('http://localhost:8080/Students') // Your GET endpoint
    .then(response => response.json())
    .then(data => {
        const tableBody = document.getElementById('studentTableBody');
        tableBody.innerHTML = ""; // Clear old data
        
        data.forEach(student => {
            tableBody.innerHTML += `
                <tr style="border-bottom: 1px solid #eee; height: 50px;">
                    <td>${student.id}</td>
                    <td>${student.name}</td>
                    <td>${student.semester}</td>
                    <td>
                        <button onclick="deleteStudent(${student.id})" style="background: #e74c3c; color: white; border: none; padding: 5px 10px; border-radius: 4px; cursor: pointer;">Delete</button>
                    </td>
                </tr>
            `;
        });
    });
}

// 2. Add a new student
function addStudent() {
    const name = document.getElementById('studentName').value;
    const semester = document.getElementById('studentSemester').value;

    if (!name || !semester) {
        alert("Please fill in both Name and Semester");
        return;
    }

    // Injecting variables into the URL path as expected by your @PathVariable
    const url = `http://localhost:8080/add/${name}/${semester}`;

    fetch(url, { method: 'POST' }) 
    .then(response => {
        if (!response.ok) throw new Error("Failed to add student. Check if ID exists.");
        return response.json();
    })
    .then(data => {
        alert(`Student ${data.name} added successfully!`);
        getAllStudents(); // Refresh your table
        document.getElementById('studentName').value = "";
        document.getElementById('studentSemester').value = "";
    })
    .catch(err => {
        console.error(err);
        alert("Error: " + err.message);
    });
}

function deleteStudent(id) {
    if (confirm("Are you sure you want to delete this student?")) {
        
        // Ensure the URL matches your @DeleteMapping("delete/{id}")
        fetch(`http://localhost:8080/delete/${id}`, { 
            method: 'DELETE' 
        })
        .then(response => {
            if (!response.ok) {
                throw new Error("Server error: Could not delete student.");
            }
            // Even if you don't use the list, you must parse it if Java returns it
            return response.json(); 
        })
        .then(data => {
            alert("Student Deleted successfully!");
            getAllStudents(); // Refresh the table to show the student is gone
        })
        .catch(error => {
            console.error("Delete Error:", error);
            alert("Delete failed. Check Console (F12) for details.");
        });
    }
}
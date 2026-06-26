# CodeAlpha_StudentGradeTracker

## Task 1: Student Grade Tracker — CodeAlpha Java Internship

### 📋 Description
A console-based Java application to input and manage student grades subject-wise.
Automatically calculates the average mark, highest score, lowest score, and letter
grade for each student, and displays a full class summary report.

---

### 🚀 Features
- Add multiple students by name
- Enter marks **subject by subject** (e.g. Maths, Science, English)
- Automatically calculates **average mark** after all subjects are entered
- View individual student report with subject-wise mark breakdown
- View all students in a formatted summary table
- Class-wide statistics (top performer, lowest performer, class average)
- Letter grade assigned based on average (A / B / C / D / F)
- Input validation (mark range 0–100, duplicate name check)

---

### 🧮 How Average is Calculated
```
Subjects entered:
  Maths   → 85
  Science → 90
  English → 78

Average = (85 + 90 + 78) / 3 = 84.33  →  Letter Grade: B
```

---

### 📊 Sample Output
```
  Subject              Mark
  ──────────────────────────────
  Maths                85.0
  Science              90.0
  English              78.0
  ──────────────────────────────
  AVERAGE              84.33  ← Average Mark
  Highest Mark         90.00
  Lowest Mark          78.00
  Letter Grade  : B
```

---

### 🛠️ How to Run

```bash
# Step 1 — Create output folder
mkdir out

# Step 2 — Compile
javac src/StudentGradeTracker.java -d out/

# Step 3 — Run
java -cp out/ StudentGradeTracker
```

---

### 💡 Concepts Used
- **OOP** — `Student` inner class with encapsulated fields and methods
- **Two parallel ArrayLists** — `subjects` (names) and `grades` (marks) at matching indexes
- **Average calculation** — sum of all marks ÷ number of subjects
- **Input validation** — try-catch for invalid numbers, range check 0–100
- **Console formatting** — `printf` for aligned table output

---

### 📂 File Structure
```
CodeAlpha_StudentGradeTracker/
├── src/
│   └── StudentGradeTracker.java
├── out/           ← compiled .class files go here
└── README.md
```

---

### 🎓 Menu Options
| Option | Action |
|--------|--------|
| 1 | Add new student |
| 2 | Add subject marks (enter all subjects at once) |
| 3 | View individual student report |
| 4 | View all students summary table |
| 5 | Class statistics |
| 6 | Exit |

---

### 👤 Author
Narasimman G — CodeAlpha Java Programming Intern


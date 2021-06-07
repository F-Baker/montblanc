# Acceptance Testings

## Design decision questions
- the Member role should  __not__ include Student
- doing so allows requires more logic gates to keep access pertinent
    - e.g.
        - A director who is a member must have access to all student attendance
        - there needs to be a gate to distinguish the director(member) from a student(member)
        
- Who has full CRUD for the courses (séances)?
    - currently, the only spec is for updating a date in Course (3.IV)

- Is Participation and Attendance actually the same entity?
    - currently there is a need to join the two to get attendance to a course ** and ** participation in a exercise
    - absence in Participation is ambiguous 
    
- Should Administrators have access to CRUD Participation?
    - currently, it's just teachers
    - e.g
        - planned absence coordinated with the Director
        - no need for the student to ask both the teacher and the director to update a planned absence
    
- (for fun) Could teachers be included in the same attendance system as the students? One timesheet for everyone?

- Need more clarification on the question types; Question, Questionnaire, Exercise, Evaluation
    - are they not all the same type?
- What do evaluations belong to?
    - Course?  A student?
    
- Is a "session" a "séance"

- Why do trainers have the ability to sign up students?  
    - This should be exclusively reserved for the Administrators/Directors
    
- What is a member of a channel called?  Participants?  Member? 9.III

## 1. Group work, exercises
- As a teacher, I can 
    - CRUD exercises
- As a participant, I can 
    - CRUD groups
    - CRUD participants
    - Read exercises for a channel
    - Read channels
    
### Constraints
    - teachers and students who __are not__ participants cannot access 
        - CRUD methods of EFG
        - CRUD methods of Group 
    - teachers become participants only after creating a new group
    - students become participants only after an invitation


## 2. Q/A
- As a teacher, I can 
    - CRUD question
    - Question has multiple types
- As a student, I can
    - CRU responses
    - Read questions
    - Read channels
    - Response has multiple types

### Constraints
- teachers only have access to Read Response
- students only have access to Read Question
    
    
## 3. Attendance
- As a student, I can create an attendance entry
- As a member (prof or director) I can CRUD Attendance for 
    - a student
    - all students
    - a course
- As a teacher, I can 
    - start a Course (update start_date in Course)
    
### Constraints
- students can only create an entry if
    - the day is the current day
    - the attendance sheet is a course containing the student ID

    
## 4. Participation
- As a teacher, I can CRUD Participation

### Constraints
- Participations must have 4 types
    - absent
    - none
    - some
    - excellent
- 2 q's for clarification before advancing


## 5 Questionnaires
- As a teacher, I can 
    - CRUD Questionnaire
        - update start_date
        - list one, list all, list by
    - Read Response
        - sum by Questionnaire attempts (passages)
        - 
    - add a Questionnaire to a course
        - this is done by updating the course_id of a Questionnaire
- As a student, I can
    - Read Questionnaire
    - CR Response

### Constraints
- a teacher only has Read access over responses
- a student cannot update/delete a Response once created
    - retaking the Questionnaire generates a new entry
- attempt results should be displayed
    - as a percentage or 20-based
    - list one, list all, list average by group

    
## 8. Evaluations
- As a teacheer, I can
    - CRUD Evaluation
- As a student, I can Read from Evaluations

### Constraints
- open/incomplete/complete evaluations must be marked as such


## 7. Trombinoscope (yearbook)
- As a member, I can 
    - CRUD all sessions (??) 

### Constraints
- any session can be listed, regardless of being active/inactive 


## 8. Student signup
- As a student, I can
    - submit a Create request for Users
- As a director or teacher, I can
    - approve the request

### Constraints
- the student must recieve 
    - a confirmation email that the request has been successfuly submitted
    - a confirmation email that the request has been successfuly accepted
    - a confirmation email that the request has been denied/more actions required
- a director or teacher can only update the "accepted" column


## 9. Channel management for classes and subject
- As an admin, I can
    - CRUD Channel
    - CRUD Participant
- As a Participant, I can
    - Read Participant
    - Read Channel 

### Constraints
- nothing glaring besides the clarification of "member"

## 10. Kanban board
- As a participant, I can
    - CRUD kanban

### Constraints
- this is a huge portion to add and hasn't been properly outlined
- need clarification


## 11. Task Delegation














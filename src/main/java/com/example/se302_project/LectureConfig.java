package com.example.se302_project;



public class LectureConfig {
    private int lecture_id;
    private String lecture_Name;
    private String lecturer_Name;
    private String lecture_Code;
    public String semester;
    private String  theory_Hour_In_Week;
    private String application_or_Lab_Hour_In_Week;
    private String local_Credits;
    private String ECTS;
    private String prerequisites;
    private String course_Language;
    private String course_Type;
    private String course_Level;
    private String teaching_Methods_and_Techniques;
    private String course_Coordinator;
    private String assistant;
    private String course_Objectives;
    private String learning_Outcomes;
    private String course_Description;
    private String course_Category;
    private String week1Subjects;
    private String week2Subjects;
    private String week3Subjects;
    private String week4Subjects;
    private String week5Subjects;
    private String week6Subjects;
    private String week7Subjects;
    private String week8Subjects;
    private String week9Subjects;
    private String week10Subjects;
    private String week11Subjects;
    private String week12Subjects;
    private String week13Subjects;
    private String week14Subjects;
    private String week15Subjects;

    private String week1ReqMat;
    private String week2ReqMat;
    private String week3ReqMat;
    private String week4ReqMat;
    private String week5ReqMat;
    private String week6ReqMat;
    private String week7ReqMat;
    private String week8ReqMat;
    private String week9ReqMat;
    private String week10ReqMat;
    private String week11ReqMat;
    private String week12ReqMat;
    private String week13ReqMat;
    private String week14ReqMat;
    private String week15ReqMat;
    private String courseNotesAndTextBooks;
    private String suggestedReadingsAndMaterials;
   /* private String Participation_Number;
    private String Laboratory_or_Application_Number;
    private String Field_Work_Number;
    private String Quiz_or_StudioCritique_Number;
    private String Homework_or_Assignment_Number;
    private String Presentation_or_Jury_Number;
    private String Project_Number ;
    private String Seminar_or_Workshop_Number;
    private String Oral_Exam_Number;
    private String Midterm_Number ;
    private String Final_Exam_Number;
    private String Participation_Weigthing;
    private String Laboratory_or_Application_Weigthing;
    private String Field_Work_Weigthing;
    private String Quiz_or_StudioCritique_Weigthing;
    private String Homework_or_Assignment_Weigthing;
    private String Presentation_or_Jury_Weigthing;
    private String Project_Weigthing ;
    private String Seminar_or_Workshop_Weigthing;
    private String Oral_Exam_Weigthing;
    private String Midterm_Weigthing ;
    private String Final_Exam_Weigthing;
    private String Participation_LO1;
    private String Laboratory_or_Application_LO1;
    private String Field_Work_LO1;
    private String Quiz_or_StudioCritique_LO1;
    private String Homework_or_Assignment_LO1;
    private String Presentation_or_Jury_LO1;
    private String Project_LO1 ;
    private String Seminar_or_Workshop_LO1;
    private String Oral_Exam_LO1;
    private String Midterm_LO1 ;
    private String Final_Exam_LO1;

    private String Participation_LO2;
    private String Laboratory_or_Application_LO2;
    private String Field_Work_LO2;
    private String Quiz_or_StudioCritique_LO2;
    private String Homework_or_Assignment_LO2;
    private String Presentation_or_Jury_LO2;
    private String Project_LO2 ;
    private String Seminar_or_Workshop_LO2;
    private String Oral_Exam_LO2;
    private String Midterm_LO2 ;
    private String Final_Exam_LO2;

    private String Participation_LO3;
    private String Laboratory_or_Application_LO3;
    private String Field_Work_LO3;
    private String Quiz_or_StudioCritique_LO3;
    private String Homework_or_Assignment_LO3;
    private String Presentation_or_Jury_LO3;
    private String Project_LO3 ;
    private String Seminar_or_Workshop_LO3;
    private String Oral_Exam_LO3;
    private String Midterm_LO3 ;
    private String Final_Exam_LO3;
    private String Participation_LO4;
    private String Laboratory_or_Application_LO4;
    private String Field_Work_LO4;
    private String Quiz_or_StudioCritique_LO4;
    private String Homework_or_Assignment_LO4;
    private String Presentation_or_Jury_LO4;
    private String Project_LO4 ;
    private String Seminar_or_Workshop_LO4;
    private String Oral_Exam_LO4;
    private String Midterm_LO4 ;
    private String Final_Exam_LO4;*/
    private String StudyHoursoutofClassNum;
    private String StudyHoursoutofClassDur;
    private String StudyHoursoutofClassWork;
    private String FieldWorkNum;
    private String FieldWorkDur;
    private String FieldWorkWork;
    private String QuizNum;
    private String QuizDur;
    private String QuizWork;
    private String HWNum;
    private String HWDur;
    private String HWWork;
    private String PresentationNum;
    private String PresentationDur;
    private String PresentationWork;
    private String ProjectNum;
    private String ProjectDur;
    private String ProjectWork;
    private String PortfolioNum;
    private String PortfolioDur;
    private String PortfolioWork;
    private String SeminarNum;
    private String SeminarDur;
    private String SeminarWork;
    private String OralExamNum;
    private String OralExamDur;
    private String OralExamWork;
    private String MidtermNum;
    private String MidterDur;
    private String MidterWork;
    private String FinalExamNum;
    private String FinalExamDur;
    private String FinalExamWork;

    public String getLecture_Code() {
        return lecture_Code;
    }

    public void setLecture_Code(String lecture_Code) {
        this.lecture_Code = lecture_Code;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        switch (semester) {
            case "Fall":
                // code for handling Fall semester
                break;
            case "Spring":
                // code for handling Spring semester
                break;
        }
    }

    public String getTheory_Hour_In_Week() {
        return theory_Hour_In_Week;
    }

    public void setTheory_Hour_In_Week(String theory_Hour_In_Week) {
        this.theory_Hour_In_Week = theory_Hour_In_Week;
    }

    public String getApplication_or_Lab_Hour_In_Week() {
        return application_or_Lab_Hour_In_Week;
    }

    public void setApplication_or_Lab_Hour_In_Week(String application_or_Lab_Hour_In_Week) {
        this.application_or_Lab_Hour_In_Week = application_or_Lab_Hour_In_Week;
    }

    public String getLocal_Credits() {
        return local_Credits;
    }

    public void setLocal_Credits(String local_Credits) {
        this.local_Credits = local_Credits;
    }

    public String  getECTS() {
        return ECTS;
    }

    public void setECTS(String ECTS) {
        this.ECTS = ECTS;
    }

    public String getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }

    public String getCourse_Language() {
        return course_Language;
    }

    public void setCourse_Language(String course_Language) {
        switch (course_Language) {
            case "English":
                break;
            case "Turkish":
                break;
            case "Second Foreign Language":
                break;
        }

    }

    public String getCourse_Type() {
        return course_Type;
    }

    public void setCourse_Type(String course_Type) {
        switch (course_Type) {
            case "Required":
                break;
            case "Elective":
                break;
        }
    }

    public String getCourse_Level() {
        return course_Level;
    }

    public void setCourse_Level(String course_Level) {
        switch (course_Level) {
            case "Short Cycle":
                break;
            case "First Cycle":
                break;
            case "Second Cycle":
                break;
            case "Third Cycle":
                break;
        }
    }

    public String getTeaching_Methods_and_Techniques() {
        return teaching_Methods_and_Techniques;
    }

    public void setTeaching_Methods_and_Techniques(String teaching_Methods_and_Techniques) {
        this.teaching_Methods_and_Techniques = teaching_Methods_and_Techniques;
    }

    public String getCourse_Coordinator() {
        return course_Coordinator;
    }

    public void setCourse_Coordinator(String course_Coordinator) {
        this.course_Coordinator = course_Coordinator;
    }

    public String getAssistant() {
        return assistant;
    }

    public void setAssistant(String assistant) {
        this.assistant = assistant;
    }

    public String getCourse_Objectives() {
        return course_Objectives;
    }

    public void setCourse_Objectives(String course_Objectives) {
        this.course_Objectives = course_Objectives;
    }

    public String getLearning_Outcomes() {
        return learning_Outcomes;
    }

    public void setLearning_Outcomes(String learning_Outcomes) {
        this.learning_Outcomes = learning_Outcomes;
    }

    public String getCourse_Description() {
        return course_Description;
    }

    public void setCourse_Description(String course_Description) {
        this.course_Description = course_Description;
    }

    public String getCourse_Category() {
        return course_Category;
    }

    public void setCourse_Category(String course_Category) {
        switch (course_Category) {
            case "Core Course":
                break;
            case "Major Area Course":
                break;
            case "Supportive Course":
                break;
            case "Communication and Management Skills Course":
                break;
            case "Transferable Skill Courses":
                break;
        }
    }

    public String getWeek1Subjects() {
        return week1Subjects;
    }

    public void setWeek1Subjects(String week1Subjects) {
        this.week1Subjects = week1Subjects;
    }

    public String getWeek2Subjects() {
        return week2Subjects;
    }

    public void setWeek2Subjects(String week2Subjects) {
        this.week2Subjects = week2Subjects;
    }

    public String getWeek3Subjects() {
        return week3Subjects;
    }

    public void setWeek3Subjects(String week3Subjects) {
        this.week3Subjects = week3Subjects;
    }

    public String getWeek4Subjects() {
        return week4Subjects;
    }

    public void setWeek4Subjects(String week4Subjects) {
        this.week4Subjects = week4Subjects;
    }

    public String getWeek5Subjects() {
        return week5Subjects;
    }

    public void setWeek5Subjects(String week5Subjects) {
        this.week5Subjects = week5Subjects;
    }

    public String getWeek6Subjects() {
        return week6Subjects;
    }

    public void setWeek6Subjects(String week6Subjects) {
        this.week6Subjects = week6Subjects;
    }

    public String getWeek7Subjects() {
        return week7Subjects;
    }

    public void setWeek7Subjects(String week7Subjects) {
        this.week7Subjects = week7Subjects;
    }

    public String getWeek8Subjects() {
        return week8Subjects;
    }

    public void setWeek8Subjects(String week8Subjects) {
        this.week8Subjects = week8Subjects;
    }

    public String getWeek9Subjects() {
        return week9Subjects;
    }

    public void setWeek9Subjects(String week9Subjects) {
        this.week9Subjects = week9Subjects;
    }

    public String getWeek10Subjects() {
        return week10Subjects;
    }

    public void setWeek10Subjects(String week10Subjects) {
        this.week10Subjects = week10Subjects;
    }

    public String getWeek11Subjects() {
        return week11Subjects;
    }

    public void setWeek11Subjects(String week11Subjects) {
        this.week11Subjects = week11Subjects;
    }

    public String getWeek12Subjects() {
        return week12Subjects;
    }

    public void setWeek12Subjects(String week12Subjects) {
        this.week12Subjects = week12Subjects;
    }

    public String getWeek13Subjects() {
        return week13Subjects;
    }

    public void setWeek13Subjects(String week13Subjects) {
        this.week13Subjects = week13Subjects;
    }

    public String getWeek14Subjects() {
        return week14Subjects;
    }

    public void setWeek14Subjects(String week14Subjects) {
        this.week14Subjects = week14Subjects;
    }

    public String getWeek15Subjects() {
        return week15Subjects;
    }

    public void setWeek15Subjects(String week15Subjects) {
        this.week15Subjects = week15Subjects;
    }

    public String getWeek1ReqMat() {
        return week1ReqMat;
    }

    public void setWeek1ReqMat(String week1ReqMat) {
        this.week1ReqMat = week1ReqMat;
    }

    public String getWeek2ReqMat() {
        return week2ReqMat;
    }

    public void setWeek2ReqMat(String week2ReqMat) {
        this.week2ReqMat = week2ReqMat;
    }

    public String getWeek3ReqMat() {
        return week3ReqMat;
    }

    public void setWeek3ReqMat(String week3ReqMat) {
        this.week3ReqMat = week3ReqMat;
    }

    public String getWeek4ReqMat() {
        return week4ReqMat;
    }

    public void setWeek4ReqMat(String week4ReqMat) {
        this.week4ReqMat = week4ReqMat;
    }

    public String getWeek5ReqMat() {
        return week5ReqMat;
    }

    public void setWeek5ReqMat(String week5ReqMat) {
        this.week5ReqMat = week5ReqMat;
    }

    public String getWeek6ReqMat() {
        return week6ReqMat;
    }

    public void setWeek6ReqMat(String week6ReqMat) {
        this.week6ReqMat = week6ReqMat;
    }

    public String getWeek7ReqMat() {
        return week7ReqMat;
    }

    public void setWeek7ReqMat(String week7ReqMat) {
        this.week7ReqMat = week7ReqMat;
    }

    public String getWeek8ReqMat() {
        return week8ReqMat;
    }

    public void setWeek8ReqMat(String week8ReqMat) {
        this.week8ReqMat = week8ReqMat;
    }

    public String getWeek9ReqMat() {
        return week9ReqMat;
    }

    public void setWeek9ReqMat(String week9ReqMat) {
        this.week9ReqMat = week9ReqMat;
    }

    public String getWeek10ReqMat() {
        return week10ReqMat;
    }

    public void setWeek10ReqMat(String week10ReqMat) {
        this.week10ReqMat = week10ReqMat;
    }

    public String getWeek11ReqMat() {
        return week11ReqMat;
    }

    public void setWeek11ReqMat(String week11ReqMat) {
        this.week11ReqMat = week11ReqMat;
    }

    public String getWeek12ReqMat() {
        return week12ReqMat;
    }

    public void setWeek12ReqMat(String week12ReqMat) {
        this.week12ReqMat = week12ReqMat;
    }

    public String getWeek13ReqMat() {
        return week13ReqMat;
    }

    public void setWeek13ReqMat(String week13ReqMat) {
        this.week13ReqMat = week13ReqMat;
    }

    public String getWeek14ReqMat() {
        return week14ReqMat;
    }

    public void setWeek14ReqMat(String week14ReqMat) {
        this.week14ReqMat = week14ReqMat;
    }

    public String getWeek15ReqMat() {
        return week15ReqMat;
    }

    public void setWeek15ReqMat(String week15ReqMat) {
        this.week15ReqMat = week15ReqMat;
    }

    public String getCourseNotesAndTextBooks() {
        return courseNotesAndTextBooks;
    }

    public void setCourseNotesAndTextBooks(String courseNotesAndTextBooks) {
        this.courseNotesAndTextBooks = courseNotesAndTextBooks;
    }

    public String getSuggestedReadingsAndMaterials() {
        return suggestedReadingsAndMaterials;
    }

  /*  public String getParticipation_Number() {
        return Participation_Number;
    }

    public void setParticipation_Number(String participation_Number) {
        Participation_Number = participation_Number;
    }

    public String getLaboratory_or_Application_Number() {
        return Laboratory_or_Application_Number;
    }

    public void setLaboratory_or_Application_Number(String laboratory_or_Application_Number) {
        Laboratory_or_Application_Number = laboratory_or_Application_Number;
    }

    public String getField_Work_Number() {
        return Field_Work_Number;
    }

    public void setField_Work_Number(String field_Work_Number) {
        Field_Work_Number = field_Work_Number;
    }

    public String getQuiz_or_StudioCritique_Number() {
        return Quiz_or_StudioCritique_Number;
    }

    public void setQuiz_or_StudioCritique_Number(String quiz_or_StudioCritique_Number) {
        Quiz_or_StudioCritique_Number = quiz_or_StudioCritique_Number;
    }

    public String getHomework_or_Assignment_Number() {
        return Homework_or_Assignment_Number;
    }

    public void setHomework_or_Assignment_Number(String homework_or_Assignment_Number) {
        Homework_or_Assignment_Number = homework_or_Assignment_Number;
    }

    public String getPresentation_or_Jury_Number() {
        return Presentation_or_Jury_Number;
    }

    public void setPresentation_or_Jury_Number(String presentation_or_Jury_Number) {
        Presentation_or_Jury_Number = presentation_or_Jury_Number;
    }

    public String getProject_Number() {
        return Project_Number;
    }

    public void setProject_Number(String project_Number) {
        Project_Number = project_Number;
    }

    public String getSeminar_or_Workshop_Number() {
        return Seminar_or_Workshop_Number;
    }

    public void setSeminar_or_Workshop_Number(String seminar_or_Workshop_Number) {
        Seminar_or_Workshop_Number = seminar_or_Workshop_Number;
    }

    public String getOral_Exam_Number() {
        return Oral_Exam_Number;
    }

    public void setOral_Exam_Number(String oral_Exam_Number) {
        Oral_Exam_Number = oral_Exam_Number;
    }

    public String getMidterm_Number() {
        return Midterm_Number;
    }

    public void setMidterm_Number(String midterm_Number) {
        Midterm_Number = midterm_Number;
    }

    public String getFinal_Exam_Number() {
        return Final_Exam_Number;
    }

    public void setFinal_Exam_Number(String final_Exam_Number) {
        Final_Exam_Number = final_Exam_Number;
    }

    public String getParticipation_Weigthing() {
        return Participation_Weigthing;
    }

    public void setParticipation_Weigthing(String participation_Weigthing) {
        Participation_Weigthing = participation_Weigthing;
    }

    public String getLaboratory_or_Application_Weigthing() {
        return Laboratory_or_Application_Weigthing;
    }

    public void setLaboratory_or_Application_Weigthing(String laboratory_or_Application_Weigthing) {
        Laboratory_or_Application_Weigthing = laboratory_or_Application_Weigthing;
    }

    public String getField_Work_Weigthing() {
        return Field_Work_Weigthing;
    }

    public void setField_Work_Weigthing(String field_Work_Weigthing) {
        Field_Work_Weigthing = field_Work_Weigthing;
    }

    public String getQuiz_or_StudioCritique_Weigthing() {
        return Quiz_or_StudioCritique_Weigthing;
    }

    public void setQuiz_or_StudioCritique_Weigthing(String quiz_or_StudioCritique_Weigthing) {
        Quiz_or_StudioCritique_Weigthing = quiz_or_StudioCritique_Weigthing;
    }

    public String getHomework_or_Assignment_Weigthing() {
        return Homework_or_Assignment_Weigthing;
    }

    public void setHomework_or_Assignment_Weigthing(String homework_or_Assignment_Weigthing) {
        Homework_or_Assignment_Weigthing = homework_or_Assignment_Weigthing;
    }

    public String getPresentation_or_Jury_Weigthing() {
        return Presentation_or_Jury_Weigthing;
    }

    public void setPresentation_or_Jury_Weigthing(String presentation_or_Jury_Weigthing) {
        Presentation_or_Jury_Weigthing = presentation_or_Jury_Weigthing;
    }

    public String getProject_Weigthing() {
        return Project_Weigthing;
    }

    public void setProject_Weigthing(String project_Weigthing) {
        Project_Weigthing = project_Weigthing;
    }

    public String getSeminar_or_Workshop_Weigthing() {
        return Seminar_or_Workshop_Weigthing;
    }

    public void setSeminar_or_Workshop_Weigthing(String seminar_or_Workshop_Weigthing) {
        Seminar_or_Workshop_Weigthing = seminar_or_Workshop_Weigthing;
    }

    public String getOral_Exam_Weigthing() {
        return Oral_Exam_Weigthing;
    }

    public void setOral_Exam_Weigthing(String oral_Exam_Weigthing) {
        Oral_Exam_Weigthing = oral_Exam_Weigthing;
    }

    public String getMidterm_Weigthing() {
        return Midterm_Weigthing;
    }

    public void setMidterm_Weigthing(String midterm_Weigthing) {
        Midterm_Weigthing = midterm_Weigthing;
    }

    public String getFinal_Exam_Weigthing() {
        return Final_Exam_Weigthing;
    }

    public void setFinal_Exam_Weigthing(String final_Exam_Weigthing) {
        Final_Exam_Weigthing = final_Exam_Weigthing;
    }

    public String getParticipation_LO1() {
        return Participation_LO1;
    }

    public void setParticipation_LO1(String participation_LO1) {
        Participation_LO1 = participation_LO1;
    }

    public String getLaboratory_or_Application_LO1() {
        return Laboratory_or_Application_LO1;
    }

    public void setLaboratory_or_Application_LO1(String laboratory_or_Application_LO1) {
        Laboratory_or_Application_LO1 = laboratory_or_Application_LO1;
    }

    public String getField_Work_LO1() {
        return Field_Work_LO1;
    }

    public void setField_Work_LO1(String field_Work_LO1) {
        Field_Work_LO1 = field_Work_LO1;
    }

    public String getQuiz_or_StudioCritique_LO1() {
        return Quiz_or_StudioCritique_LO1;
    }

    public void setQuiz_or_StudioCritique_LO1(String quiz_or_StudioCritique_LO1) {
        Quiz_or_StudioCritique_LO1 = quiz_or_StudioCritique_LO1;
    }

    public String getHomework_or_Assignment_LO1() {
        return Homework_or_Assignment_LO1;
    }

    public void setHomework_or_Assignment_LO1(String homework_or_Assignment_LO1) {
        Homework_or_Assignment_LO1 = homework_or_Assignment_LO1;
    }

    public String getPresentation_or_Jury_LO1() {
        return Presentation_or_Jury_LO1;
    }

    public void setPresentation_or_Jury_LO1(String presentation_or_Jury_LO1) {
        Presentation_or_Jury_LO1 = presentation_or_Jury_LO1;
    }

    public String getProject_LO1() {
        return Project_LO1;
    }

    public void setProject_LO1(String project_LO1) {
        Project_LO1 = project_LO1;
    }

    public String getSeminar_or_Workshop_LO1() {
        return Seminar_or_Workshop_LO1;
    }

    public void setSeminar_or_Workshop_LO1(String seminar_or_Workshop_LO1) {
        Seminar_or_Workshop_LO1 = seminar_or_Workshop_LO1;
    }

    public String getOral_Exam_LO1() {
        return Oral_Exam_LO1;
    }

    public void setOral_Exam_LO1(String oral_Exam_LO1) {
        Oral_Exam_LO1 = oral_Exam_LO1;
    }

    public String getMidterm_LO1() {
        return Midterm_LO1;
    }

    public void setMidterm_LO1(String midterm_LO1) {
        Midterm_LO1 = midterm_LO1;
    }

    public String getFinal_Exam_LO1() {
        return Final_Exam_LO1;
    }

    public void setFinal_Exam_LO1(String final_Exam_LO1) {
        Final_Exam_LO1 = final_Exam_LO1;
    }

    public String getParticipation_LO2() {
        return Participation_LO2;
    }

    public void setParticipation_LO2(String participation_LO2) {
        Participation_LO2 = participation_LO2;
    }

    public String getLaboratory_or_Application_LO2() {
        return Laboratory_or_Application_LO2;
    }

    public void setLaboratory_or_Application_LO2(String laboratory_or_Application_LO2) {
        Laboratory_or_Application_LO2 = laboratory_or_Application_LO2;
    }

    public String getField_Work_LO2() {
        return Field_Work_LO2;
    }

    public void setField_Work_LO2(String field_Work_LO2) {
        Field_Work_LO2 = field_Work_LO2;
    }

    public String getQuiz_or_StudioCritique_LO2() {
        return Quiz_or_StudioCritique_LO2;
    }

    public void setQuiz_or_StudioCritique_LO2(String quiz_or_StudioCritique_LO2) {
        Quiz_or_StudioCritique_LO2 = quiz_or_StudioCritique_LO2;
    }

    public String getHomework_or_Assignment_LO2() {
        return Homework_or_Assignment_LO2;
    }

    public void setHomework_or_Assignment_LO2(String homework_or_Assignment_LO2) {
        Homework_or_Assignment_LO2 = homework_or_Assignment_LO2;
    }

    public String getPresentation_or_Jury_LO2() {
        return Presentation_or_Jury_LO2;
    }

    public void setPresentation_or_Jury_LO2(String presentation_or_Jury_LO2) {
        Presentation_or_Jury_LO2 = presentation_or_Jury_LO2;
    }

    public String getProject_LO2() {
        return Project_LO2;
    }

    public void setProject_LO2(String project_LO2) {
        Project_LO2 = project_LO2;
    }

    public String getSeminar_or_Workshop_LO2() {
        return Seminar_or_Workshop_LO2;
    }

    public void setSeminar_or_Workshop_LO2(String seminar_or_Workshop_LO2) {
        Seminar_or_Workshop_LO2 = seminar_or_Workshop_LO2;
    }

    public String getOral_Exam_LO2() {
        return Oral_Exam_LO2;
    }

    public void setOral_Exam_LO2(String oral_Exam_LO2) {
        Oral_Exam_LO2 = oral_Exam_LO2;
    }

    public String getMidterm_LO2() {
        return Midterm_LO2;
    }

    public void setMidterm_LO2(String midterm_LO2) {
        Midterm_LO2 = midterm_LO2;
    }

    public String getFinal_Exam_LO2() {
        return Final_Exam_LO2;
    }

    public void setFinal_Exam_LO2(String final_Exam_LO2) {
        Final_Exam_LO2 = final_Exam_LO2;
    }

    public String getParticipation_LO3() {
        return Participation_LO3;
    }

    public void setParticipation_LO3(String participation_LO3) {
        Participation_LO3 = participation_LO3;
    }

    public String getLaboratory_or_Application_LO3() {
        return Laboratory_or_Application_LO3;
    }

    public void setLaboratory_or_Application_LO3(String laboratory_or_Application_LO3) {
        Laboratory_or_Application_LO3 = laboratory_or_Application_LO3;
    }

    public String getField_Work_LO3() {
        return Field_Work_LO3;
    }

    public void setField_Work_LO3(String field_Work_LO3) {
        Field_Work_LO3 = field_Work_LO3;
    }

    public String getQuiz_or_StudioCritique_LO3() {
        return Quiz_or_StudioCritique_LO3;
    }

    public void setQuiz_or_StudioCritique_LO3(String quiz_or_StudioCritique_LO3) {
        Quiz_or_StudioCritique_LO3 = quiz_or_StudioCritique_LO3;
    }

    public String getHomework_or_Assignment_LO3() {
        return Homework_or_Assignment_LO3;
    }

    public void setHomework_or_Assignment_LO3(String homework_or_Assignment_LO3) {
        Homework_or_Assignment_LO3 = homework_or_Assignment_LO3;
    }

    public String getPresentation_or_Jury_LO3() {
        return Presentation_or_Jury_LO3;
    }

    public void setPresentation_or_Jury_LO3(String presentation_or_Jury_LO3) {
        Presentation_or_Jury_LO3 = presentation_or_Jury_LO3;
    }

    public String getProject_LO3() {
        return Project_LO3;
    }

    public void setProject_LO3(String project_LO3) {
        Project_LO3 = project_LO3;
    }

    public String getSeminar_or_Workshop_LO3() {
        return Seminar_or_Workshop_LO3;
    }

    public void setSeminar_or_Workshop_LO3(String seminar_or_Workshop_LO3) {
        Seminar_or_Workshop_LO3 = seminar_or_Workshop_LO3;
    }

    public String getOral_Exam_LO3() {
        return Oral_Exam_LO3;
    }

    public void setOral_Exam_LO3(String oral_Exam_LO3) {
        Oral_Exam_LO3 = oral_Exam_LO3;
    }

    public String getMidterm_LO3() {
        return Midterm_LO3;
    }

    public void setMidterm_LO3(String midterm_LO3) {
        Midterm_LO3 = midterm_LO3;
    }

    public String getFinal_Exam_LO3() {
        return Final_Exam_LO3;
    }

    public void setFinal_Exam_LO3(String final_Exam_LO3) {
        Final_Exam_LO3 = final_Exam_LO3;
    }

    public String getParticipation_LO4() {
        return Participation_LO4;
    }

    public void setParticipation_LO4(String participation_LO4) {
        Participation_LO4 = participation_LO4;
    }

    public String getLaboratory_or_Application_LO4() {
        return Laboratory_or_Application_LO4;
    }

    public void setLaboratory_or_Application_LO4(String laboratory_or_Application_LO4) {
        Laboratory_or_Application_LO4 = laboratory_or_Application_LO4;
    }

    public String getField_Work_LO4() {
        return Field_Work_LO4;
    }

    public void setField_Work_LO4(String field_Work_LO4) {
        Field_Work_LO4 = field_Work_LO4;
    }

    public String getQuiz_or_StudioCritique_LO4() {
        return Quiz_or_StudioCritique_LO4;
    }

    public void setQuiz_or_StudioCritique_LO4(String quiz_or_StudioCritique_LO4) {
        Quiz_or_StudioCritique_LO4 = quiz_or_StudioCritique_LO4;
    }

    public String getHomework_or_Assignment_LO4() {
        return Homework_or_Assignment_LO4;
    }

    public void setHomework_or_Assignment_LO4(String homework_or_Assignment_LO4) {
        Homework_or_Assignment_LO4 = homework_or_Assignment_LO4;
    }

    public String getPresentation_or_Jury_LO4() {
        return Presentation_or_Jury_LO4;
    }

    public void setPresentation_or_Jury_LO4(String presentation_or_Jury_LO4) {
        Presentation_or_Jury_LO4 = presentation_or_Jury_LO4;
    }

    public String getProject_LO4() {
        return Project_LO4;
    }

    public void setProject_LO4(String project_LO4) {
        Project_LO4 = project_LO4;
    }

    public String getSeminar_or_Workshop_LO4() {
        return Seminar_or_Workshop_LO4;
    }

    public void setSeminar_or_Workshop_LO4(String seminar_or_Workshop_LO4) {
        Seminar_or_Workshop_LO4 = seminar_or_Workshop_LO4;
    }

    public String getOral_Exam_LO4() {
        return Oral_Exam_LO4;
    }

    public void setOral_Exam_LO4(String oral_Exam_LO4) {
        Oral_Exam_LO4 = oral_Exam_LO4;
    }

    public String getMidterm_LO4() {
        return Midterm_LO4;
    }

    public void setMidterm_LO4(String midterm_LO4) {
        Midterm_LO4 = midterm_LO4;
    }

    public String getFinal_Exam_LO4() {
        return Final_Exam_LO4;
    }

    public void setFinal_Exam_LO4(String final_Exam_LO4) {
        Final_Exam_LO4 = final_Exam_LO4;
    }*/

    public void setSuggestedReadingsAndMaterials(String suggestedReadingsAndMaterials) {
        this.suggestedReadingsAndMaterials = suggestedReadingsAndMaterials;
    }

    public String getStudyHoursoutofClassNum() {
        return StudyHoursoutofClassNum;
    }

    public void setStudyHoursoutofClassNum(String studyHoursoutofClassNum) {
        StudyHoursoutofClassNum = studyHoursoutofClassNum;
    }

    public String getStudyHoursoutofClassDur() {
        return StudyHoursoutofClassDur;
    }

    public void setStudyHoursoutofClassDur(String studyHoursoutofClassDur) {
        StudyHoursoutofClassDur = studyHoursoutofClassDur;
    }

    public String getStudyHoursoutofClassWork() {
        return StudyHoursoutofClassWork;
    }

    public void setStudyHoursoutofClassWork(String studyHoursoutofClassWork) {
        StudyHoursoutofClassWork = studyHoursoutofClassWork;
    }

    public String getFieldWorkNum() {
        return FieldWorkNum;
    }

    public void setFieldWorkNum(String fieldWorkNum) {
        FieldWorkNum = fieldWorkNum;
    }

    public String getFieldWorkDur() {
        return FieldWorkDur;
    }

    public void setFieldWorkDur(String fieldWorkDur) {
        FieldWorkDur = fieldWorkDur;
    }

    public String getFieldWorkWork() {
        return FieldWorkWork;
    }

    public void setFieldWorkWork(String fieldWorkWork) {
        FieldWorkWork = fieldWorkWork;
    }

    public String getQuizNum() {
        return QuizNum;
    }

    public void setQuizNum(String quizNum) {
        QuizNum = quizNum;
    }

    public String getQuizDur() {
        return QuizDur;
    }

    public void setQuizDur(String quizDur) {
        QuizDur = quizDur;
    }

    public String getQuizWork() {
        return QuizWork;
    }

    public void setQuizWork(String quizWork) {
        QuizWork = quizWork;
    }

    public String getHWNum() {
        return HWNum;
    }

    public void setHWNum(String HWNum) {
        this.HWNum = HWNum;
    }

    public String getHWDur() {
        return HWDur;
    }

    public void setHWDur(String HWDur) {
        this.HWDur = HWDur;
    }

    public String getHWWork() {
        return HWWork;
    }

    public void setHWWork(String HWWork) {
        this.HWWork = HWWork;
    }

    public String getPresentationNum() {
        return PresentationNum;
    }

    public void setPresentationNum(String presentationNum) {
        PresentationNum = presentationNum;
    }

    public String getPresentationDur() {
        return PresentationDur;
    }

    public void setPresentationDur(String presentationDur) {
        PresentationDur = presentationDur;
    }

    public String getPresentationWork() {
        return PresentationWork;
    }

    public void setPresentationWork(String presentationWork) {
        PresentationWork = presentationWork;
    }

    public String getProjectNum() {
        return ProjectNum;
    }

    public void setProjectNum(String projectNum) {
        ProjectNum = projectNum;
    }

    public String getProjectDur() {
        return ProjectDur;
    }

    public void setProjectDur(String projectDur) {
        ProjectDur = projectDur;
    }

    public String getProjectWork() {
        return ProjectWork;
    }

    public void setProjectWork(String projectWork) {
        ProjectWork = projectWork;
    }

    public String getPortfolioNum() {
        return PortfolioNum;
    }

    public void setPortfolioNum(String portfolioNum) {
        PortfolioNum = portfolioNum;
    }

    public String getPortfolioDur() {
        return PortfolioDur;
    }

    public void setPortfolioDur(String portfolioDur) {
        PortfolioDur = portfolioDur;
    }

    public String getPortfolioWork() {
        return PortfolioWork;
    }

    public void setPortfolioWork(String portfolioWork) {
        PortfolioWork = portfolioWork;
    }

    public String getSeminarNum() {
        return SeminarNum;
    }

    public void setSeminarNum(String seminarNum) {
        SeminarNum = seminarNum;
    }

    public String getSeminarDur() {
        return SeminarDur;
    }

    public void setSeminarDur(String seminarDur) {
        SeminarDur = seminarDur;
    }

    public String getSeminarWork() {
        return SeminarWork;
    }

    public void setSeminarWork(String seminarWork) {
        SeminarWork = seminarWork;
    }

    public String getOralExamNum() {
        return OralExamNum;
    }

    public void setOralExamNum(String oralExamNum) {
        OralExamNum = oralExamNum;
    }

    public String getOralExamDur() {
        return OralExamDur;
    }

    public void setOralExamDur(String oralExamDur) {
        OralExamDur = oralExamDur;
    }

    public String getOralExamWork() {
        return OralExamWork;
    }

    public void setOralExamWork(String oralExamWork) {
        OralExamWork = oralExamWork;
    }

    public String getMidtermNum() {
        return MidtermNum;
    }

    public void setMidtermNum(String midtermNum) {
        MidtermNum = midtermNum;
    }

    public String getMidterDur() {
        return MidterDur;
    }

    public void setMidterDur(String midterDur) {
        MidterDur = midterDur;
    }

    public String getMidterWork() {
        return MidterWork;
    }

    public void setMidterWork(String midterWork) {
        MidterWork = midterWork;
    }

    public String getFinalExamNum() {
        return FinalExamNum;
    }

    public void setFinalExamNum(String finalExamNum) {
        FinalExamNum = finalExamNum;
    }

    public String getFinalExamDur() {
        return FinalExamDur;
    }

    public void setFinalExamDur(String finalExamDur) {
        FinalExamDur = finalExamDur;
    }

    public String getFinalExamWork() {
        return FinalExamWork;
    }

    public void setFinalExamWork(String finalExamWork) {
        FinalExamWork = finalExamWork;
    }

    public LectureConfig(int lecture_id, String lecture_Name, String lecturer_Name, String lecture_Code, String semester, String theory_Hour_In_Week, String application_or_Lab_Hour_In_Week, String local_Credits, String ECTS, String prerequisites, String course_Language, String course_Type, String course_Level, String teaching_Methods_and_Techniques, String course_Coordinator, String assistant, String course_Objectives, String learning_Outcomes, String course_Description, String course_Category, String week1Subjects, String week2Subjects, String week3Subjects, String week4Subjects, String week5Subjects, String week6Subjects, String week7Subjects, String week8Subjects, String week9Subjects, String week10Subjects, String week11Subjects, String week12Subjects, String week13Subjects, String week14Subjects, String week15Subjects, String week1ReqMat, String week2ReqMat, String week3ReqMat, String week4ReqMat, String week5ReqMat, String week6ReqMat, String week7ReqMat, String week8ReqMat, String week9ReqMat, String week10ReqMat, String week11ReqMat, String week12ReqMat, String week13ReqMat, String week14ReqMat, String week15ReqMat, String courseNotesAndTextBooks, String suggestedReadingsAndMaterials) {
        this.lecture_id = lecture_id;
        this.lecture_Name = lecture_Name;
        this.lecturer_Name = lecturer_Name;
        this.lecture_Code = lecture_Code;
        this.semester = semester;
        this.theory_Hour_In_Week = theory_Hour_In_Week;
        this.application_or_Lab_Hour_In_Week = application_or_Lab_Hour_In_Week;
        this.local_Credits = local_Credits;
        this.ECTS = ECTS;
        this.prerequisites = prerequisites;
        this.course_Language = course_Language;
        this.course_Type = course_Type;
        this.course_Level = course_Level;
        this.teaching_Methods_and_Techniques = teaching_Methods_and_Techniques;
        this.course_Coordinator = course_Coordinator;
        this.assistant = assistant;
        this.course_Objectives = course_Objectives;
        this.learning_Outcomes = learning_Outcomes;
        this.course_Description = course_Description;
        this.course_Category = course_Category;
        this.week1Subjects = week1Subjects;
        this.week2Subjects = week2Subjects;
        this.week3Subjects = week3Subjects;
        this.week4Subjects = week4Subjects;
        this.week5Subjects = week5Subjects;
        this.week6Subjects = week6Subjects;
        this.week7Subjects = week7Subjects;
        this.week8Subjects = week8Subjects;
        this.week9Subjects = week9Subjects;
        this.week10Subjects = week10Subjects;
        this.week11Subjects = week11Subjects;
        this.week12Subjects = week12Subjects;
        this.week13Subjects = week13Subjects;
        this.week14Subjects = week14Subjects;
        this.week15Subjects = week15Subjects;
        this.week1ReqMat = week1ReqMat;
        this.week2ReqMat = week2ReqMat;
        this.week3ReqMat = week3ReqMat;
        this.week4ReqMat = week4ReqMat;
        this.week5ReqMat = week5ReqMat;
        this.week6ReqMat = week6ReqMat;
        this.week7ReqMat = week7ReqMat;
        this.week8ReqMat = week8ReqMat;
        this.week9ReqMat = week9ReqMat;
        this.week10ReqMat = week10ReqMat;
        this.week11ReqMat = week11ReqMat;
        this.week12ReqMat = week12ReqMat;
        this.week13ReqMat = week13ReqMat;
        this.week14ReqMat = week14ReqMat;
        this.week15ReqMat = week15ReqMat;
        this.courseNotesAndTextBooks = courseNotesAndTextBooks;
        this.suggestedReadingsAndMaterials = suggestedReadingsAndMaterials;
    }

    public LectureConfig(int lecture_id, String lecture_Name, String lecturer_Name, String lecture_Code, String semester, String theory_Hour_In_Week, String application_or_Lab_Hour_In_Week, String local_Credits, String ECTS, String prerequisites, String course_Language, String course_Type, String course_Level, String teaching_Methods_and_Techniques, String course_Coordinator, String assistant, String course_Objectives, String learning_Outcomes, String course_Description, String course_Category, String week1Subjects, String week2Subjects, String week3Subjects, String week4Subjects, String week5Subjects, String week6Subjects, String week7Subjects, String week8Subjects, String week9Subjects, String week10Subjects, String week11Subjects, String week12Subjects, String week13Subjects, String week14Subjects, String week15Subjects, String week1ReqMat, String week2ReqMat, String week3ReqMat, String week4ReqMat, String week5ReqMat, String week6ReqMat, String week7ReqMat, String week8ReqMat, String week9ReqMat, String week10ReqMat, String week11ReqMat, String week12ReqMat, String week13ReqMat, String week14ReqMat, String week15ReqMat, String courseNotesAndTextBooks, String suggestedReadingsAndMaterials, String studyHoursoutofClassNum, String studyHoursoutofClassDur, String studyHoursoutofClassWork, String fieldWorkNum, String fieldWorkDur, String fieldWorkWork, String quizNum, String quizDur, String quizWork, String HWNum, String HWDur, String HWWork, String presentationNum, String presentationDur, String presentationWork, String projectNum, String projectDur, String projectWork, String portfolioNum, String portfolioDur, String portfolioWork, String seminarNum, String seminarDur, String seminarWork, String oralExamNum, String oralExamDur, String oralExamWork, String midtermNum, String midterDur, String midterWork, String finalExamNum, String finalExamDur, String finalExamWork) {
        this.lecture_id = lecture_id;
        this.lecture_Name = lecture_Name;
        this.lecturer_Name = lecturer_Name;
        this.lecture_Code = lecture_Code;
        this.semester = semester;
        this.theory_Hour_In_Week = theory_Hour_In_Week;
        this.application_or_Lab_Hour_In_Week = application_or_Lab_Hour_In_Week;
        this.local_Credits = local_Credits;
        this.ECTS = ECTS;
        this.prerequisites = prerequisites;
        this.course_Language = course_Language;
        this.course_Type = course_Type;
        this.course_Level = course_Level;
        this.teaching_Methods_and_Techniques = teaching_Methods_and_Techniques;
        this.course_Coordinator = course_Coordinator;
        this.assistant = assistant;
        this.course_Objectives = course_Objectives;
        this.learning_Outcomes = learning_Outcomes;
        this.course_Description = course_Description;
        this.course_Category = course_Category;
        this.week1Subjects = week1Subjects;
        this.week2Subjects = week2Subjects;
        this.week3Subjects = week3Subjects;
        this.week4Subjects = week4Subjects;
        this.week5Subjects = week5Subjects;
        this.week6Subjects = week6Subjects;
        this.week7Subjects = week7Subjects;
        this.week8Subjects = week8Subjects;
        this.week9Subjects = week9Subjects;
        this.week10Subjects = week10Subjects;
        this.week11Subjects = week11Subjects;
        this.week12Subjects = week12Subjects;
        this.week13Subjects = week13Subjects;
        this.week14Subjects = week14Subjects;
        this.week15Subjects = week15Subjects;
        this.week1ReqMat = week1ReqMat;
        this.week2ReqMat = week2ReqMat;
        this.week3ReqMat = week3ReqMat;
        this.week4ReqMat = week4ReqMat;
        this.week5ReqMat = week5ReqMat;
        this.week6ReqMat = week6ReqMat;
        this.week7ReqMat = week7ReqMat;
        this.week8ReqMat = week8ReqMat;
        this.week9ReqMat = week9ReqMat;
        this.week10ReqMat = week10ReqMat;
        this.week11ReqMat = week11ReqMat;
        this.week12ReqMat = week12ReqMat;
        this.week13ReqMat = week13ReqMat;
        this.week14ReqMat = week14ReqMat;
        this.week15ReqMat = week15ReqMat;
        this.courseNotesAndTextBooks = courseNotesAndTextBooks;
        this.suggestedReadingsAndMaterials = suggestedReadingsAndMaterials;
       /* this.Participation_Number = participation_Number;
        this.Laboratory_or_Application_Number = laboratory_or_Application_Number;
        this.Field_Work_Number = field_Work_Number;
        this.Quiz_or_StudioCritique_Number = quiz_or_StudioCritique_Number;
        this.Homework_or_Assignment_Number = homework_or_Assignment_Number;
        this.Presentation_or_Jury_Number = presentation_or_Jury_Number;
        this.Project_Number = project_Number;
        this.Seminar_or_Workshop_Number = seminar_or_Workshop_Number;
        this.Oral_Exam_Number = oral_Exam_Number;
        this.Midterm_Number = midterm_Number;
        this.Final_Exam_Number = final_Exam_Number;
        this.Participation_Weigthing = participation_Weigthing;
        this.Laboratory_or_Application_Weigthing = laboratory_or_Application_Weigthing;
        this.Field_Work_Weigthing = field_Work_Weigthing;
        this.Quiz_or_StudioCritique_Weigthing = quiz_or_StudioCritique_Weigthing;
        this.Homework_or_Assignment_Weigthing = homework_or_Assignment_Weigthing;
        this.Presentation_or_Jury_Weigthing = presentation_or_Jury_Weigthing;
        this.Project_Weigthing = project_Weigthing;
        this.Seminar_or_Workshop_Weigthing = seminar_or_Workshop_Weigthing;
        this.Oral_Exam_Weigthing = oral_Exam_Weigthing;
        this.Midterm_Weigthing = midterm_Weigthing;
        this.Final_Exam_Weigthing = final_Exam_Weigthing;
        this.Participation_LO1 = participation_LO1;
        this.Laboratory_or_Application_LO1 = laboratory_or_Application_LO1;
        this.Field_Work_LO1 = field_Work_LO1;
        this.Quiz_or_StudioCritique_LO1 = quiz_or_StudioCritique_LO1;
        this.Homework_or_Assignment_LO1 = homework_or_Assignment_LO1;
        this.Presentation_or_Jury_LO1 = presentation_or_Jury_LO1;
        this.Project_LO1 = project_LO1;
        this.Seminar_or_Workshop_LO1 = seminar_or_Workshop_LO1;
        this.Oral_Exam_LO1 = oral_Exam_LO1;
        this.Midterm_LO1 = midterm_LO1;
        this.Final_Exam_LO1 = final_Exam_LO1;
        this.Participation_LO2 = participation_LO2;
       this.Laboratory_or_Application_LO2 = laboratory_or_Application_LO2;
        this.Field_Work_LO2 = field_Work_LO2;
        this.Quiz_or_StudioCritique_LO2 = quiz_or_StudioCritique_LO2;
        this.Homework_or_Assignment_LO2 = homework_or_Assignment_LO2;
        this.Presentation_or_Jury_LO2 = presentation_or_Jury_LO2;
        this. Project_LO2 = project_LO2;
        this.Seminar_or_Workshop_LO2 = seminar_or_Workshop_LO2;
        this. Oral_Exam_LO2 = oral_Exam_LO2;
        this. Midterm_LO2 = midterm_LO2;
        this. Final_Exam_LO2 = final_Exam_LO2;
        this. Participation_LO3 = participation_LO3;
        this.Laboratory_or_Application_LO3 = laboratory_or_Application_LO3;
      this.  Field_Work_LO3 = field_Work_LO3;
       this. Quiz_or_StudioCritique_LO3 = quiz_or_StudioCritique_LO3;
        this. Homework_or_Assignment_LO3 = homework_or_Assignment_LO3;
        this.Presentation_or_Jury_LO3 = presentation_or_Jury_LO3;
        this.Project_LO3 = project_LO3;
        this.Seminar_or_Workshop_LO3 = seminar_or_Workshop_LO3;
        this.Oral_Exam_LO3 = oral_Exam_LO3;
        this.Midterm_LO3 = midterm_LO3;
        this.Final_Exam_LO3 = final_Exam_LO3;
        this.Participation_LO4 = participation_LO4;
        this.Laboratory_or_Application_LO4 = laboratory_or_Application_LO4;
        this.Field_Work_LO4 = field_Work_LO4;
        this.Quiz_or_StudioCritique_LO4 = quiz_or_StudioCritique_LO4;
        this.Homework_or_Assignment_LO4 = homework_or_Assignment_LO4;
        this.Presentation_or_Jury_LO4 = presentation_or_Jury_LO4;
        this.Project_LO4 = project_LO4;
        this.Seminar_or_Workshop_LO4 = seminar_or_Workshop_LO4;
        this.Oral_Exam_LO4 = oral_Exam_LO4;
        this.Midterm_LO4 = midterm_LO4;
        this.Final_Exam_LO4 = final_Exam_LO4;*/
        this.StudyHoursoutofClassNum = studyHoursoutofClassNum;
        this.StudyHoursoutofClassDur = studyHoursoutofClassDur;
        this.StudyHoursoutofClassWork = studyHoursoutofClassWork;
        this.FieldWorkNum = fieldWorkNum;
        this.FieldWorkDur = fieldWorkDur;
        this.FieldWorkWork = fieldWorkWork;
        this.QuizNum = quizNum;
        this.QuizDur = quizDur;
        this.QuizWork = quizWork;
        this.HWNum = HWNum;
        this.HWDur = HWDur;
        this.HWWork = HWWork;
        this.PresentationNum = presentationNum;
        this.PresentationDur = presentationDur;
        this.PresentationWork = presentationWork;
        this.ProjectNum = projectNum;
        this.ProjectDur = projectDur;
        this.ProjectWork = projectWork;
        this.PortfolioNum = portfolioNum;
        this.PortfolioDur = portfolioDur;
        this.PortfolioWork = portfolioWork;
        this.SeminarNum = seminarNum;
        this.SeminarDur = seminarDur;
        this.SeminarWork = seminarWork;
        this.OralExamNum = oralExamNum;
        this.OralExamDur = oralExamDur;
        this.OralExamWork = oralExamWork;
        this.MidtermNum = midtermNum;
        this.MidterDur = midterDur;
        this.MidterWork = midterWork;
        this.FinalExamNum = finalExamNum;
        this.FinalExamDur = finalExamDur;
        this.FinalExamWork = finalExamWork;
    }

    public LectureConfig(int lecture_id, String lecture_Name, String lecturer_Name) {
        this.lecture_id = lecture_id;
        this.lecture_Name = lecture_Name;
        this.lecturer_Name = lecturer_Name;
    }

    public int getLecture_id() {
        return lecture_id;
    }

    public void setLecture_id(int lecture_id) {
        this.lecture_id = lecture_id;
    }

    public String getLecture_Name() {
        return lecture_Name;
    }

    public void setLecture_Name(String lecture_Name) {
        this.lecture_Name = lecture_Name;
    }

    public String getLecturer_Name() {
        return lecturer_Name;
    }

    public void setLecturer_Name(String lecturer_Name) {
        this.lecturer_Name = lecturer_Name;
    }


}


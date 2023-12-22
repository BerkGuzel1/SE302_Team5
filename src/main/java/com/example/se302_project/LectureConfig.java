package com.example.se302_project;



public class LectureConfig {
    private String lecture_Code;
    public String semester;
    private Integer theory_Hour_In_Week;
    private Integer application_or_Lab_Hour_In_Week;
    private Integer local_Credits;
    private Integer ECTS;
    private String prerequisites;
    private String course_Language;
    private String course_Type;
    private String course_Level;
    private String teaching_Methods_and_Techniques;
    private String course_Coordinator;
    private String course_Lecturer;
    private String assistant;
    private String course_Objectives;
    private String learning_Outcomes;
    private String course_Description;
    private String course_Category;
    private Integer week;
    private String subjects;
    private String required_Material;
    private String course_Notes_or_Textbooks;
    private String suggested_Readings_or_Materials;
    private String assesment_Semester_Activities;
    private Integer assesment_Number;
    private Integer weigthing;
    private Integer LO1;
    private Integer LO2;
    private Integer LO3;
    private Integer LO4;
    private Integer LO5;
    private Integer LO6;
    private Integer LO7;
    private String ECTS_Semester_Activities;
    private String ECTS_Number;
    private Integer duration_Hours;
    private Integer workload;
    private Integer outcome_Number;
    private String program_Competencies_or_Outcomes;
    private String contribution_Level;
    private Integer number_of_LOs;

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

    public Integer getTheory_Hour_In_Week() {
        return theory_Hour_In_Week;
    }

    public void setTheory_Hour_In_Week(Integer theory_Hour_In_Week) {
        this.theory_Hour_In_Week = theory_Hour_In_Week;
    }

    public Integer getApplication_or_Lab_Hour_In_Week() {
        return application_or_Lab_Hour_In_Week;
    }

    public void setApplication_or_Lab_Hour_In_Week(Integer application_or_Lab_Hour_In_Week) {
        this.application_or_Lab_Hour_In_Week = application_or_Lab_Hour_In_Week;
    }

    public Integer getLocal_Credits() {
        return local_Credits;
    }

    public void setLocal_Credits(Integer local_Credits) {
        this.local_Credits = local_Credits;
    }

    public Integer getECTS() {
        return ECTS;
    }

    public void setECTS(Integer ECTS) {
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

    public String getCourse_Lecturer() {
        return course_Lecturer;
    }

    public void setCourse_Lecturer(String course_Lecturer) {
        this.course_Lecturer = course_Lecturer;
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

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public String getRequired_Material() {
        return required_Material;
    }

    public void setRequired_Material(String required_Material) {
        this.required_Material = required_Material;
    }

    public String getCourse_Notes_or_Textbooks() {
        return course_Notes_or_Textbooks;
    }

    public void setCourse_Notes_or_Textbooks(String course_Notes_or_Textbooks) {
        this.course_Notes_or_Textbooks = course_Notes_or_Textbooks;
    }

    public String getSuggested_Readings_or_Materials() {
        return suggested_Readings_or_Materials;
    }

    public void setSuggested_Readings_or_Materials(String suggested_Readings_or_Materials) {
        this.suggested_Readings_or_Materials = suggested_Readings_or_Materials;
    }

    public String getAssesment_Semester_Activities() {
        return assesment_Semester_Activities;
    }

    public void setAssesment_Semester_Activities(String assesment_Semester_Activities) {
        this.assesment_Semester_Activities = assesment_Semester_Activities;
    }

    public Integer getAssesment_Number() {
        return assesment_Number;
    }

    public void setAssesment_Number(Integer assesment_Number) {
        this.assesment_Number = assesment_Number;
    }

    public Integer getWeigthing() {
        return weigthing;
    }

    public void setWeigthing(Integer weigthing) {
        this.weigthing=weigthing;
    }

    public Integer getLO1() {
        return LO1;
    }

    public void setLO1(Integer LO1) {
        this.LO1 = LO1;
    }

    public Integer getLO2() {
        return LO2;
    }

    public void setLO2(Integer LO2) {
        this.LO2 = LO2;
    }

    public Integer getLO3() {
        return LO3;
    }

    public void setLO3(Integer LO3) {
        this.LO3 = LO3;
    }

    public Integer getLO4() {
        return LO4;
    }

    public void setLO4(Integer LO4) {
        this.LO4 = LO4;
    }

    public Integer getLO5() {
        return LO5;
    }

    public void setLO5(Integer LO5) {
        this.LO5 = LO5;
    }

    public Integer getLO6() {
        return LO6;
    }

    public void setLO6(Integer LO6) {
        this.LO6 = LO6;
    }

    public Integer getLO7() {
        return LO7;
    }

    public void setLO7(Integer LO7) {
        this.LO7 = LO7;
    }

    public String getECTS_Semester_Activities() {
        return ECTS_Semester_Activities;
    }

    public void setECTS_Semester_Activities(String ECTS_Semester_Activities) {
        this.ECTS_Semester_Activities = ECTS_Semester_Activities;
    }

    public String getECTS_Number() {
        return ECTS_Number;
    }

    public void setECTS_Number(String ECTS_Number) {
        this.ECTS_Number = ECTS_Number;
    }

    public Integer getDuration_Hours() {
        return duration_Hours;
    }

    public void setDuration_Hours(Integer duration_Hours) {
        this.duration_Hours = duration_Hours;
    }

    public Integer getWorkload() {
        return workload;
    }

    public void setWorkload(Integer workload) {
        this.workload = workload;
    }

    public Integer getOutcome_Number() {
        return outcome_Number;
    }

    public void setOutcome_Number(Integer outcome_Number) {
        if (outcome_Number != null && outcome_Number <= 16) {
            this.outcome_Number = outcome_Number;
        } else {
            // Handle the case where the input is greater than 16
            throw new IllegalArgumentException("0");
            // Alternatively, you can set a default value or handle it in a different way based on your requirements.
        }
    }


    public String getProgram_Competencies_or_Outcomes() {
        return program_Competencies_or_Outcomes;
    }

    public void setProgram_Competencies_or_Outcomes(String program_Competencies_or_Outcomes) {
        this.program_Competencies_or_Outcomes = program_Competencies_or_Outcomes;
    }

    public String getContribution_Level() {
        return contribution_Level;
    }

    public void setContribution_Level(String contribution_Level) {
        switch (contribution_Level) {
            case "1":
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                break;
            case "5":
                break;
        }
    }

    public Integer getNumber_of_LOs() {
        return number_of_LOs;
    }

    public void setNumber_of_LOs(Integer number_of_LOs) {
        this.number_of_LOs = number_of_LOs;
    }

    public LectureConfig(String lecture_Code, String semester, Integer theory_Hour_In_Week,
                         Integer application_or_Lab_Hour_In_Week, Integer local_Credits, Integer ECTS,
                         String prerequisites, String course_Language, String course_Type,
                         String course_Level, String teaching_Methods_and_Techniques,
                         String course_Coordinator, String course_Lecturer, String assistant,
                         String course_Objectives, String learning_Outcomes, String course_Description,
                         String course_Category, Integer week, String subjects, String required_Material,
                         String course_Notes_or_Textbooks, String suggested_Readings_or_Materials,
                         String assesment_Semester_Activities, Integer assesment_Number,
                         Integer weigthing, Integer LO1, Integer LO2, Integer LO3, Integer LO4,
                         Integer LO5, Integer LO6, Integer LO7, String ECTS_Semester_Activities,
                         String ECTS_Number, Integer duration_Hours, Integer workload,
                         Integer outcome_Number, String program_Competencies_or_Outcomes,
                         String contribution_Level, Integer number_of_LOs, int lecture_id,
                         String lecture_Name, String lecturer_Name) {
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
        this.course_Lecturer = course_Lecturer;
        this.assistant = assistant;
        this.course_Objectives = course_Objectives;
        this.learning_Outcomes = learning_Outcomes;
        this.course_Description = course_Description;
        this.course_Category = course_Category;
        this.week = week;
        this.subjects = subjects;
        this.required_Material = required_Material;
        this.course_Notes_or_Textbooks = course_Notes_or_Textbooks;
        this.suggested_Readings_or_Materials = suggested_Readings_or_Materials;
        this.assesment_Semester_Activities = assesment_Semester_Activities;
        this.assesment_Number = assesment_Number;
        this.weigthing = weigthing;
        this.LO1 = LO1;
        this.LO2 = LO2;
        this.LO3 = LO3;
        this.LO4 = LO4;
        this.LO5 = LO5;
        this.LO6 = LO6;
        this.LO7 = LO7;
        this.ECTS_Semester_Activities = ECTS_Semester_Activities;
        this.ECTS_Number = ECTS_Number;
        this.duration_Hours = duration_Hours;
        this.workload = workload;
        this.outcome_Number = outcome_Number;
        this.program_Competencies_or_Outcomes = program_Competencies_or_Outcomes;
        this.contribution_Level = contribution_Level;
        this.number_of_LOs = number_of_LOs;
        this.lecture_id = lecture_id;
        this.lecture_Name = lecture_Name;
        this.lecturer_Name = lecturer_Name;
    }

    private int lecture_id;
    private String lecture_Name;
    private String lecturer_Name;


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


public class Patient {
    private String id;
    private String name;
    private int age;
    private String diagnosis;

    public Patient(String id, String name, int age, String diagnosis) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.diagnosis = diagnosis;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", diagnosis='" + diagnosis + '\'' +
                '}';
    }
}

public class Member {
    int id;
    String name;
    int age;
    String skill;

    public Member(int id, String name, int age, String skill) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.skill = skill;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSkill() {
        return skill;
    }

    public void printSkill() {
        System.out.println("Skill: " + getSkill());
    }
}

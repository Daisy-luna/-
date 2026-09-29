class Student {
    String name;
    double score;
    Student(String name, double score) {
        this.name = name;
        this.score = score;
    }
    void introduce() {
        System.out.println("我是" + name + "，成绩" + score);
    }
}
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class ScoreSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> list = new ArrayList<>();

        // while循环 菜单一直循环
        while(true){
            System.out.println("====学生成绩管理系统====");
            System.out.println("1.录入成绩");
            System.out.println("2.显示全部");
            System.out.println("3.按分数排序");
            System.out.println("4.退出");
            System.out.print("请输入你的选择：");

            try{
                int choice = sc.nextInt();
                sc.nextLine();

                if(choice == 1){
                    //录入学生
                    System.out.print("请输入姓名：");
                    String name = sc.nextLine();
                    System.out.print("请输入分数：");
                    double score = sc.nextDouble();
                    sc.nextLine();
                    Student s = new Student(name, score);
                    list.add(s);
                    System.out.println("录入成功！");
                }else if(choice == 2){
                    //显示全部
                    System.out.println("====全部学生====");
                    for(int i=0; i < list.size(); i++){
                        Student stu = list.get(i);
                        stu.introduce();
                    }
                }else if(choice == 3){
                    //Collections.sort 从高到低排序
                    Collections.sort(list, new Comparator<Student>() {
                        @Override
                        public int compare(Student o1, Student o2) {
                            return Double.compare(o2.score, o1.score);
                        }
                    });
                    System.out.println("排序完成！");
                }else if(choice == 4){
                    System.out.println("程序结束");
                    sc.close();
                    break;
                }else{
                    System.out.println("数字不在范围内，请重新输入！");
                }
            }catch(Exception e){
                System.out.println("请输入数字");
                sc.nextLine();
            }
        }
    }
}
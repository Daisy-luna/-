import java.sql.*;
import java.util.Scanner;

public class ScoreSystem {
    //获取数据库连接
    public static Connection getConn() throws Exception{
        String url = "jdbc:mysql://localhost:3306/lab?useSSL=false&characterEncoding=utf8&serverTimezone=UTC";
        return DriverManager.getConnection(url,"root","qinghua985");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

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
                    //录入成绩，写入MySQL
                    System.out.print("请输入姓名：");
                    String name = sc.nextLine();
                    System.out.print("请输入分数：");
                    double score = sc.nextDouble();
                    sc.nextLine();

                    Connection conn = getConn();
                    String sql = "INSERT INTO student(name,score) VALUES (?,?)";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1,name);
                    ps.setDouble(2,score);
                    ps.executeUpdate();
                    ps.close();
                    conn.close();
                    System.out.println("录入成功！");
                }else if(choice == 2){
                    //查询全部学生
                    System.out.println("====全部学生====");
                    Connection conn = getConn();
                    String sql = "SELECT name,score FROM student";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();

                    while(rs.next()){
                        String name = rs.getString("name");
                        double score = rs.getDouble("score");
                        Student stu = new Student(name,score);
                        stu.introduce();
                    }
                    rs.close();
                    ps.close();
                    conn.close();
                }else if(choice == 3){
                    System.out.println("====按分数降序排列====");
                    Connection conn = getConn();
                    String sql = "SELECT name,score FROM student ORDER BY score DESC";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();

                    while(rs.next()){
                        String name = rs.getString("name");
                        double score = rs.getDouble("score");
                        Student stu = new Student(name,score);
                        stu.introduce();
                    }
                    rs.close();
                    ps.close();
                    conn.close();
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
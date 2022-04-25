import java.io.*;

public class file_class {

    public static void main(String[] args) throws IOException {
        File students = new File("d:\\students.txt");

        RandomAccessFile std_file = null;

        try{
                std_file = new RandomAccessFile(students, "rw");
                boolean found = false;


            byte[] id = new byte[4];
            byte[] rec =  new byte[55];

            String idno = null;
            String std_rec = null;

            while (std_file.getFilePointer() <= std_file.length()-2){
                std_file.read(id,0,4);
                idno = new String(id);

                if (idno.equals("6235")){
                    std_file.seek(std_file.getFilePointer()+32);
                    std_file.write("3.50".getBytes("UTF-8"));
                    std_file.seek(std_file.getFilePointer()-40);
                    std_file.read(rec,0,53);
                    std_rec = new String(rec);
                    found = true;
                }
                if (found)
                    break;

                std_file.seek(std_file.getFilePointer()+51);


            }
            System.out.println(std_rec);

        } catch(EOFException e){
            e.printStackTrace();
        }finally {
            std_file.close();
        }
    }


}

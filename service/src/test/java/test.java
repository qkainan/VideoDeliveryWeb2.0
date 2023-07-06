import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class test {

    //字节流练习
    @Test
    public static void main(String[] args) {
        char i = 97;
        System.out.println(i);
    }

    @Test
    public void IODemo01() throws IOException {
        //创建流
        FileInputStream fis = new FileInputStream("D:\\其他文件\\大学语文\\短时.mp4");
        FileOutputStream fos = new FileOutputStream("D:\\其他文件\\转正资料\\IODemo01.mp4");

        //拷贝
        int code;
        while ((code = fis.read()) != -1) {
            System.out.println(code);
            fos.write(code);
        }

        //释放资源，先打开的流后关闭
        fos.close();
        fis.close();
    }

    @Test
    public void IODemo02() throws IOException {
        //创建流
        FileInputStream fis = new FileInputStream("D:\\其他文件\\大学语文\\短时.mp4");
        FileOutputStream fos = new FileOutputStream("D:\\其他文件\\转正资料\\IODemo01.mp4");

        //拷贝
        int code;
        byte[] bytes = new byte[1024 * 1024 * 5];
        while ((code = fis.read(bytes)) != -1) {
            System.out.println(code);
            fos.write(bytes, 0, code);
        }

        //释放资源，先打开的流后关闭
        fos.close();
        fis.close();
    }

    FileInputStream fis = null;
    FileOutputStream fos = null;

    @Test
    public void IODemo03() {
        try {
            //创建流
            fis = new FileInputStream("D:\\其他文件\\大学语文\\短时.mp4");
            fos = new FileOutputStream("D:\\其他文件\\转正资料\\IODemo01.mp4");
            //拷贝
            int code;
            byte[] bytes = new byte[1024 * 1024 * 5];
            while ((code = fis.read(bytes)) != -1) {
                System.out.println(code);
                fos.write(bytes, 0, code);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            //释放资源，先打开的流后关闭
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Test
    public void encodingDemo() throws UnsupportedEncodingException {
        //创建字符串
        String str01 = "学习Java";
        //编码
        byte[] bytes = str01.getBytes();
        System.out.println(bytes);
        //解码
        String str02 = new String(bytes);
        String str03 = new String(bytes, "UTF-8");
        String str04 = new String(bytes, "GBK");
        System.out.println(str02);
        System.out.println(str03);
        System.out.println(str04);
    }

    //字符流练习
    @Test
    public void IODemo04() throws IOException {
        //创建字符流
        FileReader fr = new FileReader("D:\\Develop\\jerbrains\\idea\\idea projects\\VideoDeliveryWeb2.0\\service\\src\\main\\resources\\static\\test.txt");

        //读取文件（read方法会将读取的字节转成十进制，所以要将十进制转成字符）
        int code01;
        while ((code01 = fr.read()) != -1) {
            System.out.print((char) code01);
        }

        //关流
        fr.close();
    }

    @Test
    public void IODemo05() throws IOException {
        //创建字符流
        FileReader fr = new FileReader("D:\\Develop\\jerbrains\\idea\\idea projects\\VideoDeliveryWeb2.0\\service\\src\\main\\resources\\static\\test.txt");

        //读取文件（read方法会将读取的字节转成十进制，所以要将十进制转成字符）
        char[] chars = new char[2];
        while (fr.read(chars) != -1) {
            System.out.println(new String(chars, 0, chars.length));
        }

        //关流
        fr.close();
    }

    //拷贝文件夹练习
    @Test
    public void IODemo06() throws IOException {
        //1.创建文件
        File test = new File("D:\\其他文件\\IOPractice\\test");
        File desk = new File("D:\\其他文件\\IOPractice\\destination");
        //2.调用方法拷贝
        copydir(test, desk);
    }

    private void copydir(File test, File desk) throws IOException {
        desk.mkdirs();
        //1.进入数据源
        File[] files = test.listFiles();
        //2.遍历文件（文件夹）
        for (File f : files) {
            //判断是否为文件，是则拷贝，不是是则递归
            //3.拷贝文件
            if (f.isFile()) {
                //创建流
                FileInputStream fis = new FileInputStream(f);
                FileOutputStream fos = new FileOutputStream(new File(desk, f.getName()));
                //拷贝
                byte[] bytes = new byte[1024 * 1024 * 1];
                while ((fis.read(bytes)) != -1) {
                    fos.write(bytes, 0, bytes.length);
                }
                //关流
                fos.close();
                fis.close();
            } else {
                //4.文件夹递归
                copydir(f, new File(desk, f.getName()));
            }
        }
    }

    //压缩流练习
    @Test
    public void ZipStreamDemo01() throws IOException {
        /*
         * 需求：把D:\其他文件\IOPractice压缩成一个压缩包
         *
         * */
        //1.创建file表示需要压缩的文件
        File src = new File("D:\\其他文件\\IOPractice");
        //2.创建destination表示压缩后的文件放在哪里
        File parentFile = src.getParentFile();
        //3.创建File对象表示压缩包的路径
        File destination = new File(parentFile, src.getName() + ".zip");
        //4.创建压缩流关联压缩包
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(destination));
        //5.获取src每一个文件，将其变成ZipEntry对象，再放到压缩包当中
        compress(src, zos, src.getName());
        //6.释放资源
        zos.close();
    }

    /*
     * 作用：获取src每一个文件，将其变成ZipEntry对象，再放到压缩包当中
     * 参数一：数据源
     * 参数二：压缩流
     * 参数三：压缩包内部的路径
     * */
    public void compress(File src, ZipOutputStream zos, String name) throws IOException {
        //1.进入数据源
        File[] files = src.listFiles();
        //2.遍历数组
        for (File file : files) {
            if (file.isFile()) {
                //3.判断-文件，直接创建ZipEntry对象，放入压缩包当中
                ZipEntry zipEntry = new ZipEntry(name + "\\" + file.getName());
                zos.putNextEntry(zipEntry);
                //读取文件中的数据，将其放入压缩包当中
                FileInputStream fis = new FileInputStream(file);
                byte[] bytes = new byte[1024];
                while ((fis.read(bytes)) != -1) {
                    zos.write(bytes);
                }
                fis.close();
                zos.closeEntry();
            } else {
                //4.判断-文件夹，递归
                compress(file, zos, name + "\\" + file.getName());
            }
        }
    }

    @Test
    public void getAvailableThreads(){
        int availableThreads = Runtime.getRuntime().availableProcessors();
        System.out.println(availableThreads);
    }


}



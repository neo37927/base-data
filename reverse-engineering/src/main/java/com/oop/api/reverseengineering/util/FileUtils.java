package com.oop.api.reverseengineering.util;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    static int countFiles = 0;
    static int countFolders = 0;
    private static String SLASH = "/";
    private static String DOT = ".";

    public FileUtils() {
    }

    public static String readerResourcesFile(String filePath) {
        try {
            StringBuffer sb = new StringBuffer();
            FileReader freader = new FileReader(new File(PathUtil.getClassResources() + "/" + filePath));
            BufferedReader buffer = new BufferedReader(freader);

            for(String str_line = buffer.readLine(); str_line != null; str_line = buffer.readLine()) {
                sb.append(str_line);
                sb.append("\n");
            }

            buffer.close();
            freader.close();
            return sb.toString();
        } catch (Exception var5) {
            var5.printStackTrace();
            return null;
        }
    }

    public static boolean writeFile(String filePath, String content) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                String[] array = filePath.split(SLASH);
                StringBuffer buff = new StringBuffer();

                for(int i = 0; i < array.length; ++i) {
                    buff.append(array[i]).append(SLASH);
                    if (i > 0 && array[i].indexOf(DOT) == -1) {
                        String path = buff.toString().substring(0, buff.toString().length());
                        File mkdirFile = new File(path);
                        if (!mkdirFile.exists() && !mkdirFile.isDirectory()) {
                            mkdirFile.mkdir();
                            System.out.println("创建文件夹：" + path + " 成功");
                        }
                    }
                }

                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
            return true;
        } catch (IOException var8) {
            var8.printStackTrace();
            return false;
        }
    }

    public static File[] searchFile(File folder, final String fileName) {
        List<String> pathList = new ArrayList();
        File[] subFolders = folder.listFiles(new FileFilter() {
            public boolean accept(File pathname) {
                return pathname.isDirectory() || pathname.isFile() && pathname.getName().toLowerCase().equals(fileName.toLowerCase());
            }
        });
        List<File> result = new ArrayList();

        for(int i = 0; i < subFolders.length; ++i) {
            if (subFolders[i].isFile()) {
                pathList.add(subFolders[i].getAbsolutePath());
                result.add(subFolders[i]);
            } else {
                File[] foldResult = searchFile(subFolders[i], fileName);

                for(int j = 0; j < foldResult.length; ++j) {
                    pathList.add(foldResult[j].getAbsolutePath());
                }
            }
        }

        File[] files = new File[result.size()];
        result.toArray(files);
        return files;
    }

    public static boolean createDir(String destDirName) {
        File dir = new File(destDirName);
        if (dir.exists()) {
            return false;
        } else {
            if (!destDirName.endsWith(File.separator)) {
                destDirName = destDirName + File.separator;
            }

            return dir.mkdirs();
        }
    }

    public static void delFile(String filePathAndName) {
        try {
            String filePath = filePathAndName.toString();
            File myDelFile = new File(filePath);
            myDelFile.delete();
        } catch (Exception var3) {
            System.out.println("删除文件操作出错");
            var3.printStackTrace();
        }

    }

    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath);
            String filePath = folderPath.toString();
            File myFilePath = new File(filePath);
            myFilePath.delete();
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        } else if (!file.isDirectory()) {
            return flag;
        } else {
            String[] tempList = file.list();
            File temp = null;

            for(int i = 0; i < tempList.length; ++i) {
                if (path.endsWith(File.separator)) {
                    temp = new File(path + tempList[i]);
                } else {
                    temp = new File(path + File.separator + tempList[i]);
                }

                if (temp.isFile()) {
                    temp.delete();
                }

                if (temp.isDirectory()) {
                    delAllFile(path + "/" + tempList[i]);
                    delFolder(path + "/" + tempList[i]);
                    flag = true;
                }
            }

            return flag;
        }
    }

    public static byte[] getContent(String filePath) throws IOException {
        File file = new File(filePath);
        long fileSize = file.length();
        if (fileSize > 2147483647L) {
            return null;
        } else {
            FileInputStream fi = new FileInputStream(file);
            byte[] buffer = new byte[(int)fileSize];
            int offset = 0;

            int numRead;
            for(boolean var7 = false; offset < buffer.length && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0; offset += numRead) {
                ;
            }

            fi.close();
            if (offset != buffer.length) {
                throw new IOException("Could not completely read file ，" + file.getName());
            } else {
                return buffer;
            }
        }
    }

    public static byte[] toByteArray(String filePath) throws IOException {
        File f = new File(filePath);
        if (!f.exists()) {
            throw new FileNotFoundException(filePath);
        } else {
            ByteArrayOutputStream bos = new ByteArrayOutputStream((int)f.length());
            BufferedInputStream in = null;

            try {
                in = new BufferedInputStream(new FileInputStream(f));
                int buf_size = 1024;
                byte[] buffer = new byte[buf_size];
                boolean var6 = false;

                int len;
                while(-1 != (len = in.read(buffer, 0, buf_size))) {
                    bos.write(buffer, 0, len);
                }

                byte[] var7 = bos.toByteArray();
                return var7;
            } catch (IOException var16) {
                var16.printStackTrace();
                throw var16;
            } finally {
                try {
                    in.close();
                } catch (IOException var15) {
                    var15.printStackTrace();
                }

                bos.close();
            }
        }
    }

    public static byte[] toByteArray2(String filePath) throws IOException {
        File f = new File(filePath);
        if (!f.exists()) {
            throw new FileNotFoundException(filePath);
        } else {
            FileChannel channel = null;
            FileInputStream fs = null;

            try {
                fs = new FileInputStream(f);
                channel = fs.getChannel();
                ByteBuffer byteBuffer = ByteBuffer.allocate((int)channel.size());

                while(channel.read(byteBuffer) > 0) {
                    ;
                }

                byte[] var5 = byteBuffer.array();
                return var5;
            } catch (IOException var17) {
                var17.printStackTrace();
                throw var17;
            } finally {
                try {
                    channel.close();
                } catch (IOException var16) {
                    var16.printStackTrace();
                }

                try {
                    fs.close();
                } catch (IOException var15) {
                    var15.printStackTrace();
                }

            }
        }
    }

    public static byte[] toByteArray3(String filePath) throws IOException {
        FileChannel fc = null;
        RandomAccessFile rf = null;

        byte[] var5;
        try {
            rf = new RandomAccessFile(filePath, "r");
            fc = rf.getChannel();
            MappedByteBuffer byteBuffer = fc.map(FileChannel.MapMode.READ_ONLY, 0L, fc.size()).load();
            byte[] result = new byte[(int)fc.size()];
            if (byteBuffer.remaining() > 0) {
                byteBuffer.get(result, 0, byteBuffer.remaining());
            }

            var5 = result;
        } catch (IOException var14) {
            var14.printStackTrace();
            throw var14;
        } finally {
            try {
                rf.close();
                fc.close();
            } catch (IOException var13) {
                var13.printStackTrace();
            }

        }

        return var5;
    }

    public static int decideStoreTable(int id) {
        int tableOrderNum = id % 20 + 1;
        return tableOrderNum;
    }
}

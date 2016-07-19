package net.chat.util;

import java.io.File;


public class DeleteLocalFile {
	private static File file=null;

    /**
     *  ����·��ɾ��ָ����Ŀ¼���ļ������۴������
     *@param sPath  Ҫɾ����Ŀ¼���ļ�
     *@return Boolean
     *             ɾ���ɹ����� true
     *                 ����
     *             ���� false��
     */
    public static boolean DeleteFolder(String sPath) {
        file = new File(sPath);
        // �ж�Ŀ¼���ļ��Ƿ����
        if (!file.exists()) {  // �����ڷ��� false
            return false;
        } else {
            // �ж��Ƿ�Ϊ�ļ�
            if (file.isFile()) {  // Ϊ�ļ�ʱ����ɾ���ļ�����
                return deleteFile(sPath);
            } else {  // ΪĿ¼ʱ����ɾ��Ŀ¼����
                return deleteDirectory(sPath);
            }
        }
    }

    /**
     * ɾ�������ļ�
     * @param   sPath    ��ɾ���ļ����ļ���
     * @return Boolean
     *             �����ļ�ɾ���ɹ�����true
     *                 ����
     *             ����false
     */
    public static boolean deleteFile(String sPath) {
        file = new File(sPath);
        // ·��Ϊ�ļ��Ҳ�Ϊ�������ɾ��
        if (file.isFile() && file.exists()) {
        	try {
        		file.delete();
        		return true;
			} catch (Exception e) {
				return false;
				// TODO: handle exception
			}
        }
        else {
			return false;
		}
    }
    
    /**
     * ɾ��Ŀ¼���ļ��У��Լ�Ŀ¼�µ��ļ�
     * @param   sPath ��ɾ��Ŀ¼���ļ�·��
     * @return Boolean
     *            Ŀ¼ɾ���ɹ�����true
     *                ����
     *            ����false
     */
    public static boolean deleteDirectory(String sPath) {
        //���sPath�����ļ��ָ�����β���Զ������ļ��ָ���
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //���dir��Ӧ���ļ������ڣ����߲���һ��Ŀ¼�����˳�
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        //ɾ���ļ����µ������ļ�(������Ŀ¼)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //ɾ�����ļ�
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) break;
            } //ɾ����Ŀ¼
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) return false;
        //ɾ����ǰĿ¼
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }
}
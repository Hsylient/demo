package com.novax.ex.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.zip.*;

/**
 * Description:
 *
 * @Author: my.miao
 * @Date: 2019/8/28 17:36
 */
public class ZipUtil {
    /**
     * 功能：使用gzip进行压缩，然后再用Base64进行编码
     * @return 返回压缩后字符串
     * @author 20160827
     */
    @SuppressWarnings("restriction")
    public static String gzip(String primStr) {
        if (primStr == null || primStr.length() == 0) {
            return primStr;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = null;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(primStr.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (gzip != null) {
                try {
                    gzip.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  Base64.getEncoder().encodeToString(out.toByteArray());
    }

    /**
     * Description:使用gzip进行解压缩
     * 先对压缩数据进行BASE64解码。再进行Gzip解压
     * @param compressedStr 压缩字符串
     * @return 返回解压字符串
     */
    @SuppressWarnings("restriction")
    public static String gunzip(String compressedStr) {
        if (compressedStr == null) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = null;
        GZIPInputStream ginzip = null;
        byte[] compressed = null;
        String decompressed = null;
        try {
            compressed = Base64.getDecoder().decode(compressedStr);
            in = new ByteArrayInputStream(compressed);
            ginzip = new GZIPInputStream(in);
            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = ginzip.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            decompressed = out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ginzip != null) {
                try {
                    ginzip.close();
                } catch (IOException e) {
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }
        return decompressed;
    }

    /**
     * 使用zip进行压缩
     * @param str
     *            压缩前的文本
     * @return 返回压缩后的文本
     */
    @SuppressWarnings("restriction")
    public static final String zip(String str) {
        if (str == null) {
            return null;
        }
        byte[] compressed;
        ByteArrayOutputStream out = null;
        ZipOutputStream zout = null;
        String compressedStr = null;
        try {
            out = new ByteArrayOutputStream();
            zout = new ZipOutputStream(out);
            zout.putNextEntry(new ZipEntry("0"));
            zout.write(str.getBytes());
            zout.closeEntry();
            compressed = out.toByteArray();
            compressedStr = Base64.getEncoder().encodeToString(compressed);
        } catch (IOException e) {
            compressed = null;
        } finally {
            if (zout != null) {
                try {
                    zout.close();
                } catch (IOException e) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }
        return compressedStr;
    }

    /**
     * 使用zip进行解压缩
     *            压缩后的文本
     * @return 解压后的字符串
     */
    @SuppressWarnings("restriction")
    public static final String unzip(String compressedStr) {
        if (compressedStr == null) {
            return null;
        }
        ByteArrayOutputStream out = null;
        ByteArrayInputStream in = null;
        ZipInputStream zin = null;
        String decompressed = null;
        try {
            byte[] compressed = Base64.getDecoder().decode(compressedStr);
            out = new ByteArrayOutputStream();
            in = new ByteArrayInputStream(compressed);
            zin = new ZipInputStream(in);
            zin.getNextEntry();
            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = zin.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            decompressed = out.toString();
        } catch (IOException e) {
            decompressed = null;
        } finally {
            if (zin != null) {
                try {
                    zin.close();
                } catch (IOException e) {
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }
        return decompressed;
    }

    /**
     * 功能：使用gzip进行压缩
     * @return 返回压缩后字符串
     * @author  20160827
     */
    @SuppressWarnings("restriction")
    public static byte[] guzip(String primStr) {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        byte[] b = primStr.getBytes();
        try (GZIPOutputStream gout = new GZIPOutputStream(bout)) {
            //我这里用字节流输出的，所以转正byte[]
            //缓存数据用的字节数组流
            //压缩用的是这个流
            gout.write(b);//把b写入到缓冲区中，也就是ByteArrayOutputStream
            gout.close();//关闭流，也就是把数据全都刷到字节数组流中
            b = bout.toByteArray();//这个字节数组流关闭之后还能用，不用担心，从他里面把压缩好的数据拿出来，还是放在byte[]中
            return b;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Description:使用gzip进行解压缩
     * @param compressedStr 压缩字符串
     * @return 返回解压字符串
     */
    @SuppressWarnings("restriction")
    public static String gunzip(byte[] compressedStr) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            ByteArrayInputStream bis = new ByteArrayInputStream(compressedStr);
            GZIPInputStream gis = new GZIPInputStream(bis);
            int len = -1;
            byte[] b1 = new byte[1024];
            while ((len = gis.read(b1)) != -1) {
                bos.write(b1, 0, len);
            }
            bos.close();
            return bos.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

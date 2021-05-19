package com.behere.common.utils;
import java.net.InetAddress;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
* 生成唯一主键工具类
* 
* @author nanbo
* @version V1.0.0-RELEASE 日期：2016-4-20
* @since 1.0.0-RELEASE
*/
public class IDGenerator {
    private static long primarykeyId;

    private final static long twepoch = 1288834974657L;

    private static long sequence = 0L;

    private final static long primarykeyIdBits = 4L;

    public final static long maxWorkerId = -1L ^ -1L << primarykeyIdBits;

    private final static long sequenceBits = 10L;

    private final static long primarykeyIdBitsShift = sequenceBits;

    private final static long timestampLeftShift = sequenceBits + primarykeyIdBits;

    public final static long sequenceMask = -1L ^ -1L << sequenceBits;

    private static long lastTimestamp = -1L;
    
    private final static Logger logger = LoggerFactory.getLogger(IDGenerator.class);
    
    @SuppressWarnings("static-access")
    public IDGenerator(final long primarykeyId) {
        super();
        if (primarykeyId > this.maxWorkerId || primarykeyId < 0) {
            throw new IllegalArgumentException(String.format(
                "worker Id can't be greater than %d or less than 0", this.maxWorkerId));
        }
        this.primarykeyId = primarykeyId;
    }
    
	static {
		if (IDGenerator.primarykeyId == 0) {
			try {
				String address = InetAddress.getLocalHost().getHostAddress();
				String ip = address.substring(address.lastIndexOf(".") + 1);
				IDGenerator.primarykeyId = Long.parseLong(ip.concat(String.valueOf(new Random().nextInt(9))));
			} catch (Exception e) {
				e.printStackTrace();
				IDGenerator.primarykeyId = new Random().nextInt(999);
			}
		}
	}

    /**
     * 获取主键生成
     * @return String
     */
    public static synchronized String getId() {
        // 转换成十六进制
        return Long.toHexString(nextId()).toUpperCase()+String.valueOf(new Random().nextInt(99));
    }
    
    /**
     * 获取主键生成
     * @return long
     */
    public static synchronized long getPKId() {
    	// 转换成十六进制
    	return nextId();
    }

    private static long nextId() {
        long timestamp = timeGen();
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }
        if (timestamp < lastTimestamp) {
            try {
                throw new Exception(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
            } catch (Exception e) {
            	logger.error(e.getMessage());
            }
        }
        lastTimestamp = timestamp;
        long nextId = ((timestamp - twepoch << timestampLeftShift)) | (primarykeyId << primarykeyIdBitsShift) | (sequence);
        return nextId;
    }

    private static long tilNextMillis(final long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private static long timeGen() {
        return System.currentTimeMillis();
    }
}
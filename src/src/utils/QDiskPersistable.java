package utils;
/**
 * @Author Unsal Ozturk
 * @Version 20190501
 * Interface for I/O
 */
public interface QDiskPersistable {
    public void encode();
    public Object decode();
    public boolean delete();
}
